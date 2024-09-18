package org.allaymc.codegen;

import com.squareup.javapoet.*;
import lombok.SneakyThrows;
import org.allaymc.dependence.EntityId;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author daoge_cmd | Cool_Loong | IWareQ
 */
public class EntityInterfaceGen extends BaseInterfaceGen {

    public static final TypeSpec.Builder ENTITY_TYPE_DEFAULT_INITIALIZER_CLASS_BUILDER =
            TypeSpec.classBuilder(ClassNames.ENTITY_TYPE_DEFAULT_INITIALIZER)
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL);

    public static void main(String[] args) {
        EntityIdEnumGen.generate();
        generate();
    }

    @SneakyThrows
    public static void generate() {
        var interfaceDir = Path.of("Allay-API/src/main/java/org/allaymc/api/entity/interfaces");
        if (!Files.exists(interfaceDir)) Files.createDirectories(interfaceDir);
        var typesClass = TypeSpec.classBuilder(ClassNames.ENTITY_TYPES).addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        for (var id : EntityId.values()) {
            typesClass.addField(
                    FieldSpec.builder(ParameterizedTypeName.get(ClassNames.ENTITY_TYPE, generateClassFullName(id)), id.name())
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                            .build()
            );
            var entityClassSimpleName = generateClassSimpleName(id);
            var entityClassFullName = generateClassFullName(id);
            var path = interfaceDir.resolve(entityClassSimpleName + ".java");
            if (!Files.exists(path)) {
                System.out.println("Generating " + entityClassSimpleName + "...");
                generateClass(ClassNames.ENTITY, entityClassFullName, path);
            }
            addDefaultEntityTypeInitializer(id, entityClassFullName);
        }
        generateDefaultEntityTypeInitializer();
        var javaFile = JavaFile.builder(ClassNames.ENTITY_TYPES.packageName(), typesClass.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + ClassNames.ENTITY_TYPES.simpleName() + ".java ...");
        Files.writeString(Path.of("Allay-API/src/main/java/org/allaymc/api/entity/type/" + ClassNames.ENTITY_TYPES.simpleName() + ".java"), javaFile.toString());
    }

    private static void addDefaultEntityTypeInitializer(EntityId id, ClassName entityClassName) {
        var initializer = CodeBlock.builder();
        initializer
                .add("$T.$N = $T\n", ClassNames.ENTITY_TYPES, id.name(), ClassNames.ENTITY_TYPE_BUILDER)
                .add("        .builder($T.class)\n", entityClassName)
                .add("        .vanillaEntity($T.$N)\n", ClassNames.ENTITY_ID, id.name())
                .add("        .build();");
        ENTITY_TYPE_DEFAULT_INITIALIZER_CLASS_BUILDER
                .addMethod(
                        MethodSpec.methodBuilder(generateInitializerMethodName(id))
                                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                .addStatement("if ($T.$N != null) return", ClassNames.ENTITY_TYPES, id.name())
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
        var javaFile = JavaFile.builder(ClassNames.ENTITY_TYPE_DEFAULT_INITIALIZER.packageName(), ENTITY_TYPE_DEFAULT_INITIALIZER_CLASS_BUILDER.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + ClassNames.ENTITY_TYPE_DEFAULT_INITIALIZER.simpleName() + ".java ...");
        Files.writeString(filePath, javaFile.toString());
    }

    private static String generateClassSimpleName(EntityId id) {
        return "Entity" + Utils.convertToPascalCase(id.getIdentifier().path());
    }

    private static String generateInitializerMethodName(EntityId id) {
        return "init" + Utils.convertToPascalCase(id.getIdentifier().path());
    }

    private static ClassName generateClassFullName(EntityId id) {
        return ClassName.get("org.allaymc.api.entity.interfaces", generateClassSimpleName(id));
    }
}
