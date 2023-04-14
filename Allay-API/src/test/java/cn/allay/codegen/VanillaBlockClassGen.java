package cn.allay.codegen;

import cn.allay.block.data.VanillaBlockId;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import lombok.SneakyThrows;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public class VanillaBlockClassGen {

     public static Path FILE_OUTPUT_PATH_BASE = Path.of("Allay-API/src/main/java/cn/allay/block/vanilla");

    @SneakyThrows
    public static void main(String[] args) {
        for (var block : VanillaBlockId.values()) {
            var className = Utils.convertToCamelCase(block.getNamespaceId().getPath());
            var path = FILE_OUTPUT_PATH_BASE.resolve("Block" + className + ".java");
            if (Files.exists(path)) {
                System.out.println("Class " + className + " already exists, skipped");
            } else {
                System.out.println("Generating " + className + ".java ...");
                generateBlockClass(block, className, path);
            }
        }
    }

    private static void generateBlockClass(VanillaBlockId block, String className, Path path) throws IOException {
        TypeSpec.Builder codeBuilder = TypeSpec.interfaceBuilder(className)
                .addJavadoc(
                        "Author: daoge_cmd <br>\n" +
                                "Allay Project <br>\n")
                .addModifiers(Modifier.PUBLIC);
        var javaFile = JavaFile.builder("cn.allay.block.vanilla", codeBuilder.build()).build();
        Files.writeString(path, javaFile.toString());
    }
}
