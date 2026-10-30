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
 * {@code BlockPropertyTypeGen} generates block-property constants and
 * supporting enums from processed property definitions. Boolean and integer
 * definitions take their default from the first listed value, while enum
 * defaults use the first constant in their final generated enum. Integer
 * domains become inclusive minimum-to-maximum ranges, so gaps in observed
 * values are not preserved as separate restrictions.
 *
 * <p>
 * Direction-specific wall and pale-moss properties share enum classes because
 * the generator maps those serialized names to a common representation. Their
 * definitions can therefore overwrite the same enum output during one run. The
 * generated property table retains individual serialization names and builds a
 * values list for consumers enumerating all generated types.
 *
 * @author daoge_cmd | IWareQ | Cool_Loong
 */
public class BlockPropertyTypeGen {
    /**
     * {@code BLOCK_PROPERTY_TYPE_INFO_FILE} exposes the parsed definition
     * snapshot reused by this generator and {@code BlockClassGen}; its nested
     * maps and lists remain mutable.
     */
    public static final BlockPropertyTypeFile BLOCK_PROPERTY_TYPE_INFO_FILE;
    /**
     * {@code BLOCK_PROPERTY_TYPES_FILE} selects processed property definitions
     * loaded once during generator class initialization before any output is
     * produced.
     */
    public static final Path BLOCK_PROPERTY_TYPES_FILE = Path.of(CodeGenConstants.DATA_PATH + "unpacked/block_property_types.json");
    /**
     * {@code TYPE_DATA_PATH} locates the API directory receiving the generated
     * property table that references all emitted property constants.
     */
    private static final Path TYPE_DATA_PATH = Path.of("api/src/main/java/org/allaymc/api/block/property/type");
    /**
     * {@code ENUM_PATH} locates the API directory receiving enum classes for
     * properties whose serialized values use named alternatives.
     */
    private static final Path ENUM_PATH = Path.of("api/src/main/java/org/allaymc/api/block/property/enums");
    /**
     * {@code PACKAGE_NAME} selects the property-type package emitted in the
     * generated constants interface and referenced by JavaPoet imports.
     */
    private static final String PACKAGE_NAME = "org.allaymc.api.block.property.type";

    // Load the shared property definition snapshot before either generator can
    // inspect it. Input and parsing exceptions abort initialization through
    // CodeGenException rather than publishing a partially populated snapshot.
    static {
        try {
            BLOCK_PROPERTY_TYPE_INFO_FILE = CodeGenConstants.GSON.fromJson(Files.newBufferedReader(BLOCK_PROPERTY_TYPES_FILE), BlockPropertyTypeFile.class);
        } catch (Exception e) {
            throw new CodeGenException(e);
        }
    }

    /**
     * Generates the enum classes and property table from the loaded snapshot.
     * {@code args} is ignored, and repository-relative paths select both input
     * data and output locations; repeated calls reuse the same mutable
     * snapshot.
     */
    public static void main(String[] args) {
        generate();
    }

    /**
     * Replaces supporting enums before emitting the block-property constants.
     * Each definition must provide a usable first value for its generated
     * default. Integer domains are reduced to their extrema, so intermediate
     * values absent from input still belong to the generated range.
     *
     * <p>
     * Enum values are emitted in input order, making the first enum constant
     * the default. Shared enum names are written repeatedly as definitions are
     * visited. Output writes are independent and are not rolled back if a later
     * enum or the final property table fails.
     */
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
                    // Represent observed integers as an inclusive range; only
                    // its extrema survive, so holes in the original domain are not retained.
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

    /**
     * Writes the enum class selected by one property definition. Values become
     * uppercase constants in their input order. Definitions sharing a class
     * name replace this output without checking domain equality, so
     * {@code ENUM} defaults ultimately use the first constant in the
     * last-written class.
     */
    @SneakyThrows
    protected static void generateEnumClass(BlockPropertyTypeFile.BlockPropertyTypeInfo info) {
        var enumName = info.getEnumClassName();
        TypeSpec.Builder enumBuilder = TypeSpec.enumBuilder(enumName)
                .addModifiers(Modifier.PUBLIC);
        for (var value : info.values) {
            enumBuilder.addEnumConstant(value.toUpperCase(Locale.ROOT));
        }

        var path = ENUM_PATH.resolve(enumName + ".java");
        // Shared enum names can target a file already emitted in this run;
        // replacement uses the current definition without preserving the previous enum contents.
        if (Files.exists(path)) Files.delete(path);

        var javaFile = JavaFile.builder("org.allaymc.api.block.property.enums", enumBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(path, javaFile.toString());
    }

    /**
     * {@code BlockPropertyTypeFile} holds global property definitions and
     * per-block aliases consumed by generators.
     */
    public static class BlockPropertyTypeFile {
        /** Definitions indexed by normalized names. */
        Map<String, BlockPropertyTypeInfo> propertyTypes;
        /**
         * {@code differentSizePropertyTypes} lists serialized property names
         * requiring per-block selection. {@code BlockClassGen} consults this
         * list with {@code specialBlockTypes} before choosing a constant.
         */
        List<String> differentSizePropertyTypes;
        /**
         * {@code specialBlockTypes} maps block identifiers to normalized
         * property aliases. {@code BlockClassGen} uses these aliases when its
         * {@code differentSizePropertyTypes} membership test selects a varying
         * property.
         */
        Map<String, Map<String, String>> specialBlockTypes;

        /**
         * {@code BlockPropertyType} identifies the Java representation emitted
         * for a property domain.
         */
        protected enum BlockPropertyType {
            /** Boolean-valued property domain. */
            BOOLEAN,
            /** Integer-valued property domain. */
            INTEGER,
            /** Named-value property domain. */
            ENUM
        }

        /**
         * {@code BlockPropertyTypeInfo} describes one serialized property and
         * its ordered observed value domain.
         */
        public static class BlockPropertyTypeInfo {
            /** Original serialized property name. */
            String serializationName;
            /** Generated property representation kind. */
            BlockPropertyType valueType;
            /**
             * {@code values} stores the ordered domain. {@code generate} uses
             * its first entry for {@code BOOLEAN} and {@code INTEGER} defaults.
             * {@code generateEnumClass} preserves this order when emitting
             * constants, but {@code ENUM} defaults use {@code values()[0]} from
             * the final generated enum. Another definition sharing that class
             * name may replace its contents.
             */
            List<String> values;

            /**
             * Returns the enum class name associated with this serialized
             * property. {@code serializationName} containing
             * {@code "wall_connection_type_"} selects
             * {@code WallConnectionType}, and {@code "pale_moss_carpet_side_"}
             * selects {@code PaleMossCarpetSide}. Other names replace namespace
             * separators before Pascal-case conversion, without checking for
             * name collisions.
             */
            public String getEnumClassName() {
                // Directional variants use shared enum representations, so block
                // properties for different sides refer to the same generated value type.
                if (serializationName.contains("wall_connection_type_")) {
                    return "WallConnectionType";
                }
                if (serializationName.contains("pale_moss_carpet_side_")) {
                    return "PaleMossCarpetSide";
                }

                // Namespaced property names can contain a colon, which must be
                // normalized before the helper constructs a Java enum class name.
                return convertToPascalCase(serializationName.replace(":", "_"));
            }
        }
    }
}
