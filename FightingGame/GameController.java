import javax.swing.*;
import java.util.HashSet;

class GameController {
    private Combatant player, ai;
    private BattleEngine engine;
    private int step = 0; 
    private JLabel statusLabel;
    private JTextArea detailedLog;

    public GameController(Combatant player, Combatant ai, BattleEngine engine, JLabel statusLabel, JTextArea detailedLog) {
        this.player = player;
        this.ai = ai;
        this.engine = engine;
        this.statusLabel = statusLabel;
        this.detailedLog = detailedLog;
    }

    public void runTurn() {
        // Обов'язкове скидання станів перед новим розрахунком
        player.lastHitWasEvaded = false; player.lastHitWasGuarded = false;
        ai.lastHitWasEvaded = false; ai.lastHitWasGuarded = false;

        // Логіка ШІ
        ai.attackTarget = BodyPartType.values()[(int) (Math.random() * 7)];
        ai.evasionPoint = BodyPartType.values()[(int) (Math.random() * 7)];
        ai.defensePoints.clear();
        while (ai.defensePoints.size() < 2) {
            ai.defensePoints.add(BodyPartType.values()[(int) (Math.random() * 7)]);
        }

        detailedLog.append(String.format(L10n.LOG_ROUND, engine.getRound()));
        engine.applyBleeding(player, detailedLog);
        engine.applyBleeding(ai, detailedLog);

        String pRes = engine.processAttack(player, ai, true, detailedLog);
        String aRes = engine.processAttack(ai, player, false, detailedLog);

        player.lastReceivedHit = ai.attackTarget;
        statusLabel.setText("<html><center>" + pRes + "<br>" + aRes + "</center></html>");

        finalizeTurn();
        engine.incrementRound();
        step = 0;
        checkGameOver();
    }

    private void finalizeTurn() {
        player.lastAtk = player.attackTarget;
        player.lastEva = player.evasionPoint;
        player.lastDef = new HashSet<>(player.defensePoints);
        ai.lastEva = ai.evasionPoint;
        ai.lastDef = new HashSet<>(ai.defensePoints);
        
        player.attackTarget = null;
        player.evasionPoint = null;
        player.defensePoints.clear();
    }

    private void checkGameOver() {
        if (player.globalHp <= 0 || ai.globalHp <= 0) {
            JOptionPane.showMessageDialog(null, player.globalHp > 0 ? L10n.WIN : L10n.LOSS);
            System.exit(0);
        }
    }

    public int getStep() { return step; }
    public void nextStep() { step++; }
    public void setStep(int s) { step = s; }
}