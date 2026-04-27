import java.util.Scanner;

// --- КЛАС: ДЛЯ ПЕРЕВІРОК ---
// CLASE: PARA GESTIONAR TODAS LAS VALIDACIONES DEL SISTEMA
public class Validador {

    // --- МЕТОД: ПЕРЕВІРКА ВВЕДЕННЯ НА ЧИСЛО (БЕЗ ТЕКСТУ) ---
    // METODO: PARA VALIDAR QUE LA ENTRADA SEA NUMÉRICA (SIN TEXTO NI SÍMBOLOS)
    public static boolean esNumerico(Scanner input) {
        if (input.hasNextFloat()) {
            return true;
        } else {
            System.out.println("ERROR: ENTRADA NO VÁLIDA. POR FAVOR, INTRODUCE SOLO NÚMEROS.");
            input.next();
            return false;
        }
    }

    // --- МЕТОД: ПЕРЕВІРКА ЦІНИ НА ДОДАТНЕ ЧИСЛО ---
    // METODO: PARA COMPROBAR QUE EL PRECIO SEA UN NÚMERO POSITIVO
    public static boolean esPrecioValido(float precio) {
        if (precio >= 0) {
            return true;
        } else {
            System.out.println("ERROR: EL PRECIO NO PUEDE SER NEGATIVO.");
            return false;
        }
    }

    // --- МЕТОД: ПЕРЕВІРКА ВІДСОТКА НА ДІАПАЗОН ВІД 0 ДО 100 ---
    // METODO: PARA COMPROBAR QUE EL PORCENTAJE ESTÉ EN EL RANGO DE 0 A 100
    public static boolean esDescuentoValido(float descuento) {
        if (descuento >= 0 && descuento <= 100) {
            return true;
        } else {
            System.out.println("ERROR: EL DESCUENTO DEBE ESTAR ENTRE 0 Y 100.");
            return false;
        }
    }
}