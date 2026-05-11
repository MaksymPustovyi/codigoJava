import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TacticalArena extends JFrame {
    private Combatant player, ai;
    private BattleEngine engine = new BattleEngine();
    private BattleManager manager;
    private BattlefieldPanel battlefield;
    private ArenaUI ui;

    public TacticalArena() {
        GameSettings.initScaling();
        StartMenu menu = new StartMenu();
        if (!menu.isStarted()) System.exit(0);

        // 1. Створюємо UI (Header, Side Log)
        ui = new ArenaUI(this);

        // 2. Розрахунок симетричних позицій
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        double battlefieldWidth = screen.getWidth() - (480 * GameSettings.scaleX);
        double centerX = battlefieldWidth / 2.0;
        double gap = 350 * GameSettings.scale;

        player = new Combatant(GameSettings.playerName, centerX - gap - (75 * GameSettings.scale));
        ai = new Combatant(L10n.AI_NAME, centerX + gap - (75 * GameSettings.scale));

        // 3. Створюємо Графічну панель
        battlefield = new BattlefieldPanel(player, ai, new EmojiRenderer(GameSettings.scale));
        
        // 4. Створюємо Менеджер Логіки
        manager = new BattleManager(player, ai, engine, ui, battlefield);

        // 5. Підключаємо керування
        battlefield.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                manager.handleMouseClick(e.getPoint());
                battlefield.repaint();
            }
        });

        // Додаємо панель у фрейм
        getContentPane().add(battlefield, BorderLayout.CENTER);
        ui.gameSurface = battlefield;

        setTitle(L10n.GAME_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        L10n.setLocale(0);
        SwingUtilities.invokeLater(TacticalArena::new);
    }
}