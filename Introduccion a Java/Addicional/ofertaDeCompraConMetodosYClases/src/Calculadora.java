// --- КЛАС: ПРОВЕДЕННЯ РОЗРАХУНКІВ ---
// CLASE: PARA GESTIONAR LA LÓGICA DE NEGOCIO
public class Calculadora {

    // --- МЕТОД: ДЛЯ ОБЧИСЛЕННЯ КІНЦЕВОЇ ЦІНИ ---
    // METODO: ESTÁTICO PARA CALCULAR EL PRECIO FINAL
    public static float calcularPrecioFinal(float precio, float descuento) {
        float ahorro = (precio / 100) * descuento;
        return precio - ahorro;
    }

    // --- МЕТОД: ДЛЯ ОБЧИСЛЕННЯ ТІЛЬКИ СУМИ ЗАОЩАДЖЕННЯ ---
    // METODO: ESTÁTICO PARA CALCULAR SOLO EL AHORRO
    public static float calcularAhorro(float precio, float descuento) {
        return (precio / 100) * descuento;
    }
}