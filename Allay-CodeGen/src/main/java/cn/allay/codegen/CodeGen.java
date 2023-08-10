package cn.allay.codegen;

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
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    //被多个codegen同时使用到的资源文件
    public static final Path BLOCK_ID_TO_ITEM_ID_MAP_PATH = Path.of("Data/unpacked/block_id_to_item_id_map.nbt");
    public static final Map<String, String> BLOCK_ID_TO_ITEM_ID_MAP = new HashMap<>();
    public static final Map<String, String> ITEM_ID_TO_BLOCK_ID_MAP = new HashMap<>();

    static {
        try (var nbtReader = new NBTInputStream(new DataInputStream(new GZIPInputStream(Files.newInputStream(BLOCK_ID_TO_ITEM_ID_MAP_PATH))))) {
            var nbt = (NbtMap) nbtReader.readTag();
            nbt.forEach((key, value) -> {
                BLOCK_ID_TO_ITEM_ID_MAP.put(key, value.toString());
                ITEM_ID_TO_BLOCK_ID_MAP.put(value.toString(), key);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        VanillaBiomeIdEnumGen.main(args);
        VanillaBlockBehaviorInterfaceGen.main(args);
        VanillaEntityClassGen.main(args);
        VanillaItemClassGen.main(args);
    }
}
