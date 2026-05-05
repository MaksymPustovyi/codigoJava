import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Clase SumarNumeros: Implementa el cálculo de sumatorias naturales mediante
 * recursividad y validación de entrada robusta.
 */
public class SumarNumeros {

    public static void main(String[] args) {
        configurarEntornoUTF8();
        Scanner entrada = new Scanner(System.in);
        
        inicioDelPrograma();

        // Ciclo de validación para garantizar un número N > 0
        int n = leerNumeroValido(entrada);
        
        // Ejecución de la lógica recursiva y visualización
        System.out.println("\n[RESULTADO]: (" + generarCadenaSuma(n) + " = " + calcularSumaRecursiva(n) + ")");

        finDelPrograma();
        entrada.close();
    }

    /**
     * Calcula la suma de los números naturales desde 1 hasta N de forma recursiva.
     * 
     * @param n Límite superior.
     * @return Sumatoria acumulada.
     */
    static int calcularSumaRecursiva(int n) {
        if (n == 1) {
            return 1;
        }
        return n + calcularSumaRecursiva(n - 1);
    }

    /**
     * Genera la representación visual de la suma (1+2+3...) de forma recursiva.
     * 
     * @param n Límite superior.
     * @return Cadena formateada.
     */
    static String generarCadenaSuma(int n) {
        if (n == 1) {
            return "1";
        }
        return generarCadenaSuma(n - 1) + "+" + n;
    }

    /**
     * Gestiona la entrada de usuario hasta obtener un N válido.
     */
    static int leerNumeroValido(Scanner entrada) {
        while (true) {
            System.out.print("Introduce un número N (mayor que 0): ");
            if (entrada.hasNextInt()) {
                int n = entrada.nextInt();
                if (n > 0) return n;
                System.out.println("❌ [ERROR]: El número debe ser mayor que cero.\n");
            } else {
                System.out.println("❌ [ERROR]: Entrada no válida. Debe ser un número entero.\n");
                entrada.next(); // Limpiar búfer
            }
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

    static void inicioDelPrograma() {
        System.out.println("\n- INICIO DEL EJERCICIO -\n");
    }

    static void finDelPrograma() {
        System.out.println("\n- FIN DEL EJERCICIO -\n");
    }
}