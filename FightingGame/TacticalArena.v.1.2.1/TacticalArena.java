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
        setUndecorated(true);
        GameSettings.initScaling();

        // 1. Послідовність меню
        StartMenu menu = new StartMenu();
        if (!menu.isStarted()) System.exit(0);

        RulesScreen rules = new RulesScreen(this);
        if (!rules.isReadyToFight()) {
            this.dispose(); 
            return;
        }

        // 2. Ініціалізація UI
        ui = new ArenaUI(this);
        ui.shortLog.setText("<html><center>" + L10n.STEP_1_ATK + "</center></html>");

        // 3. Розрахунок позицій
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double logWidth = 450 * GameSettings.scaleX;
        double battlefieldWidth = screenSize.getWidth() - logWidth;
        double centerX = battlefieldWidth / 2.0;
        double gap = 350 * GameSettings.scale;

        player = new Combatant(GameSettings.playerName, centerX - gap - (75 * GameSettings.scale));
        ai = new Combatant(L10n.AI_NAME, centerX + gap - (75 * GameSettings.scale));

        // 4. Панель та менеджер
        battlefield = new BattlefieldPanel(player, ai, new EmojiRenderer(GameSettings.scale));
        manager = new BattleManager(player, ai, engine, ui, battlefield);
        
        battlefield.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                manager.handleMouseClick(e.getPoint());
                battlefield.repaint();
            }
        });

        // 5. ВАЖЛИВО: Додаємо поле бою в ЦЕНТР контейнера UI, а не фрейму
        ui.mainContainer.add(battlefield, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        
        toFront();
        requestFocus();
    }

    public static void startAppCycle() {
        TacticalArena game = new TacticalArena();
        game.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                SwingUtilities.invokeLater(TacticalArena::startAppCycle);
            }
        });
    }

    public static void main(String[] args) {
        L10n.setLocale(1);
        SwingUtilities.invokeLater(TacticalArena::startAppCycle);
    }
}