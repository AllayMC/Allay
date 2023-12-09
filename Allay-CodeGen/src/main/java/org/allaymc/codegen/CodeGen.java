package org.allaymc.codegen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

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
        VanillaBiomeIdEnumGen.main(args);
        VanillaBlockInterfaceGen.main(args);
        VanillaEntityInterfaceGen.main(args);
        VanillaItemInterfaceGen.main(args);
    }
}
