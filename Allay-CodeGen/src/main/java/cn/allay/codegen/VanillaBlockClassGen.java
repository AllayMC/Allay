package cn.allay.codegen;

import cn.allay.block.data.VanillaBlockId;
import com.squareup.javapoet.*;
import lombok.SneakyThrows;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public class VanillaBlockClassGen {

    public static final ClassName BLOCK_CLASS_NAME = ClassName.get("cn.allay.block", "Block");
    public static final ClassName VANILLA_BLOCK_ID_CLASS_NAME = ClassName.get("cn.allay.block.data", "VanillaBlockId");
    public static final ClassName VANILLA_BLOCK_PROPERTY_TYPES_CLASS_NAME = ClassName.get("cn.allay.block.property.vanilla", "VanillaBlockPropertyTypes");
    public static final ClassName BLOCK_TYPE_CLASS_NAME = ClassName.get("cn.allay.block.type", "BlockType");
    public static final ClassName BLOCK_TYPE_BUILDER_CLASS_NAME = ClassName.get("cn.allay.block.type", "BlockTypeBuilder");
    public static Path FILE_OUTPUT_PATH_BASE = Path.of("Allay-API/src/main/java/cn/allay/block/impl");

    @SneakyThrows
    public static void main(String[] args) {
        if (!Files.exists(FILE_OUTPUT_PATH_BASE)) Files.createDirectories(FILE_OUTPUT_PATH_BASE);
        for (var block : VanillaBlockId.values()) {
            var className = "Block" + Utils.convertToCamelCase(block.getNamespaceId().getPath());
            var path = FILE_OUTPUT_PATH_BASE.resolve(className + ".java");
            if (Files.exists(path)) {
                System.out.println("Class " + className + " already exists, skipped");
            } else {
                System.out.println("Generating " + className + ".java ...");
                generateBlockClass(block, className, path);
            }
        }
    }

    private static void generateBlockClass(VanillaBlockId vanillaBlockId, String className, Path path) throws IOException {
        TypeSpec.Builder codeBuilder = TypeSpec.interfaceBuilder(className)
                .addSuperinterface(BLOCK_CLASS_NAME)
                .addJavadoc(
                        "Author: daoge_cmd <br>\n" +
                        "Allay Project <br>\n")
                .addModifiers(Modifier.PUBLIC);
        var initializer = CodeBlock.builder();
        initializer
                .add("$T\n.builder($N.class)\n", BLOCK_TYPE_BUILDER_CLASS_NAME, className)
                .add(".vanillaBlock($T.$N, true)\n", VANILLA_BLOCK_ID_CLASS_NAME, vanillaBlockId.getNamespaceId().getPath().toUpperCase());
        var blockPaletteData = CodeGen.MAPPED_BLOCK_PALETTE_NBT.get(vanillaBlockId.getNamespaceId().toString());
        var states = blockPaletteData.getCompound("states");
        if (states.size() != 0) {
            initializer.add(".withProperties(");
            AtomicInteger count = new AtomicInteger();
            states.forEach((name, value) -> {
                initializer.add("$T.$N" + (states.size() == count.incrementAndGet() ? "" : ",\n"), VANILLA_BLOCK_PROPERTY_TYPES_CLASS_NAME, name.toUpperCase());
            });
            initializer.add(")\n");
        }
        initializer.add(".addBasicComponents()\n");
        initializer.add(".build()");
        codeBuilder.addField(
                FieldSpec
                        .builder(ParameterizedTypeName.get(BLOCK_TYPE_CLASS_NAME, ClassName.get("", className)), "TYPE")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                        .initializer(initializer.build())
                        .build());
        var javaFile = JavaFile.builder("cn.allay.block.impl", codeBuilder.build()).build();
        Files.writeString(path, javaFile.toString());
    }
}
