package org.allaymc.codegen;

import com.google.gson.JsonParser;
import com.palantir.javapoet.*;
import lombok.SneakyThrows;
import org.allaymc.dependence.StringUtils;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * {@code EntityIdEnumGen} generates entity identifier enums for runtime and
 * generator-side use. Input names are collected in sorted order with their
 * numeric IDs, although the emitted enums retain only identifiers. The server
 * variant additionally resolves each constant through the runtime entity
 * registry using its full stored identifier.
 *
 * @author daoge_cmd | Cool_Loong | IWareQ
 */
public class EntityIdEnumGen {
    /**
     * {@code ENTITY_ID_MAP_FILE_PATH} selects the unpacked entity identifier
     * map loaded before generator entry points can use its sorted names.
     */
    static final Path ENTITY_ID_MAP_FILE_PATH = Path.of(CodeGenConstants.DATA_PATH + "unpacked/entity_id_map.json");
    /**
     * {@code ENTITY_ID_MAP} holds names in sorted order after class
     * initialization. This map retains numeric values, but neither generated
     * enum contains them.
     */
    static final Map<String, Integer> ENTITY_ID_MAP = new TreeMap<>();
    /**
     * {@code PACKAGE_NAME} selects the server package that receives the runtime
     * identifier enum and its entity-registry lookup method.
     */
    private static final String PACKAGE_NAME = "org.allaymc.server.entity.data";

    // Load the identifier snapshot before generation becomes available.
    // Input failure aborts class initialization through CodeGenException, and later
    // invocations reuse the map rather than rereading the resource file.
    static {
        try {
            var reader = JsonParser.parseReader(Files.newBufferedReader(ENTITY_ID_MAP_FILE_PATH));
            reader.getAsJsonObject().entrySet().forEach(entry -> {
                var id = entry.getValue().getAsInt();
                ENTITY_ID_MAP.put(entry.getKey(), id);
            });
        } catch (IOException e) {
            throw new CodeGenException(e);
        }
    }

    /**
     * Generates both entity enums from the class-initialization snapshot.
     * {@code args} is ignored, and resource paths are resolved from the
     * repository root used as the process working directory for this generator.
     */
    public static void main(String[] args) {
        generate();
    }

    /**
     * Replaces the server entity enum before writing the generator-side
     * variant. The two writes are independent, so failure in the second output
     * leaves the first file replaced without restoring its previous contents.
     */
    public static void generate() {
        generateServer(PACKAGE_NAME, Path.of("server/src/main/java/org/allaymc/server/entity/data/EntityId.java"), TypeNames.API_IDENTIFIER, TypeNames.ENTITY_TYPE);
        generateDep("org.allaymc.dependence", Path.of("codegen/src/main/java/org/allaymc/dependence/EntityId.java"), TypeNames.DEP_IDENTIFIER);
    }

    /**
     * Writes an entity enum with the supplied identifier and registry-result
     * types. Constant names use uppercase paths without namespaces, while their
     * constructor arguments retain full identifiers; callers must avoid
     * collisions between normalized names.
     */
    @SneakyThrows
    protected static void generateServer(String packageName, Path outPutPath, ClassName identifierClassName, ClassName entityTypeClassName) {
        TypeSpec.Builder codeBuilder = TypeSpec.enumBuilder("EntityId")
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec
                        .builder(identifierClassName, "identifier", Modifier.PRIVATE, Modifier.FINAL)
                        .addAnnotation(TypeNames.GETTER)
                        .build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(TypeNames.STRING, "identifier")
                        .addStatement("this.$N = new $T($N)", "identifier", identifierClassName, "identifier")
                        .build()
                )
                .addMethod(MethodSpec.methodBuilder("getEntityType")
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("return $T.ENTITIES.get(this.getIdentifier())", TypeNames.REGISTRIES)
                        .returns(ParameterizedTypeName.get(entityTypeClassName, WildcardTypeName.subtypeOf(ClassName.OBJECT)))
                        .build()
                );
        for (var entry : ENTITY_ID_MAP.entrySet()) {
            var valueName = StringUtils.fastTwoPartSplit(entry.getKey(), ":", "")[1].toUpperCase(Locale.ROOT);
            codeBuilder.addEnumConstant(valueName, TypeSpec.anonymousClassBuilder("$S", entry.getKey()).build());
        }

        var javaFile = JavaFile.builder(packageName, codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(outPutPath, javaFile.toString());
    }

    /**
     * Writes a standalone entity enum using the supplied identifier type. The
     * output retains full resource identifiers but omits runtime registry
     * access and numeric input IDs. Existing output is replaced through the
     * shared text writer.
     */
    @SneakyThrows
    protected static void generateDep(String packageName, Path outPutPath, ClassName identifierClassName) {
        TypeSpec.Builder codeBuilder = TypeSpec.enumBuilder("EntityId")
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec
                        .builder(identifierClassName, "identifier", Modifier.PRIVATE, Modifier.FINAL)
                        .addAnnotation(TypeNames.GETTER)
                        .build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(TypeNames.STRING, "identifier")
                        .addStatement("this.$N = new $T($N)", "identifier", identifierClassName, "identifier")
                        .build()
                );
        for (var entry : ENTITY_ID_MAP.entrySet()) {
            var valueName = StringUtils.fastTwoPartSplit(entry.getKey(), ":", "")[1].toUpperCase(Locale.ROOT);
            codeBuilder.addEnumConstant(valueName, TypeSpec.anonymousClassBuilder("$S", entry.getKey()).build());
        }

        var javaFile = JavaFile.builder(packageName, codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(outPutPath, javaFile.toString());
    }
}
