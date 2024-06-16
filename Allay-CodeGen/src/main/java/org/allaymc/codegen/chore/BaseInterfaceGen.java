package org.allaymc.codegen.chore;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Allay Project 2024/2/24
 *
 * @author daoge_cmd
 */
public abstract class BaseInterfaceGen {
    protected static void generateClass(ClassName superClassName, ClassName itemClassName, Path path) throws IOException {
        TypeSpec.Builder codeBuilder = TypeSpec.interfaceBuilder(itemClassName)
                .addSuperinterface(superClassName)
                .addJavadoc(
                        "@author daoge_cmd <br>\n" +
                                "Allay Project <br>\n")
                .addModifiers(Modifier.PUBLIC);
        var javaFile = JavaFile.builder(itemClassName.packageName(), codeBuilder.build()).build();
        System.out.println("Generating " + itemClassName + ".java ...");
        Files.writeString(path, javaFile.toString());
    }
}
