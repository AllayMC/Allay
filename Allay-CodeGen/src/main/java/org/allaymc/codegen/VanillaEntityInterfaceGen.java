package org.allaymc.codegen;

import org.allaymc.dependence.VanillaEntityId;
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

    public static final ClassName ENTITY_CLASS_NAME = ClassName.get("org.allaymc.api.entity", "Entity");
    public static final ClassName VANILLA_ENTITY_ID_CLASS_NAME = ClassName.get("org.allaymc.api.data", "VanillaEntityId");
    public static final ClassName ENTITY_TYPE_CLASS_NAME = ClassName.get("org.allaymc.api.entity.type", "EntityType");
    public static final ClassName ENTITY_TYPE_BUILDER_CLASS_NAME = ClassName.get("org.allaymc.api.entity.type", "EntityTypeBuilder");
    public static Path FILE_OUTPUT_PATH_BASE = Path.of("Allay-API/src/main/java/org/allaymc/api/entity/interfaces");

    @SneakyThrows
    public static void generate() {
        if (!Files.exists(FILE_OUTPUT_PATH_BASE)) Files.createDirectories(FILE_OUTPUT_PATH_BASE);
        for (var entity : VanillaEntityId.values()) {
            var entityClassSimpleName = "Entity" + Utils.convertToPascalCase(entity.getIdentifier().path());
            var folderName = Utils.convertToPascalCase(entity.getIdentifier().path()).toLowerCase();
            var entityClassName = ClassName.get("org.allaymc.api.entity.interfaces." + folderName, entityClassSimpleName);
            var path = FILE_OUTPUT_PATH_BASE.resolve(folderName).resolve(entityClassSimpleName + ".java");
            if (!Files.exists(path)) {
                System.out.println("Generating " + entityClassName + "...");
                generateEntityClass(entity, entityClassName, path);
            } else {
                System.out.println("Class " + entityClassName + " already exists during entity class generating!");
            }
        }
    }

    private static void generateEntityClass(VanillaEntityId vanillaEntityId, ClassName entityClassName, Path path) throws IOException {
        TypeSpec.Builder codeBuilder = TypeSpec.interfaceBuilder(entityClassName)
                .addSuperinterface(ENTITY_CLASS_NAME)
                .addJavadoc(
                        "@author daoge_cmd <br>\n" +
                                "Allay Project <br>\n")
                .addField(generateEntityTypeField(vanillaEntityId, entityClassName))
                .addModifiers(Modifier.PUBLIC);
        var javaFile = JavaFile.builder(entityClassName.packageName(), codeBuilder.build()).build();
        System.out.println("Generating " + entityClassName + ".java ...");
        Files.writeString(path, javaFile.toString());
    }

    private static FieldSpec generateEntityTypeField(VanillaEntityId vanillaEntityId, ClassName entityClassName) {
        var initializer = CodeBlock.builder();
        initializer
                .add("$T\n        .builder($T.class)\n", ENTITY_TYPE_BUILDER_CLASS_NAME, entityClassName)
                .add("        .vanillaEntity($T.$N)\n", VANILLA_ENTITY_ID_CLASS_NAME, vanillaEntityId.name())
                .add("        .build()");
        return FieldSpec
                .builder(ParameterizedTypeName.get(ENTITY_TYPE_CLASS_NAME, entityClassName), vanillaEntityId.name() + "_TYPE")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer(initializer.build())
                .build();
    }
}
