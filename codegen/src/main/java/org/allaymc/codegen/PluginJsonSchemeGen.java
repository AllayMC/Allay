package org.allaymc.codegen;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.*;
import com.github.victools.jsonschema.generator.*;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Cdm2883
 */
public class PluginJsonSchemeGen {
    static final SchemaVersion SCHEMA_VERSION = SchemaVersion.DRAFT_2020_12;

    static final Path[] CLASS_RESOLVE_PATHS = new Path[]{
            Path.of("api/src/main/java/org/allaymc/api/plugin"),
            Path.of("server/src/main/java/org/allaymc/server/plugin"),
    };
    static final Path INPUT_PATH = Path.of("server/src/main/java/org/allaymc/server/plugin/SimplePluginDescriptor.java");
    static final String ENTER_CLASS = "SimplePluginDescriptor";
    static final Path OUTPUT_PATH = Path.of("docs/plugin_descriptor_json_scheme.json");

    public static void main(String[] _args) throws IOException {
        var descriptor = parseSpecifiedClassFrom(INPUT_PATH, ENTER_CLASS);
        var clazz = generateClass(descriptor);

        var config = new SchemaGeneratorConfigBuilder(SCHEMA_VERSION, OptionPreset.PLAIN_JSON).build();
        var generator = new SchemaGenerator(config);
        var schema = generator.generateSchema(clazz).toString();

        var gson = new GsonBuilder().setPrettyPrinting().create();
        var json = JsonParser.parseString(schema).getAsJsonObject();
        var string = gson.toJson(json);

        Files.deleteIfExists(OUTPUT_PATH);
        Files.createFile(OUTPUT_PATH);
        Files.writeString(OUTPUT_PATH, string);
        System.out.println(string);
    }

    @SneakyThrows
    static ClassOrInterfaceDeclaration parseSpecifiedClassFrom(Path java, String name) {
        var cu = new JavaParser().parse(java).getResult().orElseThrow();
        return cu
                .findAll(ClassOrInterfaceDeclaration.class).stream()
                .filter(clazz -> name.equals(clazz.getName().asString()))
                .findFirst().orElse(null)
                instanceof ClassOrInterfaceDeclaration result ? result : cu
                .findAll(RecordDeclaration.class).stream()
                .filter(record -> name.equals(record.getName().asString()))
                .map(PluginJsonSchemeGen::convertRecordToClass)
                .findFirst().orElse(null);
    }
    static ClassOrInterfaceDeclaration convertRecordToClass(RecordDeclaration record) {
        var clazz = new ClassOrInterfaceDeclaration()
                .setName(record.getName());
        record.getParameters().forEach(parameter -> {
            FieldDeclaration field = new FieldDeclaration();
            VariableDeclarator variable = new VariableDeclarator();
            variable.setName(parameter.getName());
            variable.setType(parameter.getType());
            field.addVariable(variable);
            clazz.addMember(field);
        });
        return clazz;
    }

    static Class<?> generateClass(ClassOrInterfaceDeclaration parsed) {
        var builder = new AtomicReference<>(new ByteBuddy()
                .subclass(Object.class)
                .name(parsed.getNameAsString()));

        parsed.getFields().forEach(field -> {
            var variable = field.getVariable(0);
            var name = variable.getName().asString();
            var type = (TypeDefinition) findType(variable.getType().asString());
            var defined = builder.get().defineField(name, type, Visibility.PRIVATE);
            builder.set(defined);
        });

        try (var type = builder.get().make()) {
            return type.load(PluginJsonSchemeGen.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION).getLoaded();
        }
    }


    static Object findType(String name) {
        name = name.trim();

        switch (name.toLowerCase()) {
            case "string": return TypeDescription.ForLoadedType.of(String.class);
            case "integer": return TypeDescription.ForLoadedType.of(Integer.class);
            case "boolean": return TypeDescription.ForLoadedType.of(Boolean.class);
            case "list": return TypeDescription.ForLoadedType.of(List.class);
            // add more basic types here...
        }

        // Try to resolve class:
        for (var resolver : CLASS_RESOLVE_PATHS) {
            if (name.contains("<")) break;
            var files = resolver.toFile().listFiles();
            assert files != null;
            var resolved = resolveClass(files, name);
            if (resolved == null) continue;
            var clazz = generateClass(resolved);
            return TypeDescription.ForLoadedType.of(clazz);
        }

        // Generic Building:
        var types = name.split("[<,>]");
        if (types.length == 1) throw new IllegalArgumentException("Cannot resolve type: " + name);
        var raw = (TypeDescription) findType(types[0]);
        @SuppressWarnings("SuspiciousToArrayCall")
        var parameters = Arrays.stream(Arrays.copyOfRange(types, 1, types.length))
                .map(PluginJsonSchemeGen::findType)
                .toArray(TypeDescription[]::new);
        return TypeDescription.Generic.Builder
                .parameterizedType(raw, parameters)
                .build();
    }

    static ClassOrInterfaceDeclaration resolveClass(File[] files, String name) {
        return Arrays.stream(files)
                .filter(File::isFile)
                .map(file -> parseSpecifiedClassFrom(file.toPath(), name))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }
}
