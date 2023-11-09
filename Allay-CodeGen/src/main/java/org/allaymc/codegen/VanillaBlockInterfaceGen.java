package org.allaymc.codegen;

import org.allaymc.dependence.VanillaBlockId;
import com.squareup.javapoet.*;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static org.allaymc.codegen.VanillaBlockIdEnumGen.MAPPED_BLOCK_PALETTE_NBT;
import static org.allaymc.codegen.VanillaBlockPropertyTypeGen.BLOCK_PROPERTY_TYPE_INFO_FILE;

/**
 * Depend on VanillaBlockIdEnumGen execution
 * <p>
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd | Cool_Loong
 */
public class VanillaBlockInterfaceGen {

    public static final ClassName BLOCK_BEHAVIOR_CLASS_NAME = ClassName.get("org.allaymc.api.block", "BlockBehavior");
    public static final ClassName VANILLA_BLOCK_ID_CLASS_NAME = ClassName.get("org.allaymc.api.data", "VanillaBlockId");
    public static final ClassName VANILLA_BLOCK_PROPERTY_TYPES_CLASS_NAME = ClassName.get("org.allaymc.api.data", "VanillaBlockPropertyTypes");
    public static final ClassName BLOCK_TYPE_CLASS_NAME = ClassName.get("org.allaymc.api.block.type", "BlockType");
    public static final ClassName BLOCK_TYPE_BUILDER_CLASS_NAME = ClassName.get("org.allaymc.api.block.type", "BlockTypeBuilder");
    public static Path FILE_OUTPUT_PATH_BASE = Path.of("Allay-API/src/main/java/cn/allay/api/block/interfaces");
    public static Map<Pattern, String> SUB_PACKAGE_GROUPERS = new LinkedHashMap<>();


    public static void main(String[] args) {
        VanillaBlockIdEnumGen.generate();
        VanillaBlockPropertyTypeGen.generate();
        generate();
    }

    @SneakyThrows
    public static void generate() {
        registerSubPackage(Pattern.compile(".*Slab\\d?Behavior"), "slab");
        registerSubPackage(Pattern.compile(".*StairsBehavior"), "stairs");
        registerSubPackage(Pattern.compile(".*DoorBehavior"), "door");
        registerSubPackage(Pattern.compile(".*StandingSignBehavior"), "standingsign");
        registerSubPackage(Pattern.compile(".*HangingSignBehavior"), "hangingsign");
        registerSubPackage(Pattern.compile(".*WallSignBehavior"), "wallsign");
        registerSubPackage(Pattern.compile(".*WallBehavior"), "wall");
        registerSubPackage(Pattern.compile("BlockElement.*"), "element");
        registerSubPackage(Pattern.compile(".*CoralBehavior"), "coral");
        registerSubPackage(Pattern.compile("BlockCoralFan.*"), "coralfan");
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
        registerSubPackage(Pattern.compile(".*Torch.*Behavior"),"torch");
        if (!Files.exists(FILE_OUTPUT_PATH_BASE)) Files.createDirectories(FILE_OUTPUT_PATH_BASE);
        for (var block : VanillaBlockId.values()) {
            var blockClassSimpleName = "Block" + Utils.convertToPascalCase(block.getIdentifier().path()) + "Behavior";
            var folderName = tryFindSpecifiedFolderName(blockClassSimpleName);
            var blockClassName = ClassName.get("org.allaymc.api.block.interfaces" + (folderName != null ? "." + folderName : ""), blockClassSimpleName);
            var folderPath = folderName != null ? FILE_OUTPUT_PATH_BASE.resolve(folderName) : FILE_OUTPUT_PATH_BASE;
            var path = folderPath.resolve(blockClassSimpleName + ".java");
            if (!Files.exists(path)) {
                System.out.println("Generating " + blockClassName + "...");
                if (!Files.exists(folderPath)) Files.createDirectories(folderPath);
                generateBlockClass(block, blockClassName, path);
            } else {
                System.out.println("Class " + blockClassName + " already exists during block class generating!");
            }
        }
    }

    @Nullable
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

    private static void generateBlockClass(VanillaBlockId vanillaBlockId, ClassName blockClassName, Path path) throws IOException {
        TypeSpec.Builder codeBuilder = TypeSpec.interfaceBuilder(blockClassName)
                .addSuperinterface(BLOCK_BEHAVIOR_CLASS_NAME)
                .addJavadoc(
                        "@author daoge_cmd | Cool_Loong <br>\n" +
                        "Allay Project <br>\n")
                .addField(generateBlockTypeField(vanillaBlockId, blockClassName))
                .addModifiers(Modifier.PUBLIC);
        var javaFile = JavaFile.builder(blockClassName.packageName(), codeBuilder.build()).build();
        System.out.println("Generating " + blockClassName + ".java ...");
        Files.writeString(path, javaFile.toString());
    }

    private static FieldSpec generateBlockTypeField(VanillaBlockId vanillaBlockId, ClassName blockClassName) {
        var initializer = CodeBlock.builder();
        initializer
                .add("$T\n        .builder($T.class)\n", BLOCK_TYPE_BUILDER_CLASS_NAME, blockClassName)
                .add("        .vanillaBlock($T.$N)\n", VANILLA_BLOCK_ID_CLASS_NAME, vanillaBlockId.name());
        var blockPaletteData = MAPPED_BLOCK_PALETTE_NBT.get(vanillaBlockId.getIdentifier().toString());
        var states = blockPaletteData.getCompound("states");
        if (!states.isEmpty()) {
            initializer.add("        .setProperties(");
            AtomicInteger count = new AtomicInteger();
            states.forEach((name, value) -> {
                var propertyName = BLOCK_PROPERTY_TYPE_INFO_FILE.differentSizePropertyTypes.contains(name.replaceAll(":", "_")) && BLOCK_PROPERTY_TYPE_INFO_FILE.specialBlockTypes.containsKey(vanillaBlockId.getIdentifier().toString()) ?
                        BLOCK_PROPERTY_TYPE_INFO_FILE.specialBlockTypes.get(vanillaBlockId.getIdentifier().toString()).get(name.replaceAll(":", "_")).toUpperCase() : name.replaceAll(":", "_").toUpperCase();
                initializer.add("$T.$N" + (states.size() == count.incrementAndGet() ? "" : ", "), VANILLA_BLOCK_PROPERTY_TYPES_CLASS_NAME, propertyName);
            });
            initializer.add(")\n");
        }
        initializer.add("        .build()");
        return FieldSpec
                .builder(ParameterizedTypeName.get(BLOCK_TYPE_CLASS_NAME, blockClassName), vanillaBlockId.name() + "_TYPE")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer(initializer.build())
                .build();
    }
}
