import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class ArenaUI extends JPanel {
    public JTextArea detailedLog;
    public JLabel shortLog;
    public JPanel gameSurface;
    public JButton exitBtn; // Кнопка виходу

    private final Color BG_COLOR = new Color(20, 22, 27);
    private final Color PANEL_BG = new Color(32, 34, 40);
    private final Color ACCENT_BLUE = new Color(80, 160, 255);
    private final Color BORDER_COLOR = new Color(50, 54, 62);

    public ArenaUI(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(BG_COLOR);

        // --- ВЕРХНІЙ СТАТУС-БАР (HEADER) ---
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PANEL_BG);
        headerPanel.setPreferredSize(new Dimension(0, (int) (110 * GameSettings.scaleY)));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));

        // Центральний текст
        shortLog = new JLabel("<html><center>READY FOR BATTLE</center></html>", SwingConstants.CENTER);
        shortLog.setFont(new Font("Segoe UI", Font.BOLD, (int) (20 * GameSettings.scale)));
        shortLog.setForeground(ACCENT_BLUE);
        headerPanel.add(shortLog, BorderLayout.CENTER);

        // Кнопка виходу (справа в хедері)
        exitBtn = createVectorExitButton(frame);
        JPanel exitWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        exitWrapper.setOpaque(false);
        exitWrapper.add(exitBtn);
        headerPanel.add(exitWrapper, BorderLayout.EAST);

        // Порожній блок зліва для балансу (симетрії тексту)
        JPanel leftSpacer = new JPanel();
        leftSpacer.setOpaque(false);
        leftSpacer.setPreferredSize(new Dimension(60, 0));
        headerPanel.add(leftSpacer, BorderLayout.WEST);

        frame.add(headerPanel, BorderLayout.NORTH);

        // --- ПРАВА ПАНЕЛЬ (DETAILED LOG) ---
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(PANEL_BG);
        rightPanel.setPreferredSize(new Dimension((int) (450 * GameSettings.scaleX), 0));
        rightPanel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, BORDER_COLOR));

        JLabel logTitleLabel = new JLabel(L10n.LOG_TITLE, SwingConstants.LEFT);
        logTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, (int) (18 * GameSettings.scale)));
        logTitleLabel.setForeground(new Color(150, 160, 180)); // Приємний сіро-блакитний колір
        logTitleLabel.setBorder(new EmptyBorder(15, 20, 10, 20)); // Відступи від країв
        rightPanel.add(logTitleLabel, BorderLayout.NORTH);

        detailedLog = new JTextArea();
        detailedLog.setEditable(false);
        detailedLog.setBackground(new Color(25, 27, 33));
        detailedLog.setForeground(new Color(200, 205, 215));
        detailedLog.setFont(new Font("Segoe UI Semibold", Font.PLAIN, (int) (15 * GameSettings.scale)));
        detailedLog.setLineWrap(true);
        detailedLog.setWrapStyleWord(true);
        detailedLog.setMargin(new Insets(10, 20, 20, 20)); // Трохи зменшили верхній відступ, бо тепер є заголовок

        JScrollPane scroll = new JScrollPane(detailedLog);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        rightPanel.add(scroll, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        // --- ПОВЕРХНЯ БОЮ ---
        gameSurface = new JPanel();
        frame.add(gameSurface, BorderLayout.CENTER);
    }

    private JButton createVectorExitButton(JFrame frame) {
        JButton btn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isRollover())
                    g2.setColor(Color.RED);
                else
                    g2.setColor(new Color(160, 165, 175));

                g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                int p = 8;
                int s = getWidth() - p * 2;
                g2.drawLine(p, p, p + s, p + s);
                g2.drawLine(p, p + s, p + s, p);
                g2.dispose();
            }
        };
        btn.setPreferredSize(new Dimension(30, 30));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> frame.dispose()); 
        return btn;
    }

    private String colorToHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public void updateStatus(String pRes, String aRes) {
        String hexAccent = colorToHex(ACCENT_BLUE);
        shortLog.setText("<html><body style='width: 1200px; text-align: center;'>" +
                "<p style='margin: 0; padding: 0; color: " + hexAccent
                + "; font-weight: bold; font-size: 1.1em; white-space: nowrap;'>" + pRes + "</p>" +
                "<p style='margin: 5px 0 0 0; padding: 0; color: #BBBBBB; font-weight: normal; white-space: nowrap;'>"
                + aRes + "</p>" +
                "</body></html>");
    }
}