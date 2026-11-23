package org.allaymc.codegen;

import com.palantir.javapoet.ClassName;
import com.palantir.javapoet.CodeBlock;
import com.palantir.javapoet.FieldSpec;
import com.palantir.javapoet.JavaFile;
import com.palantir.javapoet.ParameterizedTypeName;
import com.palantir.javapoet.TypeSpec;
import lombok.SneakyThrows;
import org.allaymc.dependence.BlockId;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static org.allaymc.codegen.BlockIdEnumGen.MAPPED_BLOCK_PALETTE_NBT;
import static org.allaymc.codegen.BlockPropertyTypeGen.BLOCK_PROPERTY_TYPE_INFO_FILE;

/**
 * {@code BlockClassGen} generates {@code BlockTypes} and
 * {@code BlockTypeDefaultInitializer} from block identifiers and palette
 * metadata. Identifiers whose resolved behavior interface exists in
 * {@code api/src/main/java/org/allaymc/api/block/interfaces} bind to that
 * specialized interface and its compile-time generated {@code *Impl} class; all
 * remaining identifiers fall back to {@code BlockBehavior} and the parameterless
 * {@code AllayBlockType.builder()} factory without creating empty skeleton files.
 *
 * @author daoge_cmd | Cool_Loong
 */
public class BlockClassGen extends BaseClassGen {

    /**
     * {@code MERGED_BLOCKS} maps patterns to shared behavior interface names in
     * selection order. {@code generateClassSimpleName} selects the first matching
     * entry when checking whether a specialized family interface exists.
     */
    public static Map<Pattern, String> MERGED_BLOCKS = new LinkedHashMap<>();

    /**
     * Runs block catalog and default initializer generation from the repository root.
     */
    public static void main(String[] args) {
        generate();
    }

    /**
     * Regenerates {@code BlockTypes} and chunked {@code BlockTypeDefaultInitializer}
     * from the current block identifiers, property metadata, and existing
     * specialized block behavior interfaces in {@code api}.
     */
    @SneakyThrows
    public static void generate() {
        MERGED_BLOCKS.clear();
        registerMergedBlocks();

        var interfaceDir = Path.of("api/src/main/java/org/allaymc/api/block/interfaces");
        List<CodeBlock> defaultInitBlocks = new ArrayList<>();

        var typesClass = TypeSpec
                .classBuilder(TypeNames.BLOCK_TYPES)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(TypeNames.MINECRAFT_VERSION_SENSITIVE);

        for (var id : BlockId.values()) {
            var candidateSimpleName = generateClassSimpleName(id);
            boolean hasDedicatedInterface = Files.exists(interfaceDir.resolve(candidateSimpleName + ".java"));
            var catalogInterfaceName = hasDedicatedInterface
                    ? ClassName.get("org.allaymc.api.block.interfaces", candidateSimpleName)
                    : TypeNames.BLOCK_BEHAVIOR;
            var implClassName = hasDedicatedInterface
                    ? ClassName.get("org.allaymc.server.block.impl", candidateSimpleName + "Impl")
                    : null;

            var fieldBuilder = FieldSpec
                    .builder(ParameterizedTypeName.get(TypeNames.BLOCK_TYPE, catalogInterfaceName), id.name())
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC);

            var states = MAPPED_BLOCK_PALETTE_NBT.get(id.getIdentifier().toString()).getCompound("states");
            if (!states.isEmpty()) {
                states.forEach((name, value) -> fieldBuilder.addJavadoc(
                        "{@link $T#$N}\n", TypeNames.BLOCK_PROPERTY_TYPES, getSizedBlockPropertyTypeName(id, name)
                ));
            }
            typesClass.addField(fieldBuilder.build());

            defaultInitBlocks.add(buildDefaultBlockTypeInitializerBlock(id, implClassName));
        }

        generateDefaultBlockTypeInitializer(defaultInitBlocks);

        var javaFile = JavaFile.builder(TypeNames.BLOCK_TYPES.packageName(), typesClass.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + TypeNames.BLOCK_TYPES.simpleName() + ".java ...");
        Utils.writeFileWithCRLF(Path.of("api/src/main/java/org/allaymc/api/block/type/" + TypeNames.BLOCK_TYPES.simpleName() + ".java"), javaFile.toString());
    }

    /**
     * Builds one guarded default registration statement for {@code id}, using
     * {@code blockClassName} when a dedicated behavior interface exists or the
     * parameterless {@code builder()} otherwise.
     */
    private static CodeBlock buildDefaultBlockTypeInitializerBlock(BlockId id, ClassName blockClassName) {
        var initializer = CodeBlock.builder();
        initializer.add("$T.$N = $T\n", TypeNames.BLOCK_TYPES, id.name(), TypeNames.ALLAY_BLOCK_TYPE);
        if (blockClassName != null) {
            initializer.add("        .builder($T.class)\n", blockClassName);
        } else {
            initializer.add("        .builder()\n");
        }
        initializer.add("        .vanillaBlock($T.$N)\n", TypeNames.BLOCK_ID, id.name());

        var states = MAPPED_BLOCK_PALETTE_NBT.get(id.getIdentifier().toString()).getCompound("states");
        if (!states.isEmpty()) {
            initializer.add("        .setProperties(");
            AtomicInteger count = new AtomicInteger();
            states.forEach((name, value) -> initializer.add(
                    "$T.$N" + (states.size() == count.incrementAndGet() ? "" : ", "),
                    TypeNames.BLOCK_PROPERTY_TYPES,
                    getSizedBlockPropertyTypeName(id, name)
            ));
            initializer.add(")\n");
        }
        initializer.add("        .build()");

        return CodeBlock.builder()
                .beginControlFlow("if ($T.$N == null)", TypeNames.BLOCK_TYPES, id.name())
                .addStatement(initializer.build())
                .endControlFlow()
                .build();
    }

    /**
     * Resolves the uppercase property constant used for {@code id} and property {@code name}.
     */
    private static String getSizedBlockPropertyTypeName(BlockId id, String name) {
        var normalized = name.replaceAll(":", "_");
        if (BLOCK_PROPERTY_TYPE_INFO_FILE.differentSizePropertyTypes.contains(normalized) &&
            BLOCK_PROPERTY_TYPE_INFO_FILE.specialBlockTypes.containsKey(id.getIdentifier().toString())) {
            return BLOCK_PROPERTY_TYPE_INFO_FILE.specialBlockTypes.get(id.getIdentifier().toString()).get(normalized).toUpperCase(Locale.ROOT);
        }
        return normalized.toUpperCase(Locale.ROOT);
    }

    /**
     * Writes the chunked {@code BlockTypeDefaultInitializer} class.
     */
    @SneakyThrows
    private static void generateDefaultBlockTypeInitializer(List<CodeBlock> blocks) {
        var filePath = Path.of("server/src/main/java/org/allaymc/server/block/type/BlockTypeDefaultInitializer.java");
        var folderPath = filePath.getParent();
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        var builder = TypeSpec.classBuilder(TypeNames.BLOCK_TYPE_DEFAULT_INITIALIZER)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        addChunkedInitMethods(builder, blocks, DEFAULT_INIT_CHUNK_SIZE);

        var javaFile = JavaFile.builder(TypeNames.BLOCK_TYPE_DEFAULT_INITIALIZER.packageName(), builder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + TypeNames.BLOCK_TYPE_DEFAULT_INITIALIZER.simpleName() + ".java ...");
        Utils.writeFileWithCRLF(filePath, javaFile.toString());
    }

    /**
     * Selects a shared behavior family name or derives one from the identifier path.
     */
    private static String generateClassSimpleName(BlockId id) {
        var origin = "Block" + Utils.convertToPascalCase(id.getIdentifier().path()) + "Behavior";
        for (var entry : MERGED_BLOCKS.entrySet()) {
            if (entry.getKey().matcher(origin).find()) {
                return entry.getValue();
            }
        }
        return origin;
    }

    /**
     * Adds a regex pattern and its shared behavior interface name to {@code MERGED_BLOCKS}.
     */
    private static void registerMergedBlock(Pattern regex, String className) {
        MERGED_BLOCKS.put(regex, className);
    }

    /**
     * Registers built-in behavior family aliases in matching priority order.
     */
    private static void registerMergedBlocks() {
        registerMergedBlock(Pattern.compile(".*(Leaves\\d?|LeavesFlowered)Behavior"), "BlockLeavesBehavior");
        registerMergedBlock(Pattern.compile("Block(?!.*Deprecated).*?AnvilBehavior"), "BlockAnvilBehavior");
        registerMergedBlock(Pattern.compile(".*ShulkerBoxBehavior"), "BlockShulkerBoxBehavior");
        registerMergedBlock(Pattern.compile("Block(?!.*Copper).*?StairsBehavior"), "BlockStairsBehavior");
        registerMergedBlock(Pattern.compile("Block(?=.*Copper).*?StairsBehavior"), "BlockCopperStairsBehavior");
        registerMergedBlock(Pattern.compile("Block(?!.*Copper).*?DoubleSlabBehavior"), "BlockDoubleSlabBehavior");
        registerMergedBlock(Pattern.compile("Block.*?(?=.*Double).*?(?=.*Copper).*?SlabBehavior"), "BlockDoubleCopperSlabBehavior");
        registerMergedBlock(Pattern.compile("Block(?!.*Copper).*?SlabBehavior"), "BlockSlabBehavior");
        registerMergedBlock(Pattern.compile("Block(?=.*Copper).*?SlabBehavior"), "BlockCopperSlabBehavior");
        registerMergedBlock(Pattern.compile(".*BlastFurnaceBehavior"), "BlockBlastFurnaceBehavior");
        registerMergedBlock(Pattern.compile(".*FurnaceBehavior"), "BlockFurnaceBehavior");
        registerMergedBlock(Pattern.compile(".*SmokerBehavior"), "BlockSmokerBehavior");
        registerMergedBlock(Pattern.compile(".*WoodBehavior"), "BlockWoodBehavior");
        registerMergedBlock(Pattern.compile(".*LogBehavior"), "BlockWoodBehavior");
        registerMergedBlock(Pattern.compile(".*BambooBlockBehavior"), "BlockWoodBehavior");
        registerMergedBlock(Pattern.compile(".*CrimsonHyphaeBehavior"), "BlockWoodBehavior");
        registerMergedBlock(Pattern.compile(".*CrimsonStemBehavior"), "BlockWoodBehavior");
        registerMergedBlock(Pattern.compile(".*WarpedHyphaeBehavior"), "BlockWoodBehavior");
        registerMergedBlock(Pattern.compile(".*WarpedStemBehavior"), "BlockWoodBehavior");
        registerMergedBlock(Pattern.compile(".*IronDoorBehavior"), "BlockIronDoorBehavior");
        registerMergedBlock(Pattern.compile(".*CopperDoorBehavior"), "BlockCopperDoorBehavior");
        registerMergedBlock(Pattern.compile(".*DoorBehavior"), "BlockDoorBehavior");
        registerMergedBlock(Pattern.compile(".*HangingSignBehavior"), "BlockHangingSignBehavior");
        registerMergedBlock(Pattern.compile(".*StandingSignBehavior"), "BlockSignBehavior");
        registerMergedBlock(Pattern.compile(".*WallSignBehavior"), "BlockSignBehavior");
        registerMergedBlock(Pattern.compile(".*ButtonBehavior"), "BlockButtonBehavior");
        registerMergedBlock(Pattern.compile(".*WoolBehavior"), "BlockWoolBehavior");
        registerMergedBlock(Pattern.compile("BlockElement.*"), "BlockElementBehavior");
        registerMergedBlock(Pattern.compile(".*WallBehavior"), "BlockWallBehavior");
        registerMergedBlock(Pattern.compile("Block(?!.*Glazed)[A-Z][a-z]+(?:[A-Z][a-z]+)*TerracottaBehavior"), "BlockTerracottaBehavior");
        registerMergedBlock(Pattern.compile("[A-Z][a-z]+GlazedTerracottaBehavior"), "BlockGlazedTerracottaBehavior");
        registerMergedBlock(Pattern.compile("(?<!BlockRaw)(CopperBehavior|CopperBlockBehavior)"), "BlockCopperBehavior");
        registerMergedBlock(Pattern.compile(".*(?:Water|Lava)Behavior"), "BlockLiquidBehavior");
        registerMergedBlock(Pattern.compile(".*PlanksBehavior"), "BlockPlanksBehavior");
        registerMergedBlock(Pattern.compile(".*GlassBehavior"), "BlockGlassBehavior");
        registerMergedBlock(Pattern.compile(".*StainedGlassBehavior"), "BlockGlassBehavior");
        registerMergedBlock(Pattern.compile(".*GlassPaneBehavior"), "BlockGlassPaneBehavior");
        registerMergedBlock(Pattern.compile(".*StainedGlassPaneBehavior"), "BlockGlassPaneBehavior");
        registerMergedBlock(Pattern.compile(".*CandleBehavior"), "BlockCandleBehavior");
        registerMergedBlock(Pattern.compile(".*CandleCakeBehavior"), "BlockCandleCakeBehavior");
        registerMergedBlock(Pattern.compile(".*LightBlock.*Behavior"), "BlockLightBlockBehavior");
        registerMergedBlock(Pattern.compile(".*CarpetBehavior"), "BlockCarpetBehavior");
        registerMergedBlock(Pattern.compile(".*SaplingBehavior"), "BlockSaplingBehavior");
        registerMergedBlock(Pattern.compile(".*CoralFan.*"), "BlockCoralFanBehavior");
        registerMergedBlock(Pattern.compile(".*CoralWallFanBehavior"), "BlockCoralWallFanBehavior");
        registerMergedBlock(Pattern.compile(".*CoralBehavior"), "BlockCoralBehavior");
        registerMergedBlock(Pattern.compile(".*CoralBlockBehavior"), "BlockCoralBlockBehavior");
        registerMergedBlock(Pattern.compile(".*ConcreteBehavior"), "BlockConcreteBehavior");
        registerMergedBlock(Pattern.compile(".*ConcretePowderBehavior"), "BlockConcretePowderBehavior");
        registerMergedBlock(Pattern.compile(".*FenceBehavior"), "BlockFenceBehavior");
        registerMergedBlock(Pattern.compile(".*FenceGateBehavior"), "BlockFenceGateBehavior");
        registerMergedBlock(Pattern.compile(".*(Head|Skull)Behavior"), "BlockHeadBehavior");
        registerMergedBlock(Pattern.compile("Block(?!.*Infested).*?BricksBehavior"), "BlockBricksBehavior");
        registerMergedBlock(Pattern.compile(".*IronTrapdoorBehavior"), "BlockIronTrapdoorBehavior");
        registerMergedBlock(Pattern.compile(".*CopperTrapdoorBehavior"), "BlockCopperTrapdoorBehavior");
        registerMergedBlock(Pattern.compile(".*TrapdoorBehavior"), "BlockTrapdoorBehavior");
        registerMergedBlock(Pattern.compile(".*SandstoneBehavior"), "BlockSandstoneBehavior");
        registerMergedBlock(Pattern.compile(".*FireBehavior"), "BlockFireBehavior");
        registerMergedBlock(Pattern.compile(".*CopperBulbBehavior"), "BlockCopperBulbBehavior");
        registerMergedBlock(Pattern.compile(".*CopperGrateBehavior"), "BlockCopperGrateBehavior");
        registerMergedBlock(Pattern.compile(".*OreBehavior"), "BlockOreBehavior");
        registerMergedBlock(Pattern.compile(".*Infested.*"), "BlockInfestedBlockBehavior");
        registerMergedBlock(Pattern.compile(".*IceBehavior"), "BlockIceBehavior");
        registerMergedBlock(Pattern.compile(".*PressurePlateBehavior"), "BlockPressurePlateBehavior");
        registerMergedBlock(Pattern.compile(".*Torch(?!flower).*"), "BlockTorchBehavior");
        registerMergedBlock(Pattern.compile(".*ShelfBehavior"), "BlockShelfBehavior");
        registerMergedBlock(Pattern.compile(".*LightningRodBehavior"), "BlockLightningRodBehavior");
        registerMergedBlock(Pattern.compile(".*CopperBarsBehavior"), "BlockCopperBarsBehavior");
        registerMergedBlock(Pattern.compile("BlockSeaLanternBehavior"), "BlockSeaLanternBehavior");
        registerMergedBlock(Pattern.compile("Block(?!.*Copper).*?LanternBehavior"), "BlockLanternBehavior");
        registerMergedBlock(Pattern.compile("Block(?=.*Copper).*?LanternBehavior"), "BlockCopperLanternBehavior");
        registerMergedBlock(Pattern.compile(".*CopperGolemStatueBehavior"), "BlockCopperGolemStatueBehavior");
        registerMergedBlock(Pattern.compile(".*CopperChainBehavior"), "BlockCopperChainBehavior");
        registerMergedBlock(Pattern.compile(".*(Dandelion|Poppy|BlueOrchid|Allium|AzureBluet|RedTulip|OrangeTulip|WhiteTulip|PinkTulip|OxeyeDaisy|Cornflower|LilyOfTheValley|WitherRose|Torchflower|ClosedEyeblossom|OpenEyeblossom)Behavior"), "BlockSmallFlower");
        registerMergedBlock(Pattern.compile(".*(Sunflower|Lilac|RoseBush|Peony|PitcherPlant)Behavior"), "BlockBigFlower");
        registerMergedBlock(Pattern.compile(".*(LeafLitter|PinkPetals|Wildflowers)Behavior"), "BlockPlantPile");
        registerMergedBlock(Pattern.compile(".*CampfireBehavior"), "BlockCampfireBehavior");
        registerMergedBlock(Pattern.compile(".*DaylightDetector.*Behavior"), "BlockDaylightDetectorBehavior");
        registerMergedBlock(Pattern.compile(".*RepeaterBehavior"), "BlockRepeaterBehavior");
        registerMergedBlock(Pattern.compile(".*ComparatorBehavior"), "BlockComparatorBehavior");
        registerMergedBlock(Pattern.compile(".*RedstoneLampBehavior"), "BlockRedstoneLampBehavior");
        registerMergedBlock(Pattern.compile(".*Amethyst(Bud|Cluster)Behavior"), "BlockAmethystClusterBehavior");
    }
}
