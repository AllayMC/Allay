package org.allaymc.codegen;

import com.palantir.javapoet.*;
import lombok.SneakyThrows;
import org.allaymc.dependence.ItemId;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * {@code ItemClassGen} generates item type catalogs and missing stack skeletons
 * from the item identifier enumeration. Ordered aliases let related items share
 * one API interface and server implementation. Existing selected skeletons
 * survive regeneration so handwritten component methods remain available,
 * whereas the type catalog and default initializer are replaced on every
 * generation pass.
 *
 * <p>
 * Filenames follow alias selection, including special names that distinguish
 * otherwise colliding identifiers on case-insensitive filesystems. Cleanup
 * removes names absent from the current pass unless explicitly reserved, which
 * can also remove an obsolete handwritten extension. Writes and deletions are
 * sequential without rollback. The static initializer builder accumulates
 * statements across repeated calls in the same JVM.
 *
 * @author daoge_cmd | IWareQ
 */
public class ItemClassGen extends BaseClassGen {

    /**
     * {@code ITEM_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER} accumulates guarded
     * item registrations. {@code generate} appends statements without clearing
     * earlier calls in this JVM.
     */
    public static final MethodSpec.Builder ITEM_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER =
            MethodSpec.methodBuilder("init")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC);
    /**
     * {@code IGNORED_FILES} reserves the shared stack implementation and
     * package documentation during obsolete-file cleanup in both output
     * directories.
     */
    public static final Set<String> IGNORED_FILES = Set.of("ItemStackImpl.java", "package-info.java");

    /**
     * {@code MERGED_ITEMS} maps patterns to shared stack names in selection
     * order. {@code generateClassSimpleName} selects the first matching entry,
     * and callers can replace or mutate this public map.
     */
    public static Map<Pattern, String> MERGED_ITEMS = new LinkedHashMap<>();

    /**
     * Runs item source generation with paths relative to the repository root.
     * {@code args} is ignored; output replacement and obsolete-file cleanup
     * have the same effects as invoking {@link #generate()} directly.
     */
    public static void main(String[] args) {
        generate();
    }

    /**
     * Writes the item catalog, missing stack skeletons, and default
     * registrations from the loaded identifiers. Callers run this generator
     * from the repository root so the output paths select the intended API and
     * server source trees. Existing selected skeletons are preserved, but
     * unselected nonreserved files are removed before the initializer and
     * catalog are replaced.
     *
     * <p>
     * Alias selection determines both catalog field types and retained
     * filenames. Failures can leave earlier files changed, and repeated calls
     * accumulate initializer statements rather than rebuilding an empty method.
     */
    @SneakyThrows
    public static void generate() {
        registerMergedItems();

        var interfaceDir = Path.of("api/src/main/java/org/allaymc/api/item/interfaces");
        if (!Files.exists(interfaceDir)) {
            Files.createDirectories(interfaceDir);
        }
        var implDir = Path.of("server/src/main/java/org/allaymc/server/item/impl");
        if (!Files.exists(implDir)) {
            Files.createDirectories(implDir);
        }

        Set<String> generatedFiles = new HashSet<>();

        var typesClass = TypeSpec
                .classBuilder(TypeNames.ITEM_TYPES)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(TypeNames.MINECRAFT_VERSION_SENSITIVE);

        for (var id : ItemId.values()) {
            typesClass.addField(
                    FieldSpec.builder(ParameterizedTypeName.get(TypeNames.ITEM_TYPE, generateClassFullName(id)), id.name())
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                            .build()
            );

            var interfaceSimpleName = generateClassSimpleName(id);
            var interfaceFullName = generateClassFullName(id);
            var interfacePath = interfaceDir.resolve(interfaceSimpleName + ".java");
            generatedFiles.add(interfacePath.getFileName().toString());
            if (!Files.exists(interfacePath)) {
                System.out.println("Generating " + interfaceSimpleName + "...");
                if (!Files.exists(interfaceDir)) {
                    Files.createDirectories(interfaceDir);
                }
                generateInterface(TypeNames.ITEM_STACK, interfaceFullName, interfacePath);
            }

            var implSimpleName = generateClassSimpleName(id) + "Impl";
            var implFullName = ClassName.get("org.allaymc.server.item.impl", implSimpleName);
            var implPath = implDir.resolve(implSimpleName + ".java");
            generatedFiles.add(implPath.getFileName().toString());
            if (!Files.exists(implPath)) {
                System.out.println("Generating " + implSimpleName + "...");
                if (!Files.exists(implDir)) {
                    Files.createDirectories(implDir);
                }
                generateImpl(TypeNames.ITEM_STACK_IMPL, interfaceFullName, implFullName, TypeNames.ITEM_STACK_INIT_INFO, implPath);
            }

            addDefaultItemTypeInitializer(id, implFullName);
        }

        // Cleanup follows alias selection so every currently shared stack name
        // survives; obsolete names can include files previously extended by hand.
        deleteOldFiles(interfaceDir, generatedFiles, IGNORED_FILES);
        deleteOldFiles(implDir, generatedFiles, IGNORED_FILES);

        generateDefaultItemTypeInitializer();

        var javaFile = JavaFile.builder(TypeNames.ITEM_TYPES.packageName(), typesClass.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + TypeNames.ITEM_TYPES.simpleName() + ".java ...");
        Utils.writeFileWithCRLF(Path.of("api/src/main/java/org/allaymc/api/item/type/" + TypeNames.ITEM_TYPES.simpleName() + ".java"), javaFile.toString());
    }

    /**
     * Appends a default registration for the identifier and selected stack
     * class. The emitted {@code null} guard preserves a type installed before
     * initialization. {@code ITEM_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER}
     * retains this statement until the JVM exits.
     */
    private static void addDefaultItemTypeInitializer(ItemId id, ClassName itemClassName) {
        var initializer = CodeBlock.builder();
        initializer
                .add("$T.$N = $T\n", TypeNames.ITEM_TYPES, id.name(), TypeNames.ALLAY_ITEM_TYPE)
                .add("        .builder($T.class)\n", itemClassName)
                .add("        .vanillaItem($T.$N)\n", TypeNames.ITEM_ID, id.name())
                .add("        .build()");
        ITEM_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER
                .beginControlFlow("if ($T.$N == null)", TypeNames.ITEM_TYPES, id.name())
                .addStatement(initializer.build())
                .endControlFlow();
    }

    /**
     * Replaces the default initializer with the accumulated registration
     * method. The old file is deleted before serialization and writing, so a
     * later failure can leave it absent instead of preserving its previous
     * contents.
     */
    @SneakyThrows
    private static void generateDefaultItemTypeInitializer() {
        var filePath = Path.of("server/src/main/java/org/allaymc/server/item/type/ItemTypeDefaultInitializer.java");
        // Deletion precedes output construction; a failure below does not restore
        // the initializer, even though earlier skeleton generation may have succeeded.
        Files.deleteIfExists(filePath);
        var folderPath = filePath.getParent();
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        TypeSpec.Builder builder =
                TypeSpec.classBuilder(TypeNames.ITEM_TYPE_DEFAULT_INITIALIZER)
                        .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        builder.addMethod(ITEM_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER.build());

        var javaFile = JavaFile.builder(TypeNames.ITEM_TYPE_DEFAULT_INITIALIZER.packageName(), builder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + TypeNames.ITEM_TYPE_DEFAULT_INITIALIZER.simpleName() + ".java ...");
        Utils.writeFileWithCRLF(filePath, javaFile.toString());
    }

    /**
     * Selects a shared stack name or derives one from the identifier path.
     * {@code ItemId.NETHERBRICK} and {@code ItemId.TALLGRASS} receive distinct
     * names so their source paths do not collide with the underscored
     * identifiers on case-insensitive filesystems. Other paths replace dots
     * with underscores before Pascal-case conversion.
     *
     * <p>
     * {@code MERGED_ITEMS} patterns then inspect that Java name in map order;
     * the first match determines the interface and implementation basename.
     * These aliases intentionally share behavior classes while catalog fields
     * still retain their separate item identifiers for registration.
     */
    private static String generateClassSimpleName(ItemId id) {
        // These legacy spellings otherwise differ from the underscored item names
        // only by letter case, causing their source paths to collide on Windows.
        String origin = switch (id) {
            case NETHERBRICK -> "ItemNetherbrick0Stack";
            case TALLGRASS -> "ItemTallgrass0Stack";
            default -> "Item" + Utils.convertToPascalCase(id.getIdentifier().path().replace(".", "_")) + "Stack";
        };
        for (var entry : MERGED_ITEMS.entrySet()) {
            if (entry.getKey().matcher(origin).find()) {
                return entry.getValue();
            }
        }
        // An unmatched identifier keeps its own stack type, allowing a new item
        // to receive a skeleton before any shared alias is registered.
        return origin;
    }

    /**
     * Returns the selected stack name in the API interface package. Selection
     * uses the current entries in {@code MERGED_ITEMS}, so different
     * identifiers may intentionally resolve to the same Java type.
     */
    private static ClassName generateClassFullName(ItemId id) {
        return ClassName.get("org.allaymc.api.item.interfaces", generateClassSimpleName(id));
    }

    /**
     * Adds a pattern and its shared stack name to the alias map. In the default
     * insertion-ordered map, earlier matching patterns retain priority over
     * patterns registered later.
     */
    private static void registerMergedItem(Pattern regex, String className) {
        MERGED_ITEMS.put(regex, className);
    }

    /**
     * Registers the built-in stack aliases in their matching priority order.
     * Existing {@code MERGED_ITEMS} entries remain present, so caller-supplied
     * earlier patterns can take precedence over these appended defaults.
     */
    private static void registerMergedItems() {
        // Sign variants precede the general sign rule because the first matching
        // pattern selects the API interface shared by each generated item field.
        registerMergedItem(Pattern.compile(".*SpawnEggStack"), "ItemSpawnEggStack");
        registerMergedItem(Pattern.compile(".*SwordStack"), "ItemSwordStack");
        registerMergedItem(Pattern.compile(".*PickaxeStack"), "ItemPickaxeStack");
        registerMergedItem(Pattern.compile(".*ShovelStack"), "ItemShovelStack");
        registerMergedItem(Pattern.compile(".*HoeStack"), "ItemHoeStack");
        registerMergedItem(Pattern.compile(".*AxeStack"), "ItemAxeStack");
        registerMergedItem(Pattern.compile(".*ShulkerBoxStack"), "ItemShulkerBoxStack");
        registerMergedItem(Pattern.compile("^(?!.*MilkBucketStack$).*BucketStack$"), "ItemBucketStack");
        registerMergedItem(Pattern.compile("^(?!.*(Turtle|Leather)HelmetStack$).*HelmetStack$"), "ItemHelmetStack");
        registerMergedItem(Pattern.compile("^(?!.*LeatherChestplateStack$).*ChestplateStack$"), "ItemChestplateStack");
        registerMergedItem(Pattern.compile("^(?!.*LeatherLeggingsStack$).*LeggingsStack$"), "ItemLeggingsStack");
        registerMergedItem(Pattern.compile("^(?!.*LeatherBootsStack$).*BootsStack$"), "ItemBootsStack");
        registerMergedItem(Pattern.compile("^(?!.*LeatherHorseArmorStack$).*HorseArmorStack$"), "ItemHorseArmorStack");
        registerMergedItem(Pattern.compile(".*HangingSignStack"), "ItemHangingSignStack");
        registerMergedItem(Pattern.compile(".*WallSignStack"), "ItemWallSignStack");
        registerMergedItem(Pattern.compile(".*StandingSignStack"), "ItemStandingSignStack");
        registerMergedItem(Pattern.compile(".*SignStack"), "ItemSignStack");
        registerMergedItem(Pattern.compile(".*StairsStack"), "ItemStairsStack");
        registerMergedItem(Pattern.compile(".*DoorStack"), "ItemDoorStack");
        registerMergedItem(Pattern.compile(".*Slab\\d?Stack"), "ItemSlabStack");
        registerMergedItem(Pattern.compile(".*WallStack"), "ItemWallStack");
        registerMergedItem(Pattern.compile("ItemElement.*"), "ItemElementStack");
        registerMergedItem(Pattern.compile(".*CoralStack"), "ItemCoralStack");
        registerMergedItem(Pattern.compile(".*CoralBlockStack"), "ItemCoralBlockStack");
        registerMergedItem(Pattern.compile(".*CoralFan.*"), "ItemCoralFanStack");
        registerMergedItem(Pattern.compile(".*CoralWallFanStack"), "ItemCoralWallFanStack");
        registerMergedItem(Pattern.compile(".*BricksStack"), "ItemBricksStack");
        registerMergedItem(Pattern.compile(".*WoolStack"), "ItemWoolStack");
        registerMergedItem(Pattern.compile(".*ButtonStack"), "ItemButtonStack");
        registerMergedItem(Pattern.compile(".*PlanksStack"), "ItemPlanksStack");
        registerMergedItem(Pattern.compile(".*TrapdoorStack"), "ItemTrapdoorStack");
        registerMergedItem(Pattern.compile(".*CandleStack"), "ItemCandleStack");
        registerMergedItem(Pattern.compile(".*CandleCakeStack"), "ItemCandleCakeStack");
        registerMergedItem(Pattern.compile(".*ConcreteStack"), "ItemConcreteStack");
        registerMergedItem(Pattern.compile(".*ConcretePowderStack"), "ItemConcretePowderStack");
        registerMergedItem(Pattern.compile(".*TerracottaStack"), "ItemTerracottaStack");
        registerMergedItem(Pattern.compile(".*CarpetStack"), "ItemCarpetStack");
        registerMergedItem(Pattern.compile(".*CushionStack"), "ItemCushionStack");
        registerMergedItem(Pattern.compile(".*WoodStack"), "ItemWoodStack");
        registerMergedItem(Pattern.compile(".*FenceStack"), "ItemFenceStack");
        registerMergedItem(Pattern.compile(".*FenceGateStack"), "ItemFenceGateStack");
        registerMergedItem(Pattern.compile(".*(Leaves\\d?|LeavesFlowered)Stack"), "ItemLeavesStack");
        registerMergedItem(Pattern.compile(".*Log\\d?Stack"), "ItemLogStack");
        registerMergedItem(Pattern.compile(".*CopperStack"), "ItemCopperStack");
        registerMergedItem(Pattern.compile(".*SaplingStack"), "ItemSaplingStack");
        registerMergedItem(Pattern.compile(".*(?:Water|Lava)Stack"), "ItemLiquidStack");
        registerMergedItem(Pattern.compile(".*BoatStack"), "ItemBoatStack");
        registerMergedItem(Pattern.compile(".*MinecartStack"), "ItemMinecartStack");
        registerMergedItem(Pattern.compile("ItemMusicDisc.*"), "ItemMusicDiscStack");
        registerMergedItem(Pattern.compile("ItemPiston.*"), "ItemPistonStack");
        registerMergedItem(Pattern.compile("ItemStickyPiston.*"), "ItemStickyPistonStack");
        registerMergedItem(Pattern.compile(".*StainedGlassStack"), "ItemGlassStack");
        registerMergedItem(Pattern.compile(".*StainedGlassPaneStack"), "ItemGlassPaneStack");
        registerMergedItem(Pattern.compile(".*GlassStack"), "ItemGlassStack");
        registerMergedItem(Pattern.compile(".*GlassPaneStack"), "ItemGlassPaneStack");
        registerMergedItem(Pattern.compile(".*SandstoneStack"), "ItemSandstoneStack");
        registerMergedItem(Pattern.compile(".*SandStack"), "ItemSandStack");
        registerMergedItem(Pattern.compile(".*Torch(?!flower).*Stack"), "ItemTorchStack");
        registerMergedItem(Pattern.compile(".*LightBlock.*Stack"), "ItemLightBlockStack");
        registerMergedItem(Pattern.compile(".*DirtStack"), "ItemDirtStack");
        registerMergedItem(Pattern.compile(".*AnvilStack"), "ItemAnvilStack");
        registerMergedItem(Pattern.compile("ItemPurpur.*"), "ItemPurpurStack");
        registerMergedItem(Pattern.compile(".*SpongeStack"), "ItemSpongeStack");
        registerMergedItem(Pattern.compile(".*TntStack"), "ItemTntStack");
        registerMergedItem(Pattern.compile(".*(Head|Skull)Stack"), "ItemHeadStack");
        registerMergedItem(Pattern.compile(".*BundleStack"), "ItemBundleStack");
        registerMergedItem(Pattern.compile(".*(Furnace|Smoker)Stack"), "ItemFurnaceStack");
        registerMergedItem(Pattern.compile(".*HarnessStack"), "ItemHarnessStack");
        registerMergedItem(Pattern.compile(".*ShelfStack"), "ItemShelfStack");
        registerMergedItem(Pattern.compile(".*LightningRodStack"), "ItemLightningRodStack");
        registerMergedItem(Pattern.compile(".*CopperBarsStack"), "ItemCopperBarsStack");
        registerMergedItem(Pattern.compile("Item(?!.*Sea).*?LanternStack"), "ItemLanternStack");
        registerMergedItem(Pattern.compile(".*CopperGolemStatueStack"), "ItemCopperGolemStatueStack");
        registerMergedItem(Pattern.compile(".*CopperChainStack"), "ItemCopperChainStack");
        registerMergedItem(Pattern.compile(".*DyeStack"), "ItemDyeStack");
        registerMergedItem(Pattern.compile(".*SwordStack"), "ItemSwordStack");
        registerMergedItem(Pattern.compile(".*NautilusArmorStack"), "ItemNautilusArmorStack");
        registerMergedItem(Pattern.compile(".*SpearStack"), "ItemSpearStack");
        registerMergedItem(Pattern.compile(".*(Dandelion|Poppy|BlueOrchid|Allium|AzureBluet|RedTulip|OrangeTulip|WhiteTulip|PinkTulip|OxeyeDaisy|Cornflower|LilyOfTheValley|WitherRose|Torchflower|ClosedEyeblossom|OpenEyeblossom)Stack"), "ItemSmallFlowerStack");
        registerMergedItem(Pattern.compile(".*(Sunflower|Lilac|RoseBush|Peony|PitcherPlant)Stack"), "ItemBigFlowerStack");
        registerMergedItem(Pattern.compile(".*(LeafLitter|PinkPetals|Wildflowers)Stack"), "ItemPlantPileStack");
        registerMergedItem(Pattern.compile(".*CampfireStack"), "ItemCampfireStack");
        registerMergedItem(Pattern.compile(".*DaylightDetector.*Stack"), "ItemDaylightDetectorStack");
        registerMergedItem(Pattern.compile(".*RepeaterStack"), "ItemRepeaterStack");
        registerMergedItem(Pattern.compile(".*ComparatorStack"), "ItemComparatorStack");
        registerMergedItem(Pattern.compile(".*RedstoneLampStack"), "ItemRedstoneLampStack");
        registerMergedItem(Pattern.compile("Item(Egg|BlueEgg|BrownEgg)Stack"), "ItemEggStack");
    }
}
