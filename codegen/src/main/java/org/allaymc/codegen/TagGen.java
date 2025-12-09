package org.allaymc.codegen;

import com.palantir.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

import static org.allaymc.codegen.TypeNames.*;

/**
 * @author IWareQ
 */
public class TagGen {
    public static void main(String[] args) throws IOException {
        generate("api/src/main/java/org/allaymc/api/item/data/ItemTags.java", ITEM_TAG, "unpacked/item_tags.json", "item_tags_custom.json");
        generate("api/src/main/java/org/allaymc/api/block/data/BlockTags.java", BLOCK_TAG, "unpacked/block_tags.json", "block_tags_custom.json");
    }

    public static void generate(String writeTo, ClassName tagClass, String... tagFiles) throws IOException {
        Path path = Path.of(writeTo);
        var interfaceName = path.getFileName().toString().replace(".java", "");
        var interfaceBuilder = TypeSpec.interfaceBuilder(interfaceName)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(MINECRAFT_VERSION_SENSITIVE)
                .addField(
                        FieldSpec.builder(ParameterizedTypeName.get(MAP, STRING, tagClass), "NAME_TO_TAG")
                                .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                                .initializer("new $T<>()", HashMap.class)
                                .build()
                );

        var keys = new HashSet<String>();
        for (var tagFile : tagFiles) {
            for (var key : Utils.parseKeys(Path.of(CodeGenConstants.DATA_PATH + tagFile))) {
                if (keys.contains(key)) {
                    throw new IllegalStateException("Duplicate key: " + key);
                }
                keys.add(key);
            }
        }

        for (var key : keys) {
            var fieldName = key.split(":")[1].toUpperCase(Locale.ROOT);
            interfaceBuilder.addField(
                    FieldSpec.builder(tagClass, fieldName)
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                            .initializer("create($S)", key)
                            .build()
            );
        }

        interfaceBuilder.addMethod(
                MethodSpec.methodBuilder("create")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .returns(tagClass)
                        .addParameter(String.class, "name")
                        .addStatement("var tag = new $T(name)", tagClass)
                        .addStatement("NAME_TO_TAG.put(name, tag)")
                        .addStatement("return tag")
                        .build()
        ).addMethod(
                MethodSpec.methodBuilder("getTagByName")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .returns(tagClass)
                        .addParameter(String.class, "name")
                        .addStatement("return NAME_TO_TAG.get(name)")
                        .build()
        );

        var javaFile = JavaFile.builder(tagClass.packageName(), interfaceBuilder.build())
                .indent(CodeGenConstants.INDENT)
                .skipJavaLangImports(true)
                .build();
        Utils.writeFileWithCRLF(path, javaFile.toString());
    }
}
