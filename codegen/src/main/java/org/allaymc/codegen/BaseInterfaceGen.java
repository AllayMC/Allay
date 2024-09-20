package org.allaymc.codegen;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author daoge_cmd | IWareQ
 */
public abstract class BaseInterfaceGen {
    protected static void generateClass(ClassName superClassName, ClassName itemClassName, Path path) throws IOException {
        TypeSpec.Builder codeBuilder = TypeSpec.interfaceBuilder(itemClassName)
                .addSuperinterface(superClassName)
                .addModifiers(Modifier.PUBLIC);
        var javaFile = JavaFile.builder(itemClassName.packageName(), codeBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        System.out.println("Generating " + itemClassName + ".java ...");
        Files.writeString(path, javaFile.toString());
    }
}
