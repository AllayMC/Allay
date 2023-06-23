package cn.allay.codegen;


import cn.allay.dependence.StringUtils;

import java.util.List;

/**
 * @author daoge_cmd <br>
 * @date 2023/4/8 <br>
 * Allay Project <br>
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
