package cn.allay.codegen;

import cn.allay.dependence.VanillaBlockId;
import com.squareup.javapoet.*;
import lombok.SneakyThrows;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
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

    public static void main(String[] args) {
        VanillaBlockIdEnumGen.generate();
        VanillaBlockPropertyTypeGen.generate();
        generate();
    }

    @SneakyThrows
    public static void generate() {
        if (!Files.exists(FILE_OUTPUT_PATH_BASE)) Files.createDirectories(FILE_OUTPUT_PATH_BASE);
        for (var block : VanillaBlockId.values()) {
            var blockClassSimpleName = "Block" + Utils.convertToPascalCase(block.getIdentifier().path()) + "Behavior";
            var blockClassName = ClassName.get("cn.allay.api.block.interfaces", blockClassSimpleName);
            var path = FILE_OUTPUT_PATH_BASE.resolve(blockClassSimpleName + ".java");
            if (!Files.exists(path)) {
                System.out.println("Generating " + blockClassName + "...");
                generateBlockClass(block, blockClassName, path);
            } else {
                System.out.println("Class " + blockClassName + " already exists during block class generating!");
            }
        }
    }

    private static void generateBlockClass(VanillaBlockId vanillaBlockId, ClassName blockClassName, Path path) throws IOException {
        TypeSpec.Builder codeBuilder = TypeSpec.interfaceBuilder(blockClassName)
                .addSuperinterface(BLOCK_BEHAVIOR_CLASS_NAME)
                .addJavadoc(
                        "@author daoge_cmd | Cool_Loong <br>\n" +
                        "Allay Project <br>\n")
                .addField(generateBlockTypeField(vanillaBlockId, blockClassName))
                .addModifiers(Modifier.PUBLIC);
        var javaFile = JavaFile.builder("cn.allay.api.block.interfaces", codeBuilder.build()).build();
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
