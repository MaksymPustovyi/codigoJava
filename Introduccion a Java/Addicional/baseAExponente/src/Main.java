import java.util.Scanner;
// Algoritmo baseAExponente
public class Main {
    public static void main(String[] args) {
// Definir base, exponente, contador, resultado Como Entero;
        int base; int exponente; int contador; int resultado;
// -- otro codigo --
        try (Scanner input = new Scanner(System.in)) {
// Escribir "Introduce Base:";
            System.out.println("Introduce Base: ");
// Leer base;
            base = input.nextInt();
// Escribir "Introduce Exponente:";
            System.out.println("Introduce Exponente: ");
// Leer exponente;
            exponente = input.nextInt();
// resultado=1;
// contador=1;
            resultado = 1;
            contador = 0;
// Mientras  contador < exponente
            while (contador < exponente) {
// resultado = resultado * base;
// contador = contador + 1;
                resultado *= base;
                contador++;
// FinMientras
            }
// Escribir "Resultado es " resultado;
            System.out.println("Resultado es: " + resultado);
        }
    }
// FinAlgoritmo
}