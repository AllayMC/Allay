package org.allaymc.server.item.type;

import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.item.tag.ItemCustomTags;
import org.allaymc.api.item.tag.ItemTag;
import org.allaymc.api.item.tag.ItemTags;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author daoge_cmd
 */
@Slf4j
public final class InternalItemTypeData {

    private static final EnumMap<ItemId, ItemTag[]> ITEM_TAGS = new EnumMap<>(ItemId.class);
    private static final EnumMap<ItemId, ItemTag[]> ITEM_TAGS_CUSTOM = new EnumMap<>(ItemId.class);

    @SneakyThrows
    public static void init() {
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("items.json")))) {
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var id = ItemId.fromIdentifier(new Identifier(entry.getKey()));
                var obj = entry.getValue().getAsJsonObject();
                // Tags (can be null)
                if (obj.has("tags")) {
                    var tags = obj.get("tags").getAsJsonArray();
                    var itemTags = new ItemTag[tags.size()];
                    for (int i = 0; i < tags.size(); i++) {
                        var tag = ItemTags.getTagByName(tags.get(i).getAsString());
                        if (tag == null) {
                            log.warn("Unknown item tag: {}", tags.get(i).getAsString());
                            continue;
                        }
                        itemTags[i] = tag;
                    }
                    ITEM_TAGS.put(id, itemTags);
                } else {
                    ITEM_TAGS.put(id, Utils.EMPTY_ITEM_TAG_ARRAY);
                }
            });
        }
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("item_tags_custom.json")))) {
            var map = new HashMap<ItemId, Set<ItemTag>>();
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var tag = ItemCustomTags.getTagByName(entry.getKey());
                for (var obj : entry.getValue().getAsJsonArray()) {
                    var itemId = obj.getAsString();
                    var id = ItemId.fromIdentifier(new Identifier(itemId));
                    if (id == null) {
                        log.warn("Unknown item id: {}", itemId);
                        return;
                    }
                    map.computeIfAbsent(id, k -> new HashSet<>()).add(tag);
                }
            });
            map.forEach((id, tags) -> {
                var itemTags = new ItemTag[tags.size()];
                var i = 0;
                for (var tag : tags) {
                    itemTags[i++] = tag;
                }
                ITEM_TAGS_CUSTOM.put(id, itemTags);
            });
            for (var id : ItemId.values()) {
                ITEM_TAGS_CUSTOM.putIfAbsent(id, Utils.EMPTY_ITEM_TAG_ARRAY);
            }
        }
    }

    public static ItemTag[] getItemTags(ItemId id) {
        var vanilla = ITEM_TAGS.get(id);
        var custom = ITEM_TAGS_CUSTOM.get(id);
        var tags = new ItemTag[vanilla.length + custom.length];
        System.arraycopy(vanilla, 0, tags, 0, vanilla.length);
        System.arraycopy(custom, 0, tags, vanilla.length, custom.length);
        return tags;
    }
}
