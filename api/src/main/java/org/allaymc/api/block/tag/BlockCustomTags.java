package org.allaymc.api.block.tag;

import java.util.HashMap;
import java.util.Map;

/**
 * Automatically generated by {@code org.allaymc.codegen.TagGen}
 */
public interface BlockCustomTags {
    Map<String, BlockTag> NAME_TO_TAG = new HashMap<>();

    BlockTag SOUL_FIRE_CONVERTER = create("allay:soul_fire_converter");

    BlockTag REPLACEABLE = create("allay:replaceable");

    BlockTag WOOL = create("allay:wool");

    BlockTag LEAVES = create("allay:leaves");

    BlockTag LEAVES_SUPPORTER = create("allay:leaves_supporter");

    BlockTag INFINITE_FIRE_SUPPORTER = create("allay:infinite_fire_supporter");

    static BlockTag create(String name) {
        var tag = new BlockTag(name);
        NAME_TO_TAG.put(name, tag);
        return tag;
    }

    static BlockTag getTagByName(String name) {
        return NAME_TO_TAG.get(name);
    }
}
