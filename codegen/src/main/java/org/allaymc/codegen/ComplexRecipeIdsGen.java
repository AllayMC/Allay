package org.allaymc.codegen;

import com.google.gson.JsonParser;
import com.palantir.javapoet.ClassName;
import com.palantir.javapoet.FieldSpec;
import com.palantir.javapoet.JavaFile;
import com.palantir.javapoet.TypeSpec;
import lombok.SneakyThrows;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * @author IWareQ
 */
public class ComplexRecipeIdsGen {
    static final Path RECIPES_PATH = Path.of(CodeGenConstants.DATA_PATH + "recipes.json");
    static final Path OUTPUT_PATH = Path.of("server/src/main/java/org/allaymc/server/item/recipe/ComplexRecipeIds.java");

    @SneakyThrows
    public static void main(String[] args) {
        var recipesJson = JsonParser
                .parseReader(Files.newBufferedReader(RECIPES_PATH))
                .getAsJsonObject();

        var uuidClass = ClassName.get(UUID.class);

        var codeBuilder = TypeSpec.interfaceBuilder("ComplexRecipeIds")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(TypeNames.MINECRAFT_VERSION_SENSITIVE);

        List<FieldSpec> fields = new ArrayList<>();
        for (var jsonElement : recipesJson.getAsJsonArray("multi")) {
            var obj = jsonElement.getAsJsonObject();

            var id = obj.get("id").getAsString();
            var uuid = obj.get("uuid").getAsString();

            var constName = id.replace("_recipeId", "").toUpperCase(Locale.ROOT);

            var field = FieldSpec.builder(uuidClass, constName)
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                    .initializer("$T.fromString($S)", uuidClass, uuid.toUpperCase(Locale.ROOT))
                    .build();

            fields.add(field);
        }

        fields.sort(Comparator.comparing(FieldSpec::name));
        fields.forEach(codeBuilder::addField);

        var javaFile = JavaFile.builder("org.allaymc.server.item.recipe", codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Files.deleteIfExists(OUTPUT_PATH);
        Files.createFile(OUTPUT_PATH);
        Utils.writeFileWithCRLF(OUTPUT_PATH, javaFile.toString());
    }
}
