import javax.swing.*;

class BattleEngine {
    private int round = 1;

    public String processAttack(Combatant atk, Combatant def, boolean isPlayerAtk, JTextArea log) {
        // Безпечне отримання тексту
        String actionBase = (L10n.LOG_ACTION != null) ? 
            String.format(L10n.LOG_ACTION, atk.name, atk.attackTarget.getName()) : 
            atk.name + " aims at " + atk.attackTarget.getName();

        float accB = def.getAccuracy(), evaB = def.getEvasionChance(), pwrB = def.getPower();

        // 1. Ухилення
        if (def.evasionPoint == atk.attackTarget) {
            if (isPlayerAtk) def.lastHitWasEvaded = true;
            log.append(actionBase + L10n.LOG_EVADE);
            return String.format(L10n.SHORT_EVADE, atk.name);
        }

        // 2. Точність
        if (Math.random() > atk.getAccuracy()) {
            log.append(actionBase + L10n.LOG_MISS);
            return String.format(L10n.SHORT_MISS, atk.name);
        }

        // 3. Урон
        float baseDmg = 30f * (atk.getPower() / 100f);
        boolean guarded = def.defensePoints.contains(atk.attackTarget);
        if (guarded && isPlayerAtk) def.lastHitWasGuarded = true;

        float fDmg = guarded ? baseDmg * 0.8f : baseDmg;
        def.globalHp -= fDmg;

        BodyPart target = def.parts.get(atk.attackTarget);
        target.hp = Math.max(0, target.hp - baseDmg * 0.2f);

        float bleedAdded = 0;
        if (atk.attackTarget == BodyPartType.CHEST || atk.attackTarget == BodyPartType.ABDOMEN) {
            bleedAdded = fDmg * 0.20f;
            target.bleeding += bleedAdded;
        }

        // Лог удару
        log.append(actionBase + (guarded ? L10n.LOG_GUARD : "") + String.format(L10n.LOG_HIT, "", (int) fDmg));

        // Наслідки
        if (bleedAdded > 0 && L10n.CONS_BLEED != null) {
            log.append(String.format(L10n.CONS_BLEED, def.name, bleedAdded));
        }
        logConsequence(log, def.name, L10n.CONS_ACCURACY, accB, def.getAccuracy(), 100);
        logConsequence(log, def.name, L10n.CONS_EVASION, evaB, def.getEvasionChance(), 100);
        logConsequence(log, def.name, L10n.CONS_POWER, pwrB, def.getPower(), 1);

        return guarded ? String.format(L10n.SHORT_GUARD, atk.name, atk.attackTarget.getName(), (int) fDmg)
                : String.format(L10n.SHORT_HIT, atk.name, atk.attackTarget.getName(), (int) fDmg);
    }

    private void logConsequence(JTextArea log, String name, String pattern, float before, float after, int mult) {
        if (pattern != null && before > after + 0.001) {
            log.append(String.format(pattern, name, (before - after) * mult));
        }
    }

    public void applyBleeding(Combatant c, JTextArea log) {
        float bleed = c.getBleedSum();
        float cap = c.globalHp * 0.10f;
        float actual = Math.min(bleed, cap);
        if (actual > 0.5 && L10n.LOG_BLEED_LOST != null) {
            c.globalHp -= actual;
            log.append(String.format(L10n.LOG_BLEED_LOST, c.name, (int) actual));
            c.parts.values().forEach(p -> p.bleeding *= 0.5f);
        }
    }

    public int getRound() { return round; }
    public void incrementRound() { round++; }
}