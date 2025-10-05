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
 * @author daoge_cmd
 */
public class CreativeItemGroupNameGen {
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
                .interfaceBuilder(ClassNames.CREATIVE_ITEM_GROUPS)
                .addAnnotation(ClassNames.MINECRAFT_VERSION_SENSITIVE)
                .addModifiers(Modifier.PUBLIC);

        for (var name : names) {
            codeBuilder.addField(
                    FieldSpec.builder(ClassNames.STRING, Utils.camelCaseToSnakeCase(StringUtils.fastSplit(name, ".").get(2)).toUpperCase(Locale.ROOT), Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                            .initializer("$S", name)
                            .build()
            );
        }

        var javaFile = JavaFile.builder(ClassNames.CREATIVE_ITEM_GROUPS.packageName(), codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(Path.of("api/src/main/java/org/allaymc/api/item/creative/CreativeItemGroups.java"), javaFile.toString());
    }
}
