package org.allaymc.server.registry.loader;

import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.item.data.ItemData;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.item.data.ItemId;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code ItemDataRegistryLoader} reads bundled item metadata and associates it with built-in
 * item IDs. The loader skips resource identifiers absent from the runtime's ID table, while
 * recognized entries use {@code ItemData.fromJson} for their field representation and validation.
 *
 * @author daoge_cmd
 */
@Slf4j
public class ItemDataRegistryLoader implements RegistryLoader<Void, Map<ItemId, ItemData>> {
    /**
     * Loads {@code items.json} into a new mutable item-data map, ignoring the input. Unrecognized
     * item identities are logged and skipped; malformed recognized entries and resource failures
     * abort loading rather than returning a partial map.
     */
    @Override
    @SneakyThrows
    public Map<ItemId, ItemData> load(Void $) {
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("items.json")))) {
            var loaded = new HashMap<ItemId, ItemData>();
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var id = ItemId.fromIdentifier(new Identifier(entry.getKey()));
                if (id == null) {
                    // Resource entries can outpace this runtime's built-in ID table. Keep
                    // recognized item metadata and omit entries with no usable registry identity.
                    log.warn("Unknown item id: {}", entry.getKey());
                    return;
                }
                var itemData = ItemData.fromJson(entry.getValue().toString());
                loaded.put(id, itemData);
            });
            return loaded;
        }
    }
}
