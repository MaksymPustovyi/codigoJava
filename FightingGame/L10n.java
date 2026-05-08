// ==========================================
// 1. LOCALIZACIÓN (TODO EL TEXTO DEL JUEGO)
// ==========================================
class L10n {
    // Nombres de personajes y títulos
    public static final String PLAYER_NAME = "Jugador";
    public static final String AI_NAME = "Ordenador"; // O "IA" si prefieres algo más moderno
    public static final String GAME_TITLE = "Tactical Arena - Combate de Supervivencia";
    
    // Estadísticas
    public static final String HP_BLEEDING = "Sangrado: -";
    public static final String ACCURACY = "Precisión: ";
    public static final String EVASION = "Evasión: ";
    public static final String POWER = "Fuerza de ataque: ";
    
    // Pasos de elección
    public static final String STEP_1_ATK = "Paso 1: Elige punto de ATAQUE en el enemigo (⚔️)";
    public static final String STEP_2_EVA = "Paso 2: Elige punto de EVASIÓN (🏃) sobre ti";
    public static final String STEP_3_DEF = "Paso 3: Elige el PRIMER punto de DEFENSA (🛡️)";
    public static final String STEP_4_DEF = "Paso 4: Elige el SEGUNDO punto de DEFENSA (🛡️)";
    
    // Nombres de partes del cuerpo
    public static final String BP_HEAD = "Cabeza";
    public static final String BP_CHEST = "Pecho";
    public static final String BP_ABDOMEN = "Abdomen";
    public static final String BP_L_ARM = "Brazo izquierdo";
    public static final String BP_R_ARM = "Brazo derecho";
    public static final String BP_L_LEG = "Pierna izquierda";
    public static final String BP_R_LEG = "Pierna derecha";

    // Registro de combate (Combat Log)
    public static final String LOG_ROUND = "\n=== RONDA №%d ===\n";
    public static final String LOG_BLEED_LOST = "%s ha perdido %d HP por sangrado 🩸\n";
    public static final String LOG_ACTION = "%s apunta a %s";
    public static final String LOG_MISS = " -> FALLO 💨\n";
    public static final String LOG_EVADE = " -> EVASIÓN 🏃\n";
    public static final String LOG_HIT = " -> %s: -%d HP\n";
    public static final String LOG_GUARD = " (🛡️ Defensa)";
    
    // Consecuencias (Consequences)
    public static final String CONS_ACCURACY = " > Consecuencia: La precisión de %s baja un %.1f%%\n";
    public static final String CONS_EVASION = " > Consecuencia: La evasión de %s baja un %.1f%%\n";
    public static final String CONS_POWER = " > Consecuencia: La fuerza de %s baja un %.1f%%\n";
    
    // Resultado actual (Log superior)
    public static final String SHORT_HIT = "%s -> %s: -%d";
    public static final String SHORT_GUARD = "%s -> %s (🛡️): -%d";
    public static final String SHORT_MISS = "%s: FALLO";
    public static final String SHORT_EVADE = "%s: EL ENEMIGO EVADIÓ";

    // Final
    public static final String WIN = "¡VICTORIA!";
    public static final String LOSS = "¡DERROTA!";
}

/* // ==========================================
// 1. ЛОКАЛІЗАЦІЯ (ВСІ ТЕКСТИ ГРИ)
// ==========================================
class L10n {
    // Назви персонажів та заголовки
    public static final String PLAYER_NAME = "Гравець";
    public static final String AI_NAME = "Комп'ютер";
    public static final String GAME_TITLE = "Tactical Arena - Бій на Виживання";
    
    // Характеристики
    public static final String HP_BLEEDING = "Кровотеча: -";
    public static final String ACCURACY = "Точність: ";
    public static final String EVASION = "Ухилення: ";
    public static final String POWER = "Сила удару: ";
    
    // Кроки вибору
    public static final String STEP_1_ATK = "Крок 1: Оберіть точку АТАКИ на ворогові (⚔️)";
    public static final String STEP_2_EVA = "Крок 2: Оберіть точку УХИЛЕННЯ (🏃) на собі";
    public static final String STEP_3_DEF = "Крок 3: Оберіть ПЕРШУ точку ЗАХИСТУ (🛡️)";
    public static final String STEP_4_DEF = "Крок 4: Оберіть ДРУГУ точку ЗАХИСТУ (🛡️)";
    
    // Назви частин тіла
    public static final String BP_HEAD = "Голова";
    public static final String BP_CHEST = "Груди";
    public static final String BP_ABDOMEN = "Живіт";
    public static final String BP_L_ARM = "Ліва рука";
    public static final String BP_R_ARM = "Права рука";
    public static final String BP_L_LEG = "Ліва нога";
    public static final String BP_R_LEG = "Права нога";

    // Журнал бою (Combat Log)
    public static final String LOG_ROUND = "\n=== РАУНД №%d ===\n";
    public static final String LOG_BLEED_LOST = "%s втратив %d HP від кровотечі 🩸\n";
    public static final String LOG_ACTION = "%s цілить у %s";
    public static final String LOG_MISS = " -> ПРОМАХ 💨\n";
    public static final String LOG_EVADE = " -> УХИЛЕННЯ 🏃\n";
    public static final String LOG_HIT = " -> %s: -%d HP\n";
    public static final String LOG_GUARD = " (🛡️ Захист)";
    
    // Наслідки (Consequences)
    public static final String CONS_ACCURACY = " > Наслідок: Точність %s знизилась на %.1f%%\n";
    public static final String CONS_EVASION = " > Наслідок: Шанс ухилення %s знизився на %.1f%%\n";
    public static final String CONS_POWER = " > Наслідок: Сила %s впала на %.1f%%\n";
    
    // Поточний результат (Верхній лог)
    public static final String SHORT_HIT = "%s -> %s: -%d";
    public static final String SHORT_GUARD = "%s -> %s (🛡️): -%d";
    public static final String SHORT_MISS = "%s: ПРОМАХ";
    public static final String SHORT_EVADE = "%s: УХИЛЕННЯ ВОРОГА";

    // Фінал
    public static final String WIN = "ПЕРЕМОГА!";
    public static final String LOSS = "ПОРАЗКА!";
} */