import java.util.Scanner;

/**
 * Clase ValidadorBinario: Proporciona lógica para verificar la estructura de
 * sistemas numéricos.
 * Se enfoca en la validación de tipos de datos según reglas de negocio
 * específicas.
 */
public class ComprobarBinario {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        inicioDeLaPrograma();

        // Implementación de UX para guiar la entrada de datos
        System.out.print("Introduce un numero para verificar si es binario: ");
        String numeroBinario = leerSoloNumeros(entrada); // Validación robusta de entrada para cadenas

        // Lógica de evaluación arquitectónica
        if (esBinario(numeroBinario)) {
            System.out.println("\n[RESULTADO] El numero " + numeroBinario + " ES binario.");
        } else {
            System.out.println("\n[RESULTADO] El numero " + numeroBinario + " NO es binario.");
        }

        finDeLaPrograma();
        entrada.close(); // Cierre del recurso Scanner al finalizar el proceso
    }

    /**
     * Valida si la cadena proporcionada cumple con los requisitos del sistema
     * binario.
     * Recorre la cadena caracter por caracter para asegurar la integridad de los
     * datos.
     * 
     * @param entrada Cadena de texto validada previamente como numérica.
     * @return true si solo contiene '0' o '1', false en caso de contener otros
     *         dígitos (2-9).
     */
    static boolean esBinario(String entrada) {
        if (entrada == null || entrada.isEmpty())
            return false;

        for (int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i);
            if (c != '0' && c != '1') {
                return false; // Violación de la regla de sistema binario
            }
        }
        return true;
    }

    /**
     * Gestiona la captura de datos permitiendo números enteros (positivos y
     * negativos).
     * El regex "^-?[0-9]+$" permite un signo negativo opcional seguido de dígitos.
     * 
     * @param entrada Objeto Scanner para la captura de datos.
     * @return Una cadena de texto que representa un número entero válido.
     */
    static String leerSoloNumeros(Scanner entrada) {
        String input;
        while (true) {
            input = entrada.next();

            // El signo "?" indica que el guion (-) es opcional (0 o 1 vez)
            if (input.matches("^-?[0-9]+$")) {
                return input;
            } else {
                System.out.println("\n[ERROR] Entrada no valida. Introduce un numero entero (ej: 10, -5).");
                System.out.print("\nPor favor, introduce un numero valido: ");
            }
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
}
