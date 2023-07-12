package cn.allay.codegen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public class CodeGen {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        VanillaBiomeIdEnumGen.main(args);
        VanillaBlockBehaviorInterfaceGen.main(args);
        VanillaEntityClassGen.main(args);
        VanillaItemClassGen.main(args);
    }
}
