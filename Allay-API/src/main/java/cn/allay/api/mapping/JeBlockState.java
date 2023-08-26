package cn.allay.api.mapping;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.Contract;

import java.util.Map;

@Getter
@Setter
public class JeBlockState {
    private final static Cache<String, JeBlockState> CACHE = Caffeine.newBuilder()
            .maximumSize(1000)
            .build();
    private final String identifier;
    private final Map<String, String> attributes = new Object2ObjectArrayMap<>(1);
    private boolean equalsIgnoreAttributes = false;
    private boolean equalsIgnoreWaterlogged = false;

    /**
     * <pre>
     *     State Str
     *     minecraft:redstone_wire[east=up,north=up,power=0,south=up,west=side]
     * </pre>
     *
     * @param str the state str
     */
    private JeBlockState(String str) {
        var strings = str.replace("[", ",").replace("]", ",").replace(" ", "").split(",");
        identifier = strings[0];
        if (strings.length > 1) {
            for (int i = 1; i < strings.length; i++) {
                final var tmp = strings[i];
                final var index = tmp.indexOf("=");
                attributes.put(tmp.substring(0, index), tmp.substring(index + 1));
            }
        }
    }

    @Contract("_ -> new")
    public static JeBlockState of(String str) {
        return CACHE.get(str, JeBlockState::new);
    }

    /**
     * <pre>
     *      JE NBT
     *      {
     *            "Name": "minecraft:acacia_sapling",
     *            "Properties": {
     *                 "stage": 1i
     *            }
     *      }
     * </pre>
     *
     * @param jeNbtMap the je nbt
     */
    public JeBlockState(NbtMap jeNbtMap) {
        identifier = jeNbtMap.getString("Name");
        if (jeNbtMap.containsKey("Properties")) {
            for (var entry : jeNbtMap.getCompound("Properties").entrySet()) {
                attributes.put(entry.getKey(), entry.getValue().toString());
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof JeBlockState state) {
            if (equalsIgnoreAttributes || state.equalsIgnoreAttributes) {
                if (state.identifier.equals(identifier)) return true;
            }
            if (equalsIgnoreWaterlogged || state.equalsIgnoreWaterlogged) {
                var m1 = new Object2ObjectArrayMap<>(attributes);
                var m2 = new Object2ObjectArrayMap<>(state.attributes);
                m1.remove("waterlogged");
                m2.remove("waterlogged");
                if (state.identifier.equals(identifier) && m1.equals(m2)) return true;
            }
            return state.identifier.equals(identifier) && attributes.equals(state.attributes);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder(identifier).append('[');
        String join = String.join(",", attributes.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).toList());
        return ret.append(join).append(']').toString();
    }
}
