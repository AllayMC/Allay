package org.allaymc.codegen;

import com.palantir.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

/**
 * @author daoge_cmd | IWareQ
 */
public abstract class BaseClassGen {
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

    protected static void deleteOldFiles(Path dir, Set<String> generatedFiles) {
        if (!Files.exists(dir)) {
            return;
        }

        try (var files = Files.list(dir)) {
            files.filter(path -> Files.isRegularFile(path) && !BlockClassGen.IGNORED_FILES.contains(path.getFileName().toString())).forEach(file -> {
                try {
                    if (!generatedFiles.contains(file.getFileName().toString())) {
                        System.out.println("Deleting unused file: " + file.getFileName());
                        Files.delete(file);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
