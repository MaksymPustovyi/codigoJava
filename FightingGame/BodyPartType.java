// ==========================================
// 3. ТИПИ ЧАСТИН ТІЛА
// ==========================================
enum BodyPartType {
    HEAD(L10n.BP_HEAD), CHEST(L10n.BP_CHEST), ABDOMEN(L10n.BP_ABDOMEN),
    L_ARM(L10n.BP_L_ARM), R_ARM(L10n.BP_R_ARM),
    L_LEG(L10n.BP_L_LEG), R_LEG(L10n.BP_R_LEG);
    private final String name;
    BodyPartType(String name) { this.name = name; }
    public String getName() { return name; }
}