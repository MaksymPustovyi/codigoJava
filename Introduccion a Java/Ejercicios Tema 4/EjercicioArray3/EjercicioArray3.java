package EjercicioArray3;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Clase principal para la ejecución de la prueba de filtrado de caracteres.
 */
public class EjercicioArray3 {

    /**
     * Punto de entrada del programa.
     * 
     * Coordina el flujo de:
     * 1. Preparación de datos de prueba.
     * 2. Invocación del servicio de filtrado léxico.
     * 
     * @param args Argumentos del sistema (no utilizados).
     */
    public static void main(String[] args) {
        configurarEntornoUTF8();
        inicioDeLaPrograma();
        // Datos de prueba (pueden venir de tu GeneradorEstructuras anterior)
        String[][] misPalabras = GeneradorEstructuras.generarMatrizAleatoria(5, 10);
        char letraObjetivo = 'c';

        System.out.println("\n👁️  Visualizando matriz:");
        imprimirMatrizVisual(misPalabras);

        // Invocación del método solicitado
        mostrarPorLetraInicial(misPalabras, letraObjetivo);

        finDeLaPrograma();
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
    /**
     * Filtra y muestra por consola las cadenas que comienzan con un carácter específico.
     * 
     * Notas de diseño:
     * 1. Sensibilidad: El método diferencia entre mayúsculas y minúsculas (Case-sensitive).
     * 2. Robustez: Valida si el array es nulo o si contiene elementos nulos antes de procesar.
     *
     * @param misPalabras Array de Strings donde se realizará la búsqueda.
     * @param letra El carácter inicial que se desea buscar.
     */
    public static void mostrarPorLetraInicial(String[][] misPalabras, char letra) {
        if (misPalabras == null || misPalabras.length == 0) {
            System.out.println("⚠️ [SISTEMA]: El array está vacío o no existe.");
            return;
        }

        System.out.println("\n🔍 [BUSCADOR]: Filtrando palabras que empiezan por '" + letra + "':");
        boolean encontrado = false;

        for (String[] fila : misPalabras) {
            if (fila != null) {
                for (String palabra : fila) {
                    // Verificación de seguridad para evitar NullPointerException
                    if (palabra != null && !palabra.isEmpty()) {
                        if (palabra.charAt(0) == letra) {
                            System.out.println("   ✅  Coincidencia encontrada: " + palabra);
                            encontrado = true;
                        }
                    }
                }
            }
        }

        if (!encontrado) {
            System.out.println("❌ No se encontraron palabras que comiencen con '" + letra + "'.");
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