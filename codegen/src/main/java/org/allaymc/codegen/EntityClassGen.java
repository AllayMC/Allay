package org.allaymc.codegen;

import com.palantir.javapoet.*;
import lombok.SneakyThrows;
import org.allaymc.dependence.EntityId;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author daoge_cmd | Cool_Loong | IWareQ
 */
public class EntityClassGen extends BaseClassGen {

    public static final MethodSpec.Builder ENTITY_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER =
            MethodSpec.methodBuilder("init")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC);

    public static void main(String[] args) {
        generate();
    }

    @SneakyThrows
    public static void generate() {
        var interfaceDir = Path.of("api/src/main/java/org/allaymc/api/entity/interfaces");
        if (!Files.exists(interfaceDir)) {
            Files.createDirectories(interfaceDir);
        }
        var implDir = Path.of("server/src/main/java/org/allaymc/server/entity/impl");
        if (!Files.exists(implDir)) {
            Files.createDirectories(implDir);
        }
        var typesClass = TypeSpec
                .classBuilder(TypeNames.ENTITY_TYPES).addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        for (var id : EntityId.values()) {
            typesClass.addField(
                    FieldSpec.builder(ParameterizedTypeName.get(TypeNames.ENTITY_TYPE, generateClassFullName(id)), id.name())
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                            .build()
            );

            var interfaceSimpleName = generateClassSimpleName(id);
            var interfaceFullName = generateClassFullName(id);
            var interfacePath = interfaceDir.resolve(interfaceSimpleName + ".java");
            if (!Files.exists(interfacePath)) {
                System.out.println("Generating " + interfaceSimpleName + "...");
                generateInterface(TypeNames.ENTITY, interfaceFullName, interfacePath);
            }

            var implSimpleName = generateClassSimpleName(id) + "Impl";
            var implFullName = ClassName.get("org.allaymc.server.entity.impl", implSimpleName);
            var implPath = implDir.resolve(implSimpleName + ".java");
            if (!Files.exists(implPath)) {
                System.out.println("Generating " + implSimpleName + "...");
                generateImpl(TypeNames.ENTITY_IMPL, interfaceFullName, implFullName, TypeNames.ENTITY_INIT_INFO, implPath);
            }

            addDefaultEntityTypeInitializer(id, implFullName);
        }
        generateDefaultEntityTypeInitializer();
        var javaFile = JavaFile.builder(TypeNames.ENTITY_TYPES.packageName(), typesClass.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + TypeNames.ENTITY_TYPES.simpleName() + ".java ...");
        Utils.writeFileWithCRLF(Path.of("api/src/main/java/org/allaymc/api/entity/type/" + TypeNames.ENTITY_TYPES.simpleName() + ".java"), javaFile.toString());
    }

    private static void addDefaultEntityTypeInitializer(EntityId id, ClassName entityClassName) {
        var initializer = CodeBlock.builder();
        initializer
                .add("$T.$N = $T\n", TypeNames.ENTITY_TYPES, id.name(), TypeNames.ALLAY_ENTITY_TYPE)
                .add("        .builder($T.class)\n", entityClassName)
                .add("        .vanillaEntity($T.$N)\n", TypeNames.ENTITY_ID, id.name())
                .add("        .build()");
        ENTITY_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER
                .beginControlFlow("if ($T.$N == null)", TypeNames.ENTITY_TYPES, id.name())
                .addStatement(initializer.build())
                .endControlFlow();
    }

    @SneakyThrows
    private static void generateDefaultEntityTypeInitializer() {
        var filePath = Path.of("server/src/main/java/org/allaymc/server/entity/type/EntityTypeDefaultInitializer.java");
        Files.deleteIfExists(filePath);
        var folderPath = filePath.getParent();
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        TypeSpec.Builder builder =
                TypeSpec.classBuilder(TypeNames.ENTITY_TYPE_DEFAULT_INITIALIZER)
                        .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        builder.addMethod(ENTITY_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER.build());

        var javaFile = JavaFile.builder(TypeNames.ENTITY_TYPE_DEFAULT_INITIALIZER.packageName(), builder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + TypeNames.ENTITY_TYPE_DEFAULT_INITIALIZER.simpleName() + ".java ...");
        Utils.writeFileWithCRLF(filePath, javaFile.toString());
    }

    private static String generateClassSimpleName(EntityId id) {
        return "Entity" + Utils.convertToPascalCase(id.getIdentifier().path());
    }

    private static ClassName generateClassFullName(EntityId id) {
        return ClassName.get("org.allaymc.api.entity.interfaces", generateClassSimpleName(id));
    }

}
