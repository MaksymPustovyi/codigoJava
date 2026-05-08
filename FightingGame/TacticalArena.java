import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

/* ------------------
7. ГОЛОВНЕ ВІКНО ТА UI
------------------ */
public class TacticalArena extends JFrame {
    private Combatant player, ai; // Учасники бою
    private BattleEngine engine = new BattleEngine(); // Логіка розрахунків
    private EmojiRenderer emojiRenderer; // Відрисовка іконок (щити, мечі)
    private JTextArea detailedLog = new JTextArea(); // Великий лог подій праворуч
    private JLabel shortLog = new JLabel(L10n.STEP_1_ATK, SwingConstants.CENTER); // Підказки зверху
    private int step = 0; // Поточний крок ходу гравця: 0-атака, 1-ухилення, 2,3-захист

    public TacticalArena() {
        GameSettings.initScaling(this);
        this.emojiRenderer = new EmojiRenderer(GameSettings.scale);

        int playerX = (int) (GameSettings.REF_WIDTH * 0.15);
        int aiX = playerX + (int) (GameSettings.REF_WIDTH * 0.30);

        // Використовуємо дані з меню
        player = new Combatant(GameSettings.playerName, playerX);
        ai = new Combatant(L10n.AI_NAME, aiX);

        // Застосовуємо складність до ворога
        ai.globalHp = GameSettings.BASE_GLOBAL_HP * GameSettings.aiHpModifier;

        setTitle(L10n.GAME_TITLE);
        setExtendedState(MAXIMIZED_BOTH); // Вікно на весь екран
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(25, 27, 33));
        setLayout(new BorderLayout());

        setupUI();
        setVisible(true);
    }

    /*
     * ------------------
     * Створення та розміщення елементів інтерфейсу
     * ------------------
     */
    private void setupUI() {
        // Налаштування верхньої панелі підказок
        shortLog.setFont(new Font("Segoe UI Semibold", Font.PLAIN, (int) (26 * GameSettings.scale)));
        shortLog.setForeground(new Color(130, 190, 255));
        shortLog.setPreferredSize(new Dimension(0, (int) (120 * GameSettings.scaleY)));
        add(shortLog, BorderLayout.NORTH);

        // Головна ігрова панель (де малюються силуети)
        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                // Включення згладжування для гарної графіки
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(25, 27, 33));
                g2.fillRect(0, 0, getWidth(), getHeight());

                drawCombatant(g2, player, true); // Малюємо гравця (зліва)
                drawCombatant(g2, ai, false); // Малюємо ворога (справа)
            }
        };

        // Обробка кліків миші по частинах тіла
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouse(e.getPoint());
                repaint();
            }
        });
        add(gamePanel, BorderLayout.CENTER);

        // Налаштування правого текстового лога
        detailedLog.setEditable(false);
        detailedLog.setBackground(new Color(15, 15, 20));
        detailedLog.setForeground(new Color(180, 190, 210));
        detailedLog.setFont(new Font("Monospaced", Font.PLAIN, (int) (16 * GameSettings.scale)));
        JScrollPane scroll = new JScrollPane(detailedLog);
        scroll.setPreferredSize(new Dimension((int) (480 * GameSettings.scaleX), 0));
        add(scroll, BorderLayout.EAST);
    }

    /*
     * ------------------
     * Логіка вибору цілей мишкою та запуску ходу
     * ------------------
     */
    private void handleMouse(Point p) {
        if (step == 0) {
            // Крок 0: Вибір точки атаки на тілі ВОРОГА
            for (BodyPart bp : ai.parts.values())
                if (bp.bounds.contains(p)) {
                    resetVisuals();
                    player.attackTarget = bp.type;
                    step = 1;
                    shortLog.setText(L10n.STEP_2_EVA);
                    return;
                }
        } else {
            // Крок 1-3: Вибір точок захисту/ухилення на тілі ГРАВЦЯ
            for (BodyPart bp : player.parts.values())
                if (bp.bounds.contains(p)) {
                    // Крок 1: вибір ухилення
                    if (step == 1) {
                        player.evasionPoint = bp.type;
                        step = 2;
                        shortLog.setText(L10n.STEP_3_DEF);
                    }
                    // Крок 2: перший блок
                    else if (step == 2) {
                        player.defensePoints.add(bp.type);
                        step = 3;
                        shortLog.setText(L10n.STEP_4_DEF);
                    }
                    // Крок 3: другий блок (можна вибрати ту ж частину або іншу)
                    else if (step == 3 && !player.defensePoints.contains(bp.type)) {
                        player.defensePoints.add(bp.type);
                        runTurn(); // Всі вибори зроблено — запускаємо розрахунок ходу
                    }
                    return;
                }
        }
    }

    /*
     * ------------------
     * Розрахунок результатів раунду після вибору атакуючого та захисних точок
     * ------------------
     */
    private void runTurn() {
        // Логіка ШІ: рандомний вибір цілей атаки, ухилення та захисту
        ai.attackTarget = BodyPartType.values()[(int) (Math.random() * 7)];
        ai.evasionPoint = BodyPartType.values()[(int) (Math.random() * 7)];
        while (ai.defensePoints.size() < 2)
            ai.defensePoints.add(BodyPartType.values()[(int) (Math.random() * 7)]);

        detailedLog.append(String.format(L10n.LOG_ROUND, engine.getRound()));

        // Обробка періодичної шкоди (кровотеча)
        engine.applyBleeding(player, detailedLog);
        engine.applyBleeding(ai, detailedLog);

        // Виконання атак
        String pRes = engine.processAttack(player, ai, true, detailedLog);
        String aRes = engine.processAttack(ai, player, false, detailedLog);

        player.lastReceivedHit = ai.attackTarget;
        shortLog.setText("<html><center>" + pRes + "<br>" + aRes + "</center></html>");

        // Прокрутка лога вниз
        detailedLog.setCaretPosition(detailedLog.getDocument().getLength());
        saveChoices(); // Зберігаємо вибори для відмалювання емодзі
        engine.incrementRound();
        step = 0; // Скидаємо фазу ходу

        // Перевірка на перемогу/поразку
        if (player.globalHp <= 0 || ai.globalHp <= 0) {
            JOptionPane.showMessageDialog(this, player.globalHp > 0 ? L10n.WIN : L10n.LOSS);
            System.exit(0);
        }
    }

    /*
     * ------------------
     * Очищення візуальних маркерів перед новим ходом
     * ------------------
     */
    private void resetVisuals() {
        player.lastAtk = null;
        player.lastEva = null;
        player.lastDef.clear();
        player.lastReceivedHit = null;
        ai.lastHitWasGuarded = false;
        ai.lastHitWasEvaded = false;
        ai.lastAtk = null;
        ai.lastEva = null;
        ai.lastDef.clear();
    }

    /*
     * ------------------
     * Збереження останніх дій для відображення іконок у фазі вибору наступного ходу
     * ------------------
     */
    private void saveChoices() {
        player.lastAtk = player.attackTarget;
        player.lastEva = player.evasionPoint;
        player.lastDef.addAll(player.defensePoints);
        ai.lastEva = ai.evasionPoint;
        ai.lastDef.addAll(ai.defensePoints);

        // Скидаємо поточні вибори для нового ходу
        player.attackTarget = null;
        player.evasionPoint = null;
        player.defensePoints.clear();
        ai.defensePoints.clear();
    }

    /*
     * ------------------
     * Відображення персонажа на полі
     * ------------------
     */
    private void drawCombatant(Graphics2D g, Combatant c, boolean isPlayerSide) {
        double headX = c.parts.get(BodyPartType.HEAD).bounds.getX();
        double headY = c.parts.get(BodyPartType.HEAD).bounds.getY();
        double s = GameSettings.scale;

        // Координати смужки здоров'я та показників над головою
        double hbW = 300 * s, hbH = 20 * s;
        double hbX = headX - (hbW / 2) + (c.parts.get(BodyPartType.HEAD).bounds.getWidth() / 2);
        double hbY = headY - (220 * GameSettings.scaleY);

        // Розрахунок HP з урахуванням майбутньої шкоди від кровотечі
        float bleed = c.getBleedSum();
        float healthAfterBleed = Math.max(0, c.globalHp - bleed);

        // Малюємо фон ХП-бару
        g.setColor(new Color(40, 42, 50));
        g.fill(new RoundRectangle2D.Double(hbX, hbY, hbW, hbH, 12, 12));

        // Малюємо зелене здоров'я
        g.setColor(new Color(50, 215, 100));
        g.fill(new RoundRectangle2D.Double(hbX, hbY, hbW * (healthAfterBleed / GameSettings.BASE_GLOBAL_HP), hbH, 12,
                12));

        // Малюємо червону зону кровотечі на ХП-барі
        if (bleed > 0) {
            g.setColor(new Color(255, 75, 45));
            g.fill(new Rectangle2D.Double(hbX + hbW * (healthAfterBleed / GameSettings.BASE_GLOBAL_HP), hbY,
                    hbW * (bleed / GameSettings.BASE_GLOBAL_HP), hbH));
        }

        // Рамка ХП-бару
        g.setColor(Color.WHITE);
        g.draw(new RoundRectangle2D.Double(hbX, hbY, hbW, hbH, 12, 12));

        // Малювання тексту статсів (ім'я, кровотеча, точність, ухилення, сила)
        g.setFont(new Font("Segoe UI Semibold", Font.PLAIN, (int) (22 * s)));
        g.drawString(c.name, (int) hbX, (int) hbY - (int) (15 * GameSettings.scaleY));
        g.setFont(new Font("Segoe UI", Font.PLAIN, (int) (17 * s)));
        g.setColor(new Color(200, 205, 220));
        int dy = (int) (28 * GameSettings.scaleY);
        g.drawString(L10n.HP_BLEEDING + String.format("%.1f", bleed), (int) hbX,
                (int) hbY + (int) (48 * GameSettings.scaleY));
        g.drawString(L10n.ACCURACY + (int) (c.getAccuracy() * 100) + "%", (int) hbX,
                (int) hbY + (int) (48 * GameSettings.scaleY) + dy);
        g.drawString(L10n.EVASION + (int) (c.getEvasionChance() * 100) + "%", (int) hbX,
                (int) hbY + (int) (48 * GameSettings.scaleY) + dy * 2);
        g.setColor(new Color(255, 185, 80));
        g.drawString(L10n.POWER + (int) c.getPower() + "%", (int) hbX,
                (int) hbY + (int) (48 * GameSettings.scaleY) + dy * 3);

        // Малювання кожної частини тіла окремо
        for (BodyPart part : c.parts.values()) {
            Shape shape = (part.type == BodyPartType.HEAD)
                    ? new Ellipse2D.Double(part.bounds.getX(), part.bounds.getY(), part.bounds.getWidth(),
                            part.bounds.getHeight())
                    : new RoundRectangle2D.Double(part.bounds.getX(), part.bounds.getY(), part.bounds.getWidth(),
                            part.bounds.getHeight(), 25 * s, 25 * s);

            // Заливка силуету
            g.setColor(new Color(55, 58, 65));
            g.fill(shape);

            // "Заповнення" частини тіла кольором відповідно до її залишку HP (ефект рідини)
            g.setColor(new Color(45, 200, 95, 110));
            double hpFill = part.bounds.getHeight() * (part.hp / 100f);
            g.setClip(new Rectangle2D.Double(part.bounds.getX(),
                    part.bounds.getY() + (part.bounds.getHeight() - hpFill), part.bounds.getWidth(), hpFill));
            g.fill(shape);
            g.setClip(null);

            // Обводка частини тіла
            g.setColor(new Color(100, 105, 115));
            g.draw(shape);

            // Відображення % здоров'я на самій частині тіла
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, (int) (14 * s)));
            String hpTxt = (int) part.hp + "%";
            FontMetrics fm = g.getFontMetrics();
            g.drawString(hpTxt, (int) (part.bounds.getCenterX() - fm.stringWidth(hpTxt) / 2),
                    (int) (part.bounds.getMaxY() - 10 * GameSettings.scaleY));

            // ВІДОБРАЖЕННЯ ЕМОДЗІ (щити, мечі, біг) за допомогою EmojiRenderer
            emojiRenderer.render(g, part, c, isPlayerSide, step, player);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartMenu menu = new StartMenu();
            if (menu.isStarted()) {
                new TacticalArena();
            } else {
                System.exit(0);
            }
        });
    }
}