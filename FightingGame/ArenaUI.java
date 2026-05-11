import javax.swing.*;
import java.awt.*;

public class ArenaUI extends JPanel {
    public JTextArea detailedLog;
    public JLabel shortLog;
    public JPanel gameSurface;

    private final Color BG_COLOR = new Color(20, 22, 27);
    private final Color PANEL_BG = new Color(32, 34, 40);
    private final Color ACCENT_BLUE = new Color(80, 160, 255); // Тепер задіяний
    private final Color BORDER_COLOR = new Color(50, 54, 62);

    public ArenaUI(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(BG_COLOR);

        // --- ВЕРХНІЙ СТАТУС-БАР ---
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PANEL_BG);
        headerPanel.setPreferredSize(new Dimension(0, (int) (110 * GameSettings.scaleY)));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));

        // Використовуємо ACCENT_BLUE для початкового тексту
        shortLog = new JLabel("<html><center>READY FOR BATTLE</center></html>", SwingConstants.CENTER);
        shortLog.setFont(new Font("Segoe UI", Font.BOLD, (int) (20 * GameSettings.scale)));
        shortLog.setForeground(ACCENT_BLUE); 
        headerPanel.add(shortLog, BorderLayout.CENTER);
        
        frame.add(headerPanel, BorderLayout.NORTH);

        // --- ПРАВА ПАНЕЛЬ (DETAILED LOG) ---
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(PANEL_BG);
        rightPanel.setPreferredSize(new Dimension((int) (450 * GameSettings.scaleX), 0));
        rightPanel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, BORDER_COLOR));

        detailedLog = new JTextArea();
        detailedLog.setEditable(false);
        detailedLog.setBackground(new Color(25, 27, 33));
        detailedLog.setForeground(new Color(200, 205, 215));
        // Segoe UI забезпечує кращу підтримку емодзі
        detailedLog.setFont(new Font("Segoe UI Semibold", Font.PLAIN, (int) (15 * GameSettings.scale)));
        detailedLog.setLineWrap(true);
        detailedLog.setWrapStyleWord(true);
        detailedLog.setMargin(new Insets(20, 20, 20, 20));

        JScrollPane scroll = new JScrollPane(detailedLog);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        
        rightPanel.add(scroll, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        // --- ПОВЕРХНЯ БОЮ ---
        gameSurface = new JPanel();
        frame.add(gameSurface, BorderLayout.CENTER);
    }

    /**
     * Конвертує об'єкт Color у Hex-рядок для HTML (напр., #50A0FF)
     */
    private String colorToHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Оновлює верхнє табло. 
     * Використовує фіксовану ширину в стилях, щоб уникнути небажаних переносів.
     */
    public void updateStatus(String pRes, String aRes) {
        String hexAccent = colorToHex(ACCENT_BLUE);
        
        // Встановлюємо body width: 1200px, щоб текст гарантовано влізав в один рядок
        // pRes - перший рядок (Гравець)
        // aRes - другий рядок (Комп'ютер)
        shortLog.setText("<html><body style='width: 1200px; text-align: center;'>" +
                "<p style='margin: 0; padding: 0; color: " + hexAccent + "; font-weight: bold; font-size: 1.1em;'>" + 
                    pRes + 
                "</p>" +
                "<p style='margin: 5px 0 0 0; padding: 0; color: #BBBBBB; font-weight: normal;'>" + 
                    aRes + 
                "</p>" +
                "</body></html>");
    }
}