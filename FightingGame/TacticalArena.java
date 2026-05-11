import javax.swing.*;
import java.awt.event.*; // Стандартний Java AWT пакет для подій миші
import java.awt.*;
import java.awt.geom.*;

// ==========================================
// 7. ГОЛОВНЕ ВІКНО ТА UI
// ==========================================
public class TacticalArena extends JFrame {
    private Combatant player, ai;
    private BattleEngine engine = new BattleEngine();
    private EmojiRenderer emojiRenderer;
    private JTextArea detailedLog = new JTextArea();
    private JLabel shortLog = new JLabel(L10n.STEP_1_ATK, SwingConstants.CENTER);
    private int step = 0;

    public TacticalArena() {
        // 1. Ініціалізація базових речей
        GameSettings.initScaling();

        // 2. Виклик меню ПЕРЕД створенням бійців
        StartMenu menu = new StartMenu();
        if (!menu.isStarted())
            System.exit(0); // Закрити, якщо меню просто закрили

        // 3. Тепер ініціалізуємо бійців з урахуванням L10n та імені
        this.emojiRenderer = new EmojiRenderer(GameSettings.scale);
        player = new Combatant(GameSettings.playerName, 300); // Використовуємо введений Name
        ai = new Combatant(L10n.AI_NAME, 1200);

        setTitle(L10n.GAME_TITLE);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(25, 27, 33));
        setLayout(new BorderLayout());

        setupUI();
        setVisible(true);
    }

    private void runTurn() {
        // 1. Комп'ютер обирає свої точки
        ai.attackTarget = BodyPartType.values()[(int) (Math.random() * 7)];
        ai.evasionPoint = BodyPartType.values()[(int) (Math.random() * 7)];
        ai.defensePoints.clear();
        while (ai.defensePoints.size() < 2)
            ai.defensePoints.add(BodyPartType.values()[(int) (Math.random() * 7)]);

        // 2. Лог раунду
        detailedLog.append(String.format(L10n.LOG_ROUND, engine.getRound()));

        // 3. Розрахунок кровотечі
        engine.applyBleeding(player, detailedLog);
        engine.applyBleeding(ai, detailedLog);

        // 4. Гравець атакує AI
        String playerAtkResult = engine.processAttack(player, ai, true, detailedLog);

        // 5. AI атакує Гравця
        String aiAtkResult = engine.processAttack(ai, player, false, detailedLog);

        player.lastReceivedHit = ai.attackTarget;

        // Оновлення короткого лога (верхня мітка)
        shortLog.setText("<html><center>" + playerAtkResult + "<br>" + aiAtkResult + "</center></html>");

        detailedLog.setEditable(false);
        detailedLog.setLineWrap(true); // Вмикає перенос слів
        detailedLog.setWrapStyleWord(true); // Переносить цілі слова
        
        // Важливо: Прокрутка лога вниз, щоб бачити нові записи
        detailedLog.setCaretPosition(detailedLog.getDocument().getLength());

        // 6. Збереження виборів для відображення емодзі на наступний хід
        saveChoices();
        engine.incrementRound();
        step = 0; // Повернення до вибору атаки

        if (player.globalHp <= 0 || ai.globalHp <= 0) {
            JOptionPane.showMessageDialog(this, player.globalHp > 0 ? L10n.WIN : L10n.LOSS);
            System.exit(0);
        }
    }

    private void saveChoices() {
        player.lastAtk = player.attackTarget;
        player.lastEva = player.evasionPoint;
        player.lastDef.clear();
        player.lastDef.addAll(player.defensePoints);

        ai.lastEva = ai.evasionPoint;
        ai.lastDef.clear();
        ai.lastDef.addAll(ai.defensePoints);

        // Очищення для нового вибору
        player.attackTarget = null;
        player.evasionPoint = null;
        player.defensePoints.clear();
        ai.defensePoints.clear();
    }

    private void resetVisuals() {
        // Очищаємо "пам'ять" минулого ходу при першому кліку нового
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

    private void setupUI() {
        // 1. Забороняємо перенесення рядків у верхньому лозі за допомогою CSS
    shortLog.setFont(new Font("Segoe UI Semibold", Font.PLAIN, (int) (24 * GameSettings.scale)));
    shortLog.setForeground(new Color(130, 190, 255));
    shortLog.setPreferredSize(new Dimension(0, (int) (120 * GameSettings.scaleY)));
    add(shortLog, BorderLayout.NORTH);

        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
                g2.setColor(new Color(25, 27, 33));
                g2.fillRect(0, 0, getWidth(), getHeight());
                drawCombatant(g2, player, true);
                drawCombatant(g2, ai, false);
            }
        };
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouse(e.getPoint());
                repaint();
            }
        });
        add(gamePanel, BorderLayout.CENTER);

        detailedLog.setEditable(false);
        detailedLog.setBackground(new Color(15, 15, 20));
        detailedLog.setForeground(new Color(180, 190, 210));
        detailedLog.setFont(new Font("Monospaced", Font.PLAIN, (int) (16 * GameSettings.scale)));
        JScrollPane scroll = new JScrollPane(detailedLog);
        scroll.setPreferredSize(new Dimension((int) (480 * GameSettings.scaleX), 0));
        add(scroll, BorderLayout.EAST);
    }

    private void handleMouse(Point p) {
        if (step == 0) {
            for (BodyPart bp : ai.parts.values())
                if (bp.bounds.contains(p)) {
                    resetVisuals();
                    player.attackTarget = bp.type;
                    step = 1;
                    shortLog.setText(L10n.STEP_2_EVA);
                    return;
                }
        } else {
            for (BodyPart bp : player.parts.values())
                if (bp.bounds.contains(p)) {
                    if (step == 1) {
                        player.evasionPoint = bp.type;
                        step = 2;
                        shortLog.setText(L10n.STEP_3_DEF);
                    } else if (step == 2) {
                        player.defensePoints.add(bp.type);
                        step = 3;
                        shortLog.setText(L10n.STEP_4_DEF);
                    } else if (step == 3 && !player.defensePoints.contains(bp.type)) {
                        player.defensePoints.add(bp.type);
                        repaint();
                        // Робимо мікро-затримку або просто викликаємо логіку через SwingUtilities,
                        // щоб UI не "фрізився" до оновлення
                        SwingUtilities.invokeLater(() -> {
                            try {
                                Thread.sleep(50); // Маленька пауза для візуального підтвердження вибору
                            } catch (InterruptedException ex) {
                            }
                            runTurn();
                            repaint();
                        });
                    }
                    return;
                }
        }
    }

    private void drawCombatant(Graphics2D g, Combatant c, boolean isPlayerSide) {
        double headX = c.parts.get(BodyPartType.HEAD).bounds.getX();
        double headY = c.parts.get(BodyPartType.HEAD).bounds.getY();
        double s = GameSettings.scale;

        double hbW = 300 * s, hbH = 20 * s;
        double hbX = headX - (hbW / 2) + (c.parts.get(BodyPartType.HEAD).bounds.getWidth() / 2);
        double hbY = headY - (220 * GameSettings.scaleY);

        float bleed = c.getBleedSum();
        float healthAfterBleed = Math.max(0, c.globalHp - bleed);

        g.setColor(new Color(40, 42, 50));
        g.fill(new RoundRectangle2D.Double(hbX, hbY, hbW, hbH, 12, 12));
        g.setColor(new Color(50, 215, 100));
        g.fill(new RoundRectangle2D.Double(hbX, hbY, hbW * (healthAfterBleed / GameSettings.BASE_GLOBAL_HP), hbH, 12,
                12));
        if (bleed > 0) {
            g.setColor(new Color(255, 75, 45));
            g.fill(new Rectangle2D.Double(hbX + hbW * (healthAfterBleed / GameSettings.BASE_GLOBAL_HP), hbY,
                    hbW * (bleed / GameSettings.BASE_GLOBAL_HP), hbH));
        }
        g.setColor(Color.WHITE);
        g.draw(new RoundRectangle2D.Double(hbX, hbY, hbW, hbH, 12, 12));

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

        for (BodyPart part : c.parts.values()) {
            Shape shape = (part.type == BodyPartType.HEAD)
                    ? new Ellipse2D.Double(part.bounds.getX(), part.bounds.getY(), part.bounds.getWidth(),
                            part.bounds.getHeight())
                    : new RoundRectangle2D.Double(part.bounds.getX(), part.bounds.getY(), part.bounds.getWidth(),
                            part.bounds.getHeight(), 25 * s, 25 * s);

            g.setColor(new Color(55, 58, 65));
            g.fill(shape);
            g.setColor(new Color(45, 200, 95, 110));
            double hpFill = part.bounds.getHeight() * (part.hp / 100f);
            g.setClip(new Rectangle2D.Double(part.bounds.getX(),
                    part.bounds.getY() + (part.bounds.getHeight() - hpFill), part.bounds.getWidth(), hpFill));
            g.fill(shape);
            g.setClip(null);
            g.setColor(new Color(100, 105, 115));
            g.draw(shape);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, (int) (14 * s)));
            String hpTxt = (int) part.hp + "%";
            FontMetrics fm = g.getFontMetrics();
            g.drawString(hpTxt, (int) (part.bounds.getCenterX() - fm.stringWidth(hpTxt) / 2),
                    (int) (part.bounds.getMaxY() - 10 * GameSettings.scaleY));

            // ВИКЛИК НОВОГО КЛАСУ ВІДОБРАЖЕННЯ
            emojiRenderer.render(g, part, c, isPlayerSide, step, player);
        }
    }

    public static void main(String[] args) {
        // Спочатку ініціалізація за замовчуванням (щоб не було null)
        L10n.setLocale(0);
        SwingUtilities.invokeLater(TacticalArena::new);
    }
}