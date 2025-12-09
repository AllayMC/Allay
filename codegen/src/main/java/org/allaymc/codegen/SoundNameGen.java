package org.allaymc.codegen;

import com.google.gson.JsonParser;
import com.palantir.javapoet.FieldSpec;
import com.palantir.javapoet.JavaFile;
import com.palantir.javapoet.TypeSpec;
import lombok.SneakyThrows;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public class SoundNameGen {

    @SneakyThrows
    public static void main(String[] args) {
        var names = new HashSet<String>();
        names.addAll(getMusicNames());
        names.addAll(getSoundNames());

        TypeSpec.Builder codeBuilder = TypeSpec.interfaceBuilder(TypeNames.SOUND_NAMES)
                .addAnnotation(TypeNames.MINECRAFT_VERSION_SENSITIVE)
                .addModifiers(Modifier.PUBLIC);

        for (var name : names) {
            codeBuilder.addField(
                    FieldSpec.builder(TypeNames.STRING, name.replace(".", "_").toUpperCase(Locale.ROOT), Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                            .initializer("$S", name)
                            .build()
            );
        }

        var javaFile = JavaFile.builder(TypeNames.SOUND_NAMES.packageName(), codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(Path.of("api/src/main/java/org/allaymc/api/world/sound/SoundNames.java"), javaFile.toString());
    }

    @SneakyThrows
    private static Set<String> getMusicNames() {
        try (var reader = Files.newBufferedReader(Path.of("data/resources/unpacked/music_definitions.json"))) {
            var musicNames = new HashSet<String>();
            JsonParser.parseReader(reader).getAsJsonObject().asMap().values().forEach(v -> {
                musicNames.add(v.getAsJsonObject().get("event_name").getAsString());
            });
            return musicNames;
        }
    }

    @SneakyThrows
    private static Set<String> getSoundNames() {
        try (var reader = Files.newBufferedReader(Path.of("data/resources/unpacked/sound_definitions.json"))) {
            return JsonParser.parseReader(reader)
                    .getAsJsonObject()
                    .getAsJsonObject("sound_definitions")
                    .asMap()
                    .keySet();
        }
    }
}
