package org.allaymc.codegen;

import com.google.gson.JsonParser;
import com.palantir.javapoet.*;
import lombok.SneakyThrows;
import org.allaymc.dependence.StringUtils;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * {@code ItemIdEnumGen} generates item identifier enums for runtime code and
 * generator dependencies. Both outputs use the item resource's names and
 * numeric IDs, collected once during class initialization in name order. The
 * server variant additionally resolves identifiers and accesses the runtime
 * item registry through API types.
 *
 * @author daoge_cmd | Cool_Loong | IWareQ
 */
public class ItemIdEnumGen {
    /**
     * {@code ITEM_NAME_TO_ID} holds item IDs ordered by identifier. Callers can
     * mutate this shared map to change subsequent generation without reloading
     * its input resource.
     */
    public static final Map<String, Integer> ITEM_NAME_TO_ID = new TreeMap<>();
    /**
     * {@code ITEM_DATA_FILE_PATH} selects the processed item resource read once
     * when this generator class is initialized in the current process.
     */
    public static final Path ITEM_DATA_FILE_PATH = Path.of(CodeGenConstants.DATA_PATH + "items.json");
    /**
     * {@code PACKAGE_NAME} selects the server package for the generated runtime
     * enum and its API-based registry lookup methods.
     */
    private static final String PACKAGE_NAME = "org.allaymc.server.item.data";

    // Load the identifier-to-ID snapshot before generator methods become usable.
    // I/O failure aborts class initialization through CodeGenException; later calls
    // reuse the mutable map instead of rereading the resource.
    static {
        try {
            var reader = new InputStreamReader(Files.newInputStream(ITEM_DATA_FILE_PATH));
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                ITEM_NAME_TO_ID.put(entry.getKey(), entry.getValue().getAsJsonObject().get("id").getAsInt());
            });
        } catch (IOException e) {
            throw new CodeGenException(e);
        }
    }

    /**
     * Generates both item enums using the current shared input map.
     * {@code args} is ignored, and resource paths are repository-relative;
     * class initialization must have loaded the item resource before generation
     * begins.
     */
    public static void main(String[] args) {
        generate();
    }

    /**
     * Replaces the generator-side enum and then its server counterpart. These
     * writes are independent, so failure while writing the second file leaves
     * the first output already replaced without rolling it back.
     */
    @SneakyThrows
    public static void generate() {
        generateToDependenceModule();
        generateToServerModule();
    }

    /**
     * Writes the item enum using the generator's standalone identifier type.
     * Constants retain resource names and numeric IDs, while this variant omits
     * the server registry and identifier-lookup methods added to the runtime
     * enum.
     */
    @SneakyThrows
    public static void generateToDependenceModule() {
        TypeSpec.Builder codeBuilder = commonBuilder(TypeNames.DEP_IDENTIFIER);
        addEnums(codeBuilder);
        var javaFile = JavaFile.builder("org.allaymc.dependence", codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(Path.of("codegen/src/main/java/org/allaymc/dependence/ItemId.java"), javaFile.toString());
    }

    /**
     * Writes the runtime item enum with identifier and registry lookup methods.
     * Generated identifier lookup normalizes the path but ignores the
     * namespace, returning {@code null} when no enum constant matches; registry
     * lookup uses the complete identifier stored by each constant.
     */
    @SneakyThrows
    public static void generateToServerModule() {
        // Generated enum lookup treats an unknown normalized path as absence;
        // keep that null result distinct from failures in the runtime registry lookup.
        TypeSpec.Builder codeBuilder = commonBuilder(TypeNames.API_IDENTIFIER)
                .addMethod(MethodSpec.methodBuilder("fromIdentifier")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(TypeNames.API_IDENTIFIER, "identifier")
                        .beginControlFlow("try")
                        .addStatement("return valueOf(identifier.path().replace(\".\", \"_\").toUpperCase($T.ROOT))", Locale.class)
                        .nextControlFlow("catch ($T ignore)", IllegalArgumentException.class)
                        .addStatement("return null")
                        .endControlFlow()
                        .returns(TypeNames.ITEM_ID)
                        .build()
                )
                .addMethod(MethodSpec.methodBuilder("getItemType")
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("return $T.ITEMS.get(this.getIdentifier())", TypeNames.REGISTRIES)
                        .returns(ParameterizedTypeName.get(TypeNames.ITEM_TYPE, WildcardTypeName.subtypeOf(ClassName.OBJECT)))
                        .build()
                );
        addEnums(codeBuilder);
        var javaFile = JavaFile.builder(PACKAGE_NAME, codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(Path.of("server/src/main/java/org/allaymc/server/item/data/ItemId.java"), javaFile.toString());
    }

    /**
     * Appends item constants to the supplied builder in resource-name order.
     * Constant names discard the namespace and split at the first path dot;
     * original full identifiers and numeric IDs remain constructor arguments.
     * Normalized-name collisions are not resolved here.
     */
    private static void addEnums(TypeSpec.Builder codeBuilder) {
        for (var entry : ITEM_NAME_TO_ID.entrySet()) {
            var split = StringUtils.fastTwoPartSplit(
                    StringUtils.fastTwoPartSplit(entry.getKey(), ":", "")[1],
                    ".", "");
            var valueName = split[0].isBlank() ? split[1].toUpperCase(Locale.ROOT) : split[0].toUpperCase(Locale.ROOT) + "_" + split[1].toUpperCase(Locale.ROOT);
            codeBuilder.addEnumConstant(valueName, TypeSpec.anonymousClassBuilder("$S, $L", entry.getKey(), entry.getValue()).build());
        }
    }

    /**
     * Creates the shared enum structure with identifier and runtime-ID fields.
     * The supplied identifier type selects the target module's representation;
     * callers add constants and optional lookup methods before building or
     * writing the returned mutable JavaPoet builder.
     */
    private static TypeSpec.Builder commonBuilder(ClassName identifierClass) {
        return TypeSpec.enumBuilder("ItemId")
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec
                        .builder(identifierClass, "identifier", Modifier.PRIVATE, Modifier.FINAL)
                        .addAnnotation(TypeNames.GETTER)
                        .build())
                .addField(FieldSpec
                        .builder(int.class, "runtimeId", Modifier.PRIVATE, Modifier.FINAL)
                        .addAnnotation(TypeNames.GETTER)
                        .build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(TypeNames.STRING, "identifier")
                        .addParameter(int.class, "runtimeId")
                        .addStatement("this.identifier = new $T(identifier)", identifierClass)
                        .addStatement("this.runtimeId = runtimeId")
                        .build()
                );
    }
}
