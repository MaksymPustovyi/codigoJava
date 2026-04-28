import java.util.Scanner;

/**
 * Pagina 184, Introducción a JAVA.pdf
 * Ejercicio 4
 * Dada una secuencia de números enteros acabada en 0,
 * obtener la suma de aquellos números tales que su número de cifras sea igual a la suma de las mismas.
 * No se pueden utilizar Arrays.
 */

public class Ejer4Iniciacion {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        String textual = "";
        int total = 0, num;

        // inicializamos el ejercicio
        System.out.println("\n- INICIO DEL EJERCICIO -\n");

       do {
            System.out.print("Introduce numero positivo, cero para terminar: ");
            num = entrada.nextInt();

            int digitos = 1;
            int suma = num % 10;
            int cifra = num;

            while (cifra > 9) {
                cifra /= 10;
                digitos++;
                suma += cifra % 10;
            }

            if (digitos == suma) {
                textual += num + "+";
                total += num;
            }
       } while (num != 0);

        // imprimimos los resultados
        System.out.println("--------------------");
        System.out.println("Resultado es " + textual + "\b=" + total);
        System.out.println("--------------------");

        System.out.println("\n- FIN DEL EJERCICIO -\n");

        entrada.close();
    }
}
