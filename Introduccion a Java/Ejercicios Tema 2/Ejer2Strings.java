import java.util.Scanner;

/**
 * Pagina 185, Introducción a JAVA.pdf
 * Ejercicio 2 Strings
 * Crea un programa en JAVA que reciba 5 palabras y luego nos muestre la que
 * tiene una longitud mayor.
 */

public class Ejer2Strings {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        String palabra = "", palabraMax = "";

        // inicializamos el ejercicio
        System.out.println("\n- INICIO DEL EJERCICIO -\n");

        for (int i = 1; i <= 5; i++) {
            System.out.print("Introduce "
                    + (i == 1 ? "primera" : i == 2 ? "segunda" : i == 3 ? "tercera" : i == 4 ? "cuarta" : "quinta")
                    + " palabra: ");
            palabra = entrada.nextLine(); // leemos una palabra del usuario

            // si la palabra actual es estrictamente más larga que la guardada, actualiza el
            // valor.
            if (palabra.length() > palabraMax.length())
                palabraMax = palabra;

        }
        // imprimimos el resultado
        System.out.println("--------------------");
        System.out.println("La primera palabra más larga es: [" + palabraMax + "]");
        System.out.println("--------------------");

        System.out.println("\n- FIN DEL EJERCICIO -\n");

        entrada.close();
    }
}
