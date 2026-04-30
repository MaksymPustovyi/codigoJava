import java.util.Scanner;

/**
 * Clase ConversorSistemas: Proporciona utilidades para la transformación entre
 * bases numéricas.
 * Se aplica un enfoque de eficiencia algorítmica para minimizar el uso de
 * recursos en memoria.
 */
public class ConversosBinario {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        inicioDeLaPrograma();

        System.out.print("\nIntroduce un numero ENTERO para convertir a binario: ");

        // Reutilizamos la lógica de validación robusta desarrollada previamente
        String input = leerYNormalizarEntero(entrada);
        long numero = Long.parseLong(input);

        // Invocación del motor de conversión
        String resultadoBinario = obtenerBinario(numero);

        System.out.println("\n[RESULTADO]: El numero " + numero + " en binario es: " + resultadoBinario);

        finDeLaPrograma();
        entrada.close();
    }

    /**
     * Transforma un número entero decimal en su representación binaria (Base 2).
     * Utiliza StringBuilder para una concatenación eficiente, evitando la creación
     * excesiva de objetos String en el Heap.
     * 
     * @param numero El número decimal de tipo long para soportar rangos extensos.
     * @return Una cadena de texto (String) con el equivalente binario del
     *         parámetro.
     */
    static String obtenerBinario(long numero) {
        if (numero == 0)
            return "0";

        StringBuilder binario = new StringBuilder();
        boolean esNegativo = numero < 0;
        long valor = Math.abs(numero);

        // Algoritmo de divisiones sucesivas por 2
        while (valor > 0) {
            long residuo = valor % 2;
            binario.insert(0, residuo); // Inserta el bit al inicio de la cadena
            valor = valor / 2;
        }

        return esNegativo ? "-" + binario.toString() : binario.toString();
    }

    /**
 * Gestiona la captura de números, permitiendo la entrada de decimales pero 
 * procesando únicamente la parte entera para la conversión binaria.
 * 
 * @param entrada Objeto Scanner inyectado.
 * @return La parte entera del número ingresado como String.
 */
static String leerYNormalizarEntero(Scanner entrada) {
    while (true) {
        String input = entrada.next();
        
        // Reemplazamos coma por punto para estandarizar el formato
        String normalizado = input.replace(",", ".");

        // Intentamos validar si es un número (entero o decimal)
        if (normalizado.matches("^-?[0-9]+(\\.[0-9]+)?$")) {
            // Si contiene un punto, extraemos solo lo que está antes del punto
            if (normalizado.contains(".")) {
                return normalizado.split("\\.")[0];
            }
            return normalizado;
        }

        System.out.println("\n[ERROR]: Entrada no valida. Por favor, introduce un numero.");
        System.out.print("\nIntroduce un numero: ");
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
