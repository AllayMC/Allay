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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.GZIPInputStream;

import static cn.allay.codegen.Utils.convertToPascalCase;

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

    static final Path ENTITY_DATA_FILE_PATH = Path.of("Data/entity_data.json");
    static final Map<String, Map<String, JsonElement>> MAPPED_ENTITY_DATA = new TreeMap<>();

    static {
        try {
            var reader = JsonParser.parseReader(Files.newBufferedReader(ENTITY_DATA_FILE_PATH));
            reader.getAsJsonObject().entrySet().forEach(entry -> {
                var obj = entry.getValue().getAsJsonObject();
                MAPPED_ENTITY_DATA.put(obj.get("canonicalName").getAsString(), obj.asMap());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Path BLOCK_PROPERTY_TYPES_FILE = Path.of("Data/unpacked/block_property_types.json");
    static final BlockPropertyTypeFile BLOCK_PROPERTY_TYPE_INFO_FILE;
    static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static class BlockPropertyTypeFile {
        Map<String, BlockPropertyTypeInfo> propertyTypes;
        List<String> multiple_propertyType;

        public static class BlockPropertyTypeInfo {
            boolean multi;
            String serializationName;
            BlockPropertyType valueType;
            List<String> values;

            public String getEnumClassName() {
                return convertToPascalCase(serializationName);
            }
        }

        protected enum BlockPropertyType {
            BOOLEAN,
            INTEGER,
            ENUM
        }
    }

    static {
        try {
            BLOCK_PROPERTY_TYPE_INFO_FILE = GSON.fromJson(Files.newBufferedReader(BLOCK_PROPERTY_TYPES_FILE), BlockPropertyTypeFile.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println();
        VanillaBlockPropertyTypeGen.generate();
        VanillaBlockIdEnumGen.generate();
        VanillaItemIdEnumCodeGen.generate();
        VanillaEntityIdEnumGen.generate();
    }
}
