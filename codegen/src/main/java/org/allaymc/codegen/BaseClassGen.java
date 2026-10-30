package org.allaymc.codegen;

import com.palantir.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

/**
 * {@code BaseClassGen} builds the initial interface and implementation
 * skeletons used by type generators. Subclasses decide when an existing
 * handwritten extension must be preserved; these output helpers themselves
 * replace their target files. Cleanup removes obsolete names while retaining
 * files explicitly reserved by the calling generator.
 *
 * @author daoge_cmd | IWareQ
 */
public abstract class BaseClassGen {
    /**
     * Writes an empty public interface extending the supplied parent type. The
     * target is replaced directly with JavaPoet output, so callers must check
     * whether an existing interface contains handwritten additions before
     * invoking this helper.
     *
     * @throws IOException if the output cannot be written
     */
    protected static void generateInterface(ClassName superClassName, ClassName className, Path path) throws IOException {
        TypeSpec.Builder codeBuilder = TypeSpec.interfaceBuilder(className)
                .addSuperinterface(superClassName)
                .addModifiers(Modifier.PUBLIC);
        var javaFile = JavaFile.builder(className.packageName(), codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + className + ".java ...");
        Utils.writeFileWithCRLF(path, javaFile.toString());
    }

    /**
     * Writes a public implementation whose constructor forwards initialization
     * information and component providers to its superclass. The output
     * contains no handwritten component delegation; callers must preserve
     * existing customized classes before selecting this direct replacement
     * operation.
     *
     * @throws IOException if the output cannot be written
     */
    protected static void generateImpl(ClassName superClassName, ClassName superInterfaceName, ClassName className, ClassName initInfoClass, Path path) throws IOException {
        TypeSpec.Builder codeBuilder = TypeSpec.classBuilder(className)
                .superclass(superClassName)
                .addSuperinterface(superInterfaceName)
                .addModifiers(Modifier.PUBLIC);
        codeBuilder.addMethod(
                MethodSpec
                        .constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(initInfoClass, "initInfo")
                        .addParameter(ParameterizedTypeName.get(TypeNames.LIST, ParameterizedTypeName.get(TypeNames.COMPONENT_PROVIDER, WildcardTypeName.subtypeOf(TypeNames.COMPONENT))), "componentProviders")
                        .addStatement("super(initInfo, componentProviders)")
                        .build()
        );
        var javaFile = JavaFile.builder(className.packageName(), codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + className + ".java ...");
        Utils.writeFileWithCRLF(path, javaFile.toString());
    }

    /**
     * Removes regular files whose basenames are absent from both supplied sets.
     * A missing directory requires no cleanup, and subdirectories are retained.
     * Individual deletion failures are printed before processing continues;
     * failure to open the directory is wrapped in a runtime exception. Cleanup
     * is nonrecursive and does not restrict candidates to Java files.
     */
    protected static void deleteOldFiles(Path dir, Set<String> generatedFiles, Set<String> ignoredFiles) {
        if (!Files.exists(dir)) {
            return;
        }

        try (var files = Files.list(dir)) {
            files.filter(path -> Files.isRegularFile(path) && !ignoredFiles.contains(path.getFileName().toString())).forEach(file -> {
                try {
                    if (!generatedFiles.contains(file.getFileName().toString())) {
                        System.out.println("Deleting unused file: " + file.getFileName());
                        Files.delete(file);
                    }
                } catch (IOException e) {
                    // Keep processing the remaining obsolete files after one deletion
                    // fails; the printed error leaves that individual cleanup failure visible.
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
