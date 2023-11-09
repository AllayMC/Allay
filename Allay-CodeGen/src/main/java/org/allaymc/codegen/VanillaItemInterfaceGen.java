package org.allaymc.codegen;

import org.allaymc.dependence.VanillaItemId;
import com.squareup.javapoet.*;
import lombok.SneakyThrows;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
public class VanillaItemInterfaceGen {
    public static final Map<String, NbtMap> MAPPED_ITEM_DATA = new TreeMap<>();
    public static final Path ITEM_DATA_FILE_PATH = Path.of(CodeGen.DATA_PATH + "item_data.nbt");
    public static final ClassName ITEM_CLASS_NAME = ClassName.get("org.allaymc.api.item", "ItemStack");
    public static final ClassName VANILLA_ITEM_ID_CLASS_NAME = ClassName.get("org.allaymc.api.data", "VanillaItemId");
    public static final ClassName ITEM_TYPE_CLASS_NAME = ClassName.get("org.allaymc.api.item.type", "ItemType");
    public static final ClassName ITEM_TYPE_BUILDER_CLASS_NAME = ClassName.get("org.allaymc.api.item.type", "ItemTypeBuilder");
    public static Path FILE_OUTPUT_PATH_BASE = Path.of("Allay-API/src/main/java/cn/allay/api/item/interfaces");
    public static Map<Pattern, String> SUB_PACKAGE_GROUPERS = new LinkedHashMap<>();

    static {
        try {
            NbtMap reader = (NbtMap) NbtUtils.createGZIPReader(Files.newInputStream(ITEM_DATA_FILE_PATH)).readTag();
            reader.getList("item", NbtType.COMPOUND).forEach(item -> MAPPED_ITEM_DATA.put(item.getString("name"), item));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        VanillaItemIdEnumGen.generate();
        generate();
    }

    @SneakyThrows
    public static void generate() {
        registerSubPackage(Pattern.compile(".*StairsStack"), "stairs");
        registerSubPackage(Pattern.compile(".*DoorStack"), "door");
        registerSubPackage(Pattern.compile(".*Slab\\d?Stack"), "slab");
        registerSubPackage(Pattern.compile(".*StandingSignStack"), "standingsign");
        registerSubPackage(Pattern.compile(".*HangingSignStack"), "hangingsign");
        registerSubPackage(Pattern.compile(".*WallSignStack"), "wallsign");
        registerSubPackage(Pattern.compile(".*SignStack"), "sign");
        registerSubPackage(Pattern.compile(".*WallStack"), "wall");
        registerSubPackage(Pattern.compile("ItemElement.*"), "element");
        registerSubPackage(Pattern.compile(".*CoralStack"), "coral");
        registerSubPackage(Pattern.compile("ItemCoralFan.*"), "coralfan");
        registerSubPackage(Pattern.compile(".*BricksStack"), "bricks");
        registerSubPackage(Pattern.compile(".*WoolStack"), "wool");
        registerSubPackage(Pattern.compile(".*ButtonStack"), "button");
        registerSubPackage(Pattern.compile(".*PlanksStack"), "planks");
        registerSubPackage(Pattern.compile(".*TrapdoorStack"), "trapdoor");
        registerSubPackage(Pattern.compile(".*CandleStack"), "candle");
        registerSubPackage(Pattern.compile(".*CandleCakeStack"), "candlecake");
        registerSubPackage(Pattern.compile(".*ConcreteStack"), "concrete");
        registerSubPackage(Pattern.compile(".*ConcretePowderStack"), "concretepowder");
        registerSubPackage(Pattern.compile(".*TerracottaStack"), "terracotta");
        registerSubPackage(Pattern.compile(".*ShulkerBoxStack"), "shulkerbox");
        registerSubPackage(Pattern.compile(".*CarpetStack"), "carpet");
        registerSubPackage(Pattern.compile(".*WoodStack"), "wood");
        registerSubPackage(Pattern.compile(".*(Leaves\\d?|LeavesFlowered)Stack"), "leaves");
        registerSubPackage(Pattern.compile(".*FenceStack"), "fence");
        registerSubPackage(Pattern.compile(".*FenceGateStack"), "fencegate");
        registerSubPackage(Pattern.compile(".*Log\\d?Stack"), "log");
        registerSubPackage(Pattern.compile(".*CopperStack"), "copper");
        registerSubPackage(Pattern.compile(".*SaplingStack"), "sapling");
        registerSubPackage(Pattern.compile(".*(?:Water|Lava)Stack"), "liquid");
        registerSubPackage(Pattern.compile(".*BoatStack"), "boat");
        registerSubPackage(Pattern.compile(".*MinecartStack"), "minecart");
        registerSubPackage(Pattern.compile(".*BucketStack"), "bucket");
        registerSubPackage(Pattern.compile(".*EggStack"), "egg");
        registerSubPackage(Pattern.compile("ItemMusicDisc.*"), "musicdisc");
        registerSubPackage(Pattern.compile("ItemPiston.*"), "piston");
        registerSubPackage(Pattern.compile("ItemStickyPiston.*"), "piston");
        registerSubPackage(Pattern.compile(".*StainedGlassStack"), "stainedglass");
        registerSubPackage(Pattern.compile(".*StainedGlassPaneStack"), "stainedglasspane");
        registerSubPackage(Pattern.compile(".*GlassStack"), "glass");
        registerSubPackage(Pattern.compile(".*GlassPaneStack"), "glasspane");
        if (!Files.exists(FILE_OUTPUT_PATH_BASE)) Files.createDirectories(FILE_OUTPUT_PATH_BASE);
        for (var item : VanillaItemId.values()) {
            var itemClassSimpleName = item == VanillaItemId.NETHERBRICK ? "ItemNetherbrick0Stack" : "Item" + Utils.convertToPascalCase(item.getIdentifier().path().replace(".", "_")) + "Stack";
            var folderName = tryFindSpecifiedFolderName(itemClassSimpleName);
            var itemClassName = ClassName.get("org.allaymc.api.item.interfaces" + (folderName != null ? "." + folderName : ""), itemClassSimpleName);
            var folderPath = folderName != null ? FILE_OUTPUT_PATH_BASE.resolve(folderName) : FILE_OUTPUT_PATH_BASE;
            var path = folderPath.resolve(itemClassSimpleName + ".java");
            if (!Files.exists(path)) {
                System.out.println("Generating " + itemClassName + "...");
                if (!Files.exists(folderPath)) Files.createDirectories(folderPath);
                generateItemClass(item, itemClassName, path);
            } else {
                System.out.println("Class " + itemClassName + " already exists during block class generating!");
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
