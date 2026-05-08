import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

// ==========================================
// 3. СИСТЕМА ВІДОБРАЖЕННЯ ЕМОДЗІ (EmojiRenderer)
// ==========================================
class EmojiRenderer {
    private final Font mainFont;
    private final Font hitFont;
    private final FontMetrics fmMain;
    private final FontMetrics fmHit;

    public EmojiRenderer(double scale) {
        this.mainFont = new Font("Segoe UI Emoji", Font.PLAIN, (int) (36 * scale));
        this.hitFont = new Font("Segoe UI Emoji", Font.PLAIN, (int) (29 * scale)); // 80% від основного
        // Тимчасовий контекст для метрик
        Canvas c = new Canvas();
        this.fmMain = c.getFontMetrics(mainFont);
        this.fmHit = c.getFontMetrics(hitFont);
    }

    public void render(Graphics2D g, BodyPart part, Combatant c, boolean isPlayerSide, int step, Combatant playerRef) {
        int cx = (int) part.bounds.getCenterX();
        int cy = (int) part.bounds.getCenterY() + (int) (5 * GameSettings.scaleY);

        if (isPlayerSide) {
            g.setFont(mainFont);
            // 1. Ухилення
            if (c.evasionPoint == part.type || (step == 0 && c.lastEva == part.type))
                drawCentered(g, "🏃", cx, cy, fmMain, Color.WHITE);

            // 2. Захист
            if (c.defensePoints.contains(part.type) || (step == 0 && c.lastDef.contains(part.type)))
                drawCentered(g, "🛡️", cx, cy, fmMain, Color.WHITE);

            // 3. Влучання ворога поверх усього
            if (c.lastReceivedHit == part.type && step == 0) {
                g.setFont(hitFont);
                drawCentered(g, "💥", cx, cy, fmHit, new Color(255, 60, 60));
            }
        } else {
            // Сторона AI (відображення атаки гравця)
            if (playerRef.attackTarget == part.type || (step == 0 && playerRef.lastAtk == part.type)) {
                g.setFont(mainFont);
                // Малюємо "підкладку" захисту/ухилення AI, якщо вона спрацювала
                if (c.lastHitWasEvaded && c.lastEva == part.type) {
                    drawCentered(g, "🏃", cx, cy, fmMain, Color.WHITE);
                } else if (c.lastHitWasGuarded && c.lastDef.contains(part.type)) {
                    drawCentered(g, "🛡️", cx, cy, fmMain, Color.WHITE);
                }

                // Малюємо червоний меч атаки гравця поверх
                drawCentered(g, "⚔️", cx, cy, fmMain, new Color(255, 60, 60));
            }
        }
    }

    private void drawCentered(Graphics2D g, String text, int x, int y, FontMetrics fm, Color col) {
        g.setColor(col);

        // Отримуємо візуальні межі символу (Bounding Box)
        java.awt.geom.Rectangle2D rect = g.getFont().createGlyphVector(g.getFontRenderContext(), text)
                .getVisualBounds();

        // Розраховуємо зміщення
        int drawX = x - (int) (rect.getWidth() / 2 + rect.getX());
        int drawY = y - (int) (rect.getHeight() / 2 + rect.getY());

        g.drawString(text, drawX, drawY);
    }
}