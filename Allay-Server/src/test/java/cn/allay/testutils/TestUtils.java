package cn.allay.testutils;

import lombok.SneakyThrows;

import java.nio.file.Path;

/**
 * Allay Project 2023/3/26
 *
 * @author daoge_cmd
 */
public final class TestUtils {

    @SneakyThrows
    public static Path getResourceFilePath(String fileName) {
        //It's ugly...
        return Path.of(TestUtils.class.getClassLoader().getResource("").toURI())
                .getParent()
                .getParent()
                .getParent()
                .getParent()
                .getParent()
                .resolve("Data/" + fileName);
    }
}
