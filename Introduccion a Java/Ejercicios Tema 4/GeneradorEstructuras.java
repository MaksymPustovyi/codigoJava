import java.util.Random;

/**
 * Clase responsable de la creación de estructuras de datos (matrices).
 */
public class GeneradorEstructuras {
    private static final Random RANDOM = new Random();

    /**
     * Crea una matriz bidimensional con dimensiones aleatorias.
     */
    public static String[][] generarMatrizAleatoria(int minDim, int maxDim) {
        int filas = RANDOM.nextInt((maxDim - minDim) + 1) + minDim;
        int columnas = RANDOM.nextInt((maxDim - minDim) + 1) + minDim;
        int palabraLongitudMin = 3;
        int palabraLongitudMax = 8;

        System.out.println("⚙️  [SISTEMA]: Creando matriz de 🎲  tamaño (" + filas + "x" + columnas + ")");
        System.out.println("⚙️  [SISTEMA]: Creando palabras de 🎲  longitud (" + palabraLongitudMin + " a " + palabraLongitudMax + " caracteres)");

        String[][] matriz = new String[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                // Delegamos la creación de la palabra al GeneradorLexico
                matriz[i][j] = GeneradorLexico.generarPalabraAleatoria(palabraLongitudMin, palabraLongitudMax);
            }
        }
        return matriz;
    }
}