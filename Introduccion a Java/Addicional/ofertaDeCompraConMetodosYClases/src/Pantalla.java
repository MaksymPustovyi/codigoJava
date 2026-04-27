// --- КЛАС: РОБОТА З ІНТЕРФЕЙСОМ ---
// CLASE: PARA LA INTERFAZ DE USUARIO
public class Pantalla {

    // --- МЕТОД: ПОКАЗУ РЕЗУЛЬТАТІВ ---
    // METODO: PARA MOSTRAR LOS RESULTADOS DE FORMA FORMATEADA
    public static void mostrarTicket(float precio, float ahorro, float precioFinal) {
        System.out.println("\n--- RESULTADO DEL CALCULO ---");
        System.out.println("PRECIO ORIGINAL:     " + precio + " euro");
        System.out.println("DESCUENTO APLICADO:  " + ahorro + " euro");
        System.out.println("PRECIO FINAL PAGADO: " + precioFinal + " euro");
        System.out.println("-----------------------------\n");
    }

    // --- МЕТОД: ОЧИЩЕННЯ КОНСОЛІ ---
    // METODO: PARA LIMPIAR LA CONSOLA IMPRIMIENDO LÍNEAS EN BLANCO
    public static void clearScreen() {
        // --- ПИШЕМО 50 ПУСТИХ СТРОК ДЛЯ ОЧИЩЕННЯ ---
        // IMPRIMIMOS 50 LÍNEAS VACÍAS PARA DESPLAZAR EL TEXTO ANTERIOR
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}