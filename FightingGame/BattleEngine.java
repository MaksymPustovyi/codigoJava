// ==========================================
// 5. ДВИГУН БОЮ (МАТЕМАТИКА ТА ЛОГУВАННЯ)
// ==========================================

import javax.swing.JTextArea;

class BattleEngine {
    private int round = 1;

    public String processAttack(Combatant atk, Combatant def, boolean isPlayerAtk, JTextArea log) {
        String actionBase = String.format(L10n.LOG_ACTION, atk.name, atk.attackTarget.getName());
        
        float accB = def.getAccuracy(), evaB = def.getEvasionChance(), pwrB = def.getPower();

        if (def.evasionPoint == atk.attackTarget) {
            if (isPlayerAtk) def.lastHitWasEvaded = true;
            log.append(actionBase + L10n.LOG_EVADE);
            return String.format(L10n.SHORT_EVADE, atk.name);
        }

        if (Math.random() > atk.getAccuracy()) {
            log.append(actionBase + L10n.LOG_MISS);
            return String.format(L10n.SHORT_MISS, atk.name);
        }

        float dmg = 30f * (atk.getPower() / 100f);
        boolean guarded = def.defensePoints.contains(atk.attackTarget);
        if (guarded && isPlayerAtk) def.lastHitWasGuarded = true;

        float fDmg = guarded ? dmg * 0.8f : dmg;
        def.globalHp -= fDmg;
        BodyPart target = def.parts.get(atk.attackTarget);
        target.hp = Math.max(0, target.hp - dmg * 0.2f);

        if (atk.attackTarget == BodyPartType.CHEST || atk.attackTarget == BodyPartType.ABDOMEN) {
            target.bleeding += dmg * 0.20f;
        }

        log.append(actionBase + (guarded ? L10n.LOG_GUARD : "") + String.format(L10n.LOG_HIT, "", (int)fDmg));
        
        // Логування наслідків
        logConsequence(log, def.name, L10n.CONS_ACCURACY, accB, def.getAccuracy(), 100);
        logConsequence(log, def.name, L10n.CONS_EVASION, evaB, def.getEvasionChance(), 100);
        logConsequence(log, def.name, L10n.CONS_POWER, pwrB, def.getPower(), 1);

        return guarded ? String.format(L10n.SHORT_GUARD, atk.name, atk.attackTarget.getName(), (int)fDmg)
                       : String.format(L10n.SHORT_HIT, atk.name, atk.attackTarget.getName(), (int)fDmg);
    }

    private void logConsequence(JTextArea log, String name, String pattern, float before, float after, int mult) {
        if (before > after) log.append(String.format(pattern, name, (before - after) * mult));
    }

    public void applyBleeding(Combatant c, JTextArea log) {
        float bleed = c.getBleedSum();
        float actual = Math.min(bleed, c.globalHp * 0.10f);
        if (actual > 0.5) {
            c.globalHp -= actual;
            log.append(String.format(L10n.LOG_BLEED_LOST, c.name, (int)actual));
            c.parts.values().forEach(p -> p.bleeding *= 0.5f);
        }
    }

    public int getRound() { return round; }
    public void incrementRound() { round++; }
}