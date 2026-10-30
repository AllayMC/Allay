package org.allaymc.codegen;

import com.google.gson.JsonParser;
import org.allaymc.dependence.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * {@code Utils} converts resource names and writes text for the handwritten
 * source generators. Naming helpers preserve characters outside their specific
 * case conversion, leaving callers responsible for choosing valid Java
 * identifiers. File helpers read JSON object keys or replace output files using
 * the requested path.
 *
 * @author daoge_cmd | IWareQ
 */
public class Utils {
    /**
     * Joins underscore-separated segments after capitalizing their first
     * characters. The remaining characters retain their case; empty segments,
     * including an empty input or trailing underscore, cannot supply a first
     * character and cause an index exception.
     */
    public static String convertToPascalCase(String str) {
        var parts = StringUtils.fastSplit(str, "_");
        var output = new StringBuilder();

        for (var part : parts) {
            output.append(Character.toUpperCase(part.charAt(0)));
            output.append(part.substring(1));
        }

        return output.toString();
    }

    /**
     * Inserts an underscore before each noninitial uppercase character and
     * lowercases that character. Consecutive capitals are processed separately,
     * so {@code HTTP} becomes {@code h_t_t_p}; existing punctuation and
     * lowercase characters remain unchanged.
     */
    public static String camelCaseToSnakeCase(String str) {
        var output = new StringBuilder();

        var chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            var ch = chars[i];
            if (Character.isUpperCase(ch)) {
                if (i > 0) output.append('_');
                output.append(Character.toLowerCase(ch));
            } else {
                output.append(ch);
            }
        }

        return output.toString();
    }

    /**
     * Reads a JSON object's top-level member names into a new mutable set. The
     * result has no iteration-order guarantee, and values are not retained.
     * Input failures are wrapped in {@link CodeGenException}; malformed JSON or
     * a nonobject root fails through the JSON parser.
     */
    public static Set<String> parseKeys(Path path) {
        Set<String> keys = new HashSet<>();
        try (var reader = new InputStreamReader(Files.newInputStream(path))) {
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> keys.add(entry.getKey()));
            return keys;
        } catch (IOException e) {
            throw new CodeGenException(e);
        }
    }

    /**
     * Writes UTF-8 text after prefixing each line feed with a carriage return.
     * Callers supply LF-only input: existing carriage returns are retained, so
     * CRLF input gains additional carriage returns. Existing files are
     * truncated, and the parent directory must already exist.
     *
     * @throws IOException if the output cannot be created or written
     */
    public static void writeFileWithCRLF(Path path, String content) throws IOException {
        // Generated sources use CRLF; this conversion assumes LF-only generator output.
        String crlfContent = content.replace("\n", "\r\n");
        Files.writeString(path, crlfContent);
    }
}
