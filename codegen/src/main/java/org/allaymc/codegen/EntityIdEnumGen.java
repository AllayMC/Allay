package org.allaymc.codegen;

import com.google.gson.JsonParser;
import com.palantir.javapoet.*;
import lombok.SneakyThrows;
import org.allaymc.dependence.StringUtils;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author daoge_cmd | Cool_Loong | IWareQ
 */
public class EntityIdEnumGen {
    static final Path ENTITY_ID_MAP_FILE_PATH = Path.of(CodeGenConstants.DATA_PATH + "unpacked/entity_id_map.json");
    static final Map<String, Integer> ENTITY_ID_MAP = new TreeMap<>();
    private static final String PACKAGE_NAME = "org.allaymc.api.entity.data";

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

    public static void main(String[] args) {
        generate();
    }

    public static void generate() {
        generateApi(PACKAGE_NAME, Path.of("api/src/main/java/org/allaymc/api/entity/data/EntityId.java"), ClassNames.API_IDENTIFIER, ClassNames.API_ENTITY_TYPE);
        generateDep("org.allaymc.dependence", Path.of("codegen/src/main/java/org/allaymc/dependence/EntityId.java"), ClassNames.DEP_IDENTIFIER);
    }

    @SneakyThrows
    protected static void generateApi(String packageName, Path outPutPath, ClassName identifierClassName, ClassName entityTypeClassName) {
        TypeSpec.Builder codeBuilder = TypeSpec.enumBuilder("EntityId")
                .addJavadoc("Automatically generated by {@code org.allaymc.codegen.EntityIdEnumGen}")
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec
                        .builder(identifierClassName, "identifier", Modifier.PRIVATE, Modifier.FINAL)
                        .addAnnotation(ClassNames.GETTER)
                        .build())
                .addField(FieldSpec
                        .builder(int.class, "networkId", Modifier.PRIVATE, Modifier.FINAL)
                        .addAnnotation(ClassNames.GETTER)
                        .build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(ClassNames.STRING, "identifier")
                        .addParameter(int.class, "networkId")
                        .addStatement("this.$N = new $T($N)", "identifier", identifierClassName, "identifier")
                        .addStatement("this.$N = $N", "networkId", "networkId")
                        .build()
                )
                .addMethod(MethodSpec.methodBuilder("getEntityType")
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("return $T.ENTITIES.get(this.getIdentifier())", ClassNames.REGISTRIES)
                        .returns(ParameterizedTypeName.get(entityTypeClassName, WildcardTypeName.subtypeOf(ClassName.OBJECT)))
                        .build()
                );
        for (var entry : ENTITY_ID_MAP.entrySet()) {
            var valueName = StringUtils.fastTwoPartSplit(
                    entry.getKey(),
                    ":",
                    "")[1].toUpperCase();
            codeBuilder.addEnumConstant(valueName, TypeSpec.anonymousClassBuilder("$S, $L", entry.getKey(), entry.getValue()).build());
        }

        var javaFile = JavaFile.builder(packageName, codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(outPutPath, javaFile.toString());
    }

    @SneakyThrows
    protected static void generateDep(String packageName, Path outPutPath, ClassName identifierClassName) {
        TypeSpec.Builder codeBuilder = TypeSpec.enumBuilder("EntityId")
                .addJavadoc("Automatically generated by {@code org.allaymc.codegen.EntityIdEnumGen}")
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec
                        .builder(identifierClassName, "identifier", Modifier.PRIVATE, Modifier.FINAL)
                        .addAnnotation(ClassNames.GETTER)
                        .build())
                .addField(FieldSpec
                        .builder(int.class, "networkId", Modifier.PRIVATE, Modifier.FINAL)
                        .addAnnotation(ClassNames.GETTER)
                        .build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(ClassNames.STRING, "identifier")
                        .addParameter(int.class, "networkId")
                        .addStatement("this.$N = new $T($N)", "identifier", identifierClassName, "identifier")
                        .addStatement("this.$N = $N", "networkId", "networkId")
                        .build()
                );
        for (var entry : ENTITY_ID_MAP.entrySet()) {
            var valueName = StringUtils.fastTwoPartSplit(
                    entry.getKey(),
                    ":",
                    "")[1].toUpperCase();
            codeBuilder.addEnumConstant(valueName, TypeSpec.anonymousClassBuilder("$S, $L", entry.getKey(), entry.getValue()).build());
        }

        var javaFile = JavaFile.builder(packageName, codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(outPutPath, javaFile.toString());
    }
}
