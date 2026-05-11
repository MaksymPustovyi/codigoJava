import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class StartMenu extends JDialog {
    private JTextField nameField;
    private JComboBox<String> langBox;
    private JLabel title, subtitle, labelName, labelLang;
    private JButton startBtn;
    private boolean started = false;

    private final Color COLOR_BG = new Color(25, 27, 33);
    private final Color COLOR_INPUT = new Color(45, 48, 56);
    private final Color COLOR_ACCENT = new Color(70, 130, 255);
    private final Color COLOR_TEXT_MAIN = new Color(240, 240, 240);
    private final Color COLOR_TEXT_DIM = new Color(160, 165, 175);

    public StartMenu() {
        setUndecorated(true); // Прибираємо системну рамку для сучасного вигляду
        setModal(true);
        setSize(400, 520);
        setLocationRelativeTo(null);
        
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBackground(COLOR_BG);
        root.setBorder(new EmptyBorder(10, 30, 40, 30));

        // --- ВЕРХНЯ ПАНЕЛЬ З КНОПКОЮ ВИХОДУ ---
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        topBar.setOpaque(false);
        topBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        JButton exitBtn = new JButton("✕");
        styleCloseButton(exitBtn);
        exitBtn.addActionListener(e -> System.exit(0));
        topBar.add(exitBtn);

        // Ініціалізація компонентів
        title = new JLabel(L10n.GAME_TITLE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(COLOR_ACCENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        subtitle = new JLabel(L10n.L_SUBTITLE);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(COLOR_TEXT_DIM);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelName = createFieldLabel(L10n.L_NAME);
        nameField = new JTextField("Warrior");
        styleInputField(nameField);

        labelLang = createFieldLabel(L10n.L_LANG);
        langBox = new JComboBox<>(new String[]{"Українська", "English", "Español"});
        styleComboBox(langBox);
        
        langBox.addActionListener(e -> {
            L10n.setLocale(langBox.getSelectedIndex());
            refreshTexts();
        });

        startBtn = new JButton(L10n.L_START);
        stylePrimaryButton(startBtn);
        startBtn.addActionListener(e -> {
            GameSettings.playerName = nameField.getText().trim();
            if (GameSettings.playerName.isEmpty()) GameSettings.playerName = "Hero";
            started = true;
            dispose();
        });

        // Комнування
        root.add(topBar);
        root.add(Box.createRigidArea(new Dimension(0, 10)));
        root.add(title);
        root.add(Box.createRigidArea(new Dimension(0, 5)));
        root.add(subtitle);
        root.add(Box.createRigidArea(new Dimension(0, 40)));
        root.add(labelName);
        root.add(Box.createRigidArea(new Dimension(0, 8)));
        root.add(nameField);
        root.add(Box.createRigidArea(new Dimension(0, 30)));
        root.add(labelLang);
        root.add(Box.createRigidArea(new Dimension(0, 8)));
        root.add(langBox);
        root.add(Box.createVerticalGlue());
        root.add(startBtn);

        add(root);
        langBox.setSelectedIndex(1);
        L10n.setLocale(1);
        refreshTexts();
        setVisible(true);
    }

    private void styleCloseButton(JButton btn) {
        btn.setFont(new Font("Dialog", Font.BOLD, 18));
        btn.setForeground(COLOR_TEXT_DIM);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setForeground(Color.RED); }
            public void mouseExited(MouseEvent e) { btn.setForeground(COLOR_TEXT_DIM); }
        });
    }

    private void refreshTexts() {
        title.setText(L10n.GAME_TITLE);
        subtitle.setText(L10n.L_SUBTITLE);
        labelName.setText(L10n.L_NAME);
        labelLang.setText(L10n.L_LANG);
        startBtn.setText(L10n.L_START);
    }

    private JLabel createFieldLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.BOLD, 12));
        l.setForeground(COLOR_TEXT_DIM);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private void styleInputField(JTextField field) {
        field.setBackground(COLOR_INPUT);
        field.setForeground(COLOR_TEXT_MAIN);
        field.setCaretColor(COLOR_ACCENT);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_INPUT, 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
    }

    private void styleComboBox(JComboBox<String> combo) {
        combo.setBackground(COLOR_INPUT);
        combo.setForeground(COLOR_TEXT_MAIN);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        combo.setFocusable(false);
        combo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    private void stylePrimaryButton(JButton btn) {
        btn.setBackground(COLOR_ACCENT);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public boolean isStarted() { return started; }
}