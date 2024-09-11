package org.allaymc.codegen;

import com.squareup.javapoet.*;
import lombok.SneakyThrows;
import org.allaymc.dependence.BlockId;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static org.allaymc.codegen.BlockIdEnumGen.MAPPED_BLOCK_PALETTE_NBT;
import static org.allaymc.codegen.BlockPropertyTypeGen.BLOCK_PROPERTY_TYPE_INFO_FILE;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd | Cool_Loong
 */
public class BlockInterfaceGen extends BaseInterfaceGen {

    public static final ClassName BLOCK_BEHAVIOR_CLASS_NAME = ClassName.get("org.allaymc.api.block", "BlockBehavior");
    public static final ClassName BLOCK_ID_CLASS_NAME = ClassName.get("org.allaymc.api.block.data", "BlockId");
    public static final ClassName BLOCK_PROPERTY_TYPES_CLASS_NAME = ClassName.get("org.allaymc.api.block.property.type", "BlockPropertyTypes");
    public static final ClassName BLOCK_TYPE_CLASS_NAME = ClassName.get("org.allaymc.api.block.type", "BlockType");
    public static final ClassName BLOCK_TYPES_CLASS_NAME = ClassName.get("org.allaymc.api.block.type", "BlockTypes");
    public static final ClassName BLOCK_TYPE_BUILDER_CLASS_NAME = ClassName.get("org.allaymc.server.block.type", "AllayBlockType");
    public static final ClassName BLOCK_TYPE_DEFAULT_INITIALIZER_CLASS_NAME = ClassName.get("org.allaymc.server.block.type", "BlockTypeDefaultInitializer");
    public static final TypeSpec.Builder BLOCK_TYPE_DEFAULT_INITIALIZER_CLASS_BUILDER =
            TypeSpec.classBuilder(BLOCK_TYPE_DEFAULT_INITIALIZER_CLASS_NAME)
                    .addJavadoc(
                            "@author daoge_cmd <br>\n" +
                            "Allay Project <br>\n"
                    )
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
    public static Map<Pattern, String> SUB_PACKAGE_GROUPERS = new LinkedHashMap<>();
    public static Map<InitBuilder, Set<BlockId>> BUILD_GROUPERS = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        // NOTICE: Please run BlockIdEnumGen.generate() first before running this method
//        BlockPropertyTypeGen.generate();
        generate();
    }

    @SneakyThrows
    public static void generate() {
        registerSubPackages();
        registerBuilders();
        var interfaceDir = Path.of("Allay-API/src/main/java/org/allaymc/api/block/interfaces");
        if (!Files.exists(interfaceDir)) Files.createDirectories(interfaceDir);
        var typesClass = TypeSpec.classBuilder(BLOCK_TYPES_CLASS_NAME).addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        for (var id : BlockId.values()) {
            typesClass.addField(
                    FieldSpec.builder(ParameterizedTypeName.get(BLOCK_TYPE_CLASS_NAME, generateClassFullName(id)), id.name())
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                            .build()
            );
            var blockClassSimpleName = generateClassSimpleName(id);
            var blockClassFullName = generateClassFullName(id);
            var folderName = tryFindSpecifiedFolderName(blockClassSimpleName);
            var folderPath = folderName != null ? interfaceDir.resolve(folderName) : interfaceDir;
            var path = folderPath.resolve(blockClassSimpleName + ".java");
            if (!Files.exists(path)) {
                System.out.println("Generating " + blockClassSimpleName + "...");
                if (!Files.exists(folderPath))
                    Files.createDirectories(folderPath);
                generateClass(BLOCK_BEHAVIOR_CLASS_NAME, blockClassFullName, path);
            }
            addDefaultBlockTypeInitializer(id, blockClassFullName);
        }
        generateDefaultBlockTypeInitializer();
        var javaFile = JavaFile.builder(BLOCK_TYPES_CLASS_NAME.packageName(), typesClass.build())
                .indent(Utils.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + BLOCK_TYPES_CLASS_NAME.simpleName() + ".java ...");
        Files.writeString(Path.of("Allay-API/src/main/java/org/allaymc/api/block/type/" + BLOCK_TYPES_CLASS_NAME.simpleName() + ".java"), javaFile.toString());
    }

    private static void addDefaultBlockTypeInitializer(BlockId id, ClassName blockClassName) {
        for (var entry : BUILD_GROUPERS.entrySet()) {
            if (entry.getKey().regex().matcher(id.name()).find()) {
                var blocks = entry.getValue();
                blocks.add(id);
                BUILD_GROUPERS.put(entry.getKey(), blocks);
                return;
            }
        }

        var initializer = CodeBlock.builder();
        initializer
                .add("$T.$N = $T\n", BLOCK_TYPES_CLASS_NAME, id.name(), BLOCK_TYPE_BUILDER_CLASS_NAME)
                .add("        .builder($T.class)\n", blockClassName)
                .add("        .vanillaBlock($T.$N)\n", BLOCK_ID_CLASS_NAME, id.name());
        var blockPaletteData = MAPPED_BLOCK_PALETTE_NBT.get(id.getIdentifier().toString());
        var states = blockPaletteData.getCompound("states");
        if (!states.isEmpty()) {
            initializer.add("        .setProperties(");
            AtomicInteger count = new AtomicInteger();
            states.forEach((name, value) -> {
                var propertyName = BLOCK_PROPERTY_TYPE_INFO_FILE.differentSizePropertyTypes.contains(name.replaceAll(":", "_")) && BLOCK_PROPERTY_TYPE_INFO_FILE.specialBlockTypes.containsKey(id.getIdentifier().toString()) ?
                        BLOCK_PROPERTY_TYPE_INFO_FILE.specialBlockTypes.get(id.getIdentifier().toString()).get(name.replaceAll(":", "_")).toUpperCase() : name.replaceAll(":", "_").toUpperCase();
                initializer.add("$T.$N" + (states.size() == count.incrementAndGet() ? "" : ", "), BLOCK_PROPERTY_TYPES_CLASS_NAME, propertyName);
            });
            initializer.add(")\n");
        }
        initializer.add("        .build();");
        BLOCK_TYPE_DEFAULT_INITIALIZER_CLASS_BUILDER.addMethod(
                MethodSpec.methodBuilder(generateInitializerMethodName(id))
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addStatement("if ($T.$N != null) return", BLOCK_TYPES_CLASS_NAME, id.name())
                        .addCode(initializer.build())
                        .build()
        );
    }

    @SneakyThrows
    private static void generateDefaultBlockTypeInitializer() {
        var filePath = Path.of("Allay-Server/src/main/java/org/allaymc/server/block/type/BlockTypeDefaultInitializer.java");
        Files.deleteIfExists(filePath);
        var folderPath = filePath.getParent();
        if (!Files.exists(folderPath))
            Files.createDirectories(folderPath);

        var blockBehaviorClass = ClassName.get("org.allaymc.api.block", "BlockBehavior");

        for (var entry : BUILD_GROUPERS.entrySet()) {
            var initBuilder = entry.getKey();
            var blocks = entry.getValue();

            var initBlocks = CodeBlock.builder();
            blocks.forEach(block -> {
                var blockClassFullName = generateClassFullName(block);
                initBlocks.addStatement(
                        "if ($T.$N == null) $T.$N = build$N($T.class, $T.$N)",
                        BLOCK_TYPES_CLASS_NAME, block.name(),
                        BLOCK_TYPES_CLASS_NAME, block.name(),
                        initBuilder.buildName(),
                        blockClassFullName, BLOCK_ID_CLASS_NAME, block.name()
                );
            });

            BLOCK_TYPE_DEFAULT_INITIALIZER_CLASS_BUILDER.addMethod(
                    MethodSpec.methodBuilder("init" + initBuilder.initName())
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                            .addCode(initBlocks.build())
                            .build()
            );

            var buildBlock = MethodSpec.methodBuilder("build" + initBuilder.buildName())
                    .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                    .addTypeVariable(TypeVariableName.get("T", blockBehaviorClass))
                    .returns(ParameterizedTypeName.get(BLOCK_TYPE_CLASS_NAME, TypeVariableName.get("T")))
                    .addParameter(ParameterizedTypeName.get(ClassName.get("java.lang", "Class"), TypeVariableName.get("T")), "clazz")
                    .addParameter(BLOCK_ID_CLASS_NAME, "blockId");


            var builderCode = new StringJoiner("\n");
            builderCode.add("return AllayBlockType.builder(clazz)");
            builderCode.add("       .vanillaBlock(blockId)");

            for (var block : blocks) {
                var blockPaletteData = MAPPED_BLOCK_PALETTE_NBT.get(block.getIdentifier().toString());
                var states = blockPaletteData.getCompound("states");
                if (!states.isEmpty()) {
                    var propertiesBuilder = new StringJoiner(", ");
                    Set<String> propertyNames = new HashSet<>();
                    states.forEach((name, value) -> {
                        propertyNames.add(BLOCK_PROPERTY_TYPE_INFO_FILE.differentSizePropertyTypes.contains(name.replaceAll(":", "_")) && BLOCK_PROPERTY_TYPE_INFO_FILE.specialBlockTypes.containsKey(block.getIdentifier().toString()) ?
                                BLOCK_PROPERTY_TYPE_INFO_FILE.specialBlockTypes.get(block.getIdentifier().toString()).get(name.replaceAll(":", "_")).toUpperCase() : name.replaceAll(":", "_").toUpperCase());
                    });
                    propertyNames.forEach(propertyName -> propertiesBuilder.add("BlockPropertyTypes." + propertyName));
                    builderCode.add("       .setProperties(" + propertiesBuilder + ")");
                    break;
                }
            }

            buildBlock.addCode(builderCode + "\n");

            buildBlock.addStatement("       .build()");

            BLOCK_TYPE_DEFAULT_INITIALIZER_CLASS_BUILDER.addMethod(buildBlock.build());
        }

        var javaFile = JavaFile.builder(BLOCK_TYPE_DEFAULT_INITIALIZER_CLASS_NAME.packageName(), BLOCK_TYPE_DEFAULT_INITIALIZER_CLASS_BUILDER.build())
                .indent(Utils.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + BLOCK_TYPE_DEFAULT_INITIALIZER_CLASS_NAME.simpleName() + ".java ...");
        Files.writeString(filePath, javaFile.toString());
    }

    private static ClassName generateClassFullName(BlockId id) {
        var simpleName = generateClassSimpleName(id);
        var folderName = tryFindSpecifiedFolderName(simpleName);
        return ClassName.get("org.allaymc.api.block.interfaces" + (folderName != null ? "." + folderName : ""), simpleName);
    }

    private static String generateClassSimpleName(BlockId id) {
        return "Block" + Utils.convertToPascalCase(id.getIdentifier().path()) + "Behavior";
    }

    private static String generateInitializerMethodName(BlockId id) {
        return "init" + Utils.convertToPascalCase(id.getIdentifier().path());
    }

    private static String tryFindSpecifiedFolderName(String blockClassSimpleName) {
        for (var entry : SUB_PACKAGE_GROUPERS.entrySet()) {
            var pattern = entry.getKey();
            if (pattern.matcher(blockClassSimpleName).find()) {
                return entry.getValue();
            }
        }
        return null;
    }

    private static void registerSubPackage(Pattern regex, String packageName) {
        SUB_PACKAGE_GROUPERS.put(regex, packageName);
    }

    private static void registerSubPackages() {
        registerSubPackage(Pattern.compile(".*Slab\\d?Behavior"), "slab");
        registerSubPackage(Pattern.compile(".*StairsBehavior"), "stairs");
        registerSubPackage(Pattern.compile(".*DoorBehavior"), "door");
        registerSubPackage(Pattern.compile(".*StandingSignBehavior"), "standingsign");
        registerSubPackage(Pattern.compile(".*HangingSignBehavior"), "hangingsign");
        registerSubPackage(Pattern.compile(".*WallSignBehavior"), "wallsign");
        registerSubPackage(Pattern.compile(".*WallBehavior"), "wall");
        registerSubPackage(Pattern.compile("BlockElement.*"), "element");
        registerSubPackage(Pattern.compile(".*CoralBehavior"), "coral");
        registerSubPackage(Pattern.compile(".*CoralBlockBehavior"), "coralblock");
        registerSubPackage(Pattern.compile(".*CoralFan.*"), "coralfan");
        registerSubPackage(Pattern.compile(".*BricksBehavior"), "bricks");
        registerSubPackage(Pattern.compile(".*WoolBehavior"), "wool");
        registerSubPackage(Pattern.compile(".*ButtonBehavior"), "button");
        registerSubPackage(Pattern.compile(".*PlanksBehavior"), "planks");
        registerSubPackage(Pattern.compile(".*TrapdoorBehavior"), "trapdoor");
        registerSubPackage(Pattern.compile(".*CandleBehavior"), "candle");
        registerSubPackage(Pattern.compile(".*CandleCakeBehavior"), "candlecake");
        registerSubPackage(Pattern.compile(".*ConcreteBehavior"), "concrete");
        registerSubPackage(Pattern.compile(".*ConcretePowderBehavior"), "concretepowder");
        registerSubPackage(Pattern.compile(".*TerracottaBehavior"), "terracotta");
        registerSubPackage(Pattern.compile(".*ShulkerBoxBehavior"), "shulkerbox");
        registerSubPackage(Pattern.compile(".*CarpetBehavior"), "carpet");
        registerSubPackage(Pattern.compile(".*WoodBehavior"), "wood");
        registerSubPackage(Pattern.compile(".*(Leaves\\d?|LeavesFlowered)Behavior"), "leaves");
        registerSubPackage(Pattern.compile(".*FenceBehavior"), "fence");
        registerSubPackage(Pattern.compile(".*FenceGateBehavior"), "fencegate");
        registerSubPackage(Pattern.compile(".*LogBehavior"), "log");
        registerSubPackage(Pattern.compile(".*CopperBehavior"), "copper");
        registerSubPackage(Pattern.compile(".*SaplingBehavior"), "sapling");
        registerSubPackage(Pattern.compile(".*(?:Water|Lava)Behavior"), "liquid");
        registerSubPackage(Pattern.compile("BlockPiston.*"), "piston");
        registerSubPackage(Pattern.compile("BlockStickyPiston.*"), "piston");
        registerSubPackage(Pattern.compile(".*StainedGlassBehavior"), "stainedglass");
        registerSubPackage(Pattern.compile(".*StainedGlassPaneBehavior"), "stainedglasspane");
        registerSubPackage(Pattern.compile(".*GlassBehavior"), "glass");
        registerSubPackage(Pattern.compile(".*GlassPaneBehavior"), "glasspane");
        registerSubPackage(Pattern.compile(".*AmethystBudBehavior"), "amethystbud");
        registerSubPackage(Pattern.compile(".*Torch.*Behavior"), "torch");
        registerSubPackage(Pattern.compile(".*Torchflower.*Behavior"), "torchflower");
        registerSubPackage(Pattern.compile(".*LightBlock.*Behavior"), "lightblock");
        registerSubPackage(Pattern.compile(".*CoralWallFanBehavior"), "coralwallfan");
        registerSubPackage(Pattern.compile(".*AnvilBehavior"), "anvil");
        registerSubPackage(Pattern.compile(".*DirtBehavior"), "dirt");
        registerSubPackage(Pattern.compile(".*SandBehavior"), "sand");
        registerSubPackage(Pattern.compile(".*SandstoneBehavior"), "sandstone");
    }

    private static void registerBuilders() {
        registerBuild("\\w+_BUTTON", "Button", "Buttons");
        registerBuild("\\w+_LEAVES\\b", "Leaves", "Leaves");
        registerBuild("\\w+_SHULKER_BOX", "ShulkerBox", "ShulkerBoxes");
        registerBuild("\\w+_STAIRS", "Stairs", "Stairs");
        registerBuild("\\b(?!STRIPPED_)\\w+_LOG", "Log", "Logs");
        registerBuild("\\bSTRIPPED_\\w+_LOG", "StrippedLog", "StrippedLogs");
        registerBuild("\\w+_HANGING_SIGN", "HangingSign", "HangingSigns");
        registerBuild("(\\w+)?STANDING_SIGN", "StandingSign", "StandingSigns");
        registerBuild("\\w+_WOOL", "Wool", "Wools");
        registerBuild("\\w+_GLAZED_TERRACOTTA\\b", "GlazedTerracotta", "GlazedTerracotta");
        registerBuild("\\w+(?!GLAZED)_TERRACOTTA", "Terracotta", "Terracotta");
        registerBuild("\\w+_STAINED_GLASS_PANE", "StainedGlassPane", "StainedGlassPanes");
        registerBuild("\\w+_STAINED_GLASS\\b", "StainedGlass", "StainedGlass");
        registerBuild("\\w+_CONCRETE_POWDER", "ConcretePowder", "ConcretePowders");
        registerBuild("\\w+_CONCRETE\\b", "Concrete", "Concretes");
        registerBuild("\\w+_CARPET", "Carpet", "Carpets");
        registerBuild("\\w+_CANDLE_CAKE", "CandleCake", "CandleCakes");
        registerBuild("\\w+_CANDLE\\b", "Candle", "Candles");
        registerBuild("\\w+_PRESSURE_PLATE", "PressurePlate", "PressurePlates");
        registerBuild("\\w+_DOOR", "Door", "Doors");
        registerBuild("\\w+_TULIP", "Tulip", "Tulips");
        registerBuild("\\b\\w*DOUBLE\\w*_SLAB\\b", "DoubleSlab", "DoubleSlabs");
        registerBuild("\\w+(?!DOUBLE)_SLAB", "Slab", "Slabs");
        registerBuild("(\\w+)?TRAPDOOR", "Trapdoor", "Trapdoors");
        registerBuild("\\w+_COPPER_GRATE", "CopperGrate", "CopperGrates");
        registerBuild("\\w+_CUT_COPPER\\b", "CutCopper", "CutCoppers");
        registerBuild("\\w+_COPPER_BULB", "CopperBulb", "CopperBulbs");
        registerBuild("(\\w+)?WALL_SIGN", "WallSign", "WallSigns");
        registerBuild("\\w+_PLANKS", "Planks", "Planks");
        registerBuild("\\w+_FENCE(?!_GATE)", "Fence", "Fences");
        registerBuild("\\w+_FENCE_GATE", "FenceGate", "FenceGates");
        registerBuild("\\w+_WALL\\b", "Wall", "Walls");
        registerBuild("\\b(?!STRIPPED_)\\w+_WOOD", "Wood", "Woods");
        registerBuild("\\bSTRIPPED_\\w+_WOOD", "StrippedWood", "StrippedWoods");
        registerBuild("\\w+_SAPLING", "Sapling", "Saplings");
        registerBuild("\\bELEMENT_[0-9]", "Element", "Elements");
    }

    private static void registerBuild(String regex, String buildName, String initName) {
        BUILD_GROUPERS.put(new InitBuilder(Pattern.compile(regex), buildName, initName), new TreeSet<>());
    }

    public record InitBuilder(Pattern regex, String buildName, String initName) {}
}
