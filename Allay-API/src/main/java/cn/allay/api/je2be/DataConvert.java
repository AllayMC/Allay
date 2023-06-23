package cn.allay.api.je2be;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.cloudburstmc.nbt.NbtMap;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

/**
 * @author Cool_Loong <br>
 * @date 6/7/2023 <br>
 * Allay Project
 */
public final class DataConvert {
    private static final Pattern NUMBER = Pattern.compile("^[0-9]*$");
    private static final Gson gson = new GsonBuilder().setObjectToNumberStrategy(jsonReader -> {
        String value = jsonReader.nextString();
        return Double.valueOf(value).intValue();
    }).create();
    private static Object2ObjectOpenHashMap<NbtMap, NbtMap> BE_2_JE_BLOCKS_MAPPING;
    private static Object2ObjectOpenHashMap<NbtMap, NbtMap> JE_2_BE_BLOCKS_MAPPING;

    public static void loadBlockStateMapping() {
        BE_2_JE_BLOCKS_MAPPING = new Object2ObjectOpenHashMap<>();
        JE_2_BE_BLOCKS_MAPPING = new Object2ObjectOpenHashMap<>();
        try (var input = DataConvert.class.getClassLoader().getResourceAsStream("mappings/blocks.json")) {
            assert input != null;
            Map<String, Map<String, Object>> blocks = gson.fromJson(new InputStreamReader(input), Map.class);
            blocks.forEach((k, v) -> {
                String[] strings = k.replace("[", ",").replace("]", ",").split(",");
                var Name = strings[0];
                var Properties = new TreeMap<String, Object>();
                if (strings.length > 1) {
                    for (int i = 1; i < strings.length; i++) {
                        final var tmp = strings[i];
                        final var index = tmp.indexOf("=");
                        String value = tmp.substring(index + 1);
                        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
                            Properties.put(tmp.substring(0, index), Boolean.parseBoolean(value));
                        } else if (NUMBER.matcher(value).matches()) {
                            Properties.put(tmp.substring(0, index), Integer.parseInt(value));
                        } else {
                            Properties.put(tmp.substring(0, index), value);
                        }
                    }
                }
                NbtMap jeNBT = NbtMap.builder().putString("Name", Name)
                        .putCompound("Properties", NbtMap.fromMap(Properties))
                        .build();

                var name = v.get("bedrock_identifier").toString();
                TreeMap<String, Object> states = new TreeMap<>();
                if (v.containsKey("bedrock_states")) {
                    states.putAll((Map<String, Object>) v.get("bedrock_states"));
                } else {
                    states = new TreeMap<>();
                }
                NbtMap beNBT = NbtMap.builder().putString("name", name)
                        .putCompound("states", NbtMap.fromMap(states))
                        .build();
                BE_2_JE_BLOCKS_MAPPING.put(beNBT, jeNBT);
                JE_2_BE_BLOCKS_MAPPING.put(jeNBT, beNBT);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BE_2_JE_BLOCKS_MAPPING.trim();
        JE_2_BE_BLOCKS_MAPPING.trim();
    }

    /**
     * Convert je BlockState NBT to be BlockState NBT
     *
     * <pre>
     *           BE                                                  JE
     *  {                                          {
     *   "name": "minecraft:sapling",                "Name": "minecraft:acacia_sapling",
     *   "states": {                                 "Properties": {
     *     "age_bit": true,               =>             "stage": 1i
     *     "sapling_type": "acacia"                   }
     *   }                                         }
     * }
     *
     * </pre>
     * @param beBlockState the BlockState NBT
     * @return the nbt map
     */
    public static NbtMap convertToJEBlockState(NbtMap beBlockState) {
        if (BE_2_JE_BLOCKS_MAPPING == null) {
            loadBlockStateMapping();
        }
        return BE_2_JE_BLOCKS_MAPPING.get(beBlockState);
    }
}
