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
import java.util.Map;

/**
 * Allay Project 2024/7/2
 *
 * @author daoge_cmd
 */
public final class InternalItemTypeData {
    private static final Map<VanillaItemId, ItemTag[]> VANILLA_ITEM_TAGS = new HashMap<>();

    static {
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
    }

    public static ItemTag[] getItemTags(VanillaItemId id) {
        return VANILLA_ITEM_TAGS.getOrDefault(id, Utils.EMPTY_ITEM_TAG_ARRAY);
    }
}
