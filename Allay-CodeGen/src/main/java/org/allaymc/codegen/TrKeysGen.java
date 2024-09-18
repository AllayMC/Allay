package org.allaymc.codegen;

import com.google.gson.JsonParser;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import lombok.SneakyThrows;
import org.allaymc.dependence.Identifier;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.allaymc.codegen.ClassNames.STRING;

/**
 * Allay Project 2023/12/22
 *
 * @author daoge_cmd
 */
public class TrKeysGen {
    static final Path TR_EN_FILE_PATH = Path.of(CodeGen.DATA_PATH + "lang/en_US.json");
    static final Path OUTPUT_PATH = Path.of("Allay-API/src/main/java/org/allaymc/api/i18n/TrKeys.java");

    @SneakyThrows
    public static void main(String[] args) {
        var keys = JsonParser
                .parseReader(Files.newBufferedReader(TR_EN_FILE_PATH))
                .getAsJsonObject()
                .keySet();
        var codeBuilder = TypeSpec.interfaceBuilder(ClassNames.TR_KEYS)
                .addModifiers(Modifier.PUBLIC)
                .addJavadoc("@author daoge_cmd <br>\n" +
                            "Allay Project <br>\n");
        for (var key : keys) {
            var identifier = new Identifier(key);
            var namespace = handleNamespace(identifier.namespace());
            var path = identifier.path().replaceAll("\\.", "_").replaceAll("-", "_").toUpperCase();
            var fieldName = namespace + "_" + path;
            codeBuilder.addField(
                    FieldSpec
                            .builder(STRING, fieldName, Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                            .initializer("\"" + key + "\"")
                            .build()
            );
        }
        var javaFile = JavaFile.builder(ClassNames.TR_KEYS.packageName(), codeBuilder.build())
                .indent(Utils.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + ClassNames.TR_KEYS.simpleName() + ".java ...");
        Files.deleteIfExists(OUTPUT_PATH);
        Files.createFile(OUTPUT_PATH);
        Files.writeString(OUTPUT_PATH, javaFile.toString());
    }

    protected static String handleNamespace(String origin) {
        return switch (origin) {
            case "minecraft" -> "M";
            case "allay" -> "A";
            default -> origin.toUpperCase();
        };
    }
}
