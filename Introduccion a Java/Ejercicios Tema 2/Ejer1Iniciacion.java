import java.util.Scanner;

/**
 * Pagina 184, Introducción a JAVA.pdf
 * Ejercicio 1
 * Leer una cantidad 'N' y luego introducir 'N' números enteros.
 * Se pide imprimir el mayor y el menor y las veces que aparece cada uno.
 */

public class Ejer1Iniciacion {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        int cantidadNumeros, numActual;
        int contador = 1, max = 0, min = 0, contadorMax = 0, contadorMin = 0;

        // leemos una cantidad 'N'

        System.out.println("\n- INICIO DEL EJERCICIO -\n");

        System.out.print("Cuantos numeros quieres introducir? ");
        cantidadNumeros = entrada.nextInt();
        System.out.println("--------------------");

        // un bucle para leer y analizar la cantidad 'N' de numeros
        while (contador <= cantidadNumeros) {
            System.out.print("Introduce un numero " + (contador) + " de " + cantidadNumeros + ": ");
            numActual = entrada.nextInt();
            System.out.println("--------------------");

            // si es primero numero >>> inicializamos max y min
            if (contador == 0) {
                max = numActual;
                min = numActual;
                contadorMax = 1;
                contadorMin = 1;

            } else {
                // nuevo maximo
                if (numActual > max) {
                    max = numActual;
                    contadorMax = 1;
                }
                // mismo maximo
                else if (numActual == max) {
                    contadorMax++;
                }

                // nuevo minimo
                if (numActual < min) {
                    min = numActual;
                    contadorMin = 1;
                }
                // mismo minimo
                else if (numActual == min) {
                    contadorMin++;
                }
            }

            // contador siguiente
            contador++;
        }

        // imprimimos los resultados
        System.out.println(
                "\nEl minimo es [" + min + "] y aparece (" + contadorMin + (contadorMin == 1 ? ") vez" : ") veces"));
        System.out.println(
                "El maximo es [" + max + "] y aparece (" + contadorMax + (contadorMax == 1 ? ") vez" : ") veces"));

        System.out.println("\n- FIN DEL EJERCICIO -\n");

        entrada.close();
    }
}
