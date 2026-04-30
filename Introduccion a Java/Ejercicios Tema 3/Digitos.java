import java.util.Scanner;

/**
 * Clase Digitos: Implementa una solución para el análisis de magnitudes numéricas.
 * Se enfoca en la descomposición recursiva para determinar la longitud de un entero.
 */
public class Digitos {

    /**
     * Orquestador principal del flujo de ejecución (Workflow).
     */
    public static void main(String[] args) {

        inicioDeLaPrograma();
        int numero = leerNumero();
        int resultado = calcularDigitos(numero);
        mostrarResultados(resultado, numero);
        finDeLaPrograma();

    }

    /**
     * Gestiona la entrada de datos desde el flujo estándar (System.in).
     * Implementa una validación mediante un bucle do-while para asegurar la integridad de los datos.
     * 
     * @return numero Un entero positivo validado.
     */
    static int leerNumero() {
        Scanner entrada = new Scanner(System.in);
        int numero=0;
        boolean esValido = false;
        do {
            try {
            System.out.print("Introduce numero mayor que cero para calcular los digitos que tiene: ");
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
        entrada.close();
        return numero;
    }

    /**
     * Algoritmo recursivo para el cálculo de la cardinalidad de dígitos.
     * Aplica el principio de "divide y vencerás" mediante división entera sucesiva.
     * 
     * @param numero Valor de entrada para el análisis.
     * @return El conteo total de dígitos (Caso base: numero < 10).
     */
    static int calcularDigitos(int numero) {
        int n = Math.abs(numero);
        if (n < 10)
            return 1;
        else
            return calcularDigitos(n / 10) + 1;
    }

    /**
     * Capa de presentación: Formatea y despliega los resultados analizados.
     * Incluye lógica ternaria para la correcta concordancia gramatical (singular/plural).
     */
    static void mostrarResultados(int resultado, int numero) {
        System.out.println("\nEl numero " + numero + " tiene " + resultado + " digito" + (resultado == 1 ? "" : "s"));
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
