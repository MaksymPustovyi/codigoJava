// ==========================================
// 1. НАЛАШТУВАННЯ ТА КОНСТАНТИ
// ==========================================
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

class GameSettings {
    public static final float BASE_GLOBAL_HP = 600f;
    public static final float BASE_ACCURACY = 0.95f;
    public static final float BASE_EVASION = 0.85f;
    public static final double REF_WIDTH = 1920.0;
    public static final double REF_HEIGHT = 1080.0;
    public static double scaleX, scaleY, scale;
    public static float aiHpModifier = 1.0f; // Змінюється меню
    public static String playerName = "Jugador";

    public static void initScaling(Component c) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        scaleX = screen.width / REF_WIDTH;
        scaleY = screen.height / REF_HEIGHT;
        scale = Math.min(scaleX, scaleY);
    }
}