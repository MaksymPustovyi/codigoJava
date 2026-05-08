import java.util.*;

class Combatant {
    public String name;
    public float globalHp = GameSettings.BASE_GLOBAL_HP;
    public Map<BodyPartType, BodyPart> parts = new LinkedHashMap<>();
    
    public BodyPartType attackTarget, evasionPoint, lastAtk, lastEva, lastReceivedHit;
    public Set<BodyPartType> defensePoints = new HashSet<>(), lastDef = new HashSet<>();
    public boolean lastHitWasGuarded, lastHitWasEvaded;

    public Combatant(String name, double startX) {
        this.name = name;
        double yOff = 280;
        parts.put(BodyPartType.HEAD,    new BodyPart(BodyPartType.HEAD,    startX + 45, yOff + 0, 75, 75));
        parts.put(BodyPartType.CHEST,   new BodyPart(BodyPartType.CHEST,   startX + 20, yOff + 90, 125, 95));
        parts.put(BodyPartType.ABDOMEN, new BodyPart(BodyPartType.ABDOMEN, startX + 35, yOff + 200, 95, 75));
        parts.put(BodyPartType.L_ARM,   new BodyPart(BodyPartType.L_ARM,   startX - 50, yOff + 90, 60, 140));
        parts.put(BodyPartType.R_ARM,   new BodyPart(BodyPartType.R_ARM,   startX + 155, yOff + 90, 60, 140));
        parts.put(BodyPartType.L_LEG,   new BodyPart(BodyPartType.L_LEG,   startX + 35, yOff + 290, 45, 170));
        parts.put(BodyPartType.R_LEG,   new BodyPart(BodyPartType.R_LEG,   startX + 85, yOff + 290, 45, 170));
    }

    public float getAccuracy() {
        float arms = (parts.get(BodyPartType.L_ARM).hp + parts.get(BodyPartType.R_ARM).hp) / 200f;
        return GameSettings.BASE_ACCURACY * (0.5f + 0.5f * arms);
    }

    public float getEvasionChance() {
        float legs = (parts.get(BodyPartType.L_LEG).hp + parts.get(BodyPartType.R_LEG).hp) / 200f;
        return GameSettings.BASE_EVASION * (0.4f + 0.6f * legs);
    }

    public float getPower() {
        return 0.5f + 0.5f * (globalHp / GameSettings.BASE_GLOBAL_HP);
    }

    public float getBleedTotal() {
        float total = 0;
        for (BodyPart p : parts.values()) total += p.bleeding;
        return total;
    }
}