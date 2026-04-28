import java.util.Scanner;

/**
 * Pagina 184, Introducción a JAVA.pdf
 * Ejercicio 3
 * Escribir un programa que pida números enteros hasta que se introduzca un
 * valor menor o igual a cero.
 * A continuación debe mostrar la suma total de dichos números.
 * Se deben incluir todos los números que hemos ido introduciendo por teclado.
 * No se pueden utilizar Arrays.
 */

public class Ejer3Iniciacion {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        int num, total = 0;
        String suma = "";

        // inicializamos el ejercicio
        System.out.println("\n- INICIO DEL EJERCICIO -\n");

        boolean esPrimero = true; //veriable para verificar primero numero introducido

        do {
            // leemos los numeros
            System.out.print("Introduce los numeros para sumar, cero para salir: ");
            num = entrada.nextInt();

            // si es primero numero - contamos total, guardamos String(suma) sin symbolo "+" y cambiamos esPrimero a False
            if (esPrimero) {
                suma = "" + num;
                total += num;
                esPrimero = false;
            }
            // si introducido > 0 - contamos total y añadimos a String(suma) [+numero]
            else if (num > 0) {
                total += num;
                suma += "+" + num;

            // si introducido < 0 - contamos total pero usamos symbolo negativo para añadir a String(suma)
            } else if (num < 0) {
                total += num;
                suma += "" + num;
            
            // si introducido "0" - añadimos a String(suma) el symbolo "="
            }else
                suma += "=";
        } while (num != 0); // esperamos "0" para salir

        // imprimimos los resultados
        System.out.println("--------------------");
        System.out.println("Resultado de suma: " + suma + total);
        System.out.println("--------------------");

        System.out.println("\n- FIN DEL EJERCICIO -\n");

        entrada.close();
    }
}
