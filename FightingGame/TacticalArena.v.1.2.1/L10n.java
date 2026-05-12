class L10n {
    public static String 
        // Metadata & Characters
        PLAYER_NAME, AI_NAME, GAME_TITLE, L_SUBTITLE,
        // Stats Labels
        HP_BLEEDING, ACCURACY, EVASION, POWER,
        // Body Parts
        BP_HEAD, BP_CHEST, BP_ABDOMEN, BP_L_ARM, BP_R_ARM, BP_L_LEG, BP_R_LEG,
        // UI Steps
        STEP_1_ATK, STEP_2_EVA, STEP_3_DEF, STEP_4_DEF,
        // Menus & Settings
        L_START, L_NAME, L_LANG, L_SETTINGS, L_RES_LABEL, L_SAVE,
        // Rules
        L_RULES_TITLE, L_RULES_DONE, L_RULE_1, L_RULE_2, L_RULE_3, L_RULE_4,
        // Game Over & Confirmation
        WIN, LOSS, L_RESTART, L_EXIT_GAME, L_QUIT_MATCH, L_CONFIRM_TITLE, L_CONFIRM_MSG, L_YES, L_NO,
        // Detailed Log (HTML)
        LOG_TITLE, LOG_ROUND, LOG_ACTION, LOG_HIT, LOG_MISS, LOG_EVADE, LOG_EVADE_FAIL, LOG_BLEED_LOST, LOG_GUARD,
        // Consequences (HTML)
        CONS_ACCURACY, CONS_EVASION, CONS_POWER, CONS_BLEED,
        // Short Status Bar
        SHORT_HIT, SHORT_GUARD, SHORT_MISS, SHORT_EVADE;

    public static void setLocale(int langIndex) {
        if (langIndex == 0) { // Українська
            GAME_TITLE = "Tactical Arena Pro"; L_SUBTITLE = "Готуйся до бою";
            PLAYER_NAME = "Гравець"; AI_NAME = "Комп'ютер";
            HP_BLEEDING = "Кровотеча: -"; ACCURACY = "Точність: "; EVASION = "Ухилення: "; POWER = "Сила: ";
            BP_HEAD = "Голова"; BP_CHEST = "Груди"; BP_ABDOMEN = "Живіт";
            BP_L_ARM = "Л. Рука"; BP_R_ARM = "П. Рука"; BP_L_LEG = "Л. Нога"; BP_R_LEG = "П. Нога";
            STEP_1_ATK = "Крок 1: Оберіть точку АТАКИ (⚔️)"; STEP_2_EVA = "Крок 2: Оберіть точку УХИЛЕННЯ (🏃)";
            STEP_3_DEF = "Крок 3: Перший ЗАХИСТ (🛡️)"; STEP_4_DEF = "Крок 4: Другий ЗАХИСТ (🛡️)";
            L_START = "ПОЧАТИ ГРУ"; L_NAME = "ІМ'Я ГРАВЦЯ"; L_LANG = "МОВА"; L_SETTINGS = "НАЛАШТУВАННЯ"; L_RES_LABEL = "РОЗДІЛЬНА ЗДАТНІСТЬ"; L_SAVE = "ЗБЕРЕГТИ";
            L_RULES_TITLE = "ПРАВИЛА АРЕНИ"; L_RULES_DONE = "Я ГОТОВИЙ ДО БОЮ";
            L_RULE_1 = "<html><b>Фази ходу:</b> Спершу обери точку атаки на ворогу, потім точку ухилення на собі, і нарешті — дві точки захисту.</html>";
            L_RULE_2 = "<html><b>Захист:</b> Влучання в незахищену зону завдає 100% урону. Захищена зона поглинає 20%. Успішне ухилення скасовує урон.</html>";
            L_RULE_3 = "<html><b>Травми:</b> Пошкодження голови знижує точність. Рук — силу атаки. Ніг — шанс ухилення.</html>";
            L_RULE_4 = "<html><b>Кровотеча:</b> Влучання в тулуб викликає кровотечу. Максимальна кровотеча — 10% від поточного здоров'я.</html>";
            WIN = "ПЕРЕМОГА!"; LOSS = "ПОРАЗКА!"; L_RESTART = "НОВИЙ БІЙ"; L_EXIT_GAME = "ВИЙТИ З ГРИ";
            L_QUIT_MATCH = "ПОКИНУТИ БІЙ"; L_CONFIRM_TITLE = "ПІДТВЕРДЖЕННЯ"; L_CONFIRM_MSG = "Ви впевнені, що хочете перервати бій?"; L_YES = "ТАК, ВИЙТИ"; L_NO = "НІ, ГРАТИ";
            LOG_TITLE = "ЖУРНАЛ БОЮ"; LOG_ROUND = "<div style='margin-top:10px; color:#50A0FF; font-weight:bold;'>РАУНД №%d</div>";
            LOG_ACTION = "<div style='margin-left:10px;'>%s цілить у %s";
            LOG_HIT = "<div style='margin-left:10px;'>%s цілить у %s%s -> <b style='color:#FF5050;'>-%d HP</b></div>";
            LOG_MISS = "<span style='color:#999999;'> -> ПРОМАХ</span></div>";
            LOG_EVADE = "<span style='color:#50FFA0;'> -> УХИЛЕННЯ</span></div>";
            LOG_EVADE_FAIL = "<div style='margin-left:20px; color:#FFA500; font-size:11px;'>! Напрямок вгадано, але не вистачило спритності!</div>";
            LOG_BLEED_LOST = "<div style='color:#FF4040; font-weight:bold;'>• %s втратив %d HP (кровотеча)</div>";
            LOG_GUARD = " (Захист)";
            CONS_ACCURACY = "<div style='margin-left:25px; color:#FFD700; font-size:11px;'>• Точність знизилась на %.1f%%</div>";
            CONS_EVASION = "<div style='margin-left:25px; color:#FFD700; font-size:11px;'>• Ухилення знизилось на %.1f%%</div>";
            CONS_POWER = "<div style='margin-left:25px; color:#FFD700; font-size:11px;'>• Сила впала на %.1f%%</div>";
            CONS_BLEED = "<div style='margin-left:25px; color:#FF6060; font-size:11px;'>• Кровотеча посилилась (+%.1f)</div>";
            SHORT_HIT = "%s -> %s: -%d"; SHORT_GUARD = "%s -> %s (🛡️): -%d"; SHORT_MISS = "%s: ПРОМАХ"; SHORT_EVADE = "%s: УХИЛЕННЯ (🏃)";

        } else if (langIndex == 1) { // English
            GAME_TITLE = "Tactical Arena Pro"; L_SUBTITLE = "Prepare for battle";
            PLAYER_NAME = "Player"; AI_NAME = "Computer";
            HP_BLEEDING = "Bleeding: -"; ACCURACY = "Accuracy: "; EVASION = "Evasion: "; POWER = "Power: ";
            BP_HEAD = "Head"; BP_CHEST = "Chest"; BP_ABDOMEN = "Abdomen";
            BP_L_ARM = "L. Arm"; BP_R_ARM = "R. Arm"; BP_L_LEG = "L. Leg"; BP_R_LEG = "R. Leg";
            STEP_1_ATK = "Step 1: Choose ATTACK point (⚔️)"; STEP_2_EVA = "Step 2: Choose EVASION point (🏃)";
            STEP_3_DEF = "Step 3: First DEFENSE (🛡️)"; STEP_4_DEF = "Step 4: Second DEFENSE (🛡️)";
            L_START = "START GAME"; L_NAME = "PLAYER NAME"; L_LANG = "LANGUAGE"; L_SETTINGS = "SETTINGS"; L_RES_LABEL = "RESOLUTION"; L_SAVE = "SAVE";
            L_RULES_TITLE = "ARENA RULES"; L_RULES_DONE = "I AM READY";
            L_RULE_1 = "<html><b>Phases:</b> First pick an attack point on the enemy, then evasion on yourself, and finally two defense points.</html>";
            L_RULE_2 = "<html><b>Defense:</b> Hits to unprotected areas deal 100% damage. Guarded zones absorb 20%. Evasion negates all damage.</html>";
            L_RULE_3 = "<html><b>Injuries:</b> Head damage lowers accuracy. Arms — attack power. Legs — evasion chance.</html>";
            L_RULE_4 = "<html><b>Bleeding:</b> Torso hits cause bleeding. Max bleeding is capped at 10% of current HP.</html>";
            WIN = "VICTORY!"; LOSS = "DEFEAT!"; L_RESTART = "NEW BATTLE"; L_EXIT_GAME = "EXIT GAME";
            L_QUIT_MATCH = "QUIT MATCH"; L_CONFIRM_TITLE = "CONFIRMATION"; L_CONFIRM_MSG = "Are you sure you want to quit the battle?"; L_YES = "YES, QUIT"; L_NO = "NO, STAY";
            LOG_TITLE = "COMBAT LOG"; LOG_ROUND = "<div style='margin-top:10px; color:#50A0FF; font-weight:bold;'>ROUND #%d</div>";
            LOG_ACTION = "<div style='margin-left:10px;'>%s aims at %s";
            LOG_HIT = "<div style='margin-left:10px;'>%s aims at %s%s -> <b style='color:#FF5050;'>-%d HP</b></div>";
            LOG_MISS = "<span style='color:#999999;'> -> MISS</span></div>";
            LOG_EVADE = "<span style='color:#50FFA0;'> -> EVADED</span></div>";
            LOG_EVADE_FAIL = "<div style='margin-left:20px; color:#FFA500; font-size:11px;'>! Direction guessed, but not fast enough!</div>";
            LOG_BLEED_LOST = "<div style='color:#FF4040; font-weight:bold;'>• %s lost %d HP (bleeding)</div>";
            LOG_GUARD = " (Guarded)";
            CONS_ACCURACY = "<div style='margin-left:25px; color:#FFD700; font-size:11px;'>• Accuracy decreased by %.1f%%</div>";
            CONS_EVASION = "<div style='margin-left:25px; color:#FFD700; font-size:11px;'>• Evasion decreased by %.1f%%</div>";
            CONS_POWER = "<div style='margin-left:25px; color:#FFD700; font-size:11px;'>• Power decreased by %.1f%%</div>";
            CONS_BLEED = "<div style='margin-left:25px; color:#FF6060; font-size:11px;'>• Bleeding increased (+%.1f)</div>";
            SHORT_HIT = "%s -> %s: -%d"; SHORT_GUARD = "%s -> %s (🛡️): -%d"; SHORT_MISS = "%s: MISS"; SHORT_EVADE = "%s: EVADED (🏃)";

        } else { // Spanish
            GAME_TITLE = "Tactical Arena Pro"; L_SUBTITLE = "Prepárate para la batalla";
            PLAYER_NAME = "Jugador"; AI_NAME = "Ordenador";
            HP_BLEEDING = "Sangrado: -"; ACCURACY = "Puntería: "; EVASION = "Evasión: "; POWER = "Fuerza: ";
            BP_HEAD = "Cabeza"; BP_CHEST = "Pecho"; BP_ABDOMEN = "Abdomen";
            BP_L_ARM = "Brazo I."; BP_R_ARM = "Brazo D."; BP_L_LEG = "Pierna I."; BP_R_LEG = "Pierna D.";
            STEP_1_ATK = "Paso 1: Elige punto de ATAQUE (⚔️)"; STEP_2_EVA = "Paso 2: Elige EVASIÓN (🏃)";
            STEP_3_DEF = "Paso 3: Primera DEFENSA (🛡️)"; STEP_4_DEF = "Paso 4: Segunda DEFENSA (🛡️)";
            L_START = "JUGAR"; L_NAME = "NOMBRE DEL JUGADOR"; L_LANG = "IDIOMA"; L_SETTINGS = "AJUSTES"; L_RES_LABEL = "RESOLUCIÓN"; L_SAVE = "GUARDAR";
            L_RULES_TITLE = "REGLAS DE LA ARENA"; L_RULES_DONE = "ESTOY LISTO";
            L_RULE_1 = "<html><b>Fases:</b> Elige un punto de ataque al enemigo, luego uno de evasión y finalmente dos de defensa.</html>";
            L_RULE_2 = "<html><b>Defensa:</b> Los ataques sin protección causan 100% de daño. Bloqueo absorbe 20%. Evasión anula todo.</html>";
            L_RULE_3 = "<html><b>Lesiones:</b> Daño en cabeza baja la puntería. Brazos — fuerza. Piernas — evasión.</html>";
            L_RULE_4 = "<html><b>Sangrado:</b> Los golpes al torso causan sangrado. Máximo sangrado es 10% de la salud actual.</html>";
            WIN = "¡VICTORIA!"; LOSS = "¡DERROTA!"; L_RESTART = "NUEVA BATALLA"; L_EXIT_GAME = "SALIR DEL JUEGO";
            L_QUIT_MATCH = "ABANDONAR"; L_CONFIRM_TITLE = "CONFIRMACIÓN"; L_CONFIRM_MSG = "¿Estás seguro de que quieres abandonar?"; L_YES = "SÍ, SALIR"; L_NO = "NO, JUGAR";
            LOG_TITLE = "REGISTRO"; LOG_ROUND = "<div style='margin-top:10px; color:#50A0FF; font-weight:bold;'>RONDA №%d</div>";
            LOG_ACTION = "<div style='margin-left:10px;'>%s apunta a %s";
            LOG_HIT = "<div style='margin-left:10px;'>%s apunta a %s%s -> <b style='color:#FF5050;'>-%d PS</b></div>";
            LOG_MISS = "<span style='color:#999999;'> -> FALLÓ</span></div>";
            LOG_EVADE = "<span style='color:#50FFA0;'> -> EVADIDO</span></div>";
            LOG_EVADE_FAIL = "<div style='margin-left:20px; color:#FFA500; font-size:11px;'>! Dirección acertada, ¡pero no fue suficientemente rápido!</div>";
            LOG_BLEED_LOST = "<div style='color:#FF4040; font-weight:bold;'>• %s perdió %d PS (sangrado)</div>";
            LOG_GUARD = " (Defensa)";
            CONS_ACCURACY = "<div style='margin-left:25px; color:#FFD700; font-size:11px;'>• Puntería disminuida un %.1f%%</div>";
            CONS_EVASION = "<div style='margin-left:25px; color:#FFD700; font-size:11px;'>• Evasión disminuida un %.1f%%</div>";
            CONS_POWER = "<div style='margin-left:25px; color:#FFD700; font-size:11px;'>• Fuerza disminuida un %.1f%%</div>";
            CONS_BLEED = "<div style='margin-left:25px; color:#FF6060; font-size:11px;'>• El sangrado aumentó (+%.1f)</div>";
            SHORT_HIT = "%s -> %s: -%d"; SHORT_GUARD = "%s -> %s (🛡️): -%d"; SHORT_MISS = "%s: FALLÓ"; SHORT_EVADE = "%s: EVADIDO (🏃)";
        }
    }
}