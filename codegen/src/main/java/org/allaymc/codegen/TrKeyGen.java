package org.allaymc.codegen;

import com.google.gson.JsonParser;
import com.palantir.javapoet.FieldSpec;
import com.palantir.javapoet.JavaFile;
import com.palantir.javapoet.TypeSpec;
import lombok.SneakyThrows;
import org.allaymc.dependence.Identifier;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

/**
 * @author daoge_cmd | IWareQ
 */
public class TrKeyGen {
    static final Path TR_EN_FILE_PATH = Path.of(CodeGenConstants.DATA_PATH + "lang/en_US.json");
    static final Path OUTPUT_PATH = Path.of("api/src/main/java/org/allaymc/api/message/TrKeys.java");

    @SneakyThrows
    public static void main(String[] args) {
        var langJson = JsonParser
                .parseReader(Files.newBufferedReader(TR_EN_FILE_PATH))
                .getAsJsonObject();
        var keys = langJson.keySet();
        var codeBuilder = TypeSpec.interfaceBuilder(ClassNames.TR_KEYS)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(ClassNames.MINECRAFT_VERSION_SENSITIVE);
        for (var key : keys) {
            var identifier = new Identifier(key);
            var namespace = handleNamespace(identifier.namespace());
            var path = identifier.path().replaceAll("\\.", "_").replaceAll("-", "_").toUpperCase(Locale.ROOT);
            var fieldName = namespace + "_" + path;
            codeBuilder.addField(
                    FieldSpec
                            .builder(ClassNames.STRING, fieldName, Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                            .initializer("\"" + key + "\"")
                            .addJavadoc(langJson.get(key).getAsString().replaceAll("\\$", "#"))
                            .build()
            );
        }
        var javaFile = JavaFile.builder(ClassNames.TR_KEYS.packageName(), codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + ClassNames.TR_KEYS.simpleName() + ".java ...");
        Files.deleteIfExists(OUTPUT_PATH);
        Files.createFile(OUTPUT_PATH);
        Utils.writeFileWithCRLF(OUTPUT_PATH, javaFile.toString());
    }

    protected static String handleNamespace(String origin) {
        return switch (origin) {
            case "minecraft" -> "MC";
            case "allay" -> "ALLAY";
            default -> origin.toUpperCase(Locale.ROOT);
        };
    }
}
