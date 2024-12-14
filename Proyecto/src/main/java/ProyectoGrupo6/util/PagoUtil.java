/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoGrupo6.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Armando Elizondo M
 */
public class PagoUtil {

    private static final NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CR"));

    public static String formatearMonto(double monto) {
        return formatoMoneda.format(monto);
    }

    public static String ocultarNumeroTarjeta(String numeroTarjeta) {
        if (numeroTarjeta == null || numeroTarjeta.length() < 4) {
            return "****";
        }
        return "**** **** **** " + numeroTarjeta.substring(numeroTarjeta.length() - 4);
    }

}
