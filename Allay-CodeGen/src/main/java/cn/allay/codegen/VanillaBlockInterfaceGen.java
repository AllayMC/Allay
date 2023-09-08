package cn.allay.codegen;

import cn.allay.dependence.VanillaBlockId;
import com.squareup.javapoet.*;
import lombok.SneakyThrows;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

import static cn.allay.codegen.VanillaBlockIdEnumGen.MAPPED_BLOCK_PALETTE_NBT;
import static cn.allay.codegen.VanillaBlockPropertyTypeGen.BLOCK_PROPERTY_TYPE_INFO_FILE;

/**
 * Depend on VanillaBlockIdEnumGen execution
 * <p>
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd | Cool_Loong
 */
public class VanillaBlockInterfaceGen {

    public static final ClassName BLOCK_BEHAVIOR_CLASS_NAME = ClassName.get("cn.allay.api.block", "BlockBehavior");
    public static final ClassName VANILLA_BLOCK_ID_CLASS_NAME = ClassName.get("cn.allay.api.data", "VanillaBlockId");
    public static final ClassName VANILLA_BLOCK_PROPERTY_TYPES_CLASS_NAME = ClassName.get("cn.allay.api.data", "VanillaBlockPropertyTypes");
    public static final ClassName BLOCK_TYPE_CLASS_NAME = ClassName.get("cn.allay.api.block.type", "BlockType");
    public static final ClassName BLOCK_TYPE_BUILDER_CLASS_NAME = ClassName.get("cn.allay.api.block.type", "BlockTypeBuilder");
    public static Path FILE_OUTPUT_PATH_BASE = Path.of("Allay-API/src/main/java/cn/allay/api/block/interfaces");
    private static final Path BLOCK_TYPE_OUTPUT_PATH = Path.of("Allay-API/src/main/java/cn/allay/api/data/VanillaBlockTypes.java");
    private static final String BLOCK_TYPE_PACKAGE_NAME = "cn.allay.api.data";
    private static final TypeSpec.Builder TYPES_CLASS = TypeSpec.interfaceBuilder("VanillaBlockTypes")
            .addModifiers(Modifier.PUBLIC)
            .addJavadoc("Allay Project <p>\n@author daoge_cmd");

    public static void main(String[] args) {
        VanillaBlockIdEnumGen.generate();
        VanillaBlockPropertyTypeGen.generate();
        generate();
    }

    @SneakyThrows
    public static void generate() {
        if (!Files.exists(FILE_OUTPUT_PATH_BASE)) Files.createDirectories(FILE_OUTPUT_PATH_BASE);
        for (var block : VanillaBlockId.values()) {
            var className = "Block" + Utils.convertToPascalCase(block.getIdentifier().path()) + "Behavior";
            var path = FILE_OUTPUT_PATH_BASE.resolve(className + ".java");
            if (!Files.exists(path)) {
                System.out.println("Generating " + className + ".java ...");
                generateBlockClass(className, path);
            }
            generateBlockType(block, className);
        }
        var typesJavaFile = JavaFile
                .builder(BLOCK_TYPE_PACKAGE_NAME, TYPES_CLASS.build())
                .build();
        Files.writeString(BLOCK_TYPE_OUTPUT_PATH, typesJavaFile.toString());
    }

    private static void generateBlockClass(String className, Path path) throws IOException {
        TypeSpec.Builder codeBuilder = TypeSpec.interfaceBuilder(className)
                .addSuperinterface(BLOCK_BEHAVIOR_CLASS_NAME)
                .addJavadoc(
                        "@author daoge_cmd | Cool_Loong <br>\n" +
                        "Allay Project <br>\n")
                .addModifiers(Modifier.PUBLIC);
        var javaFile = JavaFile.builder("cn.allay.api.block.interfaces", codeBuilder.build()).build();
        System.out.println("Generating " + className + ".java ...");
        Files.writeString(path, javaFile.toString());
    }

    private static void generateBlockType(VanillaBlockId vanillaBlockId, String classNameStr) {
        var className = ClassName.get("cn.allay.api.block.interfaces", classNameStr);
        var initializer = CodeBlock.builder();
        initializer
                .add("$T\n        .builder($T.class)\n", BLOCK_TYPE_BUILDER_CLASS_NAME, className)
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
        initializer.add("        .addBasicComponents()\n");
        initializer.add("        .build()");
        TYPES_CLASS.addField(
                FieldSpec
                        .builder(ParameterizedTypeName.get(BLOCK_TYPE_CLASS_NAME, className), vanillaBlockId.name() + "_TYPE")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                        .initializer(initializer.build())
                        .build());
    }
}
