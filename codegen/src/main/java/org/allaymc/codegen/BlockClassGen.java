package org.allaymc.codegen;

import com.palantir.javapoet.*;
import lombok.SneakyThrows;
import org.allaymc.dependence.BlockId;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static org.allaymc.codegen.BlockIdEnumGen.MAPPED_BLOCK_PALETTE_NBT;
import static org.allaymc.codegen.BlockPropertyTypeGen.BLOCK_PROPERTY_TYPE_INFO_FILE;

/**
 * {@code BlockClassGen} generates block type catalogs and missing behavior
 * skeletons from the block identifiers and palette metadata. Several
 * identifiers can share one behavior interface and implementation, selected by
 * the first matching alias rule. Existing skeleton files are retained so their
 * handwritten component methods survive regeneration, while the catalog and
 * default initializer are replaced.
 *
 * <p>
 * Cleanup uses the filenames selected during the current pass and preserves
 * explicitly reserved base files. A renamed or removed alias can therefore make
 * an old handwritten file eligible for deletion. Property references use the
 * block-specific size aliases produced by the property metadata tool. Output
 * changes are sequential, without rollback, and the shared initializer builder
 * retains statements across calls within the same process.
 *
 * @author daoge_cmd | Cool_Loong
 */
public class BlockClassGen extends BaseClassGen {

    /**
     * {@code BLOCK_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER} accumulates guarded
     * block registrations. {@code generate} appends statements without clearing
     * earlier calls in this JVM.
     */
    public static final MethodSpec.Builder BLOCK_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER =
            MethodSpec.methodBuilder("init")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC);
    /**
     * {@code IGNORED_FILES} reserves the shared behavior implementation and
     * package documentation during obsolete-file cleanup in both output
     * directories.
     */
    public static final Set<String> IGNORED_FILES = Set.of("BlockBehaviorImpl.java", "package-info.java");

    /**
     * {@code MERGED_BLOCKS} maps patterns to shared behavior names in selection
     * order. {@code generateClassSimpleName} selects the first matching entry,
     * and callers can replace or mutate this public map.
     */
    public static Map<Pattern, String> MERGED_BLOCKS = new LinkedHashMap<>();

    /**
     * Runs block source generation with paths relative to the repository root.
     * {@code args} is ignored; output replacement and obsolete-file cleanup
     * have the same effects as invoking {@link #generate()} directly.
     */
    public static void main(String[] args) {
        generate();
    }

    /**
     * Writes the block catalog, missing behavior skeletons, and default
     * registrations using the loaded identifier and property data. Callers run
     * this generator from the repository root after the palette and property
     * metadata are available. Existing selected skeletons are preserved, but
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
        registerMergedBlocks();

        var interfaceDir = Path.of("api/src/main/java/org/allaymc/api/block/interfaces");
        if (!Files.exists(interfaceDir)) {
            Files.createDirectories(interfaceDir);
        }
        var implDir = Path.of("server/src/main/java/org/allaymc/server/block/impl");
        if (!Files.exists(implDir)) {
            Files.createDirectories(implDir);
        }

        Set<String> generatedFiles = new HashSet<>();

        var typesClass = TypeSpec
                .classBuilder(TypeNames.BLOCK_TYPES)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(TypeNames.MINECRAFT_VERSION_SENSITIVE);

        for (var id : BlockId.values()) {
            var fieldBuilder = FieldSpec
                    .builder(ParameterizedTypeName.get(TypeNames.BLOCK_TYPE, generateClassFullName(id)), id.name())
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC);

            var states = MAPPED_BLOCK_PALETTE_NBT.get(id.getIdentifier().toString()).getCompound("states");
            if (!states.isEmpty()) {
                states.forEach((name, value) -> {
                    fieldBuilder.addJavadoc("{@link $T#$N}\n", TypeNames.BLOCK_PROPERTY_TYPES, getSizedBlockPropertyTypeName(id, name));
                });
            }

            typesClass.addField(fieldBuilder.build());

            var interfaceSimpleName = generateClassSimpleName(id);
            var interfaceFullName = generateClassFullName(id);
            var interfacePath = interfaceDir.resolve(interfaceSimpleName + ".java");
            generatedFiles.add(interfacePath.getFileName().toString());
            if (!Files.exists(interfacePath)) {
                System.out.println("Generating " + interfaceSimpleName + "...");
                if (!Files.exists(interfaceDir)) {
                    Files.createDirectories(interfaceDir);
                }
                generateInterface(TypeNames.BLOCK_BEHAVIOR, interfaceFullName, interfacePath);
            }

            var implSimpleName = generateClassSimpleName(id) + "Impl";
            var implFullName = ClassName.get("org.allaymc.server.block.impl", implSimpleName);
            var implPath = implDir.resolve(implSimpleName + ".java");
            generatedFiles.add(implPath.getFileName().toString());
            if (!Files.exists(implPath)) {
                System.out.println("Generating " + implSimpleName + "...");
                if (!Files.exists(implDir)) {
                    Files.createDirectories(implDir);
                }
                generateBlockImpl(interfaceFullName, implFullName, implPath);
            }

            addDefaultBlockTypeInitializer(id, implFullName);
        }

        // Cleanup follows alias selection so every currently shared behavior name
        // survives; obsolete names can include files previously extended by hand.
        deleteOldFiles(interfaceDir, generatedFiles, IGNORED_FILES);
        deleteOldFiles(implDir, generatedFiles, IGNORED_FILES);

        generateDefaultBlockTypeInitializer();

        var javaFile = JavaFile.builder(TypeNames.BLOCK_TYPES.packageName(), typesClass.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + TypeNames.BLOCK_TYPES.simpleName() + ".java ...");
        Utils.writeFileWithCRLF(Path.of("api/src/main/java/org/allaymc/api/block/type/" + TypeNames.BLOCK_TYPES.simpleName() + ".java"), javaFile.toString());
    }

    /**
     * Writes a behavior implementation whose constructor forwards component
     * providers to the shared base class. The target is overwritten directly;
     * callers must check for an existing handwritten implementation before
     * requesting this initial skeleton.
     *
     * @throws IOException if the implementation source cannot be written
     */
    protected static void generateBlockImpl(ClassName superInterfaceName, ClassName className, Path path) throws IOException {
        TypeSpec.Builder codeBuilder = TypeSpec.classBuilder(className)
                .superclass(TypeNames.BLOCK_BEHAVIOR_IMPL)
                .addSuperinterface(superInterfaceName)
                .addModifiers(Modifier.PUBLIC);
        codeBuilder.addMethod(
                MethodSpec
                        .constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(ParameterizedTypeName.get(TypeNames.LIST, ParameterizedTypeName.get(TypeNames.COMPONENT_PROVIDER, WildcardTypeName.subtypeOf(TypeNames.COMPONENT))), "componentProviders")
                        .addStatement("super(componentProviders)")
                        .build()
        );
        var javaFile = JavaFile.builder(className.packageName(), codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + className + ".java ...");
        Utils.writeFileWithCRLF(path, javaFile.toString());
    }

    /**
     * Appends a default registration using the block's palette property names.
     * The emitted {@code null} guard preserves a type installed before
     * initialization. {@code BLOCK_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER}
     * retains this statement until the JVM exits.
     */
    private static void addDefaultBlockTypeInitializer(BlockId id, ClassName blockClassName) {
        var initializer = CodeBlock.builder();
        initializer
                .add("$T.$N = $T\n", TypeNames.BLOCK_TYPES, id.name(), TypeNames.ALLAY_BLOCK_TYPE)
                .add("        .builder($T.class)\n", blockClassName)
                .add("        .vanillaBlock($T.$N)\n", TypeNames.BLOCK_ID, id.name());
        var states = MAPPED_BLOCK_PALETTE_NBT.get(id.getIdentifier().toString()).getCompound("states");
        if (!states.isEmpty()) {
            initializer.add("        .setProperties(");
            AtomicInteger count = new AtomicInteger();
            states.forEach((name, value) -> {
                initializer.add("$T.$N" + (states.size() == count.incrementAndGet() ? "" : ", "), TypeNames.BLOCK_PROPERTY_TYPES, getSizedBlockPropertyTypeName(id, name));
            });
            initializer.add(")\n");
        }
        initializer.add("        .build()");
        BLOCK_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER
                .beginControlFlow("if ($T.$N == null)", TypeNames.BLOCK_TYPES, id.name())
                .addStatement(initializer.build())
                .endControlFlow();
    }

    /**
     * Resolves the uppercase property constant used for this block identifier.
     * This method replaces colons in {@code name} with underscores before
     * membership testing in {@code differentSizePropertyTypes} and lookup in
     * {@code specialBlockTypes}. This method selects an alias only when the
     * normalized name belongs to the list and the block has a mapping. That
     * mapping must contain the normalized name or lookup fails.
     *
     * <p>
     * The data tool stores serialization names in
     * {@code differentSizePropertyTypes} without this normalization. A
     * colon-containing entry therefore does not select an alias through this
     * membership test and uses the common constant name.
     */
    private static String getSizedBlockPropertyTypeName(BlockId id, String name) {
        if (BLOCK_PROPERTY_TYPE_INFO_FILE.differentSizePropertyTypes.contains(name.replaceAll(":", "_")) &&
            BLOCK_PROPERTY_TYPE_INFO_FILE.specialBlockTypes.containsKey(id.getIdentifier().toString())
        ) {
            return BLOCK_PROPERTY_TYPE_INFO_FILE.specialBlockTypes.get(id.getIdentifier().toString()).get(name.replaceAll(":", "_")).toUpperCase(Locale.ROOT);
        }

        // Properties without a selected block-specific size use the common
        // constant, keeping catalog links and initializer arguments on one name.
        return name.replaceAll(":", "_").toUpperCase(Locale.ROOT);
    }

    /**
     * Replaces the default initializer with the accumulated registration
     * method. The old file is deleted before serialization and writing, so a
     * later failure can leave it absent instead of preserving its previous
     * contents.
     */
    @SneakyThrows
    private static void generateDefaultBlockTypeInitializer() {
        var filePath = Path.of("server/src/main/java/org/allaymc/server/block/type/BlockTypeDefaultInitializer.java");
        // Deletion precedes output construction; a failure below does not restore
        // the initializer, even though earlier skeleton generation may have succeeded.
        Files.deleteIfExists(filePath);
        var folderPath = filePath.getParent();
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        TypeSpec.Builder builder =
                TypeSpec.classBuilder(TypeNames.BLOCK_TYPE_DEFAULT_INITIALIZER)
                        .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        builder.addMethod(BLOCK_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER.build());

        var javaFile = JavaFile.builder(TypeNames.BLOCK_TYPE_DEFAULT_INITIALIZER.packageName(), builder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + TypeNames.BLOCK_TYPE_DEFAULT_INITIALIZER.simpleName() + ".java ...");
        Utils.writeFileWithCRLF(filePath, javaFile.toString());
    }

    /**
     * Returns the selected behavior name in the API interface package.
     * Selection uses the current entries in {@code MERGED_BLOCKS}, so different
     * identifiers may intentionally resolve to the same Java type.
     */
    private static ClassName generateClassFullName(BlockId id) {
        var simpleName = generateClassSimpleName(id);
        return ClassName.get("org.allaymc.api.block.interfaces", simpleName);
    }

    /**
     * Selects a shared behavior name or derives one from the identifier path.
     * {@code MERGED_BLOCKS} patterns inspect the derived Java name in map
     * order, and the first match determines the interface and implementation
     * basename.
     */
    private static String generateClassSimpleName(BlockId id) {
        var origin = "Block" + Utils.convertToPascalCase(id.getIdentifier().path()) + "Behavior";
        for (var entry : MERGED_BLOCKS.entrySet()) {
            if (entry.getKey().matcher(origin).find()) {
                return entry.getValue();
            }
        }
        // An unmatched identifier keeps its own behavior type, allowing a new
        // block to receive a skeleton before any shared alias is registered.
        return origin;
    }

    /**
     * Adds a pattern and its shared behavior name to the alias map. In the
     * default insertion-ordered map, earlier matching patterns retain priority
     * over patterns registered later.
     */
    private static void registerMergedBlock(Pattern regex, String className) {
        MERGED_BLOCKS.put(regex, className);
    }

    /**
     * Registers the built-in behavior aliases in their matching priority order.
     * Existing {@code MERGED_BLOCKS} entries remain present, so caller-supplied
     * earlier patterns can take precedence over these appended defaults.
     */
    private static void registerMergedBlocks() {
        // Specific variants precede broader families where both can match, so
        // specialized behavior classes are not replaced by their generic family.
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
