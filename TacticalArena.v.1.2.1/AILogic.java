import java.util.ArrayList;
import java.util.List;

public class AILogic {
    // ПРИБРАНО static - тепер у кожного AI своя історія
    private List<BodyPartType> playerAttackHistory = new ArrayList<>();

    // ПРИБРАНО static
    public void recordPlayerAttack(BodyPartType type) {
        if (type == null) return;
        playerAttackHistory.add(type);
        if (playerAttackHistory.size() > 3) {
            playerAttackHistory.remove(0);
        }
    }

    // ПРИБРАНО static
    public void makeDecision(Combatant ai) {
        BodyPartType[] types = BodyPartType.values();
        ai.attackTarget = types[(int) (Math.random() * 7)];
        ai.evasionPoint = decideEvasion(types);
        ai.defensePoints.clear();
        while (ai.defensePoints.size() < 2) {
            ai.defensePoints.add(types[(int) (Math.random() * 7)]);
        }
    }

    // ПРИБРАНО static
    private BodyPartType decideEvasion(BodyPartType[] allTypes) {
        int historySize = playerAttackHistory.size();
        if (historySize >= 2) {
            BodyPartType last = playerAttackHistory.get(historySize - 1);
            BodyPartType prev = playerAttackHistory.get(historySize - 2);

            if (historySize == 3) {
                BodyPartType prevPrev = playerAttackHistory.get(0);
                if (last == prev && last == prevPrev) {
                    if (Math.random() < 0.90) return last;
                }
            }
            if (last == prev) {
                if (Math.random() < 0.75) return last;
            }
        }
        return allTypes[(int) (Math.random() * 7)];
    }
}