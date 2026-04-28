import java.util.Scanner;

/**
 * Pagina 185, Introducción a JAVA.pdf
 * Ejercicio 1 Strings
 * Crea un programa que te pida una palabra y escriba las letras separadas por
 * espacios.
 * Ejemplo, a partir de "Pepe" escribirá "P e p e ".
 */

public class Ejer1Strings {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        // inicializamos el ejercicio
        System.out.println("\n- INICIO DEL EJERCICIO -\n");

        System.out.print("Introduce una palabra: ");
        String palabra = entrada.next(); // leemos una palabra del usuario

        // imprimimos el resultado
        System.out.println("--------------------");
        System.out.print("Resultado de la conversion: [");

        // сonvertimos el String a un array y concatenamos un espacio a cada carácter y
        // imprimimos resultado en una linea
        for (char letra : palabra.toCharArray()) {
            System.out.print(letra + " ");
        }
        System.out.println("\b]"); // borra el último carácter sobrante (como un espacio o coma) y cierra el
                                   // formato con ']'.
        System.out.println("--------------------");

        System.out.println("\n- FIN DEL EJERCICIO -\n");

        entrada.close();
    }
}
