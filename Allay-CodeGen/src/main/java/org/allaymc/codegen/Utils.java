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
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public class Utils {
    public static final String INDENT = "    ";

    public static String convertToPascalCase(String str) {
        var parts = StringUtils.fastSplit(str, "_");
        var output = new StringBuilder();

        for (var part : parts) {
            output.append(Character.toUpperCase(part.charAt(0)));
            output.append(part.substring(1));
        }

        return output.toString();
    }

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

    public static Set<String> parseKeys(Path path) {
        Set<String> keys = new HashSet<>();
        try (var reader = new InputStreamReader(Files.newInputStream(path))) {
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> keys.add(entry.getKey()));
            return keys;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
