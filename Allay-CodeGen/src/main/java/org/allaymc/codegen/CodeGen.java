package org.allaymc.codegen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public class CodeGen {
    //The root path of used resources
    public static final String DATA_PATH = "Allay-Data/resources/";
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        BiomeIdEnumGen.main(args);
        BlockInterfaceGen.main(args);
        EntityInterfaceGen.main(args);
        ItemInterfaceGen.main(args);
    }
}
