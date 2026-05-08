import javax.swing.*;
import java.awt.*;

class StartMenu extends JDialog {
    private JTextField nameField = new JTextField("Jugador", 15);
    private JComboBox<String> difficultyBox = new JComboBox<>(new String[]{"Fácil", "Normal", "Difícil"});
    private boolean started = false;

    public StartMenu() {
        setTitle("Tactical Arena - Inicio");
        setModal(true); // Блокує основне вікно, поки не натиснуть "Старт"
        setSize(500, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(25, 27, 33));

        // --- Блок ПРАВИЛА ---
        String rules = "<html><body style='padding:20px; color:white; font-family:sans-serif;'>"
            + "<h2 style='color:#82beff;'>Reglas de la Arena:</h2>"
            + "1. <b>Ataque:</b> Elige una parte del cuerpo del enemigo.<br>"
            + "2. <b>Evasión:</b> Elige una zona en ti para evitar el golpe.<br>"
            + "3. <b>Defensa:</b> Bloquea 2 zonas para reducir el daño un 20%.<br>"
            + "4. <b>Sangrado:</b> Golpes al pecho/abdomen causan daño constante.<br>"
            + "5. <b>Efectos:</b> Dañar brazos baja la fuerza, dañar piernas baja la evasión."
            + "</body></html>";
        add(new JLabel(rules), BorderLayout.CENTER);

        // --- Блок НАЛАШТУВАННЯ ---
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setOpaque(false);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Nombre:");
        nameLabel.setForeground(Color.WHITE);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JLabel diffLabel = new JLabel("Dificultad:");
        diffLabel.setForeground(Color.WHITE);
        inputPanel.add(diffLabel);
        inputPanel.add(difficultyBox);

        JButton startBtn = new JButton("¡A LA BATALLA!");
        startBtn.addActionListener(e -> {
            GameSettings.playerName = nameField.getText();
            // Вплив на ХП супротивника
            switch (difficultyBox.getSelectedIndex()) {
                case 0 -> GameSettings.aiHpModifier = 0.7f; // Fácil
                case 1 -> GameSettings.aiHpModifier = 1.0f; // Normal
                case 2 -> GameSettings.aiHpModifier = 1.5f; // Difícil
            }
            started = true;
            dispose();
        });
        
        add(inputPanel, BorderLayout.NORTH);
        add(startBtn, BorderLayout.SOUTH);
        setVisible(true);
    }

    public boolean isStarted() { return started; }
}