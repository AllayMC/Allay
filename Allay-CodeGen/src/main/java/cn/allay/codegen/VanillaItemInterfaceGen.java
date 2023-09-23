package cn.allay.codegen;

import cn.allay.dependence.VanillaItemId;
import com.squareup.javapoet.*;
import lombok.SneakyThrows;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public class VanillaItemInterfaceGen {
    public static void main(String[] args) {
        VanillaItemIdEnumGen.generate();
        generate();
    }

    public static final Map<String, NbtMap> MAPPED_ITEM_DATA = new TreeMap<>();
    static final Path ITEM_DATA_FILE_PATH = Path.of(CodeGen.DATA_PATH + "item_data.nbt");

    static {
        try {
            NbtMap reader = (NbtMap) NbtUtils.createGZIPReader(Files.newInputStream(ITEM_DATA_FILE_PATH)).readTag();
            reader.getList("item", NbtType.COMPOUND).forEach(item -> MAPPED_ITEM_DATA.put(item.getString("name"), item));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static final ClassName ITEM_CLASS_NAME = ClassName.get("cn.allay.api.item", "ItemStack");
    public static final ClassName VANILLA_ITEM_ID_CLASS_NAME = ClassName.get("cn.allay.api.data", "VanillaItemId");
    public static final ClassName ITEM_TYPE_CLASS_NAME = ClassName.get("cn.allay.api.item.type", "ItemType");
    public static final ClassName ITEM_TYPE_BUILDER_CLASS_NAME = ClassName.get("cn.allay.api.item.type", "ItemTypeBuilder");
    public static Path FILE_OUTPUT_PATH_BASE = Path.of("Allay-API/src/main/java/cn/allay/api/item/interfaces");

    @SneakyThrows
    public static void generate() {
        if (!Files.exists(FILE_OUTPUT_PATH_BASE)) Files.createDirectories(FILE_OUTPUT_PATH_BASE);
        for (var item : VanillaItemId.values()) {
            var itemClassSimpleName = item == VanillaItemId.NETHERBRICK ? "ItemNetherbrick0Stack" : "Item" + Utils.convertToPascalCase(item.getIdentifier().path().replace(".", "_")) + "Stack";
            var folderName = item == VanillaItemId.NETHERBRICK ? "netherbrick0" : Utils.convertToPascalCase(item.getIdentifier().path().replace(".", "_")).toLowerCase();
            var itemClassName = ClassName.get("cn.allay.api.item.interfaces." + folderName, itemClassSimpleName);
            var path = FILE_OUTPUT_PATH_BASE.resolve(folderName).resolve(itemClassSimpleName + ".java");
            if (!Files.exists(path)) {
                System.out.println("Generating " + itemClassName + "...");
                generateItemClass(item, itemClassName, path);
            } else {
                System.out.println("Class " + itemClassName + " already exists during block class generating!");
            }
        }
    }

    private static void generateItemClass(VanillaItemId vanillaItemId, ClassName itemClassName, Path path) throws IOException {
        TypeSpec.Builder codeBuilder = TypeSpec.interfaceBuilder(itemClassName)
                .addSuperinterface(ITEM_CLASS_NAME)
                .addJavadoc(
                        "@author daoge_cmd <br>\n" +
                        "Allay Project <br>\n")
                .addField(generateItemTypeField(vanillaItemId, itemClassName))
                .addModifiers(Modifier.PUBLIC);
        var javaFile = JavaFile.builder(itemClassName.packageName(), codeBuilder.build()).build();
        System.out.println("Generating " + itemClassName + ".java ...");
        Files.writeString(path, javaFile.toString());
    }

    private static FieldSpec generateItemTypeField(VanillaItemId vanillaItemId, ClassName itemClassName) {
        var initializer = CodeBlock.builder();
        initializer
                .add("$T\n        .builder($T.class)\n", ITEM_TYPE_BUILDER_CLASS_NAME, itemClassName)
                .add("        .vanillaItem($T.$N)\n", VANILLA_ITEM_ID_CLASS_NAME, vanillaItemId.name())
                .add("        .build()");
        return FieldSpec
                .builder(ParameterizedTypeName.get(ITEM_TYPE_CLASS_NAME, itemClassName), vanillaItemId.name() + "_TYPE")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer(initializer.build())
                .build();
    }
}
