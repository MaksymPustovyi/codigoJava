public enum BodyPartType {
    HEAD(() -> L10n.BP_HEAD), CHEST(() -> L10n.BP_CHEST), ABDOMEN(() -> L10n.BP_ABDOMEN),
    L_ARM(() -> L10n.BP_L_ARM), R_ARM(() -> L10n.BP_R_ARM),
    L_LEG(() -> L10n.BP_L_LEG), R_LEG(() -> L10n.BP_R_LEG);

    private final java.util.function.Supplier<String> nameSupplier;
    BodyPartType(java.util.function.Supplier<String> supplier) { this.nameSupplier = supplier; }
    public String getName() { return nameSupplier.get(); }
}