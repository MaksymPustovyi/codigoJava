import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Clase EjercicioArray1: Proporciona una interfaz de consola interactiva para
 * el análisis de datos mediante generación aleatoria y enmascaramiento visual.
 */
public class EjercicioArray1 {

    public static void main(String[] args) {
        configurarEntornoUTF8();
        Scanner entrada = new Scanner(System.in);
        
        inicioDeLaPrograma();

        // Paso 1: Configuración de la estructura de datos
        System.out.print("¿De qué tamaño deseas el array?: ");
        int tamano = leerEnteroPositivo(entrada);

        // Paso 2: Generación y visualización inmediata del contenido
        int[] datos = new int[tamano];
        llenarConNumerosAleatorios(datos, 1, 9);
        
        System.out.println("\n✅ [SISTEMA]: Array generado exitosamente.");
        System.out.println("Contenido actual: " + Arrays.toString(datos)); // Вивід до запиту числа

        // Paso 3: Interacción con el usuario basada en los datos visibles
        System.out.print("\nDe los números mostrados, ¿cuál deseas buscar (1-9)?: ");
        if (entrada.hasNextInt()) {
            int valorBuscado = entrada.nextInt();
            
            // Paso 4: Presentación de resultados comparativos
            System.out.println("\n" + "=".repeat(40));
            mostrarFrecuencia(datos, valorBuscado);
            System.out.println("Original:      " + Arrays.toString(datos));
            mostrarFilaMascarada(datos, valorBuscado);
            System.out.println("=".repeat(40));
        } else {
            System.out.println("❌ [ERROR]: Entrada no válida. Se esperaba un número entero.");
        }

        finDeLaPrograma();
        entrada.close();
    }

    /**
     * Puebla el array con enteros aleatorios para el análisis.
     */
    static void llenarConNumerosAleatorios(int[] array, int min, int max) {
        Random r = new Random();
        for (int i = 0; i < array.length; i++) array[i] = r.nextInt(max - min + 1) + min;
    }

    /**
     * Calcula la frecuencia de un elemento específico.
     */
    static void mostrarFrecuencia(int[] array, int valor) {
        int contador = 0;
        for (int n : array) if (n == valor) contador++;
        System.out.println("[ESTADÍSTICA]: El número " + valor + " aparece " + contador + " veces.");
    }

    /**
     * Genera una representación visual comparativa enmascarando los valores no deseados.
     */
    static void mostrarFilaMascarada(int[] array, int valor) {
        StringBuilder sb = new StringBuilder("Visualización: [");
        for (int i = 0; i < array.length; i++) {
            if (array[i] == valor) {
                sb.append(array[i]);
            } else {
                String numeroStr = String.valueOf(array[i]);
                for (int j = 0; j < numeroStr.length(); j++) sb.append("_");
            }
            if (i < array.length - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    static int leerEnteroPositivo(Scanner sc) {
        while (true) {
            if (sc.hasNextInt()) {
                int n = sc.nextInt();
                if (n > 0) return n;
            }
            System.out.println("❌ [ERROR]: Introduzca un número positivo.");
            if (sc.hasNext()) sc.next(); 
            System.out.print("> ");
        }
    }

    static void configurarEntornoUTF8() {
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "chcp 65001").inheritIO().start().waitFor();
            }
        } catch (Exception e) {}
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