//  PARA QUE LA SIRVE ESTA PROGRAMA:
//  Diseñar un algoritmo que, introduciendo por teclado el precio y el procentaje
//  descontado, escribe el precio pagado en una compra

// PUNTO DE ENTRADA DE LA APLICACIÓN
public class Main {
    public static void main(String[] args) {

        // --- ДЕКЛАРУЄМО ЗМІННІ ---
        // DECLARAMOS LOS VARIABLES
        float precio;
        float descuento;
        float ahorro;
        float precioFinal;

        // --- МЕТОД: ОЧИЩАЄМО ЕКРАН ---
        // METODO: QUE LIMPIA LA PANTALLA
        Pantalla.clearScreen();

        // --- МЕТОД: ЗАПИТ ЦІНИ ТА ВІДСОТКА У КОРИСТУВАЧА ---
        // METODO: PARA SOLICITAR EL PRECIO Y EL DESCUENTO AL USUARIO
        precio = Lector.solicitarPrecio();
        descuento = Lector.solicitarDescuento();

        // --- РАХУЄМО ---
        // CALCULAMOS LOS RESULTADOS USANDO LA CLASE "CALCULADORA"
        ahorro = Calculadora.calcularAhorro(precio, descuento);
        precioFinal = Calculadora.calcularPrecioFinal(precio, descuento);

        // --- ДЕМОНТРУЄМО РЕЗУЛЬТАТ ---
        // MOSTRAMOS LOS DATOS USANDO LA CLASE "PANTALLA"
        Pantalla.mostrarTicket(precio, ahorro, precioFinal);

    }
}