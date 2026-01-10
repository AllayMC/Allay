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
 * @author daoge_cmd | Cool_Loong
 */
public class BlockClassGen extends BaseClassGen {

    public static final MethodSpec.Builder BLOCK_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER =
            MethodSpec.methodBuilder("init")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC);
    public static final Set<String> IGNORED_FILES = Set.of("BlockBehaviorImpl.java", "package-info.java");

    public static Map<Pattern, String> MERGED_BLOCKS = new LinkedHashMap<>();

    public static void main(String[] args) {
        generate();
    }

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

    private static String getSizedBlockPropertyTypeName(BlockId id, String name) {
        if (BLOCK_PROPERTY_TYPE_INFO_FILE.differentSizePropertyTypes.contains(name.replaceAll(":", "_")) &&
            BLOCK_PROPERTY_TYPE_INFO_FILE.specialBlockTypes.containsKey(id.getIdentifier().toString())
        ) {
            return BLOCK_PROPERTY_TYPE_INFO_FILE.specialBlockTypes.get(id.getIdentifier().toString()).get(name.replaceAll(":", "_")).toUpperCase(Locale.ROOT);
        }

        return name.replaceAll(":", "_").toUpperCase(Locale.ROOT);
    }

    @SneakyThrows
    private static void generateDefaultBlockTypeInitializer() {
        var filePath = Path.of("server/src/main/java/org/allaymc/server/block/type/BlockTypeDefaultInitializer.java");
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

    private static ClassName generateClassFullName(BlockId id) {
        var simpleName = generateClassSimpleName(id);
        return ClassName.get("org.allaymc.api.block.interfaces", simpleName);
    }

    private static String generateClassSimpleName(BlockId id) {
        var origin = "Block" + Utils.convertToPascalCase(id.getIdentifier().path()) + "Behavior";
        for (var entry : MERGED_BLOCKS.entrySet()) {
            if (entry.getKey().matcher(origin).find()) {
                return entry.getValue();
            }
        }
        return origin;
    }

    private static void registerMergedBlock(Pattern regex, String className) {
        MERGED_BLOCKS.put(regex, className);
    }

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
    }
}
