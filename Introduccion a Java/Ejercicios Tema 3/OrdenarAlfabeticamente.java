import java.util.Scanner;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.text.Collator;
import java.util.Locale;

/**
 * Clase OrdenarAlfabeticamente: Implementa una política de ordenamiento
 * alfabético estricto.
 * No se permite la manipulación de datos; se exige una entrada íntegra
 * basada exclusivamente en el alfabeto español.
 */
public class OrdenarAlfabeticamente {

    public static void main(String[] args) {
        configurarEntornoUTF8();
        Scanner entrada = new Scanner(System.in);
        inicioDeLaPrograma();

        System.out.print("Introduce una palabra (solo letras del alfabeto español): ");
        String palabra = leerSoloLetrasEspanolas(entrada);

        if (estaOrdenada(palabra)) {
            System.out.println("\n[RESULTADO]: ✅ La palabra \"" + palabra + "\" está ordenada correctamente.");
        } else {
            System.out.println("\n[RESULTADO]: ❌ La palabra \"" + palabra + "\" NO está ordenada.");
        }

        finDeLaPrograma();
        entrada.close();
    }

    /**
     * Valida el orden alfabético utilizando las reglas de la RAE.
     * Al recibir datos ya validados, se enfoca únicamente en la lógica de
     * comparación.
     */
    static boolean estaOrdenada(String palabra) {
        Collator collator = Collator.getInstance(Locale.of("es", "ES"));
        collator.setStrength(Collator.PRIMARY); // a == á == A

        for (int i = 0; i < palabra.length() - 1; i++) {
            String actual = String.valueOf(palabra.charAt(i));
            String siguiente = String.valueOf(palabra.charAt(i + 1));

            if (collator.compare(actual, siguiente) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Bloquea la entrada hasta que el usuario proporcione una cadena que contenga
     * única y exclusivamente letras del alfabeto español.
     * 
     * @param entrada Scanner para lectura.
     * @return Palabra validada.
     */
    static String leerSoloLetrasEspanolas(Scanner entrada) {
        // Regex: solo letras minúsculas, mayúsculas, eñes y vocales con tilde
        String regexEspanol = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$";

        while (true) {
            System.out.print("> ");
            String input = entrada.next();

            if (input.matches(regexEspanol)) {
                return input;
            }

            System.out.println("\n[ERROR]: Entrada no válida.");
            System.out.println("No se permiten números, espacios ni caracteres especiales.");
            System.out.println("Por favor, usa solo letras del alfabeto español.\n");
        }
    }

   /**
     * Delimitador visual de inicio de proceso.
     */
    static void inicioDeLaPrograma() {
        System.out.println("\n- INICIO DEL EJERCICIO -\n");
    }

    /**
     * Delimitador visual de finalización de proceso.
     */
    static void finDeLaPrograma() {
        System.out.println("\n- FIN DEL EJERCICIO -\n");
    }

    /**
     * Configura el entorno de ejecución para soportar caracteres especiales.
     * Combina la redirección del flujo de salida de Java con el cambio de
     * página de códigos de la consola de Windows (CHCP 65001).
     */
    static void configurarEntornoUTF8() {
        try {
            // Configura la salida de Java para codificar en UTF-8
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

            // Ejecuta el comando de sistema para cambiar la consola a UTF-8
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "chcp 65001").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // Silenciamos la excepción para que el programa continúe si el entorno no es
            // Windows
        }
    }
}