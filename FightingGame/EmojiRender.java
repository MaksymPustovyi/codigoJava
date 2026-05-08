import java.awt.*;


class EmojiRenderer {
    private final Font mainFont, hitFont;

    public EmojiRenderer(double scale) {
        this.mainFont = new Font("Segoe UI Emoji", Font.PLAIN, (int) (36 * scale));
        this.hitFont = new Font("Segoe UI Emoji", Font.PLAIN, (int) (29 * scale));
    }

    public void render(Graphics2D g, BodyPart part, Combatant c, boolean isPlayerSide, int step, Combatant playerRef) {
        int cx = (int) part.bounds.getCenterX();
        int cy = (int) part.bounds.getCenterY();
        FontMetrics fm = g.getFontMetrics(mainFont);

        if (isPlayerSide) {
            g.setFont(mainFont);
            if (playerRef.evasionPoint == part.type) drawCentered(g, "🏃", cx, cy, fm, Color.WHITE);
            if (playerRef.defensePoints.contains(part.type)) drawCentered(g, "🛡️", cx, cy, fm, Color.WHITE);
            
            if (c.lastReceivedHit == part.type && step == 0) {
                g.setFont(hitFont);
                drawCentered(g, "💥", cx, cy, g.getFontMetrics(), Color.RED);
            }
        } else {
            if (playerRef.attackTarget == part.type) {
                g.setFont(mainFont);
                drawCentered(g, "⚔️", cx, cy, fm, Color.RED);
            }
            if (step == 0) {
                if (c.lastHitWasEvaded && c.lastEva == part.type) drawCentered(g, "🏃", cx, cy, fm, Color.WHITE);
                else if (c.lastHitWasGuarded && c.lastDef.contains(part.type)) drawCentered(g, "🛡️", cx, cy, fm, Color.WHITE);
            }
        }
    }

    private void drawCentered(Graphics2D g, String text, int x, int y, FontMetrics fm, Color col) {
        g.setColor(col);
        g.drawString(text, x - fm.stringWidth(text) / 2, y + fm.getAscent() / 2);
    }
}