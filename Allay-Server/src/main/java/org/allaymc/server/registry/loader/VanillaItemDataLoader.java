package org.allaymc.server.registry.loader;

import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.component.common.ItemData;
import org.allaymc.api.registry.loader.RegistryLoader;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2024/7/20
 *
 * @author daoge_cmd
 */
@Slf4j
public class VanillaItemDataLoader implements RegistryLoader<Void, Map<VanillaItemId, ItemData>> {
    @Override
    @SneakyThrows
    public Map<VanillaItemId, ItemData> load(Void $) {
        log.info("Start loading vanilla item data registry...");
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("items.json")))) {
            var loaded = new HashMap<VanillaItemId, ItemData>();
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var id = VanillaItemId.fromIdentifier(new Identifier(entry.getKey()));
                if (id == null) {
                    log.warn("Unknown item id: {}", entry.getKey());
                    return;
                }
                var itemData = ItemData.fromJson(entry.getValue().toString());
                loaded.put(id, itemData);
            });
            log.info("Loaded vanilla item data registry successfully");
            return loaded;
        }
    }
}
