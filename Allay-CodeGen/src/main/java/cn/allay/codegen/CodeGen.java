package cn.allay.codegen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.zip.GZIPInputStream;

import static cn.allay.codegen.Utils.convertToPascalCase;

/**
 * Allay Project 2023/4/8
 *
 * @author daoge_cmd
 */
public class CodeGen {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        VanillaBiomeIdEnumGen.main(args);
        VanillaBlockClassGen.main(args);
        VanillaEntityClassGen.main(args);
        VanillaItemClassGen.main(args);
    }
}
