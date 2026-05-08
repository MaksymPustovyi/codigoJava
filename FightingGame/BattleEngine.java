import javax.swing.JTextArea;

class BattleEngine {
    private int round = 1;

    public String processAttack(Combatant atk, Combatant def, boolean isPlayerAtk, JTextArea log) {
        String actionBase = String.format(L10n.LOG_ACTION, atk.name, atk.attackTarget.getName());
        
        if (def.evasionPoint == atk.attackTarget) {
            def.lastHitWasEvaded = true;
            log.append(actionBase + L10n.LOG_EVADE);
            return String.format(L10n.SHORT_EVADE, atk.name);
        }

        if (Math.random() > atk.getAccuracy()) {
            log.append(actionBase + L10n.LOG_MISS);
            return String.format(L10n.SHORT_MISS, atk.name);
        }

        float baseDmg = 45f * atk.getPower();
        boolean guarded = def.defensePoints.contains(atk.attackTarget);
        def.lastHitWasGuarded = guarded;

        float finalDmg = guarded ? baseDmg * 0.2f : baseDmg;
        def.parts.get(atk.attackTarget).hp -= finalDmg;
        if (def.parts.get(atk.attackTarget).hp < 0) def.parts.get(atk.attackTarget).hp = 0;
        def.globalHp -= finalDmg;

        if (atk.attackTarget == BodyPartType.CHEST || atk.attackTarget == BodyPartType.ABDOMEN) {
            def.parts.get(atk.attackTarget).bleeding += 1.5f;
        }

        log.append(actionBase + (guarded ? L10n.LOG_GUARD : "") + String.format(L10n.LOG_HIT, "", (int)finalDmg));
        return String.format(guarded ? L10n.SHORT_GUARD : L10n.SHORT_HIT, atk.name, atk.attackTarget.getName(), (int)finalDmg);
    }

    public void applyBleeding(Combatant c, JTextArea log) {
        float bleed = c.getBleedTotal();
        if (bleed > 0) {
            c.globalHp -= bleed;
            log.append(String.format(L10n.LOG_BLEED_LOST, c.name, (int)bleed));
        }
    }

    public int getRound() { return round; }
    public void incrementRound() { round++; }
}