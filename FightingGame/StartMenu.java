import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class StartMenu extends JDialog {
    private JTextField nameField;
    private JComboBox<String> langBox;
    private boolean started = false;

    // Сучасна палітра кольорів
    private final Color COLOR_BG = new Color(25, 27, 33);
    private final Color COLOR_INPUT = new Color(45, 48, 56);
    private final Color COLOR_ACCENT = new Color(70, 130, 255);
    private final Color COLOR_TEXT_MAIN = new Color(240, 240, 240);
    private final Color COLOR_TEXT_DIM = new Color(160, 165, 175);

    public StartMenu() {
        setTitle("Tactical Arena - Setup");
        setModal(true);
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Головний контейнер
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBackground(COLOR_BG);
        root.setBorder(new EmptyBorder(40, 40, 40, 40));

        // --- ЗАГОЛОВОК ---
        JLabel title = new JLabel("TACTICAL ARENA");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(COLOR_ACCENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Enter the battlefield");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(COLOR_TEXT_DIM);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- ПОЛЕ ІМЕНІ ---
        JLabel labelName = createFieldLabel("PLAYER NAME");
        nameField = new JTextField("Warrior");
        styleInputField(nameField);

        // --- ВИБІР МОВИ ---
        JLabel labelLang = createFieldLabel("LANGUAGE");
        langBox = new JComboBox<>(new String[]{"Українська", "English", "Español"});
        styleComboBox(langBox);

        // --- КНОПКА ЗАПУСКУ ---
        JButton startBtn = new JButton("START GAME");
        stylePrimaryButton(startBtn);
        startBtn.addActionListener(e -> {
            GameSettings.playerName = nameField.getText().trim();
            if (GameSettings.playerName.isEmpty()) GameSettings.playerName = "Hero";
            L10n.setLocale(langBox.getSelectedIndex());
            started = true;
            dispose();
        });

        // Додавання елементів з відступами
        root.add(title);
        root.add(Box.createRigidArea(new Dimension(0, 5)));
        root.add(subtitle);
        root.add(Box.createRigidArea(new Dimension(0, 50)));

        root.add(labelName);
        root.add(Box.createRigidArea(new Dimension(0, 8)));
        root.add(nameField);
        root.add(Box.createRigidArea(new Dimension(0, 30)));

        root.add(labelLang);
        root.add(Box.createRigidArea(new Dimension(0, 8)));
        root.add(langBox);

        root.add(Box.createVerticalGlue()); // Штовхає кнопку вниз
        root.add(startBtn);

        add(root);
        
        // Гарантуємо, що вікно закриється коректно при натисканні на хрестик
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!started) System.exit(0);
            }
        });

        setVisible(true);
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
        // Прибираємо стандартну рамку
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

        // Ефект наведення
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(COLOR_ACCENT.brighter()); }
            public void mouseExited(MouseEvent e) { btn.setBackground(COLOR_ACCENT); }
        });
    }

    public boolean isStarted() {
        return started;
    }
}