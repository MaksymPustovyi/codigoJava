import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StartMenu extends JDialog {
    private JTextField nameField;
    private JComboBox<String> langBox;
    private BattleButton startBtn, settingsBtn;
    private JLabel title, subtitle, labelName, labelLang;
    private JPanel root;
    private boolean started = false;

    private final Color COLOR_BG = new Color(25, 27, 33);
    private final Color COLOR_INPUT = new Color(45, 48, 56);
    private final Color COLOR_ACCENT = new Color(70, 130, 255);
    private final Color COLOR_TEXT_MAIN = new Color(240, 240, 240);
    private final Color COLOR_TEXT_DIM = new Color(160, 165, 175);

    public StartMenu() {
        setUndecorated(true);
        setModal(true);
        GameSettings.initScaling();
        rebuildUI();
    }

    public void rebuildUI() {
        getContentPane().removeAll();
        
        int w = Math.max(GameSettings.s(420), 380);
        int h = Math.max(GameSettings.s(600), 520);
        setSize(w, h);
        setLocationRelativeTo(null);

        root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBackground(COLOR_BG);
        // Збільшуємо бокові відступи (60), щоб "затиснути" контент по центру, хоча він вирівняний вліво
        root.setBorder(new EmptyBorder(GameSettings.s(20), GameSettings.s(60), GameSettings.s(40), GameSettings.s(60)));

        // --- TOP BAR ---
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        topBar.setOpaque(false);
        topBar.setAlignmentX(Component.LEFT_ALIGNMENT); // СУВОРО ВЛІВО
        topBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, GameSettings.s(30)));
        JButton exitBtn = createVectorExitButton();
        exitBtn.addActionListener(e -> System.exit(0));
        topBar.add(exitBtn);

        // --- HEADER ---
        title = new JLabel(L10n.GAME_TITLE);
        title.setFont(GameSettings.getScaledFont("Segoe UI", Font.BOLD, 28));
        title.setForeground(COLOR_ACCENT);
        title.setAlignmentX(Component.LEFT_ALIGNMENT); // СУВОРО ВЛІВО

        subtitle = new JLabel(L10n.L_SUBTITLE);
        subtitle.setFont(GameSettings.getScaledFont("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(COLOR_TEXT_DIM);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT); // СУВОРО ВЛІВО

        // --- FIELDS ---
        labelName = createFieldLabel(L10n.L_NAME);
        nameField = new JTextField(GameSettings.playerName);
        styleInputField(nameField);

        labelLang = createFieldLabel(L10n.L_LANG);
        langBox = new JComboBox<>(new String[]{"Українська", "English", "Español"});
        langBox.setSelectedIndex(getLangIndex());
        styleComboBox(langBox);
        
        langBox.addActionListener(e -> {
            L10n.setLocale(langBox.getSelectedIndex());
            refreshTexts();
        });

        // --- BUTTONS ---
        settingsBtn = new BattleButton(L10n.L_SETTINGS, false);
        settingsBtn.addActionListener(e -> {
            new SettingsMenu(this);
            rebuildUI();
        });

        startBtn = new BattleButton(L10n.L_START, true);
        startBtn.addActionListener(e -> {
            GameSettings.playerName = nameField.getText().trim();
            if (GameSettings.playerName.isEmpty()) GameSettings.playerName = "Warrior";
            started = true;
            dispose();
        });

        // --- ASSEMBLY ---
        root.add(topBar);
        root.add(Box.createRigidArea(new Dimension(0, GameSettings.s(15))));
        root.add(title);
        root.add(subtitle);
        root.add(Box.createRigidArea(new Dimension(0, GameSettings.s(50))));
        
        root.add(labelName);
        root.add(Box.createRigidArea(new Dimension(0, 8)));
        root.add(nameField);
        
        root.add(Box.createRigidArea(new Dimension(0, 25)));
        
        root.add(labelLang);
        root.add(Box.createRigidArea(new Dimension(0, 8)));
        root.add(langBox);
        
        root.add(Box.createRigidArea(new Dimension(0, 40)));
        root.add(settingsBtn);
        
        root.add(Box.createVerticalGlue());
        root.add(startBtn);

        add(root);
        revalidate();
        repaint();
        if (!isVisible()) setVisible(true);
    }

    private JLabel createFieldLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(GameSettings.getScaledFont("Segoe UI", Font.BOLD, 12));
        l.setForeground(COLOR_TEXT_DIM);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private void styleInputField(JTextField f) {
        f.setBackground(COLOR_INPUT);
        f.setForeground(COLOR_TEXT_MAIN);
        f.setCaretColor(COLOR_ACCENT);
        f.setFont(GameSettings.getScaledFont("Segoe UI", Font.PLAIN, 16));
        f.setAlignmentX(Component.LEFT_ALIGNMENT);
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 75, 85), 1),
            BorderFactory.createEmptyBorder(GameSettings.s(10), 15, GameSettings.s(10), 15)
        ));
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE, GameSettings.s(45)));
    }

    private void styleComboBox(JComboBox<String> c) {
        c.setBackground(COLOR_INPUT);
        c.setForeground(COLOR_TEXT_MAIN);
        c.setFont(GameSettings.getScaledFont("Segoe UI", Font.PLAIN, 16));
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
        c.setMaximumSize(new Dimension(Integer.MAX_VALUE, GameSettings.s(45)));
        c.setFocusable(false);
        
        c.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel l = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                l.setBorder(new EmptyBorder(5, 15, 5, 10));
                if (isSelected) {
                    l.setBackground(COLOR_ACCENT);
                    l.setForeground(Color.WHITE);
                } else {
                    l.setBackground(COLOR_INPUT);
                    l.setForeground(COLOR_TEXT_MAIN);
                }
                return l;
            }
        });
        c.setBorder(BorderFactory.createLineBorder(new Color(70, 75, 85), 1));
    }

    private void refreshTexts() {
        title.setText(L10n.GAME_TITLE);
        subtitle.setText(L10n.L_SUBTITLE);
        labelName.setText(L10n.L_NAME);
        labelLang.setText(L10n.L_LANG);
        settingsBtn.setText(L10n.L_SETTINGS);
        startBtn.setText(L10n.L_START);
    }

    private int getLangIndex() {
        if (L10n.PLAYER_NAME != null) {
            if (L10n.PLAYER_NAME.equals("Гравець")) return 0;
            if (L10n.PLAYER_NAME.equals("Player")) return 1;
        }
        return 2;
    }

    private JButton createVectorExitButton() {
        JButton btn = new JButton() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? Color.RED : COLOR_TEXT_DIM);
                g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                int p = 8;
                g2.drawLine(p, p, getWidth()-p, getHeight()-p);
                g2.drawLine(p, getHeight()-p, getWidth()-p, p);
                g2.dispose();
            }
        };
        btn.setPreferredSize(new Dimension(30, 30));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public boolean isStarted() { return started; }

    class BattleButton extends JButton {
        private boolean isPrimary;
        public BattleButton(String text, boolean isPrimary) {
            super(text);
            this.isPrimary = isPrimary;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(GameSettings.getScaledFont("Segoe UI", Font.BOLD, 16));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setAlignmentX(Component.LEFT_ALIGNMENT); // ТЕЖ ВЛІВО
            setMaximumSize(new Dimension(Integer.MAX_VALUE, GameSettings.s(isPrimary ? 55 : 48)));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            boolean hover = getModel().isRollover();
            if (isPrimary) {
                g2.setColor(hover ? COLOR_ACCENT.brighter() : COLOR_ACCENT);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
            } else {
                g2.setColor(new Color(255, 255, 255, hover ? 15 : 0));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.setColor(COLOR_ACCENT);
                g2.setStroke(new BasicStroke(2.0f));
                g2.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, 12, 12);
            }
            super.paintComponent(g);
            g2.dispose();
        }
    }
}