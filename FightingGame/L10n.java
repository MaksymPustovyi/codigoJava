class L10n {
    public static String PLAYER_NAME, AI_NAME, GAME_TITLE, HP_BLEEDING, ACCURACY, EVASION, POWER;
    public static String STEP_1_ATK, STEP_2_EVA, STEP_3_DEF, STEP_4_DEF;
    public static String BP_HEAD, BP_CHEST, BP_ABDOMEN, BP_L_ARM, BP_R_ARM, BP_L_LEG, BP_R_LEG;
    public static String LOG_ROUND, LOG_BLEED_LOST, LOG_ACTION, LOG_MISS, LOG_EVADE, LOG_HIT, LOG_GUARD;
    public static String CONS_ACCURACY, CONS_EVASION, CONS_POWER, CONS_BLEED, SHORT_HIT, SHORT_GUARD, SHORT_MISS,
            SHORT_EVADE, WIN, LOSS;
    public static String L_START, L_NAME, L_LANG, L_SUBTITLE;
    public static String L_RULES_TITLE, L_RULES_DONE, L_RULE_1, L_RULE_2, L_RULE_3, L_RULE_4;
    public static String LOG_TITLE;

    public static void setLocale(int langIndex) {
        if (langIndex == 0) { // Українська
            PLAYER_NAME = "Гравець";
            AI_NAME = "Комп'ютер";
            GAME_TITLE = "Tactical Arena Pro";
            HP_BLEEDING = "Кровотеча: -";
            ACCURACY = "Точність: ";
            EVASION = "Ухилення: ";
            POWER = "Сила: ";

            // Залишаємо емодзі в інтерфейсі вибору
            STEP_1_ATK = "Крок 1: Оберіть точку АТАКИ (⚔️)";
            STEP_2_EVA = "Крок 2: Оберіть точку УХИЛЕННЯ (🏃)";
            STEP_3_DEF = "Крок 3: Перший ЗАХИСТ (🛡️)";
            STEP_4_DEF = "Крок 4: Другий ЗАХИСТ (🛡️)";

            L_START = "ПОЧАТИ ГРУ";
            L_NAME = "ІМ'Я ГРАВЦЯ";
            L_LANG = "МОВА";
            L_SUBTITLE = "Готуйся до бою";

            BP_HEAD = "Голова";
            BP_CHEST = "Груди";
            BP_ABDOMEN = "Живіт";
            BP_L_ARM = "Л. Рука";
            BP_R_ARM = "П. Рука";
            BP_L_LEG = "Л. Нога";
            BP_R_LEG = "П. Нога";

            // ВИДАЛЕНО емодзі для розширеного логу (LOG_...)
            LOG_ROUND = "\n=== РАУНД №%d ===\n";
            LOG_HIT = " -> %s: -%d HP\n";
            LOG_GUARD = " (Захист)";
            LOG_BLEED_LOST = "%s втратив %d HP від кровотечі\n";
            LOG_ACTION = "%s цілить у %s";
            LOG_MISS = " -> ПРОМАХ\n";
            LOG_EVADE = " -> УХИЛЕННЯ\n";

            CONS_ACCURACY = " > Наслідок: Точність %s знизилась на %.1f%%\n";
            CONS_EVASION = " > Наслідок: Шанс ухилення %s знизився на %.1f%%\n";
            CONS_POWER = " > Наслідок: Сила %s впала на %.1f%%\n";
            CONS_BLEED = " > Наслідок: У %s посилилась кровотеча (+%.1f)\n";

            WIN = "ПЕРЕМОГА!";
            LOSS = "ПОРАЗКА!";
            
            L_RULES_TITLE = "Правила Арени:";

            // Залишаємо емодзі у верхньому короткому лозі
            SHORT_HIT = "%s -> %s: -%d";
            SHORT_GUARD = "%s -> %s (🛡️): -%d";
            SHORT_MISS = "%s: ПРОМАХ";
            SHORT_EVADE = "%s: УХИЛЕННЯ (🏃)";

            L_RULES_TITLE = "ПРАВИЛА АРЕНИ";
            L_RULES_DONE = "Я ГОТОВИЙ ДО БОЮ";
            L_RULE_1 = "<html><b>Фази ходу:</b> Спершу обери точку атаки на ворогу, потім точку ухилення на собі, і нарешті — дві точки захисту.</html>";
            L_RULE_2 = "<html><b>Захист:</b> Влучання в незахищену зону завдає 100% урону. Захищена зона (🛡️) поглинає 20% урону. Успішне ухилення (🏃) скасовує урон повністю.</html>";
            L_RULE_3 = "<html><b>Травми:</b> Пошкодження голови знижує точність. Пошкодження рук знижує силу атаки. Пошкодження ніг знижує шанс ухилення.</html>";
            L_RULE_4 = "<html><b>Кровотеча:</b> Влучання в тулуб викликає кровотечу, яка забирає HP щоходу. Максимальна кровотеча — 10% від твого поточного здоров'я.</html>";

            LOG_TITLE = "ЖУРНАЛ БОЮ";

        } else if (langIndex == 1) { // English
            PLAYER_NAME = "Player";
            AI_NAME = "AI";
            GAME_TITLE = "Tactical Arena Pro";
            HP_BLEEDING = "Bleeding: -";
            ACCURACY = "Accuracy: ";
            EVASION = "Evasion: ";
            POWER = "Power: ";
            STEP_1_ATK = "Step 1: Choose ATTACK (⚔️)";
            STEP_2_EVA = "Step 2: Choose EVASION (🏃)";
            STEP_3_DEF = "Step 3: First DEFENSE (🛡️)";
            STEP_4_DEF = "Step 4: Second DEFENSE (🛡️)";
            BP_HEAD = "Head";
            BP_CHEST = "Chest";
            BP_ABDOMEN = "Abdomen";
            BP_L_ARM = "L. Arm";
            BP_R_ARM = "R. Arm";
            BP_L_LEG = "L. Leg";
            BP_R_LEG = "R. Leg";
            L_START = "START GAME";
            L_NAME = "PLAYER NAME";
            L_LANG = "LANGUAGE";
            L_SUBTITLE = "Enter the battlefield";

            // LOG without emojis
            LOG_ROUND = "\n=== ROUND №%d ===\n";
            LOG_HIT = " -> %s: -%d HP\n";
            LOG_GUARD = " (Guard)";
            LOG_BLEED_LOST = "%s lost %d HP from bleeding\n";
            LOG_ACTION = "%s aims at %s";
            LOG_MISS = " -> MISS\n";
            LOG_EVADE = " -> EVADED\n";

            CONS_ACCURACY = " > Consequence: %s's accuracy decreased by %.1f%%\n";
            CONS_EVASION = " > Consequence: %s's evasion decreased by %.1f%%\n";
            CONS_POWER = " > Consequence: %s's power decreased by %.1f%%\n";
            CONS_BLEED = " > Consequence: %s is bleeding more (+%.1f)\n";
            WIN = "VICTORY!";
            LOSS = "DEFEAT!";
            
            L_RULES_TITLE = "Rules:";
            SHORT_HIT = "%s -> %s: -%d";
            SHORT_GUARD = "%s -> %s (🛡️): -%d";
            SHORT_MISS = "%s: MISS";
            SHORT_EVADE = "%s: EVADED (🏃)";

            L_RULES_TITLE = "ARENA RULES";
            L_RULES_DONE = "I AM READY";
            L_RULE_1 = "<html><b>Turn Phases:</b> First pick an attack point on the enemy, then an evasion point on yourself, and finally two defense points.</html>";
            L_RULE_2 = "<html><b>Defense:</b> Hits to unprotected areas deal 100% damage. Guarded zones (🛡️) absorb 20%. Successful evasion (🏃) negates all damage.</html>";
            L_RULE_3 = "<html><b>Injuries:</b> Head injuries lower accuracy. Arm injuries reduce attack power. Leg injuries decrease evasion chance.</html>";
            L_RULE_4 = "<html><b>Bleeding:</b> Torso hits cause bleeding that drains HP every turn. Max bleeding is capped at 10% of current HP.</html>";

            LOG_TITLE = "COMBAT LOG";

        } else { // Spanish
            PLAYER_NAME = "Jugador";
            AI_NAME = "IA";
            GAME_TITLE = "Tactical Arena Pro";
            HP_BLEEDING = "Sangrado: -";
            ACCURACY = "Precisión: ";
            EVASION = "Evasión: ";
            POWER = "Fuerza: ";
            STEP_1_ATK = "Paso 1: Punto de ATAQUE (⚔️)";
            STEP_2_EVA = "Paso 2: Punto de EVASIÓN (🏃)";
            STEP_3_DEF = "Paso 3: Primera DEFENSA (🛡️)";
            STEP_4_DEF = "Paso 4: Segunda DEFENSA (🛡️)";
            BP_HEAD = "Cabeza";
            BP_CHEST = "Pecho";
            BP_ABDOMEN = "Abdomen";
            BP_L_ARM = "Brazo I.";
            BP_R_ARM = "Brazo D.";
            BP_L_LEG = "Pierna I.";
            BP_R_LEG = "Pierna D.";

            L_START = "JUGAR";
            L_NAME = "NOMBRE DEL JUGADOR";
            L_LANG = "IDIOMA";
            L_SUBTITLE = "Entra al campo de batalla";

            // LOG without emojis
            LOG_ROUND = "\n=== RONDA №%d ===\n";
            LOG_HIT = " -> %s: -%d HP\n";
            LOG_GUARD = " (Defensa)";
            LOG_BLEED_LOST = "%s perdió %d PS por sangrado\n";
            LOG_ACTION = "%s apunta a %s";
            LOG_MISS = " -> FALLÓ\n";
            LOG_EVADE = " -> EVADIDO\n";

            CONS_ACCURACY = " > Consecuencia: Precisión de %s bajó %.1f%%\n";
            CONS_EVASION = " > Consecuencia: Evasión de %s bajó %.1f%%\n";
            CONS_POWER = " > Consecuencia: Fuerza de %s bajó %.1f%%\n";
            CONS_BLEED = " > Consecuencia: %s sangra más (+%.1f)\n";
            WIN = "¡VICTORIA!";
            LOSS = "¡DERROTA!";
            
            L_RULES_TITLE = "Reglas:";
            SHORT_HIT = "%s -> %s: -%d";
            SHORT_GUARD = "%s -> %s (🛡️): -%d";
            SHORT_MISS = "%s: FALLÓ";
            SHORT_EVADE = "%s: EVADIDO (🏃)";

            L_RULES_TITLE = "REGLAS DE LA ARENA";
            L_RULES_DONE = "ESTOY LISTO";
            L_RULE_1 = "<html><b>Fases:</b> Elige un punto de ataque, luego uno de evasión y finalmente dos de defensa.</html>";
            L_RULE_2 = "<html><b>Defensa:</b> Los ataques sin protección causan 100% de daño. Las zonas protegidas (🛡️) absorben el 20%. La evasión (🏃) anula todo el daño.</html>";
            L_RULE_3 = "<html><b>Lesiones:</b> Daño en la cabeza baja la puntería. Daño en brazos baja la fuerza. Daño en piernas baja la evasión.</html>";
            L_RULE_4 = "<html><b>Sangrado:</b> Los golpes al torso causan sangrado. El sangrado máximo es el 10% de tu salud actual.</html>";

            LOG_TITLE = "REGISTRO DE COMBATE";
            
        }
    }
}