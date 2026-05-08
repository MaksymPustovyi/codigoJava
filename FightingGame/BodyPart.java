// ==========================================
// 3. МОДЕЛЬ ЧАСТИНИ ТІЛА
// ==========================================
import java.awt.geom.Rectangle2D;

class BodyPart {
    public BodyPartType type;
    public float hp = 100f;
    public float bleeding = 0f;
    public Rectangle2D bounds;

    public BodyPart(BodyPartType type, double x, double y, double w, double h) {
        this.type = type;
        this.bounds = new Rectangle2D.Double(
            x * GameSettings.scaleX, 
            y * GameSettings.scaleY, 
            w * GameSettings.scale, 
            h * GameSettings.scale
        );
    }
}