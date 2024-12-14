/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoGrupo6.util;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Armando Elizondo M
 */
public class FileUtil {
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    
    public static File convertMultiPartToFile(MultipartFile file) throws IOException {
        validateFile(file);
        File convertedFile = File.createTempFile("img", getFileExtension(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        }
        return convertedFile;
    }
    
    public static void validateFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IOException("El archivo está vacío");
        }
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IOException("El archivo excede el tamaño máximo permitido de 5MB");
        }
        if (!isValidImageFile(file)) {
            throw new IOException("El archivo debe ser una imagen válida");
        }
    }
    
    public static String getFileExtension(String fileName) {
        return fileName != null && fileName.contains(".") ? 
               fileName.substring(fileName.lastIndexOf(".")) : "";
    }
    
    public static String generateFileName(Long id, String extension) {
        return String.format("img%019d%s", id, extension);
    }
    
    public static boolean isValidImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }
}
