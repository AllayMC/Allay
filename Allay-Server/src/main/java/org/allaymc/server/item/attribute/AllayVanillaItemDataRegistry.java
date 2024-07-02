package org.allaymc.server.item.attribute;

import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.component.common.ItemData;
import org.allaymc.api.item.registry.VanillaItemDataRegistry;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.registry.SimpleMappedRegistry;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.utils.ResourceUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Allay Project 2023/5/20
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayVanillaItemDataRegistry extends SimpleMappedRegistry<VanillaItemId, ItemData, Map<VanillaItemId, ItemData>> implements VanillaItemDataRegistry {
    public AllayVanillaItemDataRegistry(RegistryLoader<Void, Map<VanillaItemId, ItemData>> registryLoader) {
        super(null, registryLoader);
    }

    @AllArgsConstructor
    public static class Loader implements RegistryLoader<Void, Map<VanillaItemId, ItemData>> {
        protected Supplier<InputStream> streamSupplier;

        public Loader() {
            this(() -> new BufferedInputStream(ResourceUtils.getResource("items.json")));
        }

        @SneakyThrows
        @Override
        public Map<VanillaItemId, ItemData> load(Void input) {
            log.info("Start loading vanilla item data registry...");
            try (var reader = new InputStreamReader(streamSupplier.get())) {
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
}
