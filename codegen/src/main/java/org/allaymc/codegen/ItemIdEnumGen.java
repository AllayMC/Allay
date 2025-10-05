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
 * @author daoge_cmd | Cool_Loong | IWareQ
 */
public class ItemIdEnumGen {
    public static final Map<String, Integer> ITEM_NAME_TO_ID = new TreeMap<>();
    public static final Path ITEM_DATA_FILE_PATH = Path.of(CodeGenConstants.DATA_PATH + "items.json");
    private static final String PACKAGE_NAME = "org.allaymc.server.item.data";

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

    public static void main(String[] args) {
        generate();
    }

    @SneakyThrows
    public static void generate() {
        generateToDependenceModule();
        generateToServerModule();
    }

    @SneakyThrows
    public static void generateToDependenceModule() {
        TypeSpec.Builder codeBuilder = commonBuilder(ClassNames.DEP_IDENTIFIER);
        addEnums(codeBuilder);
        var javaFile = JavaFile.builder("org.allaymc.dependence", codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(Path.of("codegen/src/main/java/org/allaymc/dependence/ItemId.java"), javaFile.toString());
    }

    @SneakyThrows
    public static void generateToServerModule() {
        TypeSpec.Builder codeBuilder = commonBuilder(ClassNames.API_IDENTIFIER)
                .addMethod(MethodSpec.methodBuilder("fromIdentifier")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .addParameter(ClassNames.API_IDENTIFIER, "identifier")
                        .beginControlFlow("try")
                        .addStatement("return valueOf(identifier.path().replace(\".\", \"_\").toUpperCase($T.ENGLISH))", Locale.class)
                        .nextControlFlow("catch ($T ignore)", IllegalArgumentException.class)
                        .addStatement("return null")
                        .endControlFlow()
                        .returns(ClassNames.ITEM_ID)
                        .build()
                )
                .addMethod(MethodSpec.methodBuilder("getItemType")
                        .addModifiers(Modifier.PUBLIC)
                        .addStatement("return $T.ITEMS.get(this.getIdentifier())", ClassNames.REGISTRIES)
                        .returns(ParameterizedTypeName.get(ClassNames.ITEM_TYPE, WildcardTypeName.subtypeOf(ClassName.OBJECT)))
                        .build()
                );
        addEnums(codeBuilder);
        var javaFile = JavaFile.builder(PACKAGE_NAME, codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(Path.of("server/src/main/java/org/allaymc/server/item/data/ItemId.java"), javaFile.toString());
    }

    private static void addEnums(TypeSpec.Builder codeBuilder) {
        for (var entry : ITEM_NAME_TO_ID.entrySet()) {
            var split = StringUtils.fastTwoPartSplit(
                    StringUtils.fastTwoPartSplit(entry.getKey(), ":", "")[1],
                    ".", "");
            var valueName = split[0].isBlank() ? split[1].toUpperCase(Locale.ROOT) : split[0].toUpperCase(Locale.ROOT) + "_" + split[1].toUpperCase(Locale.ROOT);
            codeBuilder.addEnumConstant(valueName, TypeSpec.anonymousClassBuilder("$S, $L", entry.getKey(), entry.getValue()).build());
        }
    }

    private static TypeSpec.Builder commonBuilder(ClassName identifierClass) {
        return TypeSpec.enumBuilder("ItemId")
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec
                        .builder(identifierClass, "identifier", Modifier.PRIVATE, Modifier.FINAL)
                        .addAnnotation(ClassNames.GETTER)
                        .build())
                .addField(FieldSpec
                        .builder(int.class, "runtimeId", Modifier.PRIVATE, Modifier.FINAL)
                        .addAnnotation(ClassNames.GETTER)
                        .build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(ClassNames.STRING, "identifier")
                        .addParameter(int.class, "runtimeId")
                        .addStatement("this.identifier = new $T(identifier)", identifierClass)
                        .addStatement("this.runtimeId = runtimeId")
                        .build()
                );
    }
}
