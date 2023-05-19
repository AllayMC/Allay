package cn.allay.codegen;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/8 <br>
 * Allay Project <br>
 */
public class CodeGen {
    //TODO: 统一资源加载？eg: block_palette.nbt
    static final Path BLOCK_PALETTE_FILE_PATH = Path.of("Data/block_palette.nbt");
    static final List<NbtMap> BLOCK_PALETTE_NBT;
    static final Map<String, NbtMap> MAPPED_BLOCK_PALETTE_NBT = new HashMap<>();

    static {
        try (var nbtReader = new NBTInputStream(new DataInputStream(new GZIPInputStream(Files.newInputStream(CodeGen.BLOCK_PALETTE_FILE_PATH))))) {
            BLOCK_PALETTE_NBT = ((NbtMap) nbtReader.readTag()).getList("blocks", NbtType.COMPOUND);
            for (var entry : BLOCK_PALETTE_NBT) {
                MAPPED_BLOCK_PALETTE_NBT.put(entry.getString("name"), entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static final Path ITEM_DATA_FILE_PATH = Path.of("Data/item_data.json");
    static final Map<String, Map<String, JsonElement>> MAPPED_ITEM_DATA = new TreeMap<>();

    static {
        try {
            var reader = JsonParser.parseReader(Files.newBufferedReader(ITEM_DATA_FILE_PATH));
            reader.getAsJsonArray().forEach(item -> {
                var obj = item.getAsJsonObject();
                MAPPED_ITEM_DATA.put(obj.get("name").getAsString(), obj.asMap());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println();
        //TODO
//        VanillaBlockIdEnumGen.generate();
//        VanillaBlockPropertyTypeGen.generate();
        VanillaItemIdEnumCodeGen.generate();
    }
}
