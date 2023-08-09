package cn.allay.api.utils;

import cn.allay.api.data.VanillaBiomeId;
import cn.allay.api.identifier.Identifier;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

/**
 * Allay Project 8/8/2023
 *
 * @author Cool_Loong
 */
@UtilityClass
public class VanillaBiomeIdUtils {
    private final static Int2ObjectOpenHashMap<VanillaBiomeId> MAP1 = new Int2ObjectOpenHashMap<>();
    private final static HashMap<Identifier, VanillaBiomeId> MAP2 = new HashMap<>();

    static {
        for (var v : VanillaBiomeId.values()) {
            MAP1.put(v.getId(), v);
            MAP2.put(v.getIdentifier(), v);
        }
    }

    @Nullable
    public VanillaBiomeId fromId(int id) {
        return MAP1.get(id);
    }

    @Nullable
    public VanillaBiomeId fromIdentifier(Identifier identifier) {
        return MAP2.get(identifier);
    }
}
