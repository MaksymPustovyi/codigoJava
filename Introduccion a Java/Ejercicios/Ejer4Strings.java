import java.util.Scanner;

/**
 * Pagina 185, Introducción a JAVA.pdf
 * Ejercicio 3 Strings
 * Crea un programa en JAVA que reciba una palabra por teclado e indique si es un palíndromo.
 * Un palíndromo es una palabra que se lee igual de derecha a izquierda, que de izquierda a derecha. Ejemplo: RECONOCER
 */

public class Ejer4Strings {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        boolean esPalindromo = true;
        String palabraOriginal, palabra;
        int length;

        // inicializamos el ejercicio
        System.out.println("\n- INICIO DEL EJERCICIO -\n");

        System.out.print("Introduce una palabra: ");
        palabraOriginal = entrada.nextLine();
        palabra = palabraOriginal.toLowerCase().trim();
        length = palabra.length();
       
        // Comparamos los caracteres desde los extremos hacia el centro
        for (int i = 0; i < length / 2; i++) {
            // El carácter en la posición 'i' debe ser igual al de la posición opuesta
            if (palabra.charAt(i) != palabra.charAt(length - 1 - i)) {
                esPalindromo = false;
                break; // Si uno no coincide, ya no es palíndromo
            }
        }

        // imprimimos el resultado
        System.out.println("--------------------");
        System.out.println("La palabra [" + palabraOriginal + "] " + (esPalindromo ? "ES un polindromo." : "NO es polindromo."));
        System.out.println("--------------------");

        System.out.println("\n- FIN DEL EJERCICIO -\n");

        entrada.close();
    }
}
