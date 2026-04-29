import java.util.Scanner;

public class Menu {
    // ENTRADA DE LA PROGRAMA "MAIN"
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcion; // declaramos variable "opcion" para usar en do-while

        do {
            mostrarMenu(); // mostracion del menu

            // esperamos la entrada del usuario
            System.out.print("Elige una de las opciones: ");
            opcion = entrada.nextInt();

            // si la opcion 1-4 >>> leemos entrada del usuario para tener dimencion de la figura
            if (opcion > 0 && opcion < 5) {
                System.out.print((opcion == 1 || opcion == 2 ? "Indica el tamano del lado: "
                                : opcion == 3 || opcion == 4 ? "Indico la altura del triangulo: "
                                : ""));
                dimension = entrada.nextInt();
            }

            switch (opcion) {
                case 1: // inicializamos metodo cuadradoRelleno
                    cuadradoRelleno(dimension);
                    break;
                case 2: // inicializamos metodo cuadradoSinRelleno
                    cuadradoSinRelleno(dimension);
                    break;
                case 3: // inicializamos metodo trianguloRelleno
                    trianguloRelleno(dimension);
                    break;
                case 4: // inicializamos metodo trianguloSinRelleno
                    trianguloSinRelleno(dimension);
                    break;
                case 5: // salida de la programa
                    System.out.println("\nFIN DEL PROGRAMA\n");
                    break;
                default: //si opcion no es valida
                    System.out.println("\n[ERROR] Opcion no es valida.");
                    System.out.println("Elige una opcion correcta.\n");
            }
        } while (opcion != 5); // Esperamos "5" para salir de la programa
        entrada.close();
    }

    /** METODO: Mostrar menu
     */
    static void mostrarMenu() {
        System.out.println("------------------------------------");
        System.out.println("MENU DE LAS FIGURAS");
        System.out.println("------------------------------------");
        System.out.println("1 - Dibujar cuadrado");
        System.out.println("2 - Dibujar cuadrado sin relleno");
        System.out.println("3 - Dibujar triangulo");
        System.out.println("4 - Dibujar triangulo sin relleno");
        System.out.println("5 - SALIR");
        System.out.println("------------------------------------");
    }

    /** METODO: DIBUJA UN CUADRADO !RELLENO!
     * @param lado es el lado del cuadrado
     */
    static void cuadradoRelleno(int lado) {
        // Salto de línea inicial para separar el dibujo del menú
        System.out.println();

        // Bucle externo: controla las filas (eje vertical)
        for (int i = 0; i < lado; i++) {

            // Bucle interno: controla las columnas (eje horizontal)
            for (int j = 0; j < lado; j++) {
                // Imprime un asterisco seguido de un espacio sin saltar de línea
                System.out.print("* ");
            }

            // Al terminar cada fila, realizamos un salto de línea
            System.out.println();
        }

        // Salto de línea final para mejorar la legibilidad visual
        System.out.println();
    }

    /** METODO: DIBUJA UN CUADRADO !SIN RELLENO!
     * @param lado es el lado del cuadrado
     */
    static void cuadradoSinRelleno(int lado) {
        // Salto de línea inicial para separar el dibujo
        System.out.println();

        // Bucle externo para las filas
        for (int i = 0; i < lado; i++) {

            // Bucle interno para las columnas
            for (int j = 0; j < lado; j++) {

                // Condición para imprimir solo en los bordes:
                // Primera fila (i == 0), última fila (i == n - 1),
                // Primera columna (j == 0) o última columna (j == n - 1)
                if (i == 0 || i == lado - 1 || j == 0 || j == lado - 1) {
                    System.out.print("* ");
                } else {
                    // Espacio en blanco para el interior del cuadrado
                    System.out.print("  ");
                }
            }

            // Salto de línea al final de cada fila
            System.out.println();
        }

        // Salto de línea final para mejorar la legibilidad
        System.out.println();
    }

    /** METODO: DIBUJA UN TRIANGULO !RELLENO!
     * * @param altura La altura del triángulo.
     */
    static void trianguloRelleno(int altura) {
        // Salto de línea inicial para separar el dibujo
        System.out.println();

        // Bucle externo: controla la altura del triángulo (filas)
        for (int i = 0; i < altura; i++) {

            // Bucle interno: controla el número de asteriscos por fila.
            // La condición j <= i hace que el triángulo crezca en cada fila.
            for (int j = 0; j <= i; j++) {
                // Imprime un asterisco seguido de un espacio
                System.out.print("* ");
            }

            // Salto de línea al terminar cada fila
            System.out.println();
        }

        // Salto de línea final para mejor legibilidad
        System.out.println();
    }

    /** METODO: DIBUJA UN TRIANGULO !SIN RELLENO!
     * Solo se imprimen los asteriscos en los bordes para crear el efecto hueco.
     * * @param altura La altura del triángulo.
     */
    static void trianguloSinRelleno(int altura) {
        // Salto de línea inicial para separar el dibujo
        System.out.println();

        // Bucle externo: recorre cada fila del triángulo
        for (int i = 0; i < altura; i++) {

            // Bucle interno: recorre las columnas de la fila actual
            for (int j = 0; j <= i; j++) {

                // Condición para imprimir asteriscos solo en los bordes:
                // 1. Lado izquierdo (j == 0)
                // 2. Base del triángulo (i == n - 1)
                // 3. Lado inclinado / hipotenusa (j == i)
                if (j == 0 || i == altura - 1 || j == i) {
                    System.out.print("* ");
                } else {
                    // Espacios en blanco para el interior del triángulo
                    System.out.print("  ");
                }
            }

            // Salto de línea al finalizar cada fila
            System.out.println();
        }

        // Salto de línea final para mejorar la estética
        System.out.println();
    }

}
