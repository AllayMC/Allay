package org.allaymc.codegen;

import com.github.victools.jsonschema.generator.*;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.allaymc.server.plugin.SimplePluginDescriptor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Cdm2883
 */
public class PluginJsonSchemeGen {
    static final SchemaVersion SCHEMA_VERSION = SchemaVersion.DRAFT_2020_12;

    static final Class<?> INPUT_CLASS = SimplePluginDescriptor.class;
    static final Path OUTPUT_PATH = Path.of("docs/plugin_descriptor_json_scheme.json");

    public static void main(String[] args) throws IOException {
        var config = new SchemaGeneratorConfigBuilder(SCHEMA_VERSION, OptionPreset.PLAIN_JSON).build();
        var generator = new SchemaGenerator(config);
        var schema = generator.generateSchema(INPUT_CLASS).toString();

        var gson = new GsonBuilder().setPrettyPrinting().create();
        var json = JsonParser.parseString(schema).getAsJsonObject();

        Files.deleteIfExists(OUTPUT_PATH);
        Files.createFile(OUTPUT_PATH);
        Files.writeString(OUTPUT_PATH, gson.toJson(json));
    }
}
