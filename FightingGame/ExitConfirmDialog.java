import javax.swing.*;
import java.awt.*;

public class ExitConfirmDialog extends JDialog {
    private final Color COLOR_BG = new Color(25, 27, 33);
    private final Color COLOR_ACCENT = new Color(70, 130, 255);
    private boolean confirmed = false;

    public ExitConfirmDialog(JFrame owner) {
        super(owner, true);
        setUndecorated(true);
        setSize(GameSettings.s(450), GameSettings.s(220));
        setLocationRelativeTo(owner);

        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBackground(COLOR_BG);
        root.setBorder(BorderFactory.createLineBorder(new Color(60, 64, 75), 2));

        JLabel title = new JLabel(L10n.L_CONFIRM_TITLE);
        title.setFont(GameSettings.getScaledFont("Segoe UI", Font.BOLD, 14));
        title.setForeground(COLOR_ACCENT);
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel message = new JLabel(L10n.L_CONFIRM_MSG);
        message.setFont(GameSettings.getScaledFont("Segoe UI", Font.PLAIN, 16));
        message.setForeground(Color.WHITE);
        message.setAlignmentX(CENTER_ALIGNMENT);

        // Кнопки
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, GameSettings.s(20), 0));
        btnPanel.setOpaque(false);

        JButton btnYes = new JButton(L10n.L_YES);
        styleBtn(btnYes, new Color(255, 60, 60)); // Червона для виходу
        btnYes.addActionListener(e -> { confirmed = true; dispose(); });

        JButton btnNo = new JButton(L10n.L_NO);
        styleBtn(btnNo, new Color(100, 100, 100)); // Сіра для відміни
        btnNo.addActionListener(e -> { confirmed = false; dispose(); });

        btnPanel.add(btnNo);
        btnPanel.add(btnYes);

        root.add(Box.createRigidArea(new Dimension(0, GameSettings.s(20))));
        root.add(title);
        root.add(Box.createRigidArea(new Dimension(0, GameSettings.s(15))));
        root.add(message);
        root.add(Box.createRigidArea(new Dimension(0, GameSettings.s(30))));
        root.add(btnPanel);

        add(root);
        setVisible(true);
    }

    private void styleBtn(JButton b, Color baseColor) {
        b.setFont(GameSettings.getScaledFont("Segoe UI", Font.BOLD, 13));
        b.setForeground(Color.WHITE);
        b.setBackground(baseColor);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setPreferredSize(new Dimension(GameSettings.s(150), GameSettings.s(40)));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public boolean isConfirmed() { return confirmed; }
}