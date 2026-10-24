package org.allaymc.server.registry.loader;

import com.google.gson.JsonParser;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntMaps;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import lombok.SneakyThrows;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.item.data.ItemId;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * Loads the block state hash BDS sends for a block item with a given meta, e.g. an upward piston for
 * {@code minecraft:piston} with meta 1. BDS keeps the meta of a block item in its block and sends meta 0.
 * <p>
 * The table covers the block items of the creative inventory that have a meta or whose block is not the default
 * state, such as glow lichen on all faces, recorded from BDS 1.26.52.
 */
public class BlockItemMetaStateRegistryLoader implements RegistryLoader<Void, Map<ItemId, Int2IntMap>> {
    @SneakyThrows
    @Override
    public Map<ItemId, Int2IntMap> load(Void unused) {
        var map = new EnumMap<ItemId, Int2IntMap>(ItemId.class);
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("block_item_meta_states.json")))) {
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var id = Objects.requireNonNull(ItemId.fromIdentifier(new Identifier(entry.getKey())), entry.getKey());
                var states = new Int2IntOpenHashMap();
                entry.getValue().getAsJsonObject().entrySet().forEach(state ->
                        states.put(Integer.parseInt(state.getKey()), state.getValue().getAsInt()));
                map.put(id, Int2IntMaps.unmodifiable(states));
            });
        }

        return map;
    }
}
