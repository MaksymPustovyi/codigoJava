import java.math.BigInteger;
import java.util.Scanner;

/**
 * Clase Potencia: Implementa el cálculo de potencias mediante recursividad.
 * Se aplica una arquitectura funcional para separar la captura de datos,
 * el procesamiento matemático y la salida por consola.
 */
public class Potencia {

    /**
     * Punto de entrada principal (Execution Workflow).
     */
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        inicioDeLaPrograma();

        // Implementación de UX para guiar la entrada de datos
        System.out.print("Introduce el base: ");
        int base = leerNumero(entrada, false); // Validación robusta de entrada para la base

        System.out.print("Introduce el exponente: ");
        int exp = leerNumero(entrada, true); // Validación específica para el exponente

        // Invocación del motor de cálculo recursivo
        BigInteger resultado = potencia(base, exp);

        // Presentación de resultados con formato enriquecido
        mostrarResultados(resultado, base, exp);

        finDeLaPrograma();
        entrada.close(); // Cierre del recurso Scanner al finalizar el proceso
    }

    /**
     * Algoritmo recursivo para el cálculo de potencia (base^exp).
     * 
     * NOTA DE ARQUITECTURA: Se cambia el retorno a 'BigInteger' para evitar el
     * desbordamiento (overflow) de enteros y permitir una mayor precisión
     * en resultados de gran magnitud.
     * 
     * @param base El valor a multiplicar.
     * @param exp  El número de veces que se multiplica la base.
     * @return El resultado del cálculo matemático.
     */
    static BigInteger potencia(int base, int exp) {
        // Caso base: Cualquier número elevado a 0 es igual a 1.
        if (exp == 0)
            return BigInteger.ONE;

        // Convertimos la base a BigInteger y multiplicamos recursivamente
        return BigInteger.valueOf(base).multiply(potencia(base, exp - 1));
    }

    /**
     * Capa de presentación: Formatea y despliega los resultados analizados.
     * Se mantiene el tipado double para la consistencia de los datos.
     */
    static void mostrarResultados(BigInteger resultado, int base, int exp) {
        // BigInteger.toString() mostrará el número completo sin notación científica ni
        // decimales
        System.out.println(
                "\n[RESULTADO] El numero " + base + " elevado a " + exp + " es igual a " + resultado.toString() + " (tiene "
                        + resultado.toString().length() + " cifra" + (resultado.toString().length() == 1 ? ")" : "s)"));
    }

    /**
     * Gestiona la entrada de datos desde la consola con validación estricta.
     * Implementa un bucle infinito hasta que el usuario proporcione un valor
     * íntegro y lógico.
     * 
     * @param entrada     El objeto Scanner para capturar la entrada del usuario.
     * @param esExponente Booleano que activa la regla de negocio: el exponente no
     *                    puede ser negativo.
     * @return Un número entero validado y seguro para el flujo del programa.
     */
    static int leerNumero(Scanner entrada, boolean esExponente) {
        int numero;

        // Bucle de control hasta obtener una entrada válida
        while (true) {
            try {
                // Verifica si la entrada actual es un número entero
                if (entrada.hasNextInt()) {
                    numero = entrada.nextInt();

                    // Validación de regla de negocio para potencias
                    if (esExponente && numero < 0) {
                        System.out.println("[ERROR] El exponente no puede ser negativo.");
                        System.out.print("[ERROR] Introduce un exponente valido (>= 0): ");
                        continue; // Reinicia el bucle para solicitar un nuevo dato
                    }

                    return numero; // Retorna el valor solo si cumple todas las validaciones
                } else {
                    // Gestión de errores para entradas no numéricas (letras, símbolos)
                    System.out.println("[ERROR] Entrada no valida.");
                    entrada.next(); // Limpieza del buffer para evitar bucles infinitos
                }
            } catch (Exception e) {
                // Captura de excepciones inesperadas durante la lectura
                System.out.println("[ERROR] en la lectura.");
                entrada.next(); // Asegura la limpieza del flujo de entrada
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
