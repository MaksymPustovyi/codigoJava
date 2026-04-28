import java.util.Scanner;

/**
 * Pagina 186, Introducción a JAVA.pdf
 * Ejercicio 2 Figuras
 * Crea un programa en JAVA que dibuje un triángulo equilátero formado por
 * asteriscos(*). Se debe pedir la altura del triángulo por teclado. Además,
 * añade código adicional para que dibuje dicho rectángulo sin relleno
 */

public class Ejer3Figuras {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        // inicializamos el ejercicio
        System.out.println("\n- INICIO DEL EJERCICIO -\n");

        System.out.print("Introduce la altura: ");
        int altura = entrada.nextInt();

        // 1. Triángulo con relleno
        System.out.println("\n--- Triángulo relleno ---");
        for (int i = 0; i < altura; i++) {
            // Imprimir espacios para centrar el triángulo
            for (int j = 0; j < altura - i - 1; j++) {
                System.out.print(" ");
            }
            // Imprimir asteriscos
            for (int j = 0; j < (2 * i + 1); j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // 2. Triángulo sin relleno
        System.out.println("\n--- Triángulo sin relleno ---");
        for (int i = 0; i < altura; i++) {
            // Espacios iniciales
            for (int j = 0; j < altura - i - 1; j++) {
                System.out.print(" ");
            }
            // Bordes del triángulo
            for (int j = 0; j < (2 * i + 1); j++) {
                // Dibujamos solo si es el primer asterisco, el último o la base
                if (j == 0 || j == (2 * i) || i == altura - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        System.out.println("\n- FIN DEL EJERCICIO -\n");

        entrada.close();
    }
}
