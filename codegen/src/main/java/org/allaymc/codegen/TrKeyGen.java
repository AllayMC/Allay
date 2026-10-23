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
 * {@code TrKeyGen} generates translation-key constants from the processed
 * English language resource. Each constant retains the original namespaced key
 * as its value, while its Java name combines a namespace abbreviation with a
 * normalized uppercase path. English text is embedded in generated
 * documentation for callers selecting a translation key.
 *
 * @author daoge_cmd | IWareQ
 */
public class TrKeyGen {
    /**
     * {@code TR_EN_FILE_PATH} selects the processed English translations whose
     * keys and text supply the generated API constants.
     */
    static final Path TR_EN_FILE_PATH = Path.of(CodeGenConstants.DATA_PATH + "lang/en_US.json");
    /**
     * {@code OUTPUT_PATH} locates the generated API interface that is deleted
     * and recreated after all constant definitions are assembled.
     */
    static final Path OUTPUT_PATH = Path.of("api/src/main/java/org/allaymc/api/message/TrKeys.java");

    /**
     * Generates the translation-key interface using repository-relative paths.
     * {@code args} is ignored, and invalid identifiers or input failures abort
     * generation. Dots and hyphens become underscores in constant names;
     * distinct input keys are not disambiguated when normalization produces the
     * same name. The previous interface is replaced directly.
     */
    @SneakyThrows
    public static void main(String[] args) {
        var langJson = JsonParser
                .parseReader(Files.newBufferedReader(TR_EN_FILE_PATH))
                .getAsJsonObject();
        var keys = langJson.keySet();
        var codeBuilder = TypeSpec.interfaceBuilder(TypeNames.TR_KEYS)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(TypeNames.MINECRAFT_VERSION_SENSITIVE);
        for (var key : keys) {
            var identifier = new Identifier(key);
            var namespace = handleNamespace(identifier.namespace());
            // Normalize punctuation only in the Java field name; its initializer
            // retains the original translation key used by the language lookup tables.
            var path = identifier.path().replaceAll("\\.", "_").replaceAll("-", "_").toUpperCase(Locale.ROOT);
            var fieldName = namespace + "_" + path;
            codeBuilder.addField(
                    FieldSpec
                            .builder(TypeNames.STRING, fieldName, Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                            .initializer("\"" + key + "\"")
                            // JavaPoet treats dollars as format markers; replace them
                            // in documentation text while preserving the translation key's initializer.
                            .addJavadoc("{@literal " + langJson.get(key).getAsString().replaceAll("\\$", "#") + "}")
                            .build()
            );
        }
        var javaFile = JavaFile.builder(TypeNames.TR_KEYS.packageName(), codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + TypeNames.TR_KEYS.simpleName() + ".java ...");
        // Delete and recreate the output before writing; a failed replacement
        // cannot restore the old generated interface from this generation attempt.
        Files.deleteIfExists(OUTPUT_PATH);
        Files.createFile(OUTPUT_PATH);
        Utils.writeFileWithCRLF(OUTPUT_PATH, javaFile.toString());
    }

    /**
     * Returns the constant-name prefix corresponding to a resource namespace.
     * {@code "minecraft"} uses {@code "MC"} and {@code "allay"} uses
     * {@code "ALLAY"}; other namespaces are uppercased with the root locale
     * without further Java-name validation.
     */
    protected static String handleNamespace(String origin) {
        return switch (origin) {
            case "minecraft" -> "MC";
            case "allay" -> "ALLAY";
            default -> origin.toUpperCase(Locale.ROOT);
        };
    }
}
