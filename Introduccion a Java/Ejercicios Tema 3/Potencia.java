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

        inicioDeLaPrograma();

        // Implementación de UX para guiar la entrada de datos
        System.out.print("Introduce el base: ");
        int base = leerNumero();

        System.out.print("Introduce el exponente: ");
        int exp = leerNumero();

        // Invocación del motor de cálculo recursivo
        BigInteger resultado = potencia(base, exp);

        mostrarResultados(resultado, base, exp);

        finDeLaPrograma();
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
       if (exp == 0) return BigInteger.ONE;

       // Convertimos la base a BigInteger y multiplicamos recursivamente
    return BigInteger.valueOf(base).multiply(potencia(base, exp - 1));
    }

    /**
     * Capa de presentación: Formatea y despliega los resultados analizados.
     * Se mantiene el tipado double para la consistencia de los datos.
     */
    static void mostrarResultados(BigInteger resultado, int base, int exp) {
        // BigInteger.toString() mostrará el número completo sin notación científica ni decimales
        System.out.println("\nEl numero " + base + " elevado a " + exp + " es igual a " + resultado.toString() + " (tiene " + resultado.toString().length() + " cifra" + (resultado.toString().length()==1 ? ")" : "s)"));
    }

    /**
     * Gestiona la entrada de datos desde el flujo estándar (System.in).
     * Implementa un sistema de control de excepciones para garantizar 
     * la robustez frente a entradas no numéricas.
     * 
     * @return numero Un entero validado por el sistema.
     */
    static int leerNumero() {
        Scanner entrada = new Scanner(System.in);
        int numero = 0; // Inicialización defensiva
        boolean esValido = false;

        do {
            try {
                numero = entrada.nextInt();
                esValido = true;
                // Si la lectura es exitosa, salimos del bucle
            } catch (Exception e) {
                System.out.println("Error: Entrada no valida. Por favor, introduce un numero entero.");
                entrada.next(); // Limpiar el buffer para evitar un bucle infinito
            }
        } while (!esValido);

        // Importante: El cierre del recurso Scanner debe manejarse con precaución
        // si existen procesos de lectura posteriores en el hilo principal.
        return numero;
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
