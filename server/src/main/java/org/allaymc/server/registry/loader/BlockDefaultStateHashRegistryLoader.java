package org.allaymc.server.registry.loader;

import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.block.data.BlockId;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.Map;

/**
 * {@code BlockDefaultStateHashRegistryLoader} reads the default state hash for each built-in
 * block from bundled block-type data. The loader stores each hash's low thirty-two bits in
 * an integer so the registry uses the same bit pattern as runtime block-state lookups.
 *
 * @author daoge_cmd
 */
public class BlockDefaultStateHashRegistryLoader implements RegistryLoader<Void, Map<BlockId, Integer>> {
    /**
     * Loads default state hashes from {@code block_types.json}, ignoring the input argument.
     * The returned enum map is mutable; missing resources, malformed entries, or unresolved block
     * identities abort loading rather than producing a partial result.
     */
    @SneakyThrows
    @Override
    public Map<BlockId, Integer> load(Void unused) {
        var map = new EnumMap<BlockId, Integer>(BlockId.class);
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("block_types.json")))) {
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var id = BlockId.fromIdentifier(new Identifier(entry.getKey()));
                var obj = entry.getValue().getAsJsonObject();
                // Keep the low thirty-two bits because block-state maps use signed integer keys
                // for the same hash pattern, including hashes represented as positive JSON longs.
                map.put(id, (int) obj.get("defaultBlockStateHash").getAsLong());
            });
        }

        return map;
    }
}
