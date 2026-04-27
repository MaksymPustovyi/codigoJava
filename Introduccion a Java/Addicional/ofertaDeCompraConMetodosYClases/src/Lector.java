import java.util.Scanner;

// --- КЛАС: ЧИТАННЯ ТА ОБРОБКА ВВЕДЕННЯ ---
// CLASE: PARA GESTIONAR LA ENTRADA DE DATOS DEL USUARIO
public class Lector {

    private static final Scanner input = new Scanner(System.in);

    // --- МЕТОД: ОТРИМАННЯ ТА ВАЛІДАЦІЯ ЦІНИ ---
    // METODO: PARA OBTENER Y VALIDAR EL PRECIO
    public static float solicitarPrecio() {
        float precio;
        while (true) {
            System.out.print("INTRODUCE EL PRECIO: ");

            if (Validador.esNumerico(input)) {
                float valor = input.nextFloat();
                if (Validador.esPrecioValido(valor)) {
                    precio = valor;
                    break;
                }
            }
        }
        return precio;
    }

    // --- МЕТОД: ОТРИМАННЯ ТА ВАЛІДАЦІЯ ВІДСОТКА ---
    // METODO: PARA OBTENER Y VALIDAR EL PORCENTAJE
    public static float solicitarDescuento() {
        float descuento;
        while (true) {
            System.out.print("INTRODUCE EL PORCENTAJE DE DESCUENTO (0-100): ");

            if (Validador.esNumerico(input)) {
                float valor = input.nextFloat();
                if (Validador.esDescuentoValido(valor)) {
                    descuento = valor;
                    break;
                }
            }
        }
        return descuento;
    }
}