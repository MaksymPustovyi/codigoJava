import java.util.Scanner;

/**
 * Pagina 184, Introducción a JAVA.pdf
 * Ejercicio 2
 * Dado un número, determinar cuántos dígitos tiene.
 * No se pueden utilizar ni variables ni funciones de tipo String.
 */

public class Ejer2Iniciacion {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        int numN, numOriginal, digitosN = 0;

        // inicializamos el ejercicio
        System.out.println("\n- INICIO DEL EJERCICIO -\n");

        // leemos un numero
        System.out.print("Introduce un numero para analizar cuanto digitos tiene: ");
        numN = entrada.nextInt();
        System.out.println("--------------------");

        // guardamos numero original
        numOriginal = numN;

        // Contamos dígitos
        if (numN != 0) {
            while (numN != 0) {
                ++digitosN;
                numN = (int) numN / 10;
            }
        } else
            digitosN = 1; // Caso especial para el cero

        // imprimimos los resultados
        System.out.println("\n Numero [" + numOriginal + "] tiene (" + digitosN + ") digitos.");

        System.out.println("\n- FIN DEL EJERCICIO -\n");

        entrada.close();
    }
}
