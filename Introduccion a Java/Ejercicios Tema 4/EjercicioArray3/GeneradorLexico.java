package EjercicioArray3;
import java.util.Random;

/**
 * Clase especializada en la creación de contenido textual aleatorio.
 * Proporciona métodos para generar palabras basadas en un alfabeto predefinido.
 */
public class GeneradorLexico {
    private static final String ALFABETO = "abcdefghijklmnopqrstuvwxyz";
    private static final Random RANDOM = new Random();

    /**
     * Genera una palabra aleatoria con una longitud dinámica.
     * 
     * @param min Longitud mínima permitida.
     * @param max Longitud máxima permitida.
     * @return Una cadena de texto generada aleatoriamente.
     */
    public static String generarPalabraAleatoria(int min, int max) {
        int longitud = RANDOM.nextInt((max - min) + 1) + min;

        StringBuilder sb = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            int indice = RANDOM.nextInt(ALFABETO.length());
            sb.append(ALFABETO.charAt(indice));
        }
        return sb.toString();
    }
}