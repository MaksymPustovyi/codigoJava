import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class RulesScreen extends JDialog {
    private final Color COLOR_BG = new Color(25, 27, 33);
    private final Color COLOR_CARD = new Color(38, 42, 51);
    private final Color COLOR_ACCENT = new Color(70, 130, 255);
    private final Color COLOR_TEXT_DIM = new Color(160, 165, 175);
    private boolean readyToFight = false;

    public RulesScreen(JFrame owner) {
        super(owner, true);
        setUndecorated(true);
        
        // Масштабуємо розмір вікна
        setSize(GameSettings.s(520), GameSettings.s(720)); 
        setLocationRelativeTo(owner);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(COLOR_BG);
        mainPanel.setBorder(new EmptyBorder(GameSettings.s(15), GameSettings.s(30), GameSettings.s(30), GameSettings.s(30)));

        // --- ВЕРХНЯ ЧАСТИНА ---
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);

        JPanel exitRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        exitRow.setOpaque(false);
        JButton exitBtn = createVectorExitButton();
        exitBtn.addActionListener(e -> {
            readyToFight = false;
            dispose();
        });
        exitRow.add(exitBtn);

        JLabel title = new JLabel(L10n.L_RULES_TITLE);
        title.setFont(GameSettings.getScaledFont("Segoe UI", Font.BOLD, 28));
        title.setForeground(COLOR_ACCENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(exitRow);
        topPanel.add(Box.createRigidArea(new Dimension(0, GameSettings.s(10))));
        topPanel.add(title);
        topPanel.add(Box.createRigidArea(new Dimension(0, GameSettings.s(20))));

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // --- ЦЕНТРАЛЬНА ЧАСТИНА ---
        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setOpaque(false);

        // Додаємо картки з масштабованими відступами
        cardsPanel.add(createRuleCard("🎯", L10n.L_RULE_1));
        cardsPanel.add(Box.createRigidArea(new Dimension(0, GameSettings.s(15))));
        cardsPanel.add(createRuleCard("🛡️", L10n.L_RULE_2));
        cardsPanel.add(Box.createRigidArea(new Dimension(0, GameSettings.s(15))));
        cardsPanel.add(createRuleCard("🤕", L10n.L_RULE_3));
        cardsPanel.add(Box.createRigidArea(new Dimension(0, GameSettings.s(15))));
        cardsPanel.add(createRuleCard("🩸", L10n.L_RULE_4));

        mainPanel.add(cardsPanel, BorderLayout.CENTER);

        // --- НИЖНЯ ЧАСТИНА ---
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(new EmptyBorder(GameSettings.s(20), 0, 0, 0));

        JButton okBtn = new JButton(L10n.L_RULES_DONE);
        stylePrimaryButton(okBtn);
        okBtn.addActionListener(e -> {
            readyToFight = true;
            dispose();
        });
        bottomPanel.add(okBtn, BorderLayout.CENTER);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public boolean isReadyToFight() { return readyToFight; }

    private JPanel createRuleCard(String icon, String text) {
        JPanel card = new JPanel(new BorderLayout(GameSettings.s(15), 0));
        card.setBackground(COLOR_CARD);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, GameSettings.s(110))); 

        JPanel iconBox = new JPanel(new GridBagLayout());
        iconBox.setOpaque(false);
        iconBox.setPreferredSize(new Dimension(GameSettings.s(60), GameSettings.s(60)));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(GameSettings.getScaledFont("Segoe UI Emoji", Font.PLAIN, 28));
        iconBox.add(iconLabel);

        JLabel textLabel = new JLabel(text);
        textLabel.setFont(GameSettings.getScaledFont("Segoe UI", Font.PLAIN, 13));
        textLabel.setForeground(new Color(220, 220, 220));

        card.add(iconBox, BorderLayout.WEST);
        card.add(textLabel, BorderLayout.CENTER);

        card.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(new Color(65, 69, 78), 1, true),
                new EmptyBorder(GameSettings.s(10), GameSettings.s(10), GameSettings.s(10), GameSettings.s(15))));

        return card;
    }

    private JButton createVectorExitButton() {
        JButton btn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? Color.RED : COLOR_TEXT_DIM);
                g2.setStroke(new BasicStroke((float)(2.5 * GameSettings.scale), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                int p = GameSettings.s(8); int s = getWidth() - p * 2;
                g2.drawLine(p, p, p + s, p + s);
                g2.drawLine(p, p + s, p + s, p);
                g2.dispose();
            }
        };
        btn.setPreferredSize(new Dimension(GameSettings.s(30), GameSettings.s(30)));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void stylePrimaryButton(JButton btn) {
        btn.setBackground(COLOR_ACCENT);
        btn.setForeground(Color.WHITE);
        btn.setFont(GameSettings.getScaledFont("Segoe UI", Font.BOLD, 18));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(0, GameSettings.s(55)));
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(COLOR_ACCENT.brighter()); }
            public void mouseExited(MouseEvent e) { btn.setBackground(COLOR_ACCENT); }
        });
    }
}