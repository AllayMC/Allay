package org.allaymc.codegen;

import org.allaymc.dependence.StringUtils;

import java.util.List;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public class Utils {
    public static String convertToPascalCase(String str) {
        List<String> parts = StringUtils.fastSplit(str, "_");
        StringBuilder output = new StringBuilder();

        for (String part : parts) {
            output.append(Character.toUpperCase(part.charAt(0)));
            output.append(part.substring(1));
        }

        return output.toString();
    }
}
