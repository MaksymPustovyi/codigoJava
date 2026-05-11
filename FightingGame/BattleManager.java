import javax.swing.*;
import java.awt.*;

public class BattleManager {
    private Combatant player, ai;
    private BattleEngine engine;
    private AILogic aiLogic;
    private ArenaUI ui;
    private BattlefieldPanel panel;
    private int step = 0;

    public BattleManager(Combatant player, Combatant ai, BattleEngine engine, ArenaUI ui, BattlefieldPanel panel) {
        this.player = player;
        this.ai = ai;
        this.engine = engine;
        this.ui = ui;
        this.panel = panel;
        this.aiLogic = new AILogic();
    }

    public void handleMouseClick(Point p) {
        if (step == 0) {
            for (BodyPart bp : ai.parts.values()) if (bp.bounds.contains(p)) {
                resetVisuals();
                player.attackTarget = bp.type; step = 1;
                ui.shortLog.setText("<html><center>" + L10n.STEP_2_EVA + "</center></html>");
                break;
            }
        } else {
            for (BodyPart bp : player.parts.values()) if (bp.bounds.contains(p)) {
                if (step == 1) {
                    player.evasionPoint = bp.type; step = 2;
                    ui.shortLog.setText("<html><center>" + L10n.STEP_3_DEF + "</center></html>");
                } else if (step == 2) {
                    player.defensePoints.add(bp.type); step = 3;
                    ui.shortLog.setText("<html><center>" + L10n.STEP_4_DEF + "</center></html>");
                } else if (step == 3 && !player.defensePoints.contains(bp.type)) {
                    player.defensePoints.add(bp.type);
                    panel.repaint();
                    // Запуск розрахунку
                    SwingUtilities.invokeLater(() -> {
                        try { Thread.sleep(50); } catch (Exception ignored) {}
                        runTurn();
                        panel.repaint();
                    });
                }
                break;
            }
        }
        panel.setStep(step);
    }

    private void runTurn() {
        aiLogic.recordPlayerAttack(player.attackTarget);
        aiLogic.makeDecision(ai);

        ui.detailedLog.append(String.format(L10n.LOG_ROUND, engine.getRound()));
        engine.applyBleeding(player, ui.detailedLog);
        engine.applyBleeding(ai, ui.detailedLog);

        String pRes = engine.processAttack(player, ai, true, ui.detailedLog);
        String aRes = engine.processAttack(ai, player, false, ui.detailedLog);

        player.lastReceivedHit = ai.attackTarget;
        ui.updateStatus(pRes, aRes);

        saveChoices();
        engine.incrementRound();
        step = 0;
        panel.setStep(step);
        ui.detailedLog.setCaretPosition(ui.detailedLog.getDocument().getLength());

        checkGameOver();
    }

    private void saveChoices() {
        player.lastAtk = player.attackTarget;
        player.lastEva = player.evasionPoint;
        player.lastDef.clear(); player.lastDef.addAll(player.defensePoints);
        ai.lastAtk = ai.attackTarget;
        ai.lastEva = ai.evasionPoint;
        ai.lastDef.clear(); ai.lastDef.addAll(ai.defensePoints);
        
        player.attackTarget = null; player.evasionPoint = null; player.defensePoints.clear();
        ai.defensePoints.clear();
    }

    private void resetVisuals() {
        player.lastAtk = null; player.lastEva = null; player.lastDef.clear(); player.lastReceivedHit = null;
        ai.lastHitWasGuarded = false; ai.lastHitWasEvaded = false;
        ai.lastAtk = null; ai.lastEva = null; ai.lastDef.clear();
    }

    private void checkGameOver() {
        if (player.globalHp <= 0 || ai.globalHp <= 0) {
            JOptionPane.showMessageDialog(null, player.globalHp > 0 ? L10n.WIN : L10n.LOSS);
            System.exit(0);
        }
    }
}