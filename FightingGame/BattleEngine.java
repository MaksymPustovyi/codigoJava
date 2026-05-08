// ==========================================
// 5. ДВИГУН БОЮ (МАТЕМАТИКА ТА ЛОГУВАННЯ)
// ==========================================

import javax.swing.JTextArea;

class BattleEngine {
    private int round = 1;

    public String processAttack(Combatant atk, Combatant def, boolean isPlayerAtk, JTextArea log) {
        // Формуємо базовий рядок дії: "Ім'я атакує в ЧастинаТіла"
        String actionBase = String.format(L10n.LOG_ACTION, atk.name, atk.attackTarget.getName());

        // Зберігаємо показники захисника до удару для порівняння наслідків
        float accB = def.getAccuracy(), evaB = def.getEvasionChance(), pwrB = def.getPower();

        // 1. ПЕРЕВІРКА НА УХИЛЕННЯ
        if (def.evasionPoint == atk.attackTarget) {
            if (isPlayerAtk)
                def.lastHitWasEvaded = true;
            log.append(actionBase + L10n.LOG_EVADE);
            return String.format(L10n.SHORT_EVADE, atk.name);
        }

        // 2. ПЕРЕВІРКА НА ПРОМАХ
        if (Math.random() > atk.getAccuracy()) {
            log.append(actionBase + L10n.LOG_MISS);
            return String.format(L10n.SHORT_MISS, atk.name);
        }

        // 3. РОЗРАХУНОК ПОШКОДЖЕННЯ
        float dmg = 30f * (atk.getPower() / 100f); // Потенційний урон
        boolean guarded = def.defensePoints.contains(atk.attackTarget);
        if (guarded && isPlayerAtk)
            def.lastHitWasGuarded = true;

        // Розрахунок фактичного урону та заблокованого урону
        float fDmg = guarded ? dmg * 0.8f : dmg;
        int blockedDmg = (int) (dmg - fDmg); // Скільки урону "з'їв" блок

        def.globalHp -= fDmg;

        // Шкода конкретній частині тіла
        BodyPart target = def.parts.get(atk.attackTarget);
        target.hp = Math.max(0, target.hp - dmg * 0.5f);

        // 4. МЕХАНІКА КРОВОТЕЧІ
        if (atk.attackTarget == BodyPartType.CHEST || atk.attackTarget == BodyPartType.ABDOMEN) {
            target.bleeding += dmg * 0.20f;
        }

        // 5. ЗАПИС У ЛОГ (Оновлено для показу заблокованого урону)
        StringBuilder logEntry = new StringBuilder(actionBase);
        if (guarded) {
            // Додаємо інформацію про блок та кількість заблокованого урону
            logEntry.append(L10n.LOG_GUARD)
                    .append(String.format(" (Заблоковано: %d) ", blockedDmg));
        }
        logEntry.append(String.format(L10n.LOG_HIT, "", (int) fDmg));
        log.append(logEntry.toString());

        // Логування наслідків (дебафів)
        logConsequence(log, def.name, L10n.CONS_ACCURACY, accB, def.getAccuracy(), 100);
        logConsequence(log, def.name, L10n.CONS_EVASION, evaB, def.getEvasionChance(), 100);
        logConsequence(log, def.name, L10n.CONS_POWER, pwrB, def.getPower(), 1);

        return guarded ? String.format(L10n.SHORT_GUARD, atk.name, atk.attackTarget.getName(), (int) fDmg)
                : String.format(L10n.SHORT_HIT, atk.name, atk.attackTarget.getName(), (int) fDmg);
    }

    private void logConsequence(JTextArea log, String name, String pattern, float before, float after, int mult) {
        if (before > after)
            log.append(String.format(pattern, name, (before - after) * mult));
    }

    public void applyBleeding(Combatant c, JTextArea log) {
        float bleed = c.getBleedSum();
        float actual = Math.min(bleed, c.globalHp * 0.10f);
        if (actual > 0.5) {
            c.globalHp -= actual;
            log.append(String.format(L10n.LOG_BLEED_LOST, c.name, (int) actual));
            c.parts.values().forEach(p -> p.bleeding *= 0.5f);
        }
    }

    public int getRound() {
        return round;
    }

    public void incrementRound() {
        round++;
    }
}