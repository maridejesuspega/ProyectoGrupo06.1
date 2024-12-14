/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoGrupo6.util;

/**
 *
 * @author Armando Elizondo M
 */
public class ValidationUtil {

    public static boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("[0-9]{8}");
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}
