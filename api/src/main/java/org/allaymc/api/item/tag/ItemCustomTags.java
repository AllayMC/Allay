package org.allaymc.api.item.tag;

import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daoge_cmd
 */
@ApiStatus.Experimental
public interface ItemCustomTags {
    Map<String, ItemTag> NAME_TO_TAG = new HashMap<>();

    ItemTag BEACON_PAYMENT = create("allay:beacon_payment");

    ItemTag HEAD = create("allay:head");

    static ItemTag create(String name) {
        var tag = new ItemTag(name);
        NAME_TO_TAG.put(name, tag);
        return tag;
    }

    static ItemTag getTagByName(String name) {
        return NAME_TO_TAG.get(name);
    }
}
