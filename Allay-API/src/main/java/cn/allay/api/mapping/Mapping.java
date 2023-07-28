package cn.allay.api.mapping;

import cn.allay.api.utils.HashUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import org.cloudburstmc.nbt.NbtMap;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author daoge_cmd
 */
@SuppressWarnings("unchecked")
public class Mapping {
    private static final Gson gson = new GsonBuilder().setObjectToNumberStrategy(jsonReader -> {
        String value = jsonReader.nextString();
        return Double.valueOf(value).intValue();
    }).create();
    private static final Map<JeBlockState, Integer> JE_2_BE_BLOCKS_MAPPING = new Object2IntOpenHashMap<>();
    private static final Map<Integer, JeBlockState> BE_2_JE_BLOCKS_MAPPING = new Int2ObjectOpenHashMap<>();
    private static final Map<String, Integer> JE_2_BE_BIOME_MAP = new Object2IntOpenHashMap<>();
    private static final Map<Integer, String> BE_2_JE_BIOME_MAP = new Int2ObjectOpenHashMap<>();

    static {
        init();
    }

    @Nullable
    public static String getJeBiomeName(int beBiomeId) {
        return BE_2_JE_BIOME_MAP.get(beBiomeId);
    }

    @Nullable
    public static Integer getBeBiomeId(String jeBiomeName) {
        return JE_2_BE_BIOME_MAP.get(jeBiomeName);
    }

    @Nullable
    public static JeBlockState getJeBlockState(int beBlockStateHash) {
        return BE_2_JE_BLOCKS_MAPPING.get(beBlockStateHash);
    }

    @Nullable
    public static Integer getBeBlockStateHash(JeBlockState jeBlockState) {
        return JE_2_BE_BLOCKS_MAPPING.get(jeBlockState);
    }

    private static void init() {
        //Block
        try (var input = Mapping.class.getClassLoader().getResourceAsStream("mappings/blocks.json")) {
            assert input != null;
            Map<String, Map<String, Object>> blocks = gson.fromJson(new InputStreamReader(input), Map.class);
            blocks.forEach((k, v) -> {
                var name = v.get("bedrock_identifier").toString();
                TreeMap<String, Object> states = new TreeMap<>();
                if (v.containsKey("bedrock_states")) {
                    var bedrockStates = (Map<String, Object>) v.get("bedrock_states");
                    for (var entry : bedrockStates.entrySet()) {
                        if (entry.getValue().getClass() == Boolean.class) {
                            states.put(entry.getKey(), entry.getValue().toString().equals("true") ? (byte) 1 : (byte) 0);
                        } else {
                            states.put(entry.getKey(), entry.getValue());
                        }
                    }

                } else {
                    states = new TreeMap<>();
                }
                NbtMap beNBT = NbtMap.builder().putString("name", name)
                        .putCompound("states", NbtMap.fromMap(states))
                        .build();
                JeBlockState jeBlockState = new JeBlockState(k);
                int hash = HashUtils.fnv1a_32_nbt(beNBT);
                BE_2_JE_BLOCKS_MAPPING.put(hash, jeBlockState);
                JE_2_BE_BLOCKS_MAPPING.put(jeBlockState, hash);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Biome
        try (var input = Mapping.class.getClassLoader().getResourceAsStream("mappings/biomes.json")) {
            assert input != null;
            Map<String, Map<String, Integer>> biomes = gson.fromJson(new InputStreamReader(input), Map.class);
            biomes.forEach((k, v) -> {
                JE_2_BE_BIOME_MAP.put(k, v.get("bedrock_id"));
                BE_2_JE_BIOME_MAP.put(v.get("bedrock_id"), k);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
