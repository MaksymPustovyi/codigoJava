import java.util.Scanner;

/**
 * Pagina 186, Introducción a JAVA.pdf
 * Ejercicio 1 Figuras
 * Crear un programa en JAVA que dibuje un rectángulo formado por asteriscos(*).
 * Se deben pedir base y altura por teclado. Además, añade código adicional para
 * que dibuje dicho rectángulo sin relleno
 */

public class Ejer1Figuras {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        // inicializamos el ejercicio
        System.out.println("\n- INICIO DEL EJERCICIO -\n");

        System.out.print("Introduce la base: ");
        int base = entrada.nextInt();
        System.out.print("Introduce la altura: ");
        int altura = entrada.nextInt();

        // Dibujo del rectángulo con relleno
        System.out.println("\n--- Rectangulo relleno ---\n");

        for (int i = 0; i < altura; i++) { // Bucle interno para imprimir cada columna de la fila actual
            for (int j = 0; j < base; j++) {
                System.out.print("* ");
            }
            System.out.println(); // Salto de línea al terminar de dibujar una fila completa
        }

        // Dibujo del rectángulo sin relleno (solo bordes)
        System.out.println("\n--- Rectangulo sin relleno ---\n");
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < base; j++) {

                // Condición para imprimir asteriscos solo en los límites (bordes)
                if (i == 0 || i == altura - 1 || j == 0 || j == base - 1) {
                    System.out.print("* ");
                    
                } else { // Imprimimos espacios en blanco en el interior del rectángulo
                    System.out.print("  ");
                }
            }
            System.out.println(); // Salto de línea para pasar a la siguiente fila
        }

        System.out.println("\n- FIN DEL EJERCICIO -\n");

        entrada.close();
    }
}
