import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class BattlefieldPanel extends JPanel {
    private Combatant player, ai;
    private EmojiRenderer emojiRenderer;
    private int currentStep = 0;

    public BattlefieldPanel(Combatant player, Combatant ai, EmojiRenderer emojiRenderer) {
        this.player = player;
        this.ai = ai;
        this.emojiRenderer = emojiRenderer;
        setOpaque(true);
    }

    public void setStep(int step) { 
        this.currentStep = step; 
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // ФОН
        GradientPaint gp = new GradientPaint(0, 0, new Color(25, 27, 33), 0, getHeight(), new Color(15, 16, 20));
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());

        drawCombatant(g2, player, true);
        drawCombatant(g2, ai, false);
    }

    private void drawCombatant(Graphics2D g, Combatant c, boolean isPlayerSide) {
        double headX = c.parts.get(BodyPartType.HEAD).bounds.getX();
        double headY = c.parts.get(BodyPartType.HEAD).bounds.getY();
        double s = GameSettings.scale;

        // HP Bar
        double hbW = 300 * s, hbH = 20 * s;
        double hbX = headX - (hbW / 2) + (c.parts.get(BodyPartType.HEAD).bounds.getWidth() / 2);
        double hbY = headY - (220 * GameSettings.scaleY);

        float bleed = c.getBleedSum();
        float healthAfterBleed = Math.max(0, c.globalHp - bleed);

        g.setColor(new Color(40, 42, 50));
        g.fill(new RoundRectangle2D.Double(hbX, hbY, hbW, hbH, 12, 12));
        g.setColor(new Color(50, 215, 100));
        g.fill(new RoundRectangle2D.Double(hbX, hbY, hbW * (healthAfterBleed / GameSettings.BASE_GLOBAL_HP), hbH, 12, 12));
        if (bleed > 0) {
            g.setColor(new Color(255, 75, 45));
            g.fill(new Rectangle2D.Double(hbX + hbW * (healthAfterBleed / GameSettings.BASE_GLOBAL_HP), hbY, hbW * (bleed / GameSettings.BASE_GLOBAL_HP), hbH));
        }
        g.setColor(Color.WHITE);
        g.draw(new RoundRectangle2D.Double(hbX, hbY, hbW, hbH, 12, 12));

        // Статистика
        g.setFont(new Font("Segoe UI Semibold", Font.PLAIN, (int) (20 * s)));
        g.setColor(Color.WHITE);
        g.drawString(c.name, (int) hbX, (int) hbY - (int) (15 * GameSettings.scaleY));

        g.setFont(new Font("Segoe UI", Font.PLAIN, (int) (15 * s)));
        g.setColor(new Color(200, 205, 220));
        int dy = (int) (25 * GameSettings.scaleY);
        g.drawString(L10n.HP_BLEEDING + String.format("%.1f", bleed), (int) hbX, (int) hbY + (int) (45 * GameSettings.scaleY));
        g.drawString(L10n.ACCURACY + (int) (c.getAccuracy() * 100) + "%", (int) hbX, (int) hbY + (int) (45 * GameSettings.scaleY) + dy);
        g.drawString(L10n.EVASION + (int) (c.getEvasionChance() * 100) + "%", (int) hbX, (int) hbY + (int) (45 * GameSettings.scaleY) + dy * 2);
        g.setColor(new Color(255, 185, 80));
        g.drawString(L10n.POWER + (int) c.getPower() + "%", (int) hbX, (int) hbY + (int) (45 * GameSettings.scaleY) + dy * 3);

        for (BodyPart part : c.parts.values()) {
            Shape shape = (part.type == BodyPartType.HEAD)
                    ? new Ellipse2D.Double(part.bounds.getX(), part.bounds.getY(), part.bounds.getWidth(), part.bounds.getHeight())
                    : new RoundRectangle2D.Double(part.bounds.getX(), part.bounds.getY(), part.bounds.getWidth(), part.bounds.getHeight(), 25 * s, 25 * s);

            g.setColor(new Color(55, 58, 65));
            g.fill(shape);
            g.setColor(new Color(45, 200, 95, 110));
            double h = part.bounds.getHeight() * (part.hp / 100f);
            g.setClip(new Rectangle2D.Double(part.bounds.getX(), part.bounds.getY() + (part.bounds.getHeight() - h), part.bounds.getWidth(), h));
            g.fill(shape);
            g.setClip(null);
            g.setColor(new Color(100, 105, 115));
            g.draw(shape);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, (int) (13 * s)));
            String hpTxt = (int) part.hp + "%";
            FontMetrics fm = g.getFontMetrics();
            g.drawString(hpTxt, (int) (part.bounds.getCenterX() - fm.stringWidth(hpTxt) / 2), (int) (part.bounds.getMaxY() - 10 * GameSettings.scaleY));

            // Emoji Render
            emojiRenderer.render(g, part, c, isPlayerSide, currentStep, player);
        }
    }
}