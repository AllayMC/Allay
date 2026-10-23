package org.allaymc.codegen;

import com.palantir.javapoet.*;
import lombok.SneakyThrows;
import org.allaymc.dependence.EntityId;

import javax.lang.model.element.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * {@code EntityClassGen} generates entity type catalogs and missing entity
 * interface and implementation skeletons. The identifier enumeration supplies
 * both catalog field names and class names. Existing skeletons are retained so
 * handwritten component methods survive later generation, while the catalog and
 * default initializer are replaced to reflect the currently enumerated
 * entities.
 *
 * <p>
 * The emitted initializer fills only {@code null} catalog fields, allowing an
 * earlier registration to provide a customized entity type. Generation does not
 * remove obsolete skeletons, and output files are written sequentially without
 * rollback. The shared method builder retains appended registrations across
 * calls, so another invocation in the same JVM adds to the previous initializer
 * instead of constructing an empty one.
 *
 * @author daoge_cmd | Cool_Loong | IWareQ
 */
public class EntityClassGen extends BaseClassGen {

    /**
     * {@code ENTITY_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER} accumulates
     * guarded entity registrations. {@code generate} appends statements without
     * clearing earlier calls in this JVM.
     */
    public static final MethodSpec.Builder ENTITY_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER =
            MethodSpec.methodBuilder("init")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC);

    /**
     * Runs entity source generation with paths relative to the repository root.
     * {@code args} is ignored; output replacement and skeleton preservation
     * have the same effects as invoking {@link #generate()} directly.
     */
    public static void main(String[] args) {
        generate();
    }

    /**
     * Writes the entity catalog, missing skeletons, and default registrations
     * from the loaded identifiers. Callers run this generator from the
     * repository root so the output paths select the intended API and server
     * source trees. Existing skeletons retain their handwritten additions, and
     * obsolete skeletons are left in place.
     *
     * <p>
     * Catalog replacement follows initializer replacement, so a failed pass can
     * leave outputs reflecting different passes. Repeated calls also append
     * statements to the shared initializer builder without clearing earlier
     * work.
     */
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
        // Publish the initializer before replacing the catalog; failures after
        // this point leave earlier skeletons and initializer output in place.
        generateDefaultEntityTypeInitializer();
        var javaFile = JavaFile.builder(TypeNames.ENTITY_TYPES.packageName(), typesClass.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + TypeNames.ENTITY_TYPES.simpleName() + ".java ...");
        Utils.writeFileWithCRLF(Path.of("api/src/main/java/org/allaymc/api/entity/type/" + TypeNames.ENTITY_TYPES.simpleName() + ".java"), javaFile.toString());
    }

    /**
     * Appends a default registration for the identifier and selected entity
     * class. The emitted {@code null} guard preserves a type installed before
     * initialization. {@code ENTITY_TYPE_DEFAULT_INITIALIZER_METHOD_BUILDER}
     * retains this statement until the JVM exits.
     */
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

    /**
     * Replaces the default initializer with the accumulated registration
     * method. The old file is deleted before serialization and writing, so a
     * later failure can leave it absent instead of preserving its previous
     * contents.
     */
    @SneakyThrows
    private static void generateDefaultEntityTypeInitializer() {
        var filePath = Path.of("server/src/main/java/org/allaymc/server/entity/type/EntityTypeDefaultInitializer.java");
        // Deletion precedes output construction; a failure below does not restore
        // the initializer, even though earlier skeleton generation may have succeeded.
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

    /**
     * Returns an entity class name derived from the identifier path.
     */
    private static String generateClassSimpleName(EntityId id) {
        return "Entity" + Utils.convertToPascalCase(id.getIdentifier().path());
    }

    /**
     * Returns the entity class name in the API interface package.
     */
    private static ClassName generateClassFullName(EntityId id) {
        return ClassName.get("org.allaymc.api.entity.interfaces", generateClassSimpleName(id));
    }

}
