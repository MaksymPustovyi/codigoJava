import java.util.Scanner;

/**
 * Clase Menu: Sistema de renderizado de figuras geométricas por consola.
 * Esta arquitectura utiliza una estructura modular para separar la lógica de 
 * presentación del procesamiento de datos.
 */
public class Menu {

    /**
     * Punto de entrada principal (Execution Workflow).
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcion = 0; 
        int dimension = 0;

        inicioDeLaPrograma();

        do {
            mostrarMenu();

            try {
                System.out.print("Elige una de las opciones: ");
                opcion = entrada.nextInt();

                // Validación lógica de negocio: captura de dimensiones solo para opciones de dibujo.
                if (opcion > 0 && opcion < 5) {
                    System.out.print((opcion == 1 || opcion == 2 ? "Indica el tamano del lado: "
                            : "Indica la altura del triangulo: "));
                    dimension = entrada.nextInt();
                }

                executeAction(opcion, dimension);

            } catch (Exception e) {
                System.out.println("\n[ERROR] Entrada no valida. Asegurese de introducir un numero entero.");
                entrada.next(); // Flush del buffer para evitar bucles infinitos en el flujo de entrada.
                opcion = 0; // Reseteo del estado para continuar el ciclo de forma segura.
            }

        } while (opcion != 5);

        finDeLaPrograma();
        entrada.close(); // Cierre de recursos al finalizar el ciclo de vida de la aplicación.
    }

    /**
     * Orquestador de acciones según la selección del usuario.
     * Mejora la mantenibilidad al desacoplar el switch del flujo principal.
     */
    private static void executeAction(int opcion, int dimension) {
        switch (opcion) {
            case 1: cuadradoRelleno(dimension); break;
            case 2: cuadradoSinRelleno(dimension); break;
            case 3: trianguloRelleno(dimension); break;
            case 4: trianguloSinRelleno(dimension); break;
            case 5: break; 
            default:
                if (opcion != 0) System.out.println("\n[ERROR] Opcion no valida.\n");
        }
    }

    /**
     * Despliega la interfaz de usuario en la consola.
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

    // --- MÉTODOS DE RENDERIZADO (Implementaciones Geométricas) ---

    /**
     * Renderiza un cuadrado sólido utilizando anidamiento de bucles (O(n^2)).
     */
    static void cuadradoRelleno(int lado) {
        System.out.println();
        for (int i = 0; i < lado; i++) {
            for (int j = 0; j < lado; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Renderiza un cuadrado hueco aplicando lógica de bordes cartesianos.
     */
    static void cuadradoSinRelleno(int lado) {
        System.out.println();
        for (int i = 0; i < lado; i++) {
            for (int j = 0; j < lado; j++) {
                if (i == 0 || i == lado - 1 || j == 0 || j == lado - 1) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Genera un triángulo rectángulo sólido mediante progresión lineal.
     */
    static void trianguloRelleno(int altura) {
        System.out.println();
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Genera un triángulo hueco validando hipotenusa y catetos.
     */
    static void trianguloSinRelleno(int altura) {
        System.out.println();
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || i == altura - 1 || j == i) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        System.out.println();
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