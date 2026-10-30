package org.allaymc.codegen;

import com.google.gson.JsonParser;
import com.palantir.javapoet.FieldSpec;
import com.palantir.javapoet.JavaFile;
import com.palantir.javapoet.TypeSpec;
import lombok.SneakyThrows;
import org.allaymc.dependence.StringUtils;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Locale;

/**
 * {@code CreativeItemGroupNameGen} generates API constants for the names of
 * creative inventory groups. Blank group names are omitted and duplicate names
 * are collected once before emission. Each retained value keeps its complete
 * translation key, while the constant name is derived from the key's third
 * dot-separated segment.
 *
 * @author daoge_cmd
 */
public class CreativeItemGroupNameGen {
    /**
     * Reads creative groups and replaces the generated API name interface.
     * {@code args} is ignored, and paths are relative to the repository root.
     * Retained names must have at least three dot-separated segments; output
     * order follows a hash set, and normalized constant-name collisions are not
     * resolved by this entry point.
     */
    @SneakyThrows
    public static void main(String[] args) {
        var names = new HashSet<String>();
        try (var reader = Files.newBufferedReader(Path.of("data/resources/creative_groups.json"))) {
            JsonParser.parseReader(reader).getAsJsonArray().asList().forEach(v -> {
                var name = v.getAsJsonObject().get("name").getAsString();
                if (!name.isBlank()) {
                    names.add(name);
                }
            });
        }

        TypeSpec.Builder codeBuilder = TypeSpec
                .interfaceBuilder(TypeNames.CREATIVE_ITEM_GROUPS)
                .addAnnotation(TypeNames.MINECRAFT_VERSION_SENSITIVE)
                .addModifiers(Modifier.PUBLIC);

        for (var name : names) {
            // Derive the Java name from the group's key segment, but keep the
            // complete original key as the constant value used by runtime consumers.
            codeBuilder.addField(
                    FieldSpec.builder(TypeNames.STRING, Utils.camelCaseToSnakeCase(StringUtils.fastSplit(name, ".").get(2)).toUpperCase(Locale.ROOT), Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                            .initializer("$S", name)
                            .build()
            );
        }

        var javaFile = JavaFile.builder(TypeNames.CREATIVE_ITEM_GROUPS.packageName(), codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(Path.of("api/src/main/java/org/allaymc/api/item/creative/CreativeItemGroups.java"), javaFile.toString());
    }
}
