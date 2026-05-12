public class BattleEngine {
    private int round = 1;

    public String processAttack(Combatant atk, Combatant def, boolean isPlayerAtk, ArenaUI ui) {
        String targetName = (atk.attackTarget != null) ? atk.attackTarget.getName() : "???";
        float accB = def.getAccuracy(), evaB = def.getEvasionChance(), pwrB = def.getPower();

        // Прапорець для відстеження невдалого ухилення
        boolean evasionGuessedButFailed = false;

        // 1. ПЕРЕВІРКА ТОЧНОСТІ АТАКУЮЧОГО
        if (Math.random() > atk.getAccuracy()) {
            ui.appendToLog(safeFormat(L10n.LOG_ACTION, atk.name, targetName) + L10n.LOG_MISS);
            return safeFormat(L10n.SHORT_MISS, atk.name);
        }

        // 2. ПЕРЕВІРКА УХИЛЕННЯ ЗАХИСНИКА
        if (def.evasionPoint == atk.attackTarget && atk.attackTarget != null) {
            if (Math.random() < def.getEvasionChance()) {
                if (isPlayerAtk)
                    def.lastHitWasEvaded = true;
                ui.appendToLog(safeFormat(L10n.LOG_ACTION, atk.name, targetName) + L10n.LOG_EVADE);
                return safeFormat(L10n.SHORT_EVADE, atk.name);
            } else {
                // ФІКС: Просто запам'ятовуємо, що ухилення не вдалося
                evasionGuessedButFailed = true;
            }
        }

        // 3. ВЛУЧАННЯ ТА РОЗРАХУНОК УРОНУ
        float baseDmg = 30f * (atk.getPower() / 100f);
        boolean guarded = def.defensePoints.contains(atk.attackTarget);
        if (guarded && isPlayerAtk)
            def.lastHitWasGuarded = true;

        float fDmg = guarded ? baseDmg * 0.8f : baseDmg;
        def.globalHp -= fDmg;

        if (atk.attackTarget != null) {
            BodyPart target = def.parts.get(atk.attackTarget);
            target.hp = Math.max(0, target.hp - fDmg * 0.2f);

            float bleedAdded = 0;
            if (atk.attackTarget == BodyPartType.CHEST || atk.attackTarget == BodyPartType.ABDOMEN) {
                bleedAdded = fDmg * 0.20f;
                target.bleeding += bleedAdded;
            }

            // ПРЯМИЙ ЗАПИС В ЛОГ (основний рядок влучання)
            ui.appendToLog(safeFormat(L10n.LOG_HIT, atk.name, targetName, (guarded ? L10n.LOG_GUARD : ""), (int) fDmg));

            // ФІКС: Виводимо повідомлення про невдале ухилення ВІДРАЗУ ПІСЛЯ рядка влучання
            if (evasionGuessedButFailed) {
                ui.appendToLog(L10n.LOG_EVADE_FAIL);
            }

            if (bleedAdded > 0)
                ui.appendToLog(safeFormat(L10n.CONS_BLEED, bleedAdded));
        }

        // Логування наслідків (точність, сила тощо)
        logConsequence(ui, L10n.CONS_ACCURACY, accB, def.getAccuracy(), 100);
        logConsequence(ui, L10n.CONS_EVASION, evaB, def.getEvasionChance(), 100);
        logConsequence(ui, L10n.CONS_POWER, pwrB, def.getPower(), 1);

        return guarded ? safeFormat(L10n.SHORT_GUARD, atk.name, targetName, (int) fDmg)
                : safeFormat(L10n.SHORT_HIT, atk.name, targetName, (int) fDmg);
    }

    // ДОПОМІЖНИЙ МЕТОД ДЛЯ ЗАХИСТУ ВІД NULL
    private String safeFormat(String pattern, Object... args) {
        if (pattern == null || pattern.isEmpty())
            return "";
        try {
            return String.format(pattern, args);
        } catch (Exception e) {
            return "Format Error";
        }
    }

    private void logConsequence(ArenaUI ui, String pattern, float before, float after, int mult) {
        if (pattern != null && !pattern.isEmpty() && before > (after + 0.001)) {
            ui.appendToLog(safeFormat(pattern, (before - after) * mult));
        }
    }

    public void applyBleeding(Combatant c, ArenaUI ui) {
        float bleed = c.getBleedSum();
        float cap = c.globalHp * 0.10f;
        float actual = Math.min(bleed, cap);
        if (actual > 0.5) {
            c.globalHp -= actual;
            ui.appendToLog(safeFormat(L10n.LOG_BLEED_LOST, c.name, (int) actual));
            c.parts.values().forEach(p -> p.bleeding *= 0.5f);
        }
    }

    public void logRoundHeader(ArenaUI ui) {
        ui.appendToLog(safeFormat(L10n.LOG_ROUND, round));
    }

    public int getRound() {
        return round;
    }

    public void incrementRound() {
        round++;
    }
}