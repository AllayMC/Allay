package org.allaymc.codegen;

import com.palantir.javapoet.ClassName;
import com.palantir.javapoet.CodeBlock;
import com.palantir.javapoet.FieldSpec;
import com.palantir.javapoet.JavaFile;
import com.palantir.javapoet.ParameterizedTypeName;
import com.palantir.javapoet.TypeSpec;
import lombok.SneakyThrows;
import org.allaymc.dependence.ItemId;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * {@code ItemClassGen} generates {@code ItemTypes} and
 * {@code ItemTypeDefaultInitializer} from the item identifier enumeration.
 * Identifiers whose resolved stack interface exists in
 * {@code api/src/main/java/org/allaymc/api/item/interfaces} bind to that
 * specialized interface and its compile-time generated {@code *Impl} class; all
 * remaining identifiers fall back to {@code ItemStack} and the parameterless
 * {@code AllayItemType.builder()} factory without creating empty skeleton files.
 *
 * @author daoge_cmd | IWareQ
 */
public class ItemClassGen extends BaseClassGen {

    /**
     * {@code MERGED_ITEMS} maps patterns to shared stack interface names in
     * selection order. {@code generateClassSimpleName} selects the first matching
     * entry when checking whether a specialized family interface exists.
     */
    public static Map<Pattern, String> MERGED_ITEMS = new LinkedHashMap<>();

    /**
     * Runs item catalog and default initializer generation from the repository root.
     */
    public static void main(String[] args) {
        generate();
    }

    /**
     * Regenerates {@code ItemTypes} and chunked {@code ItemTypeDefaultInitializer}
     * from the loaded item identifiers and existing specialized item interfaces
     * in {@code api}.
     */
    @SneakyThrows
    public static void generate() {
        MERGED_ITEMS.clear();
        registerMergedItems();

        var interfaceDir = Path.of("api/src/main/java/org/allaymc/api/item/interfaces");
        List<CodeBlock> defaultInitBlocks = new ArrayList<>();

        var typesClass = TypeSpec
                .classBuilder(TypeNames.ITEM_TYPES)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(TypeNames.MINECRAFT_VERSION_SENSITIVE);

        for (var id : ItemId.values()) {
            var candidateSimpleName = generateClassSimpleName(id);
            boolean hasDedicatedInterface = Files.exists(interfaceDir.resolve(candidateSimpleName + ".java"));
            var catalogInterfaceName = hasDedicatedInterface
                    ? ClassName.get("org.allaymc.api.item.interfaces", candidateSimpleName)
                    : TypeNames.ITEM_STACK;
            var implClassName = hasDedicatedInterface
                    ? ClassName.get("org.allaymc.server.item.impl", candidateSimpleName + "Impl")
                    : null;

            typesClass.addField(
                    FieldSpec.builder(ParameterizedTypeName.get(TypeNames.ITEM_TYPE, catalogInterfaceName), id.name())
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                            .build()
            );

            defaultInitBlocks.add(buildDefaultItemTypeInitializerBlock(id, implClassName));
        }

        generateDefaultItemTypeInitializer(defaultInitBlocks);

        var javaFile = JavaFile.builder(TypeNames.ITEM_TYPES.packageName(), typesClass.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + TypeNames.ITEM_TYPES.simpleName() + ".java ...");
        Utils.writeFileWithCRLF(Path.of("api/src/main/java/org/allaymc/api/item/type/" + TypeNames.ITEM_TYPES.simpleName() + ".java"), javaFile.toString());
    }

    /**
     * Builds one guarded default registration statement for {@code id}, using
     * {@code itemClassName} when a dedicated stack interface exists or the
     * parameterless {@code builder()} otherwise.
     */
    private static CodeBlock buildDefaultItemTypeInitializerBlock(ItemId id, ClassName itemClassName) {
        var initializer = CodeBlock.builder();
        initializer.add("$T.$N = $T\n", TypeNames.ITEM_TYPES, id.name(), TypeNames.ALLAY_ITEM_TYPE);
        if (itemClassName != null) {
            initializer.add("        .builder($T.class)\n", itemClassName);
        } else {
            initializer.add("        .builder()\n");
        }
        initializer
                .add("        .vanillaItem($T.$N)\n", TypeNames.ITEM_ID, id.name())
                .add("        .build()");

        return CodeBlock.builder()
                .beginControlFlow("if ($T.$N == null)", TypeNames.ITEM_TYPES, id.name())
                .addStatement(initializer.build())
                .endControlFlow()
                .build();
    }

    /**
     * Writes the chunked {@code ItemTypeDefaultInitializer} class.
     */
    @SneakyThrows
    private static void generateDefaultItemTypeInitializer(List<CodeBlock> blocks) {
        var filePath = Path.of("server/src/main/java/org/allaymc/server/item/type/ItemTypeDefaultInitializer.java");
        var folderPath = filePath.getParent();
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        var builder = TypeSpec.classBuilder(TypeNames.ITEM_TYPE_DEFAULT_INITIALIZER)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        addChunkedInitMethods(builder, blocks, DEFAULT_INIT_CHUNK_SIZE);

        var javaFile = JavaFile.builder(TypeNames.ITEM_TYPE_DEFAULT_INITIALIZER.packageName(), builder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + TypeNames.ITEM_TYPE_DEFAULT_INITIALIZER.simpleName() + ".java ...");
        Utils.writeFileWithCRLF(filePath, javaFile.toString());
    }

    /**
     * Selects a shared stack family name or derives one from the identifier path.
     */
    private static String generateClassSimpleName(ItemId id) {
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
        return origin;
    }

    /**
     * Adds a regex pattern and its shared stack interface name to {@code MERGED_ITEMS}.
     */
    private static void registerMergedItem(Pattern regex, String className) {
        MERGED_ITEMS.put(regex, className);
    }

    /**
     * Registers built-in stack family aliases in matching priority order.
     */
    private static void registerMergedItems() {
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
