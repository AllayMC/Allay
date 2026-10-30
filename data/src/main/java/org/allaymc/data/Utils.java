package org.allaymc.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * {@code Utils} writes processed resource text using the repository's CRLF line
 * endings. Data processors supply serialized JSON and choose their output
 * paths; this helper replaces the target file directly and leaves directory
 * creation and recovery from write failures to its caller.
 *
 * @author IWareQ
 */
public class Utils {
    /**
     * Writes UTF-8 text after prefixing each line feed with a carriage return.
     * Callers supply LF-only input because existing carriage returns remain
     * unchanged. The target is created or truncated, and its parent directory
     * must already exist; a failed write can leave partial output.
     *
     * @throws IOException if the target cannot be created or written
     */
    public static void writeFileWithCRLF(Path path, String content) throws IOException {
        // Resource serializers emit LF; prefixing those separators produces CRLF output.
        String crlfContent = content.replace("\n", "\r\n");
        Files.writeString(path, crlfContent);
    }
}
