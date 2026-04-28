import java.util.Scanner;

/**
 * Pagina 186, Introducción a JAVA.pdf
 * Ejercicio 2 Figuras
 * Crea un programa en JAVA que dibuje un triángulo rectángulo formado por
 * asteriscos(*). Se debe pedir la altura del triángulo por teclado. Además,
 * añade código adicional para que dibuje dicho triángulo sin relleno
 */

public class Ejer2Figuras {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        // inicializamos el ejercicio
        System.out.println("\n- INICIO DEL EJERCICIO -\n");

        System.out.print("Introduce la altura: ");
        int altura = entrada.nextInt();

        // 1. Triángulo con relleno
        System.out.println("\n--- Triangulo relleno ---");
        for (int i = 1; i <= altura; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }

        // 2. Triángulo sin relleno
        System.out.println("\n--- Triangulo sin relleno ---");
        for (int i = 1; i <= altura; i++) {
            for (int j = 1; j <= i; j++) {
                // Condición: bordes del triángulo (primera columna, última fila e hipotenusa)
                if (j == 1 || j == i || i == altura) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        System.out.println("\n- FIN DEL EJERCICIO -\n");

        entrada.close();
    }
}
