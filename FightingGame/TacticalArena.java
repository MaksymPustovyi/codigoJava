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

         // 1. Головне меню
        StartMenu menu = new StartMenu();
        if (!menu.isStarted()) System.exit(0);

        // 2. Правила бою
        RulesScreen rules = new RulesScreen(this);
        if (!rules.isReadyToFight()) {
            // Якщо гравець закрив правила, ми "знищуємо" це вікно гри.
            // Завдяки windowClosed слухачу в startAppCycle(), меню відкриється знову.
            this.dispose(); 
            return; // ПЕРЕРИВАЄМО конструктор, гра не почнеться
        }

        // 3. Якщо ми тут — гравець натиснув "Готовий", ініціалізуємо UI та бій
        ui = new ArenaUI(this);

        // 4. Позиції бійців (симетрія)
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        double logWidth = 450 * GameSettings.scaleX;
        double battlefieldWidth = screen.getWidth() - logWidth;
        double centerX = battlefieldWidth / 2.0;
        double gap = 350 * GameSettings.scale;

        player = new Combatant(GameSettings.playerName, centerX - gap - (75 * GameSettings.scale));
        ai = new Combatant(L10n.AI_NAME, centerX + gap - (75 * GameSettings.scale));

        // 5. Панель та менеджер
        battlefield = new BattlefieldPanel(player, ai, new EmojiRenderer(GameSettings.scale));
        manager = new BattleManager(player, ai, engine, ui, battlefield);
        battlefield.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                manager.handleMouseClick(e.getPoint());
                battlefield.repaint();
            }
        });

        getContentPane().add(battlefield, BorderLayout.CENTER);
        ui.gameSurface = battlefield;

        // ВИПРАВЛЕНО: Тепер при натисканні на хрестик Windows (якщо він буде)
        // або викликані dispose(), вікно просто знищується, а не вимикає програму
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        toFront();
        requestFocus();
    }

    // НОВЕ: Метод, який запускає гру і чекає на її закриття для перезапуску
    public static void startAppCycle() {
        TacticalArena game = new TacticalArena();

        // Додаємо слухача: коли вікно закривається (dispose), запускаємо метод знову
        game.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Викликаємо самі себе, щоб почати з StartMenu
                SwingUtilities.invokeLater(TacticalArena::startAppCycle);
            }
        });
    }

    public static void main(String[] args) {
        L10n.setLocale(1);
        // Запускаємо через наш новий метод циклу
        SwingUtilities.invokeLater(TacticalArena::startAppCycle);
    }
}