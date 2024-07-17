package org.allaymc.server.item.type;

import com.google.gson.JsonParser;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.data.VanillaItemTags;
import org.allaymc.api.item.tag.ItemTag;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.utils.ResourceUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Allay Project 2024/7/2
 *
 * @author daoge_cmd
 */
public final class InternalItemTypeData {
    private static final Map<VanillaItemId, ItemTag[]> VANILLA_ITEM_TAGS = new HashMap<>();

    private static final Map<VanillaItemId, ItemTag[]> VANILLA_ITEM_TAGS_CUSTOM = new HashMap<>();

    public static void init() {
        try (var reader = new InputStreamReader(new BufferedInputStream(ResourceUtils.getResource("items.json")))) {
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var id = VanillaItemId.fromIdentifier(new Identifier(entry.getKey()));
                var obj = entry.getValue().getAsJsonObject();
                // Tags (can be null)
                if (obj.has("tags")) {
                    var tags = obj.get("tags").getAsJsonArray();
                    var itemTags = new ItemTag[tags.size()];
                    for (int i = 0; i < tags.size(); i++) {
                        itemTags[i] = VanillaItemTags.getTagByName(tags.get(i).getAsString());
                    }
                    VANILLA_ITEM_TAGS.put(id, itemTags);
                } else {
                    VANILLA_ITEM_TAGS.put(id, Utils.EMPTY_ITEM_TAG_ARRAY);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (var reader = new InputStreamReader(new BufferedInputStream(ResourceUtils.getResource("item_tags_custom.json")))) {
            var map = new HashMap<VanillaItemId, Set<ItemTag>>();
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var tag = VanillaItemTags.getTagByName(entry.getKey());
                for (var obj : entry.getValue().getAsJsonArray()) {
                    var itemId = obj.getAsString();
                    var id = VanillaItemId.fromIdentifier(new Identifier(itemId));
                    map.computeIfAbsent(id, k -> new HashSet<>()).add(tag);
                }
            });
            map.forEach((id, tags) -> {
                var itemTags = new ItemTag[tags.size()];
                var i = 0;
                for (var tag : tags) {
                    itemTags[i++] = tag;
                }
                VANILLA_ITEM_TAGS_CUSTOM.put(id, itemTags);
            });
            for (var id : VanillaItemId.values()) {
                VANILLA_ITEM_TAGS_CUSTOM.putIfAbsent(id, Utils.EMPTY_ITEM_TAG_ARRAY);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ItemTag[] getItemTags(VanillaItemId id) {
        var vanilla = VANILLA_ITEM_TAGS.get(id);
        var custom = VANILLA_ITEM_TAGS_CUSTOM.get(id);
        var tags = new ItemTag[vanilla.length + custom.length];
        System.arraycopy(vanilla, 0, tags, 0, vanilla.length);
        System.arraycopy(custom, 0, tags, vanilla.length, custom.length);
        return tags;
    }
}
