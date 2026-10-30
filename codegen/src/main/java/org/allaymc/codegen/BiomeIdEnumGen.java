package org.allaymc.codegen;

import com.palantir.javapoet.*;
import lombok.SneakyThrows;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * {@code BiomeIdEnumGen} generates the server biome enumeration from unpacked
 * biome IDs and categories. Input entries are collected in ascending numeric-ID
 * order before constants are emitted. The generated enum retains each
 * identifier, ID, and category, and provides direct numeric lookup alongside a
 * map keyed by resource identifiers.
 *
 * @author daoge_cmd | IWareQ | Cool_Loong
 */
public class BiomeIdEnumGen {
    /**
     * {@code BIOME_DATA} holds biome inputs in numeric-ID order after class
     * initialization. Later {@code generate} calls reuse this snapshot rather
     * than rereading the resource.
     */
    private static final Map<String, BiomeData> BIOME_DATA = new LinkedHashMap<>();
    /**
     * {@code PACKAGE_NAME} selects the runtime biome package used when JavaPoet
     * builds the generated enumeration and its imports.
     */
    private static final String PACKAGE_NAME = "org.allaymc.server.world.biome";
    /**
     * {@code OUTPUT_PATH} locates the server enumeration replaced by each
     * generation call after its constants and lookup methods are assembled.
     */
    private static final Path OUTPUT_PATH = Path.of("server/src/main/java/org/allaymc/server/world/biome/BiomeId.java");

    // Read and order the biome snapshot when this generator is first loaded.
    // I/O failure aborts class initialization through CodeGenException; subsequent
    // generation calls do not refresh data from disk.
    static {
        try {
            Map<String, BiomeData> unsorted = CodeGenConstants.GSON.fromJson(Files.newBufferedReader(Path.of(CodeGenConstants.DATA_PATH + "unpacked/biome_id_and_type.json")), new HashMap<String, BiomeData>() {
            }.getClass().getGenericSuperclass());
            unsorted.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(o -> o.id)))
                    .forEachOrdered(entry -> BIOME_DATA.put(entry.getKey(), entry.getValue()));
        } catch (IOException e) {
            throw new CodeGenException(e);
        }
    }

    /**
     * Runs biome enumeration generation using the snapshot loaded at class
     * initialization. {@code args} is ignored, and input and output paths are
     * resolved relative to the repository root used as the working directory.
     */
    public static void main(String[] args) {
        generate();
    }

    /**
     * Replaces the server biome enum with constants and ID lookup methods.
     * Numeric lookup reserves 256 entries without range validation, so resource
     * IDs outside that range fail in the generated initializer. Duplicate IDs
     * or identifiers overwrite their corresponding generated lookup entries.
     */
    @SneakyThrows
    public static void generate() {
        // Direct indexing keeps numeric lookup separate from identifier hashing;
        // generated callers receive an index failure for IDs outside the fixed table.
        TypeSpec.Builder codeBuilder = TypeSpec.enumBuilder("BiomeId")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(TypeNames.GETTER)
                .addStaticBlock(
                        CodeBlock.builder()
                                .addStatement("MAP1 = $L", "new BiomeId[256]")
                                .addStatement("MAP2 = $L", "new HashMap<>()")
                                .beginControlFlow("for (var biome : values())")
                                .addStatement("MAP1[biome.id] = biome")
                                .addStatement("MAP2.put(biome.identifier, biome)")
                                .endControlFlow()
                                .build()
                )
                .addField(FieldSpec
                        .builder(TypeNames.BIOME_ID_ARRAY, "MAP1", Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                        .build())
                .addField(FieldSpec
                        .builder(ParameterizedTypeName.get(TypeNames.HASH_MAP, TypeNames.API_IDENTIFIER, TypeNames.BIOME_ID), "MAP2", Modifier.PRIVATE, Modifier.FINAL, Modifier.STATIC)
                        .build())
                .addField(FieldSpec
                        .builder(TypeNames.API_IDENTIFIER, "identifier", Modifier.PRIVATE, Modifier.FINAL)
                        .build())
                .addField(FieldSpec
                        .builder(int.class, "id", Modifier.PRIVATE, Modifier.FINAL)
                        .build())
                .addField(FieldSpec
                        .builder(TypeNames.STRING, "type", Modifier.PRIVATE, Modifier.FINAL)
                        .build())
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(TypeNames.STRING, "identifier")
                        .addParameter(int.class, "id")
                        .addParameter(TypeNames.STRING, "type")
                        .addStatement("this.identifier = new $T(identifier)", TypeNames.API_IDENTIFIER)
                        .addStatement("this.id = id")
                        .addStatement("this.type = type")
                        .build()
                );
        for (var entry : BIOME_DATA.entrySet()) {
            var identifier = "minecraft:" + entry.getKey();
            var id = entry.getValue().id;
            var type = entry.getValue().type;
            codeBuilder.addEnumConstant(entry.getKey().toUpperCase(Locale.ROOT), TypeSpec.anonymousClassBuilder("$S, $L, $S", identifier, id, type).build());
        }

        codeBuilder.addMethod(MethodSpec
                .methodBuilder("fromId")
                .addParameter(int.class, "id")
                .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                .addCode("return MAP1[id];")
                .returns(TypeNames.BIOME_ID)
                .build());
        codeBuilder.addMethod(MethodSpec
                .methodBuilder("fromIdentifier")
                .addParameter(TypeNames.API_IDENTIFIER, "identifier")
                .addModifiers(Modifier.STATIC, Modifier.PUBLIC)
                .addCode("return MAP2.get(identifier);")
                .returns(TypeNames.BIOME_ID)
                .build());
        var builtCode = codeBuilder.build();
        var javaFile = JavaFile.builder(PACKAGE_NAME, builtCode)
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(OUTPUT_PATH, javaFile.toString());
    }

    /**
     * {@code BiomeData} represents the numeric identifier and category of one
     * unpacked biome.
     */
    private static class BiomeData {
        /** Numeric runtime biome identifier. */
        int id;
        /** Biome category from input. */
        String type;
    }
}
