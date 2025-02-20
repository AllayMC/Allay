package org.allaymc.server.registry.loader;

import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.item.type.ItemData;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author daoge_cmd
 */
@Slf4j
public class ItemDataLoader implements RegistryLoader<Void, Map<ItemId, ItemData>> {
    @Override
    @SneakyThrows
    public Map<ItemId, ItemData> load(Void $) {
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("items.json")))) {
            var loaded = new HashMap<ItemId, ItemData>();
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var id = ItemId.fromIdentifier(new Identifier(entry.getKey()));
                if (id == null) {
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
