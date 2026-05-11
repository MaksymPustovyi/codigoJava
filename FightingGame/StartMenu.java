import javax.swing.*;
import java.awt.*;

class StartMenu extends JDialog {
    private JTextField nameField = new JTextField("Player", 15);
    private JComboBox<String> langBox = new JComboBox<>(new String[]{"Українська", "English", "Español"});
    private boolean started = false;

    public StartMenu() {
        setTitle("Tactical Arena - Setup");
        setModal(true);
        setSize(400, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(25, 27, 33));

        // Панель вводу
        JPanel p = new JPanel(new GridLayout(4, 1, 10, 10));
        p.setOpaque(false);
        p.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nl = new JLabel("Enter Name:"); nl.setForeground(Color.WHITE);
        p.add(nl); p.add(nameField);
        
        JLabel ll = new JLabel("Select Language:"); ll.setForeground(Color.WHITE);
        p.add(ll); p.add(langBox);

        JButton btn = new JButton("START GAME");
        btn.setFont(new Font("Arial", Font.BOLD, 18));
        btn.setBackground(new Color(50, 150, 255));
        btn.setForeground(Color.WHITE);

        btn.addActionListener(e -> {
            GameSettings.playerName = nameField.getText().trim();
            if(GameSettings.playerName.isEmpty()) GameSettings.playerName = "Hero";
            L10n.setLocale(langBox.getSelectedIndex()); // Встановлюємо мову
            started = true;
            dispose();
        });

        add(p, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);
        setVisible(true);
    }
    public boolean isStarted() { return started; }
}