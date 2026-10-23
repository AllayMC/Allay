package org.allaymc.server.pack;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

/**
 * {@code PackUtils} recognizes archive pack paths by their file-name pattern.
 * The loader-selection and encryption paths use this check for {@code .zip}
 * and {@code .mcpack} candidates. Matching a path does not inspect archive
 * contents, confirm file existence, or validate its manifest.
 *
 * @author daoge_cmd
 */
public class PackUtils {

    /**
     * {@code ZIP_PACK_MATCHER} applies the default file system's glob rules to
     * candidate paths before archive loader selection or encryption.
     */
    private static final PathMatcher ZIP_PACK_MATCHER = FileSystems.getDefault().getPathMatcher("glob:**.{zip,mcpack}");

    /**
     * Returns whether the path matches the archive pack suffix pattern.
     * The default file system controls matching behavior; a positive result
     * does not establish that the path exists or contains a readable pack.
     */
    public static boolean isZipPack(Path path) {
        return ZIP_PACK_MATCHER.matches(path);
    }
}
