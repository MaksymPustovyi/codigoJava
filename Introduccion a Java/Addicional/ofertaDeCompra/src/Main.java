import java.util.Scanner;

//PUNTO DE ENTRADA DE LA APLICACIÓN
public class Main {
    public static void main(String[] args) {

        //DECLARAMOS LOS VARIABLES
        float precio;
        float descuento;
        float ahorro;
        float precioFinal;

        //LIMPIAMOS LA PANTALLA
        clearScreen();

        // CREAMOS UN SCANNER PARA LEER LA ENTRADA DEL USUARIO
        Scanner input = new Scanner(System.in);

            // CONTROL DE FLUJO: VALIDACIÓN DE DATOS DE ENTRADA
            while (true) {
                System.out.print("Introduce el precio: ");

                //VALIDAMOS QUE LOS DATOS INTRODUCIDOS SEAN MAYORES QUE CERO Y SEAN NUMERICOS
                if (input.hasNextDouble()) {
                    precio = input.nextFloat();
                    if (precio >= 0) break;
                    else System.out.println("Error: El precio no puede ser negativo.");
                } else {
                    System.out.println("Error: Por favor, introduce solo números.");
                    input.next();
                }
            }

        try (// CREAMOS UN SCANNER PARA LEER LA ENTRADA DEL USUARIO
        Scanner input2 = new Scanner(System.in)) {
            // CONTROL DE FLUJO: VALIDACIÓN DE DATOS DE ENTRADA
            while (true) {
                System.out.print("Introduce el porcentaje de descuento (0-100): ");

                // VALIDAMOS QUE EL VALOR ESTÉ ENTRE 0 Y 100
                if (input2.hasNextDouble()) {
                    descuento = input2.nextFloat();
                    if (descuento >= 0 && descuento <= 100) break;
                    else System.out.println("Error: El porcentaje debe estar entre 0 y 100.");
                } else {
                    System.out.println("Error: Por favor, introduce solo números.");
                    input2.next();
                } 
            }
        }

            //CALCULAMOS EL RESULTADO
        ahorro=precio/100*descuento;
        precioFinal=precio-ahorro;

        //MOSTRAR RESULTADOS EN PANTALLA
        System.out.println("--- RESULTADO ---");
        System.out.println("Precio original: " + precio);
        System.out.println("Descuento aplicado: " + ahorro);
        System.out.println("Precio final pagado: " + precioFinal);
        input.close();
    }

    //CREAMOS UN METODO PARA LIMPIAR LA CONSOLA
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}