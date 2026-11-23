package org.allaymc.codegen;

import com.palantir.javapoet.ClassName;
import com.palantir.javapoet.CodeBlock;
import com.palantir.javapoet.FieldSpec;
import com.palantir.javapoet.JavaFile;
import com.palantir.javapoet.ParameterizedTypeName;
import com.palantir.javapoet.TypeSpec;
import lombok.SneakyThrows;
import org.allaymc.dependence.EntityId;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code EntityClassGen} generates {@code EntityTypes} and
 * {@code EntityTypeDefaultInitializer} from the entity identifier enumeration.
 * Identifiers whose specialized interface exists in
 * {@code api/src/main/java/org/allaymc/api/entity/interfaces} bind to that
 * interface and its compile-time generated {@code *Impl} class; all remaining
 * identifiers fall back to {@code Entity} and the parameterless
 * {@code AllayEntityType.builder()} factory without creating empty skeleton files.
 *
 * @author daoge_cmd | Cool_Loong | IWareQ
 */
public class EntityClassGen extends BaseClassGen {

    /**
     * Runs entity catalog and default initializer generation from the repository root.
     */
    public static void main(String[] args) {
        generate();
    }

    /**
     * Regenerates {@code EntityTypes} and chunked {@code EntityTypeDefaultInitializer}
     * from the loaded entity identifiers and existing specialized entity
     * interfaces in {@code api}.
     */
    @SneakyThrows
    public static void generate() {
        var interfaceDir = Path.of("api/src/main/java/org/allaymc/api/entity/interfaces");
        List<CodeBlock> defaultInitBlocks = new ArrayList<>();

        var typesClass = TypeSpec
                .classBuilder(TypeNames.ENTITY_TYPES)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL);

        for (var id : EntityId.values()) {
            var candidateSimpleName = generateClassSimpleName(id);
            boolean hasDedicatedInterface = Files.exists(interfaceDir.resolve(candidateSimpleName + ".java"));
            var catalogInterfaceName = hasDedicatedInterface
                    ? ClassName.get("org.allaymc.api.entity.interfaces", candidateSimpleName)
                    : TypeNames.ENTITY;
            var implClassName = hasDedicatedInterface
                    ? ClassName.get("org.allaymc.server.entity.impl", candidateSimpleName + "Impl")
                    : null;

            typesClass.addField(
                    FieldSpec.builder(ParameterizedTypeName.get(TypeNames.ENTITY_TYPE, catalogInterfaceName), id.name())
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                            .build()
            );

            defaultInitBlocks.add(buildDefaultEntityTypeInitializerBlock(id, implClassName));
        }

        generateDefaultEntityTypeInitializer(defaultInitBlocks);

        var javaFile = JavaFile.builder(TypeNames.ENTITY_TYPES.packageName(), typesClass.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + TypeNames.ENTITY_TYPES.simpleName() + ".java ...");
        Utils.writeFileWithCRLF(Path.of("api/src/main/java/org/allaymc/api/entity/type/" + TypeNames.ENTITY_TYPES.simpleName() + ".java"), javaFile.toString());
    }

    /**
     * Builds one guarded default registration statement for {@code id}, using
     * {@code entityClassName} when a dedicated entity interface exists or the
     * parameterless {@code builder()} otherwise.
     */
    private static CodeBlock buildDefaultEntityTypeInitializerBlock(EntityId id, ClassName entityClassName) {
        var initializer = CodeBlock.builder();
        initializer.add("$T.$N = $T\n", TypeNames.ENTITY_TYPES, id.name(), TypeNames.ALLAY_ENTITY_TYPE);
        if (entityClassName != null) {
            initializer.add("        .builder($T.class)\n", entityClassName);
        } else {
            initializer.add("        .builder()\n");
        }
        initializer
                .add("        .vanillaEntity($T.$N)\n", TypeNames.ENTITY_ID, id.name())
                .add("        .build()");

        return CodeBlock.builder()
                .beginControlFlow("if ($T.$N == null)", TypeNames.ENTITY_TYPES, id.name())
                .addStatement(initializer.build())
                .endControlFlow()
                .build();
    }

    /**
     * Writes the chunked {@code EntityTypeDefaultInitializer} class.
     */
    @SneakyThrows
    private static void generateDefaultEntityTypeInitializer(List<CodeBlock> blocks) {
        var filePath = Path.of("server/src/main/java/org/allaymc/server/entity/type/EntityTypeDefaultInitializer.java");
        var folderPath = filePath.getParent();
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        var builder = TypeSpec.classBuilder(TypeNames.ENTITY_TYPE_DEFAULT_INITIALIZER)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        addChunkedInitMethods(builder, blocks, DEFAULT_INIT_CHUNK_SIZE);

        var javaFile = JavaFile.builder(TypeNames.ENTITY_TYPE_DEFAULT_INITIALIZER.packageName(), builder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + TypeNames.ENTITY_TYPE_DEFAULT_INITIALIZER.simpleName() + ".java ...");
        Utils.writeFileWithCRLF(filePath, javaFile.toString());
    }

    /**
     * Returns the candidate entity interface name derived from the identifier path.
     */
    private static String generateClassSimpleName(EntityId id) {
        return "Entity" + Utils.convertToPascalCase(id.getIdentifier().path());
    }
}
