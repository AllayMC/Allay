package org.allaymc.codegen;

import com.palantir.javapoet.*;
import lombok.SneakyThrows;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static org.allaymc.codegen.TypeNames.*;
import static org.allaymc.codegen.Utils.convertToPascalCase;

/**
 * @author daoge_cmd | IWareQ | Cool_Loong
 */
public class BlockPropertyTypeGen {
    public static final BlockPropertyTypeFile BLOCK_PROPERTY_TYPE_INFO_FILE;
    public static final Path BLOCK_PROPERTY_TYPES_FILE = Path.of(CodeGenConstants.DATA_PATH + "unpacked/block_property_types.json");
    private static final Path TYPE_DATA_PATH = Path.of("api/src/main/java/org/allaymc/api/block/property/type");
    private static final Path ENUM_PATH = Path.of("api/src/main/java/org/allaymc/api/block/property/enums");
    private static final String PACKAGE_NAME = "org.allaymc.api.block.property.type";

    static {
        try {
            BLOCK_PROPERTY_TYPE_INFO_FILE = CodeGenConstants.GSON.fromJson(Files.newBufferedReader(BLOCK_PROPERTY_TYPES_FILE), BlockPropertyTypeFile.class);
        } catch (Exception e) {
            throw new CodeGenException(e);
        }
    }

    public static void main(String[] args) {
        generate();
    }

    @SneakyThrows
    public static void generate() {
        for (BlockPropertyTypeFile.BlockPropertyTypeInfo blockPropertyTypeInfo : BLOCK_PROPERTY_TYPE_INFO_FILE.propertyTypes.values()) {
            if (blockPropertyTypeInfo.valueType == BlockPropertyTypeFile.BlockPropertyType.ENUM) {
                generateEnumClass(blockPropertyTypeInfo);
            }
        }
        TypeSpec.Builder codeBuilder = TypeSpec.interfaceBuilder("BlockPropertyTypes")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(MINECRAFT_VERSION_SENSITIVE);
        for (var entry : BLOCK_PROPERTY_TYPE_INFO_FILE.propertyTypes.entrySet()) {
            var key = entry.getKey().toUpperCase(Locale.ROOT);
            var blockPropertyTypeInfo = entry.getValue();
            var name = blockPropertyTypeInfo.serializationName;
            switch (blockPropertyTypeInfo.valueType) {
                case ENUM -> {
                    var enumClass = ClassName.get("org.allaymc.api.block.property.enums", blockPropertyTypeInfo.getEnumClassName());
                    codeBuilder.addField(
                            FieldSpec
                                    .builder(ParameterizedTypeName.get(ENUM_PROPERTY, enumClass), key, Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
                                    .initializer("$T.of($S, $T.class, $T.values()[0])", ENUM_PROPERTY, name, enumClass, enumClass)
                                    .build()
                    );
                }
                case BOOLEAN -> {
                    codeBuilder.addField(
                            FieldSpec
                                    .builder(BOOLEAN_PROPERTY, key, Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
                                    .initializer("$T.of($S, $N)", BOOLEAN_PROPERTY, name, blockPropertyTypeInfo.values.get(0))
                                    .build()
                    );
                }
                case INTEGER -> {
                    int min = Integer.MAX_VALUE;
                    int max = Integer.MIN_VALUE;
                    for (var value : blockPropertyTypeInfo.values) {
                        var intValue = Integer.parseInt(value);
                        if (intValue < min) {
                            min = intValue;
                        }
                        if (intValue > max) {
                            max = intValue;
                        }
                    }
                    codeBuilder.addField(
                            FieldSpec
                                    .builder(INT_PROPERTY, key, Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
                                    .initializer("$T.of($S, $L, $L, $L)", INT_PROPERTY, name, min, max, blockPropertyTypeInfo.values.get(0))
                                    .build()
                    );
                }
            }
        }
        var listClass = ParameterizedTypeName.get(LIST, ParameterizedTypeName.get(BLOCK_PROPERTY_TYPE, WildcardTypeName.subtypeOf(Object.class)));
        String paramStr = BLOCK_PROPERTY_TYPE_INFO_FILE.propertyTypes.keySet().stream().map(s -> s.toUpperCase(Locale.ROOT)).collect(Collectors.joining(", "));
        codeBuilder.addField(
                FieldSpec
                        .builder(listClass, "VALUES", Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
                        .initializer("List.of($N)", paramStr)
                        .build()
        );
        var javaFile = JavaFile.builder(PACKAGE_NAME, codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(TYPE_DATA_PATH.resolve("BlockPropertyTypes.java"), javaFile.toString());
    }

    @SneakyThrows
    protected static void generateEnumClass(BlockPropertyTypeFile.BlockPropertyTypeInfo info) {
        var enumName = info.getEnumClassName();
        TypeSpec.Builder enumBuilder = TypeSpec.enumBuilder(enumName)
                .addModifiers(Modifier.PUBLIC);
        for (var value : info.values) {
            enumBuilder.addEnumConstant(value.toUpperCase(Locale.ROOT));
        }

        var path = ENUM_PATH.resolve(enumName + ".java");
        if (Files.exists(path)) Files.delete(path);

        var javaFile = JavaFile.builder("org.allaymc.api.block.property.enums", enumBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(path, javaFile.toString());
    }

    public static class BlockPropertyTypeFile {
        Map<String, BlockPropertyTypeInfo> propertyTypes;
        List<String> differentSizePropertyTypes;
        Map<String, Map<String, String>> specialBlockTypes;

        protected enum BlockPropertyType {
            BOOLEAN,
            INTEGER,
            ENUM
        }

        public static class BlockPropertyTypeInfo {
            String serializationName;
            BlockPropertyType valueType;
            List<String> values;

            public String getEnumClassName() {
                // Special cases, because values equals
                if (serializationName.contains("wall_connection_type_")) {
                    return "WallConnectionType";
                }
                if (serializationName.contains("pale_moss_carpet_side_")) {
                    return "PaleMossCarpetSide";
                }

                //minecraft:cardinal_direction WTF???
                return convertToPascalCase(serializationName.replace(":", "_"));
            }
        }
    }
}
