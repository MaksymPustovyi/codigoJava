import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ArenaUI {
    public JEditorPane detailedLog;
    private List<String> logEntries = new ArrayList<>();
    public JLabel shortLog;
    public JPanel mainContainer;

    private final Color BG_COLOR = new Color(20, 22, 27);
    private final Color PANEL_BG = new Color(32, 34, 40);
    private final Color ACCENT_BLUE = new Color(80, 160, 255);
    private final Color BORDER_COLOR = new Color(50, 54, 62);

    public ArenaUI(JFrame frame) {
        mainContainer = new JPanel(new BorderLayout());
        mainContainer.setBackground(BG_COLOR);

        // --- 1. ВЕРХНІЙ СТАТУС-БАР (NORTH) ---
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PANEL_BG);
        headerPanel.setPreferredSize(new Dimension(0, GameSettings.s(110)));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));

        // ЛІВА ЧАСТИНА (Порожня розпірка для ідеальної симетрії центру)
        JPanel leftSpacer = new JPanel();
        leftSpacer.setOpaque(false);
        leftSpacer.setPreferredSize(new Dimension(GameSettings.s(200), 0));
        headerPanel.add(leftSpacer, BorderLayout.WEST);

        // ЦЕНТР (Текст статусу ходу)
        shortLog = new JLabel("<html><center>" + L10n.STEP_1_ATK + "</center></html>", SwingConstants.CENTER);
        shortLog.setFont(GameSettings.getScaledFont("Segoe UI", Font.BOLD, 20));
        shortLog.setForeground(ACCENT_BLUE);
        headerPanel.add(shortLog, BorderLayout.CENTER);

        // ПРАВА ЧАСТИНА (Єдина кнопка виходу - Хрестик)
        JButton exitBtn = createVectorExitButton(frame);
        JPanel rightWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 25, 38)); // Відступи для хрестика
        rightWrapper.setOpaque(false);
        rightWrapper.setPreferredSize(new Dimension(GameSettings.s(200), 0));
        rightWrapper.add(exitBtn);
        headerPanel.add(rightWrapper, BorderLayout.EAST);

        mainContainer.add(headerPanel, BorderLayout.NORTH);

        // --- 2. ПРАВА ПАНЕЛЬ (EAST) ---
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(PANEL_BG);
        rightPanel.setPreferredSize(new Dimension(GameSettings.s(450), 0));
        rightPanel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, BORDER_COLOR));

        JLabel logTitleLabel = new JLabel(L10n.LOG_TITLE, SwingConstants.LEFT);
        logTitleLabel.setFont(GameSettings.getScaledFont("Segoe UI", Font.BOLD, 18));
        logTitleLabel.setForeground(new Color(150, 160, 180));
        logTitleLabel.setBorder(new EmptyBorder(15, 20, 10, 20));
        rightPanel.add(logTitleLabel, BorderLayout.NORTH);

        detailedLog = new JEditorPane("text/html", "");
        detailedLog.setEditable(false);
        detailedLog.setBackground(new Color(25, 27, 33));
        
        JScrollPane scroll = new JScrollPane(detailedLog);
        scroll.setBorder(null);
        rightPanel.add(scroll, BorderLayout.CENTER);
        mainContainer.add(rightPanel, BorderLayout.EAST);

        frame.getContentPane().add(mainContainer);
    }

    public void appendToLog(String html) {
        logEntries.add(html);
        StringBuilder sb = new StringBuilder("<html><body style='font-family:Segoe UI; font-size:12px; color:#CCCCCC;'>");
        for (String s : logEntries) sb.append(s);
        sb.append("</body></html>");
        detailedLog.setText(sb.toString());
        detailedLog.setCaretPosition(detailedLog.getDocument().getLength());
    }

    private JButton createVectorExitButton(JFrame frame) {
        JButton btn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Зміна кольору при наведенні
                if (getModel().isRollover()) g2.setColor(new Color(255, 80, 80));
                else g2.setColor(new Color(160, 165, 175));
                
                g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                int p = 6; // Внутрішній відступ хрестика
                int s = getWidth() - p * 2;
                g2.drawLine(p, p, p + s, p + s);
                g2.drawLine(p, p + s, p + s, p);
                g2.dispose();
            }
        };
        btn.setPreferredSize(new Dimension(28, 28));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btn.addActionListener(e -> {
            ExitConfirmDialog dialog = new ExitConfirmDialog(frame);
            if (dialog.isConfirmed()) frame.dispose();
        });
        return btn;
    }

    private String colorToHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public void updateStatus(String pRes, String aRes) {
        String hex = colorToHex(ACCENT_BLUE);
        // Зменшили ширину body, щоб текст не розсував панель
        shortLog.setText("<html><body style='text-align: center; width: 450px;'>" +
                "<div style='color: " + hex + "; font-weight: bold; white-space: nowrap;'>" + pRes + "</div>" +
                "<div style='color: #AAAAAA; white-space: nowrap;'>" + aRes + "</div>" +
                "</body></html>");
    }
}