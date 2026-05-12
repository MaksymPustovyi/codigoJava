import java.awt.*;

public class GameSettings {
    public static final float BASE_GLOBAL_HP = 600f;
    public static final float BASE_ACCURACY = 0.95f;
    public static final float BASE_EVASION = 0.85f;
    public static final double REF_WIDTH = 1920.0;
    public static final double REF_HEIGHT = 1080.0;
    
    public static double scaleX = 1.0, scaleY = 1.0, scale = 1.0;
    public static int screenW = 1280; 
    public static int screenH = 720;
    public static String playerName = "Player";

    private static boolean initialized = false;

    public static void initScaling() {
        if (!initialized) {
            // ВИПРАВЛЕНО: Використовуємо GraphicsEnvironment для отримання реальних параметрів відеокарти
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int physicalWidth = gd.getDisplayMode().getWidth();
            int physicalHeight = gd.getDisplayMode().getHeight();
            
            // Діагностика (можна видалити потім)
            System.out.println("Detected physical resolution: " + physicalWidth + "x" + physicalHeight);

            // Перевіряємо можливість встановлення 1920x1080
            if (physicalWidth >= 1920 && physicalHeight >= 1080) {
                screenW = 1920;
                screenH = 1080;
            } else {
                // Якщо монітор реально менший - ставимо його максимум
                screenW = physicalWidth;
                screenH = physicalHeight;
            }
            initialized = true;
        }

        // Розрахунок коефіцієнтів
        scaleX = screenW / REF_WIDTH;
        scaleY = screenH / REF_HEIGHT;
        
        // Використовуємо мінімальний коефіцієнт для збереження пропорцій (scale)
        scale = Math.min(scaleX, scaleY);
    }

    public static void setResolution(String res) {
        String[] parts = res.split("x");
        screenW = Integer.parseInt(parts[0]);
        screenH = Integer.parseInt(parts[1]);
        initialized = true; 
        initScaling();
    }

    public static int s(double value) {
        return (int) Math.round(value * scale);
    }

    public static Font getScaledFont(String name, int style, int baseSize) {
        return new Font(name, style, s(baseSize));
    }
}