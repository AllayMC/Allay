package cn.allay.codegen;

import cn.allay.api.data.VanillaItemId;
import com.squareup.javapoet.*;
import lombok.SneakyThrows;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/20 <br>
 * Allay Project <br>
 */
public class VanillaItemClassGen {

    public static final ClassName ITEM_CLASS_NAME = ClassName.get("cn.allay.api.item", "ItemStack");
    public static final ClassName VANILLA_ITEM_ID_CLASS_NAME = ClassName.get("cn.allay.api.item.data", "VanillaItemId");
    public static final ClassName ITEM_TYPE_CLASS_NAME = ClassName.get("cn.allay.api.item.type", "ItemType");
    public static final ClassName ITEM_TYPE_BUILDER_CLASS_NAME = ClassName.get("cn.allay.api.item.type", "ItemTypeBuilder");
    public static final ClassName ITEM_TYPE_REGISTRY = ClassName.get("cn.allay.api.item.type", "ItemTypeRegistry");
    public static Path FILE_OUTPUT_PATH_BASE = Path.of("Allay-API/src/main/java/cn/allay/api/item/impl");

    @SneakyThrows
    public static void main(String[] args) {
        if (!Files.exists(FILE_OUTPUT_PATH_BASE)) Files.createDirectories(FILE_OUTPUT_PATH_BASE);
        for (var item : VanillaItemId.values()) {
            var typeName = item.getNamespaceId().getPath().replace(".", "_");
            var className = item == VanillaItemId.NETHERBRICK ? "ItemNetherbrick0" : "Item" + Utils.convertToPascalCase(typeName);
            var path = FILE_OUTPUT_PATH_BASE.resolve(className + ".java");
            if (Files.exists(path)) {
                System.out.println("Class " + className + " already exists, skipped");
            } else {
                System.out.println("Generating " + className + ".java ...");
                generateItemClass(item, className, path);
            }
        }
    }

    private static void generateItemClass(VanillaItemId vanillaItemId, String className, Path path) throws IOException {
        TypeSpec.Builder codeBuilder = TypeSpec.interfaceBuilder(className)
                .addSuperinterface(ITEM_CLASS_NAME)
                .addJavadoc(
                        "Author: daoge_cmd <br>\n" +
                        "Allay Project <br>\n")
                .addModifiers(Modifier.PUBLIC);
        var initializer = CodeBlock.builder();
        initializer
                .add("$T\n.builder($N.class)\n", ITEM_TYPE_BUILDER_CLASS_NAME, className)
                .add(".vanillaItem($T.$N, true)\n", VANILLA_ITEM_ID_CLASS_NAME, vanillaItemId.name())
                .add(".addBasicComponents()\n")
                .add(".build()")
                .add(".register($T.getRegistry())", ITEM_TYPE_REGISTRY);
        codeBuilder.addField(
                FieldSpec
                        .builder(ParameterizedTypeName.get(ITEM_TYPE_CLASS_NAME, ClassName.get("", className)), "TYPE")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                        .initializer(initializer.build())
                        .build());
        var javaFile = JavaFile.builder("cn.allay.api.item.impl", codeBuilder.build()).build();
        Files.writeString(path, javaFile.toString());
    }
}
