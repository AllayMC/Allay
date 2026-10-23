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
 * {@code SoundNameGen} generates sound-name constants from unpacked sound and
 * music definitions. Event names from both sources are deduplicated before
 * emission. Each constant retains its original event name as a value, while
 * dots become underscores in the uppercase Java field name.
 *
 * @author daoge_cmd
 */
public class SoundNameGen {

    /**
     * Reads both definition resources and replaces the generated sound
     * interface. {@code args} is ignored, and paths are repository-relative.
     * Field order follows a hash set; different names that normalize to the
     * same Java field are not disambiguated here.
     */
    @SneakyThrows
    public static void main(String[] args) {
        var names = new HashSet<String>();
        names.addAll(getMusicNames());
        names.addAll(getSoundNames());

        TypeSpec.Builder codeBuilder = TypeSpec.interfaceBuilder(TypeNames.SOUND_NAMES)
                .addAnnotation(TypeNames.MINECRAFT_VERSION_SENSITIVE)
                .addModifiers(Modifier.PUBLIC);

        for (var name : names) {
            // Preserve the original event string for runtime sound requests;
            // punctuation replacement applies only to the Java constant used to reference it.
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

    /**
     * Reads distinct event names from the unpacked music definitions. Each
     * definition must provide a string event name; malformed entries and input
     * failures propagate. The returned set is newly allocated and mutable.
     */
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

    /**
     * Reads the event-name keys of the unpacked sound-definitions object. The
     * returned set is backed by the parsed JSON object rather than copied;
     * input and schema failures propagate to the generator entry point.
     */
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
