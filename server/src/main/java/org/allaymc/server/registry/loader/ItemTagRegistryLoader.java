package org.allaymc.server.registry.loader;

import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.item.data.ItemTag;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.item.data.ItemId;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author daoge_cmd
 */
@Slf4j
public class ItemTagRegistryLoader implements RegistryLoader<Void, Map<ItemId, Set<ItemTag>>> {

    @SneakyThrows
    @Override
    public Map<ItemId, Set<ItemTag>> load(Void $) {
        var map = new EnumMap<ItemId, Set<ItemTag>>(ItemId.class);
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("items.json")))) {
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var id = Objects.requireNonNull(ItemId.fromIdentifier(new Identifier(entry.getKey())));
                var obj = entry.getValue().getAsJsonObject();
                // Tags (can be null)
                if (obj.has("tags")) {
                    var array = obj.get("tags").getAsJsonArray();
                    var tags = new HashSet<ItemTag>();
                    for (int i = 0; i < array.size(); i++) {
                        var tag = ItemTags.getTagByName(array.get(i).getAsString());
                        if (tag == null) {
                            log.warn("Unknown item tag: {}", array.get(i).getAsString());
                            continue;
                        }
                        tags.add(tag);
                    }
                    map.put(id, tags);
                }
            });
        }
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("item_tags_custom.json")))) {
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var tag = ItemTags.getTagByName(entry.getKey());
                for (var obj : entry.getValue().getAsJsonArray()) {
                    var id = ItemId.fromIdentifier(new Identifier(obj.getAsString()));
                    if (id == null) {
                        log.warn("Unknown item id: {}", obj.getAsString());
                        continue;
                    }

                    map.computeIfAbsent(id, k -> new HashSet<>()).add(tag);
                }
            });
        }

        return map;
    }
}
