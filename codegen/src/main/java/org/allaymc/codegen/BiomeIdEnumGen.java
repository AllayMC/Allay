package org.allaymc.codegen;

import com.palantir.javapoet.*;
import lombok.SneakyThrows;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * @author daoge_cmd | IWareQ | Cool_Loong
 */
public class BiomeIdEnumGen {
    private static final Map<String, BiomeData> BIOME_DATA = new LinkedHashMap<>();
    private static final String PACKAGE_NAME = "org.allaymc.server.world.biome";
    private static final Path OUTPUT_PATH = Path.of("server/src/main/java/org/allaymc/server/world/biome/BiomeId.java");

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

    public static void main(String[] args) {
        generate();
    }

    @SneakyThrows
    public static void generate() {
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

    private static class BiomeData {
        int id;
        String type;
    }
}
