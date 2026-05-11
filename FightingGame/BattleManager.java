import javax.swing.*;
import java.awt.*;

public class BattleManager {
    private Combatant player, ai;
    private BattleEngine engine;
    private AILogic aiLogic;
    private ArenaUI ui;
    private BattlefieldPanel panel;
    private int step = 0;
    private boolean isProcessing = false;

    public BattleManager(Combatant player, Combatant ai, BattleEngine engine, ArenaUI ui, BattlefieldPanel panel) {
        this.player = player;
        this.ai = ai;
        this.engine = engine;
        this.ui = ui;
        this.panel = panel;
        this.aiLogic = new AILogic();
    }

    public void handleMouseClick(Point p) {
        if (isProcessing) return;

        if (step == 0) { // Атака
            for (BodyPart bp : ai.parts.values()) if (bp.bounds.contains(p)) {
                resetVisuals();
                player.attackTarget = bp.type;
                step = 1;
                ui.shortLog.setText("<html><center>" + L10n.STEP_2_EVA + "</center></html>");
                break;
            }
        } else { // Ухилення та Захист
            for (BodyPart bp : player.parts.values()) if (bp.bounds.contains(p)) {
                if (step == 1) {
                    player.evasionPoint = bp.type;
                    step = 2;
                    ui.shortLog.setText("<html><center>" + L10n.STEP_3_DEF + "</center></html>");
                } else if (step == 2) {
                    player.defensePoints.add(bp.type);
                    step = 3;
                    ui.shortLog.setText("<html><center>" + L10n.STEP_4_DEF + "</center></html>");
                } else if (step == 3 && !player.defensePoints.contains(bp.type)) {
                    player.defensePoints.add(bp.type);
                    executeTurnSequence();
                }
                break;
            }
        }
        panel.setStep(step);
    }

    private void executeTurnSequence() {
        isProcessing = true;
        panel.setStep(step);
        panel.repaint();
        
        // Маленька затримка, щоб гравець побачив свій вибір
        Timer timer = new Timer(300, e -> {
            runTurn();
            isProcessing = false;
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void runTurn() {
        aiLogic.recordPlayerAttack(player.attackTarget);
        aiLogic.makeDecision(ai);

        engine.logRoundHeader(ui);
        engine.applyBleeding(player, ui);
        engine.applyBleeding(ai, ui);

        String pRes = engine.processAttack(player, ai, true, ui);
        String aRes = engine.processAttack(ai, player, false, ui);

        player.lastReceivedHit = ai.attackTarget;
        ui.updateStatus(pRes, aRes);

        saveChoicesAndReset();
        engine.incrementRound();
        
        step = 0; // ПОВЕРНЕННЯ ДО ПОЧАТКУ
        panel.setStep(step);
        
        // ВАЖЛИВО: Оновлюємо заголовок на Крок 1
        ui.shortLog.setText("<html><center>" + L10n.STEP_1_ATK + "</center></html>");
        
        panel.repaint();

        if (player.globalHp <= 0 || ai.globalHp <= 0) {
            checkGameOver();
        }
    }

    private void saveChoicesAndReset() {
        player.lastAtk = player.attackTarget;
        player.lastEva = player.evasionPoint;
        player.lastDef.clear();
        player.lastDef.addAll(player.defensePoints);

        ai.lastAtk = ai.attackTarget;
        ai.lastEva = ai.evasionPoint;
        ai.lastDef.clear();
        ai.lastDef.addAll(ai.defensePoints);

        player.attackTarget = null;
        player.evasionPoint = null;
        player.defensePoints.clear();
        ai.defensePoints.clear();
    }

    private void resetVisuals() {
        player.lastAtk = null; player.lastEva = null; player.lastDef.clear(); player.lastReceivedHit = null;
        ai.lastHitWasGuarded = false; ai.lastHitWasEvaded = false;
        ai.lastAtk = null; ai.lastEva = null; ai.lastDef.clear();
    }

    private void checkGameOver() {
        if (player.globalHp <= 0 || ai.globalHp <= 0) {
            String msg = player.globalHp > 0 ? L10n.WIN : L10n.LOSS;
            
            // Отримуємо посилання на головне вікно
            JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
            
            // Викликаємо кастомне вікно
            GameOverDialog dialog = new GameOverDialog(mainFrame, msg);
            
            if (dialog.isRestartRequested()) {
                // Якщо обрано "Новий бій", закриваємо поточне вікно гри.
                // Оскільки в TacticalArena працює AppCycle, програма автоматично повернеться в меню.
                mainFrame.dispose();
            } else {
                System.exit(0);
            }
        }
    }
}