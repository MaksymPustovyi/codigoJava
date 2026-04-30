import java.util.Scanner;

/**
 * Clase Invertir: Implementa el proceso de inversión de un número entero.
 * Se aplica una arquitectura modular para separar lógica y presentación.
 */
public class Invertir {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in); // Única instancia de Scanner
        inicioDeLaPrograma();

        System.out.print("Introduce un numero entero: ");
        // Pasamos el scanner como dependencia (Dependency Injection)
        long numero = leerNumero(entrada);

        String numeroInvertido = invertirNumeroVisual(numero);

        mostrarResultados(String.valueOf(numero), numeroInvertido);

        finDeLaPrograma();
        entrada.close(); // Cierre del recurso Scanner al finalizar el proceso
    }

    /**
     * Algoritmo visual para invertir un número.
     * Al usar String, preservamos ceros a la izquierda y evitamos desbordamientos.
     */
    static String invertirNumeroVisual(long numero) {
        String original = String.valueOf(Math.abs(numero));
        StringBuilder invertido = new StringBuilder(original).reverse();
        return (numero < 0 ? "-" : "") + invertido.toString();
    }

    /**
     * Gestión robusta de entrada de datos.
     */
    static long leerNumero(Scanner entrada) {
        long numero = 0;
        boolean esValido = false;

        do {
            try {
                // Usamos nextLong para dar más margen de entrada
                numero = entrada.nextLong();
                esValido = true;
            } catch (Exception e) {
                System.out.println("[ERROR] Entrada no valida. Introduce un numero entero.");
                entrada.next(); // Limpieza del buffer
            }
        } while (!esValido);

        return numero;
    }

    /**
     * Capa de presentación: Formatea y despliega los resultados analizados.
     * @param numero
     * @param numeroInvertido
     */
    static void mostrarResultados(String numero, String numeroInvertido) {
        System.out.println("\n[RESULTADO] El numero " + numero + " invertido es " + numeroInvertido);
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