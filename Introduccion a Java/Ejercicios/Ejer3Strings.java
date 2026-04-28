import java.util.Scanner;

/**
 * Pagina 185, Introducción a JAVA.pdf
 * Ejercicio 3 Strings
 * Crea un programa en JAVA que lea por teclado una cadena de texto e indique la
 * cantidad de palabras que tiene.
 * De la clase String, únicamente se pueden utilizar los métodos charAt(),
 * trim() y length().
 */

public class Ejer3Strings {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        // Inicialización de contadores y estado del analizador
        int contPalabra = 0, contNumero = 0;
        boolean esToken = false;

        // inicializamos el ejercicio
        System.out.println("\n- INICIO DEL EJERCICIO -\n");

        System.out.println("Introduce una cadena de texto: ");
        String texto = entrada.nextLine();
        texto = texto.trim();

        // esperamos la cadena que contiene una palabra o más
        while (texto.trim().length() == 0) {
            System.out.println("[ERROR] La cadena no contiene palabras.");
            System.out.println("Introduce una cadena de texto: ");
            texto = entrada.nextLine();
        }

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            // Comprobamos si es el inicio de un nuevo token (palabra o número)
            if (Character.isLetterOrDigit(c) && !esToken) {
                esToken = true;

                // Determinamos el tipo de token por su primer carácter
                if (Character.isDigit(c)) {
                    contNumero++;
                } else {
                    contPalabra++;
                }
            }

            // Si el carácter no es alfanumérico, el token actual termina
            else if (!Character.isLetterOrDigit(c)) {
                esToken = false;
            }
        }

        // imprimimos el resultado
        System.out.println("--------------------");
        System.out.println(
                "La cadena [" + texto + "] contiene " + contPalabra + (contPalabra == 1 ? " palabra" : " palabras")
                        + " y " + contNumero + (contNumero == 1 ? " numero." : " numeros."));
        System.out.println("--------------------");

        System.out.println("\n- FIN DEL EJERCICIO -\n");

        entrada.close();
    }
}
