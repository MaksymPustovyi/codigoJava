import javax.swing.*;
import java.awt.*;

public class GameOverDialog extends JDialog {
    private final Color COLOR_BG = new Color(25, 27, 33);
    private final Color COLOR_ACCENT = new Color(70, 130, 255);
    private boolean restartRequested = false;

    public GameOverDialog(JFrame owner, String resultText) {
        super(owner, true);
        setUndecorated(true);
        setSize(GameSettings.s(400), GameSettings.s(300));
        setLocationRelativeTo(owner);

        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBackground(COLOR_BG);
        root.setBorder(BorderFactory.createLineBorder(new Color(60, 64, 75), 2));
        
        // --- ЗАГОЛОВОК (ПЕРЕМОГА/ПОРАЗКА) ---
        JLabel labelResult = new JLabel(resultText);
        labelResult.setFont(GameSettings.getScaledFont("Segoe UI", Font.BOLD, 32));
        labelResult.setForeground(resultText.contains("!") ? COLOR_ACCENT : Color.RED);
        labelResult.setAlignmentX(CENTER_ALIGNMENT);

        // --- ПАНЕЛЬ КНОПОК ---
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, GameSettings.s(15)));
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(GameSettings.s(250), GameSettings.s(120)));

        JButton btnRestart = new JButton(L10n.L_RESTART);
        styleButton(btnRestart, true);
        btnRestart.addActionListener(e -> {
            restartRequested = true;
            dispose();
        });

        JButton btnExit = new JButton(L10n.L_EXIT_GAME);
        styleButton(btnExit, false);
        btnExit.addActionListener(e -> System.exit(0));

        buttonPanel.add(btnRestart);
        buttonPanel.add(btnExit);

        // Збірка
        root.add(Box.createVerticalGlue());
        root.add(labelResult);
        root.add(Box.createRigidArea(new Dimension(0, GameSettings.s(40))));
        root.add(buttonPanel);
        root.add(Box.createVerticalGlue());

        add(root);
        setVisible(true);
    }

    private void styleButton(JButton btn, boolean primary) {
        btn.setFont(GameSettings.getScaledFont("Segoe UI", Font.BOLD, 16));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(primary);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        if (primary) {
            btn.setBackground(COLOR_ACCENT);
        } else {
            btn.setBackground(COLOR_BG);
            btn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            btn.setContentAreaFilled(false);
        }
    }

    public boolean isRestartRequested() {
        return restartRequested;
    }
}