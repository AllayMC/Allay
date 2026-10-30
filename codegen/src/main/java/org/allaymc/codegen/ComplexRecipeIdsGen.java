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
 * {@code ComplexRecipeIdsGen} generates {@code UUID} constants for the
 * resource's multi-recipe entries. Recipe identifiers become uppercase Java
 * field names after removal of every {@code _recipeId} marker, and fields are
 * sorted by their emitted names. The output keeps {@code UUID} parsing in the
 * generated interface's initializers.
 *
 * @author IWareQ
 */
public class ComplexRecipeIdsGen {
    /**
     * {@code RECIPES_PATH} selects the recipe resource whose {@code "multi"}
     * entries supply complex-recipe identifiers and {@code UUID} text for
     * generation.
     */
    static final Path RECIPES_PATH = Path.of(CodeGenConstants.DATA_PATH + "recipes.json");
    /**
     * {@code OUTPUT_PATH} locates the generated server interface replaced after
     * the recipe constant definitions have been collected and sorted.
     */
    static final Path OUTPUT_PATH = Path.of("server/src/main/java/org/allaymc/server/item/recipe/ComplexRecipeIds.java");

    /**
     * Reads multi-recipe entries and replaces their generated {@code UUID}
     * interface. {@code args} is ignored, and resource paths are
     * repository-relative. {@code UUID} strings are emitted as parsing
     * expressions rather than validated here; invalid strings therefore remain
     * a failure for the generated initializer.
     */
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

            // Resource identifiers carry a recipeId marker that is omitted from
            // Java constant names; UUID text remains the value associated with that name.
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
        // Recreate the destination before writing the generated constants; there
        // is no backup restoration if replacement fails after the existing file is deleted.
        Files.deleteIfExists(OUTPUT_PATH);
        Files.createFile(OUTPUT_PATH);
        Utils.writeFileWithCRLF(OUTPUT_PATH, javaFile.toString());
    }
}
