package cn.allay.codegen;

import cn.allay.utils.StringUtils;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public class Utils {
    public static String convertToCamelCase(String str) {
        List<String> parts = StringUtils.fastSplit(str, "_");
        StringBuilder output = new StringBuilder();

        for (String part : parts) {
            output.append(Character.toUpperCase(part.charAt(0)));
            output.append(part.substring(1));
        }

        return output.toString();
    }
}
