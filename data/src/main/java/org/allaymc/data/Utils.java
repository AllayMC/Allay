package org.allaymc.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author IWareQ
 */
public class Utils {
    public static void writeFileWithCRLF(Path path, String content) throws IOException {
        String crlfContent = content.replace("\n", "\r\n");
        Files.writeString(path, crlfContent);
    }
}
