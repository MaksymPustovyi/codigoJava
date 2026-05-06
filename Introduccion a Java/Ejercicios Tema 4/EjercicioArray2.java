import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Clase principal que coordina el flujo y realiza el análisis de datos.
 */
public class EjercicioArray2 {

    /**
     * Punto de entrada principal del programa.
     * 
     * Coordina el flujo de ejecución realizando las siguientes tareas:
     * 1. Configura el entorno de consola para admitir caracteres UTF-8.
     * 2. Genera una estructura de datos (matriz) con dimensiones y contenido aleatorio.
     * 3. Presenta los datos de forma visual en la consola.
     * 4. Realiza un análisis estadístico para identificar las cadenas de mayor longitud.
     */
    public static void main(String[] args) {
        configurarEntornoUTF8();
        inicioDeLaPrograma();

        // Obtención de datos
        String[][] palabras = GeneradorEstructuras.generarMatrizAleatoria(3, 5);

        System.out.println("\n👁️  Visualizando matriz:");
        imprimirMatrizVisual(palabras);

        // Análisis dentro del mismo clase
        mostrarStringsMasLargas(palabras);

        finDeLaPrograma();
    }

    /**
     * Analiza la matriz para encontrar las palabras más largas.
     * Versión simplificada usando lógica de ciclos básicos.
     */
    public static void mostrarStringsMasLargas(String[][] matriz) {
        if (matriz == null)
            return;

        int maxLen = 0;
        List<String> resultados = new ArrayList<>();

        // 1. Primer paso: Encontrar cuál es la longitud máxima
        for (String[] fila : matriz) {
            if (fila == null)
                continue;
            for (String palabra : fila) {
                if (palabra != null && palabra.length() > maxLen) {
                    maxLen = palabra.length();
                }
            }
        }

        // 2. Segundo paso: Recopilar todas las palabras que tengan esa longitud
        for (String[] fila : matriz) {
            if (fila == null)
                continue;
            for (String palabra : fila) {
                if (palabra != null && palabra.length() == maxLen) {
                    // Evitamos duplicados antes de añadir
                    if (!resultados.contains(palabra)) {
                        resultados.add(palabra);
                    }
                }
            }
        }

        // 3. Mostrar resultados
        System.out.println("\n--- RESULTADO DEL ANÁLISIS ---");
        System.out.println("📏 Longitud máxima: " + maxLen);
        System.out.println("🔍 Palabras encontradas (" + resultados.size() + "): " + resultados);
    }

    public static void imprimirMatrizVisual(String[][] matriz) {
        if (matriz == null)
            return;
        for (String[] fila : matriz) {
            System.out.print("| ");
            for (String celda : fila) {
                System.out.printf("%-10s | ", celda);
            }
            System.out.println();
        }
    }

    static void configurarEntornoUTF8() {
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "chcp 65001").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
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