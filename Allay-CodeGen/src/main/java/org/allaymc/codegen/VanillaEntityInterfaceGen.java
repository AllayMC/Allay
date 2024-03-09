package org.allaymc.codegen;

import org.allaymc.dependence.VanillaEntityId;
import com.squareup.javapoet.*;
import lombok.SneakyThrows;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Depend on VanillaEntityIdEnumGen execution
 * <p>
 * Allay Project 2023/5/26
 *
 * @author daoge_cmd | Cool_Loong
 */
public class VanillaEntityInterfaceGen extends BaseInterfaceGen {

    public static void main(String[] args) {
        VanillaEntityIdEnumGen.generate();
        generate();
    }

    public static final ClassName ENTITY_CLASS_NAME = ClassName.get("org.allaymc.api.entity", "Entity");
    public static final ClassName VANILLA_ENTITY_ID_CLASS_NAME = ClassName.get("org.allaymc.api.data", "VanillaEntityId");
    public static final ClassName ENTITY_TYPE_CLASS_NAME = ClassName.get("org.allaymc.api.entity.type", "EntityType");
    public static final ClassName ENTITY_TYPES_CLASS_NAME = ClassName.get("org.allaymc.api.entity.type", "EntityTypes");
    public static final ClassName ENTITY_TYPE_BUILDER_CLASS_NAME = ClassName.get("org.allaymc.api.entity.type", "EntityTypeBuilder");

    @SneakyThrows
    public static void generate() {
        var interfaceDir = Path.of("Allay-API/src/main/java/org/allaymc/api/entity/interfaces");
        if (!Files.exists(interfaceDir)) Files.createDirectories(interfaceDir);
        var initializerDir = Path.of("Allay-Server/src/main/java/org/allaymc/server/entity/initializer");
        if (!Files.exists(initializerDir)) Files.createDirectories(initializerDir);
        var typesClass = TypeSpec.classBuilder(ENTITY_TYPES_CLASS_NAME).addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        for (var id : VanillaEntityId.values()) {
            typesClass.addField(
                    FieldSpec.builder(ParameterizedTypeName.get(ENTITY_TYPE_CLASS_NAME, generateClassFullName(id)), id.name() + "_TYPE")
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                            .build()
            );
            var entityClassSimpleName = generateClassSimpleName(id);
            var entityClassFullName = generateClassFullName(id);
            var path = interfaceDir.resolve(entityClassSimpleName + ".java");
            if (!Files.exists(path)) {
                System.out.println("Generating " + entityClassSimpleName + "...");
                generateClass(ENTITY_CLASS_NAME, entityClassFullName, path);
            }
            addDefaultEntityTypeInitializer(id, entityClassFullName);
        }
        generateDefaultEntityTypeInitializer();
        var javaFile = JavaFile.builder(ENTITY_TYPES_CLASS_NAME.packageName(), typesClass.build()).build();
        System.out.println("Generating " + ENTITY_TYPES_CLASS_NAME.simpleName() + ".java ...");
        Files.writeString(Path.of("Allay-API/src/main/java/org/allaymc/api/entity/type/" + ENTITY_TYPES_CLASS_NAME.simpleName() + ".java"), javaFile.toString());
    }

    public static final ClassName ENTITY_TYPE_DEFAULT_INITIALIZER_CLASS_NAME = ClassName.get("org.allaymc.server.entity.type", "EntityTypeDefaultInitializer");
    public static final TypeSpec.Builder ENTITY_TYPE_DEFAULT_INITIALIZER_CLASS_BUILDER =
            TypeSpec.classBuilder(ENTITY_TYPE_DEFAULT_INITIALIZER_CLASS_NAME)
                    .addJavadoc(
                            "@author daoge_cmd <br>\n" +
                                    "Allay Project <br>\n")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL);

    private static void addDefaultEntityTypeInitializer(VanillaEntityId id, ClassName entityClassName) {
        var initializer = CodeBlock.builder();
        initializer
                .add("$T.$N = $T\n", ENTITY_TYPES_CLASS_NAME, id.name() + "_TYPE", ENTITY_TYPE_BUILDER_CLASS_NAME)
                .add("        .builder($T.class)\n", entityClassName)
                .add("        .vanillaEntity($T.$N)\n", VANILLA_ENTITY_ID_CLASS_NAME, id.name())
                .add("        .build();");
        ENTITY_TYPE_DEFAULT_INITIALIZER_CLASS_BUILDER
                .addMethod(
                        MethodSpec.methodBuilder(generateInitializerMethodName(id))
                                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                .addStatement("if ($T.$N != null) return", ENTITY_TYPES_CLASS_NAME, id.name() + "_TYPE")
                                .addCode(initializer.build())
                                .build()
                );
    }

    @SneakyThrows
    private static void generateDefaultEntityTypeInitializer() {
        var filePath = Path.of("Allay-Server/src/main/java/org/allaymc/server/entity/type/EntityTypeDefaultInitializer.java");
        Files.deleteIfExists(filePath);
        var folderPath = filePath.getParent();
        if (!Files.exists(folderPath))
            Files.createDirectories(folderPath);
        var javaFile = JavaFile.builder(ENTITY_TYPE_DEFAULT_INITIALIZER_CLASS_NAME.packageName(), ENTITY_TYPE_DEFAULT_INITIALIZER_CLASS_BUILDER.build()).build();
        System.out.println("Generating " + ENTITY_TYPE_DEFAULT_INITIALIZER_CLASS_NAME.simpleName() + ".java ...");
        Files.writeString(filePath, javaFile.toString());
    }

    @SneakyThrows
    private static void generateEntityTypeInitializer(VanillaEntityId id, ClassName entityClassName) {
        var className = ClassName.get("org.allaymc.server.entity.initializer", entityClassName.simpleName() + "Initializer");
        var initializer = CodeBlock.builder();
        initializer
                .add("$T.$N = $T\n", ENTITY_TYPES_CLASS_NAME, id.name() + "_TYPE", ENTITY_TYPE_BUILDER_CLASS_NAME)
                .add("        .builder($T.class)\n", entityClassName)
                .add("        .vanillaEntity($T.$N)\n", VANILLA_ENTITY_ID_CLASS_NAME, id.name())
                .add("        .build();");
        var clazz = TypeSpec.interfaceBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addJavadoc(
                        "@author daoge_cmd <br>\n" +
                        "Allay Project <br>\n")
                .addMethod(
                        MethodSpec.methodBuilder("init")
                                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                .addCode(initializer.build())
                                .build()
                )
                .build();
        var filePath = Path.of("Allay-Server/src/main/java/org/allaymc/server/entity/initializer/" + className.simpleName() + ".java");
        if (!Files.exists(filePath)) {
            var javaFile = JavaFile.builder("org.allaymc.server.entity.initializer", clazz).build();
            System.out.println("Generating " + className.simpleName() + ".java ...");
            Files.writeString(filePath, javaFile.toString());
        }
    }

    private static String generateClassSimpleName(VanillaEntityId id) {
        return "Entity" + Utils.convertToPascalCase(id.getIdentifier().path());
    }

    private static String generateInitializerMethodName(VanillaEntityId id) {
        return "init" + Utils.convertToPascalCase(id.getIdentifier().path());
    }

    private static ClassName generateClassFullName(VanillaEntityId id) {
        return ClassName.get("org.allaymc.api.entity.interfaces", generateClassSimpleName(id));
    }
}
