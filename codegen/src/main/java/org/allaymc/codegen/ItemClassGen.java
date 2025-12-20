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
 * @author daoge_cmd | IWareQ
 */
public class ItemClassGen extends BaseClassGen {

    public static final MethodSpec.Builder ITEM_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER =
            MethodSpec.methodBuilder("init")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC);
    public static final Set<String> IGNORED_FILES = Set.of("ItemStackImpl.java", "ItemBlockImpl.java", "package-info.java");

    public static Map<Pattern, String> MERGED_ITEMS = new LinkedHashMap<>();

    public static void main(String[] args) {
        generate();
    }

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

    @SneakyThrows
    private static void generateDefaultItemTypeInitializer() {
        var filePath = Path.of("server/src/main/java/org/allaymc/server/item/type/ItemTypeDefaultInitializer.java");
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

    private static String generateClassSimpleName(ItemId id) {
        String origin = switch (id) {
            // The Windows environment is not case-sensitive, so some item IDs need to be specially processed.
            // netherbrick and nether_brick require special handling
            case NETHERBRICK -> "ItemNetherbrick0Stack";
            // tallgrass and tall_grass require special handling
            case TALLGRASS -> "ItemTallgrass0Stack";
            default -> "Item" + Utils.convertToPascalCase(id.getIdentifier().path().replace(".", "_")) + "Stack";
        };
        for (var entry : MERGED_ITEMS.entrySet()) {
            if (entry.getKey().matcher(origin).find()) {
                return entry.getValue();
            }
        }
        return origin;
    }

    private static ClassName generateClassFullName(ItemId id) {
        return ClassName.get("org.allaymc.api.item.interfaces", generateClassSimpleName(id));
    }

    private static void registerMergedItem(Pattern regex, String className) {
        MERGED_ITEMS.put(regex, className);
    }

    private static void registerMergedItems() {
        registerMergedItem(Pattern.compile(".*SpawnEggStack"), "ItemSpawnEggStack");
        registerMergedItem(Pattern.compile(".*SwordStack"), "ItemSwordStack");
        registerMergedItem(Pattern.compile(".*PickaxeStack"), "ItemPickaxeStack");
        registerMergedItem(Pattern.compile(".*ShovelStack"), "ItemShovelStack");
        registerMergedItem(Pattern.compile(".*HoeStack"), "ItemHoeStack");
        registerMergedItem(Pattern.compile(".*AxeStack"), "ItemAxeStack");
        registerMergedItem(Pattern.compile(".*ShulkerBoxStack"), "ItemShulkerBoxStack");
        registerMergedItem(Pattern.compile("^(?!.*MilkBucketStack$).*BucketStack$"), "ItemBucketStack");
        registerMergedItem(Pattern.compile("^(?!.*TurtleHelmetStack$).*HelmetStack$"), "ItemHelmetStack");
        registerMergedItem(Pattern.compile(".*ChestplateStack"), "ItemChestplateStack");
        registerMergedItem(Pattern.compile(".*LeggingsStack"), "ItemLeggingsStack");
        registerMergedItem(Pattern.compile(".*BootsStack"), "ItemBootsStack");
        registerMergedItem(Pattern.compile(".*HorseArmorStack"), "ItemHorseArmorStack");
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
    }
}
