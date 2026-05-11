import javax.swing.*;
import java.awt.*;

public class StartMenu extends JDialog {
    private JTextField nameField = new JTextField("Player", 15);
    private JComboBox<String> langBox = new JComboBox<>(new String[]{"Українська", "English", "Español"});
    private boolean started = false;

    public StartMenu() {
        setTitle("Tactical Arena - Setup"); setModal(true); setSize(400, 300);
        setLocationRelativeTo(null); setLayout(new GridLayout(4, 1, 10, 10));
        getContentPane().setBackground(new Color(25, 27, 33));

        add(new JLabel("Name:") {{ setForeground(Color.WHITE); }}); add(nameField);
        add(new JLabel("Language:") {{ setForeground(Color.WHITE); }}); add(langBox);
        
        JButton btn = new JButton("START");
        btn.addActionListener(e -> {
            GameSettings.playerName = nameField.getText();
            L10n.setLocale(langBox.getSelectedIndex());
            started = true; dispose();
        });
        add(btn); setVisible(true);
    }
    public boolean isStarted() { return started; }
}