import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SettingsMenu extends JDialog {
    private final Color COLOR_BG = new Color(25, 27, 33);
    private final Color COLOR_ACCENT = new Color(70, 130, 255);
    private final Color COLOR_INPUT = new Color(45, 48, 56);
    private final Color COLOR_TEXT_DIM = new Color(160, 165, 175);

    public SettingsMenu(JDialog owner) {
        super(owner, true);
        setUndecorated(true);
        setSize(GameSettings.s(380), GameSettings.s(350));
        setLocationRelativeTo(owner);
        
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBackground(COLOR_BG);
        root.setBorder(new EmptyBorder(GameSettings.s(15), GameSettings.s(30), GameSettings.s(30), GameSettings.s(30)));

        // --- TOP BAR ---
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        topBar.setOpaque(false);
        topBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, GameSettings.s(35)));
        
        JButton exitBtn = createVectorExitButton();
        exitBtn.addActionListener(e -> dispose());
        topBar.add(exitBtn);

        JLabel title = new JLabel(L10n.L_SETTINGS);
        title.setFont(GameSettings.getScaledFont("Segoe UI", Font.BOLD, 22));
        title.setForeground(COLOR_ACCENT);
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel resLabel = new JLabel(L10n.L_RES_LABEL);
        resLabel.setFont(GameSettings.getScaledFont("Segoe UI", Font.PLAIN, 14));
        resLabel.setForeground(Color.LIGHT_GRAY);
        resLabel.setAlignmentX(CENTER_ALIGNMENT);

        String[] resolutions = {"1024x768", "1280x720", "1366x768", "1600x900", "1920x1080"};
        JComboBox<String> resBox = new JComboBox<>(resolutions);
        resBox.setSelectedItem(GameSettings.screenW + "x" + GameSettings.screenH);
        resBox.setMaximumSize(new Dimension(GameSettings.s(220), GameSettings.s(45)));
        resBox.setBackground(COLOR_INPUT);
        resBox.setForeground(Color.WHITE);
        resBox.setAlignmentX(CENTER_ALIGNMENT);

        JButton saveBtn = new JButton(L10n.L_SAVE);
        saveBtn.setFont(GameSettings.getScaledFont("Segoe UI", Font.BOLD, 16));
        saveBtn.setBackground(COLOR_ACCENT);
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);
        saveBtn.setBorderPainted(false);
        saveBtn.setAlignmentX(CENTER_ALIGNMENT);
        saveBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, GameSettings.s(55)));
        saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        saveBtn.addActionListener(e -> {
            GameSettings.setResolution((String) resBox.getSelectedItem());
            dispose();
        });

        root.add(topBar);
        root.add(title);
        root.add(Box.createRigidArea(new Dimension(0, GameSettings.s(20))));
        root.add(resLabel);
        root.add(Box.createRigidArea(new Dimension(0, GameSettings.s(10))));
        root.add(resBox);
        root.add(Box.createVerticalGlue());
        root.add(saveBtn);

        add(root);
        setVisible(true);
    }

    private JButton createVectorExitButton() {
        JButton btn = new JButton() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? Color.RED : COLOR_TEXT_DIM);
                g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                int p = GameSettings.s(8);
                g2.drawLine(p, p, getWidth()-p, getHeight()-p);
                g2.drawLine(p, getHeight()-p, getWidth()-p, p);
                g2.dispose();
            }
        };
        btn.setPreferredSize(new Dimension(GameSettings.s(30), GameSettings.s(30)));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        return btn;
    }
}