import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;

class EmojiRenderer {
    private final Font mainFont;
    private final Font hitFont;

    public EmojiRenderer(double scale) {
        this.mainFont = new Font("Segoe UI Emoji", Font.PLAIN, (int) (36 * scale));
        this.hitFont = new Font("Segoe UI Emoji", Font.PLAIN, (int) (29 * scale));
    }

    public void render(Graphics2D g, BodyPart part, Combatant c, boolean isPlayerSide, int step, Combatant playerRef) {
        if (isPlayerSide) {
            // 1. Ухилення гравця
            if (c.evasionPoint == part.type || (step == 0 && c.lastEva == part.type))
                drawPerfectlyCentered(g, part.bounds, "🏃", mainFont, Color.WHITE);

            // 2. Захист гравця
            if (c.defensePoints.contains(part.type) || (step == 0 && c.lastDef.contains(part.type)))
                drawPerfectlyCentered(g, part.bounds, "🛡️", mainFont, Color.WHITE);

            // 3. Влучання AI по гравцю (червоне 80%) поверх всього
            if (c.lastReceivedHit == part.type && step == 0) {
                drawPerfectlyCentered(g, part.bounds, "💥", hitFont, new Color(255, 60, 60));
            }
        } else {
            // Сторона AI
            if (playerRef.attackTarget == part.type || (step == 0 && playerRef.lastAtk == part.type)) {
                // Підкладка захисту/ухилення AI
                if (c.lastHitWasEvaded && c.lastEva == part.type) {
                    drawPerfectlyCentered(g, part.bounds, "🏃", mainFont, Color.WHITE);
                } else if (c.lastHitWasGuarded && c.lastDef.contains(part.type)) {
                    drawPerfectlyCentered(g, part.bounds, "🛡️", mainFont, Color.WHITE);
                }
                // Меч атаки гравця (червоний) поверх усього
                drawPerfectlyCentered(g, part.bounds, "⚔️", mainFont, new Color(255, 60, 60));
            }
        }
    }

    private void drawPerfectlyCentered(Graphics2D g, Rectangle2D rect, String text, Font font, Color col) {
        g.setFont(font);
        g.setColor(col);

        // РАДИКАЛЬНЕ ЦЕНТРУВАННЯ: Отримуємо реальну форму символу
        GlyphVector gv = font.createGlyphVector(g.getFontRenderContext(), text);
        Rectangle2D visualBounds = gv.getVisualBounds();

        // Обчислюємо зміщення, щоб візуальний центр символу збігався з центром
        // прямокутника частини тіла
        float x = (float) (rect.getX() + (rect.getWidth() - visualBounds.getWidth()) / 2 - visualBounds.getX());
        float y = (float) (rect.getY() + (rect.getHeight() - visualBounds.getHeight()) / 2 - visualBounds.getY());

        g.drawGlyphVector(gv, x, y);
    }
}