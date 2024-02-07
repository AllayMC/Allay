package org.allaymc.server.pack;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

/**
 * Allay Project 2024/2/7
 *
 * @author daoge_cmd
 */
public class PackUtils {

    private static final PathMatcher ZIP_PACK_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**.{zip,mcpack}");

    public static boolean isZipPack(Path path) {
        return ZIP_PACK_MATCHER.matches(path);
    }
}
