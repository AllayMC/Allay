package org.allaymc.codegen;

import com.palantir.javapoet.*;
import lombok.SneakyThrows;
import org.allaymc.dependence.BlockId;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
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
                .classBuilder(ClassNames.BLOCK_TYPES)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addAnnotation(ClassNames.MINECRAFT_VERSION_SENSITIVE);

        for (var id : BlockId.values()) {
            var fieldBuilder = FieldSpec
                    .builder(ParameterizedTypeName.get(ClassNames.BLOCK_TYPE, generateClassFullName(id)), id.name())
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC);

            var states = MAPPED_BLOCK_PALETTE_NBT.get(id.getIdentifier().toString()).getCompound("states");
            if (!states.isEmpty()) {
                states.forEach((name, value) -> {
                    fieldBuilder.addJavadoc("{@link $T#$N}\n", ClassNames.BLOCK_PROPERTY_TYPES, getSizedBlockPropertyTypeName(id, name));
                });
            }

            typesClass.addField(fieldBuilder.build());

            var interfaceSimpleName = generateClassSimpleName(id);
            var interfaceFullName = generateClassFullName(id);
            var path = interfaceDir.resolve(interfaceSimpleName + ".java");
            generatedFiles.add(path.getFileName().toString());
            if (!Files.exists(path)) {
                System.out.println("Generating " + interfaceSimpleName + "...");
                if (!Files.exists(interfaceDir)) {
                    Files.createDirectories(interfaceDir);
                }
                generateInterface(ClassNames.BLOCK_BEHAVIOR, interfaceFullName, path);
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

        deleteOldFiles(interfaceDir, generatedFiles);
        deleteOldFiles(implDir, generatedFiles);

        generateDefaultBlockTypeInitializer();

        var javaFile = JavaFile.builder(ClassNames.BLOCK_TYPES.packageName(), typesClass.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + ClassNames.BLOCK_TYPES.simpleName() + ".java ...");
        Utils.writeFileWithCRLF(Path.of("api/src/main/java/org/allaymc/api/block/type/" + ClassNames.BLOCK_TYPES.simpleName() + ".java"), javaFile.toString());
    }

    private static void deleteOldFiles(Path dir, Set<String> generatedFiles) {
        if (!Files.exists(dir)) {
            return;
        }

        try (var files = Files.list(dir)) {
            files.filter(path -> Files.isRegularFile(path) && !IGNORED_FILES.contains(path.getFileName().toString())).forEach(file -> {
                try {
                    if (!generatedFiles.contains(file.getFileName().toString())) {
                        System.out.println("Deleting unused file: " + file.getFileName());
                        Files.delete(file);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static void generateBlockImpl(ClassName superInterfaceName, ClassName className, Path path) throws IOException {
        TypeSpec.Builder codeBuilder = TypeSpec.classBuilder(className)
                .superclass(ClassNames.BLOCK_BEHAVIOR_IMPL)
                .addSuperinterface(superInterfaceName)
                .addModifiers(Modifier.PUBLIC);
        codeBuilder.addMethod(
                MethodSpec
                        .constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(ParameterizedTypeName.get(ClassNames.LIST, ParameterizedTypeName.get(ClassNames.COMPONENT_PROVIDER, WildcardTypeName.subtypeOf(ClassNames.COMPONENT))), "componentProviders")
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
                .add("$T.$N = $T\n", ClassNames.BLOCK_TYPES, id.name(), ClassNames.ALLAY_BLOCK_TYPE)
                .add("        .builder($T.class)\n", blockClassName)
                .add("        .vanillaBlock($T.$N)\n", ClassNames.BLOCK_ID, id.name());
        var states = MAPPED_BLOCK_PALETTE_NBT.get(id.getIdentifier().toString()).getCompound("states");
        if (!states.isEmpty()) {
            initializer.add("        .setProperties(");
            AtomicInteger count = new AtomicInteger();
            states.forEach((name, value) -> {
                initializer.add("$T.$N" + (states.size() == count.incrementAndGet() ? "" : ", "), ClassNames.BLOCK_PROPERTY_TYPES, getSizedBlockPropertyTypeName(id, name));
            });
            initializer.add(")\n");
        }
        initializer.add("        .build()");
        BLOCK_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER
                .beginControlFlow("if ($T.$N == null)", ClassNames.BLOCK_TYPES, id.name())
                .addStatement(initializer.build())
                .endControlFlow();
    }

    private static String getSizedBlockPropertyTypeName(BlockId id, String name) {
        if (BLOCK_PROPERTY_TYPE_INFO_FILE.differentSizePropertyTypes.contains(name.replaceAll(":", "_")) &&
            BLOCK_PROPERTY_TYPE_INFO_FILE.specialBlockTypes.containsKey(id.getIdentifier().toString())
        ) {
            return BLOCK_PROPERTY_TYPE_INFO_FILE.specialBlockTypes.get(id.getIdentifier().toString()).get(name.replaceAll(":", "_")).toUpperCase();
        }

        return name.replaceAll(":", "_").toUpperCase();
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
                TypeSpec.classBuilder(ClassNames.BLOCK_TYPE_DEFAULT_INITIALIZER)
                        .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        builder.addMethod(BLOCK_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER.build());

        var javaFile = JavaFile.builder(ClassNames.BLOCK_TYPE_DEFAULT_INITIALIZER.packageName(), builder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + ClassNames.BLOCK_TYPE_DEFAULT_INITIALIZER.simpleName() + ".java ...");
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
        registerMergedBlock(Pattern.compile(".*Torch.*"), "BlockTorchBehavior");
        registerMergedBlock(Pattern.compile(".*ShelfBehavior"), "BlockShelfBehavior");
        registerMergedBlock(Pattern.compile(".*LightningRodBehavior"), "BlockLightningRodBehavior");
        registerMergedBlock(Pattern.compile(".*CopperBarsBehavior"), "BlockCopperBarsBehavior");
        registerMergedBlock(Pattern.compile("BlockSeaLanternBehavior"), "BlockSeaLanternBehavior");
        registerMergedBlock(Pattern.compile("Block(?!.*Copper).*?LanternBehavior"), "BlockLanternBehavior");
        registerMergedBlock(Pattern.compile("Block(?=.*Copper).*?LanternBehavior"), "BlockCopperLanternBehavior");
        registerMergedBlock(Pattern.compile(".*CopperGolemStatueBehavior"), "BlockCopperGolemStatueBehavior");
        registerMergedBlock(Pattern.compile(".*CopperChainBehavior"), "BlockCopperChainBehavior");
    }
}
