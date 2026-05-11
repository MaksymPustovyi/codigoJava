import java.util.*;

public class Combatant {
    public String name;
    public float globalHp = GameSettings.BASE_GLOBAL_HP;
    public Map<BodyPartType, BodyPart> parts = new LinkedHashMap<>();
    public BodyPartType attackTarget, evasionPoint, lastAtk, lastEva, lastReceivedHit;
    public Set<BodyPartType> defensePoints = new HashSet<>(), lastDef = new HashSet<>();
    public boolean lastHitWasGuarded, lastHitWasEvaded;

    public Combatant(String name, double startX) {
        this.name = name;
        double yOff = 280;
        parts.put(BodyPartType.HEAD, new BodyPart(BodyPartType.HEAD, startX + 45, yOff + 0, 75, 75));
        parts.put(BodyPartType.CHEST, new BodyPart(BodyPartType.CHEST, startX + 20, yOff + 90, 125, 95));
        parts.put(BodyPartType.ABDOMEN, new BodyPart(BodyPartType.ABDOMEN, startX + 35, yOff + 200, 95, 75));
        parts.put(BodyPartType.L_ARM, new BodyPart(BodyPartType.L_ARM, startX - 50, yOff + 90, 60, 140));
        parts.put(BodyPartType.R_ARM, new BodyPart(BodyPartType.R_ARM, startX + 155, yOff + 90, 60, 140));
        parts.put(BodyPartType.L_LEG, new BodyPart(BodyPartType.L_LEG, startX + 35, yOff + 290, 45, 170));
        parts.put(BodyPartType.R_LEG, new BodyPart(BodyPartType.R_LEG, startX + 85, yOff + 290, 45, 170));
    }

    public float getArmEff() {
        return (parts.get(BodyPartType.L_ARM).hp + parts.get(BodyPartType.R_ARM).hp) / 200f;
    }

    public float getLegEff() {
        return (parts.get(BodyPartType.L_LEG).hp + parts.get(BodyPartType.R_LEG).hp) / 200f;
    }

    public float getHeadEff() {
        return parts.get(BodyPartType.HEAD).hp / 100f;
    }

    public float getBleedSum() {
        return (float) parts.values().stream().mapToDouble(p -> p.bleeding).sum();
    }

    public float getPower() {
        return getArmEff() * 100f;
    }

    public float getEvasionChance() {
        return GameSettings.BASE_EVASION * getLegEff();
    }

    public float getAccuracy() {
        return GameSettings.BASE_ACCURACY - (1.0f - getArmEff()) * 0.5f - (1.0f - getHeadEff());
    }
}