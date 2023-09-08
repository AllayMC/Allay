package cn.allay.codegen;

import cn.allay.dependence.VanillaEntityId;
import com.squareup.javapoet.*;
import lombok.SneakyThrows;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Depend on VanillaEntityIdEnumGen execution
 * <p>
 * Allay Project 2023/5/26
 *
 * @author daoge_cmd | Cool_Loong
 */
public class VanillaEntityInterfaceGen {

    public static void main(String[] args) {
        VanillaEntityIdEnumGen.generate();
        generate();
    }

    public static final ClassName ENTITY_CLASS_NAME = ClassName.get("cn.allay.api.entity", "Entity");
    public static final ClassName VANILLA_ENTITY_ID_CLASS_NAME = ClassName.get("cn.allay.api.data", "VanillaEntityId");
    public static final ClassName ENTITY_TYPE_CLASS_NAME = ClassName.get("cn.allay.api.entity.type", "EntityType");
    public static final ClassName ENTITY_TYPE_BUILDER_CLASS_NAME = ClassName.get("cn.allay.api.entity.type", "EntityTypeBuilder");
    public static Path FILE_OUTPUT_PATH_BASE = Path.of("Allay-API/src/main/java/cn/allay/api/entity/interfaces");
    private static final String ENTITY_TYPE_PACKAGE_NAME = "cn.allay.api.data";
    private static final Path ENTITY_TYPE_OUTPUT_PATH = Path.of("Allay-API/src/main/java/cn/allay/api/data/VanillaEntityTypes.java");

    private static final TypeSpec.Builder TYPES_CLASS = TypeSpec.interfaceBuilder("VanillaEntityTypes")
            .addModifiers(Modifier.PUBLIC)
            .addJavadoc("Allay Project <p>\n@author daoge_cmd");

    @SneakyThrows
    public static void generate() {
        if (!Files.exists(FILE_OUTPUT_PATH_BASE)) Files.createDirectories(FILE_OUTPUT_PATH_BASE);
        for (var entity : VanillaEntityId.values()) {
            var typeName = entity.getIdentifier().path();
            var className = "Entity" + Utils.convertToPascalCase(typeName);
            var path = FILE_OUTPUT_PATH_BASE.resolve(className + ".java");
            if (!Files.exists(path)) {
                System.out.println("Generating " + className + ".java ...");
                generateEntityClass(entity, className, path);
            }
            generateEntityType(entity, className);
        }
        var typesJavaFile = JavaFile
                .builder(ENTITY_TYPE_PACKAGE_NAME, TYPES_CLASS.build())
                .build();
        Files.writeString(ENTITY_TYPE_OUTPUT_PATH, typesJavaFile.toString());
    }

    private static void generateEntityType(VanillaEntityId vanillaEntityId, String classNameStr) {
        var className = ClassName.get("cn.allay.api.entity.interfaces", classNameStr);
        var initializer = CodeBlock.builder();
        initializer
                .add("$T\n        .builder($T.class)\n", ENTITY_TYPE_BUILDER_CLASS_NAME, className)
                .add("        .vanillaEntity($T.$N)\n", VANILLA_ENTITY_ID_CLASS_NAME, vanillaEntityId.name())
                .add("        .addBasicComponents()\n")
                .add("        .build()");
        TYPES_CLASS.addField(
                FieldSpec
                        .builder(ParameterizedTypeName.get(ENTITY_TYPE_CLASS_NAME, className), vanillaEntityId.name() + "_TYPE")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                        .initializer(initializer.build())
                        .build());
    }

    private static void generateEntityClass(VanillaEntityId vanillaEntityId, String className, Path path) throws IOException {
        TypeSpec.Builder codeBuilder = TypeSpec.interfaceBuilder(className)
                .addSuperinterface(ENTITY_CLASS_NAME)
                .addJavadoc(
                        "@author daoge_cmd <br>\n" +
                                "Allay Project <br>\n")
                .addModifiers(Modifier.PUBLIC);
        var javaFile = JavaFile.builder("cn.allay.api.entity.interfaces", codeBuilder.build()).build();
        Files.writeString(path, javaFile.toString());
    }
}
