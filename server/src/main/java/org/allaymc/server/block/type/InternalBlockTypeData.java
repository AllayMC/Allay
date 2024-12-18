package org.allaymc.server.block.type;

import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.tag.BlockTag;
import org.allaymc.api.block.tag.BlockTags;
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
public final class InternalBlockTypeData {

    // Use array instead of set to reduce memory usage
    private static final EnumMap<BlockId, BlockTag[]> BLOCK_TAGS = new EnumMap<>(BlockId.class);
    private static final EnumMap<BlockId, BlockTag[]> BLOCK_TAGS_CUSTOM = new EnumMap<>(BlockId.class);
    private static final EnumMap<BlockId, Integer> BLOCK_DEFAULT_STATES = new EnumMap<>(BlockId.class);

    @SneakyThrows
    public static void init() {
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("block_types.json")))) {
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var id = BlockId.fromIdentifier(new Identifier(entry.getKey()));
                var obj = entry.getValue().getAsJsonObject();
                // Tags (can be null)
                if (obj.has("tags")) {
                    var tags = obj.get("tags").getAsJsonArray();
                    var blockTags = new BlockTag[tags.size()];
                    for (int i = 0; i < tags.size(); i++) {
                        var tag = BlockTags.getTagByName(tags.get(i).getAsString());
                        if (tag == null) {
                            log.warn("Unknown block tag: {}", tags.get(i).getAsString());
                            continue;
                        }
                        blockTags[i] = tag;
                    }
                    BLOCK_TAGS.put(id, blockTags);
                } else {
                    BLOCK_TAGS.put(id, Utils.EMPTY_BLOCK_TAG_ARRAY);
                }
                // Default state
                BLOCK_DEFAULT_STATES.put(id, (int) obj.get("defaultBlockStateHash").getAsLong());
            });
        }
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("block_tags_custom.json")))) {
            var map = new HashMap<BlockId, Set<BlockTag>>();
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var tag = BlockCustomTags.getTagByName(entry.getKey());
                for (var obj : entry.getValue().getAsJsonArray()) {
                    var blockId = obj.getAsString();
                    var id = BlockId.fromIdentifier(new Identifier(blockId));
                    if (id == null) {
                        log.warn("Unknown block id: {}", blockId);
                        return;
                    }
                    map.computeIfAbsent(id, k -> new HashSet<>()).add(tag);
                }
            });
            map.forEach((id, tags) -> {
                var blockTags = new BlockTag[tags.size()];
                var i = 0;
                for (var tag : tags) {
                    blockTags[i++] = tag;
                }
                BLOCK_TAGS_CUSTOM.put(id, blockTags);
            });
            for (var id : BlockId.values()) {
                BLOCK_TAGS_CUSTOM.putIfAbsent(id, Utils.EMPTY_BLOCK_TAG_ARRAY);
            }
        }
    }

    public static BlockTag[] getBlockTags(BlockId id) {
        var vanilla = BLOCK_TAGS.get(id);
        var custom = BLOCK_TAGS_CUSTOM.get(id);
        var tags = new BlockTag[vanilla.length + custom.length];
        System.arraycopy(vanilla, 0, tags, 0, vanilla.length);
        System.arraycopy(custom, 0, tags, vanilla.length, custom.length);
        return tags;
    }

    public static int getDefaultBlockStateHash(BlockId id) {
        return BLOCK_DEFAULT_STATES.get(id);
    }
}
