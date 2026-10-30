package org.allaymc.server.registry.loader;

import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockTag;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.registry.RegistryLoader;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.block.data.BlockId;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * {@code BlockTagRegistryLoader} combines bundled block tag membership with the server's
 * custom membership resource. The first resource supplies per-block tags, and the custom
 * resource adds block identities to each named tag without discarding the membership
 * already collected for that identity.
 *
 * @author daoge_cmd
 */
@Slf4j
public class BlockTagRegistryLoader implements RegistryLoader<Void, Map<BlockId, Set<BlockTag>>> {

    /**
     * Loads mutable block-to-tag sets from {@code "block_types.json"} and
     * {@code "block_tags_custom.json"}. Unknown names in the first resource's tag lists and
     * unresolved
     * identities in custom memberships are warned about and skipped; other malformed data aborts
     * loading. A custom tag lookup can contribute {@code null} when that tag name is unknown.
     */
    @SneakyThrows
    @Override
    public Map<BlockId, Set<BlockTag>> load(Void $) {
        var map = new EnumMap<BlockId, Set<BlockTag>>(BlockId.class);
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("block_types.json")))) {
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var id = Objects.requireNonNull(BlockId.fromIdentifier(new Identifier(entry.getKey())));
                var obj = entry.getValue().getAsJsonObject();
                if (obj.has("tags")) {
                    var array = obj.get("tags").getAsJsonArray();
                    var tags = new HashSet<BlockTag>();
                    for (int i = 0; i < array.size(); i++) {
                        var tag = BlockTags.getTagByName(array.get(i).getAsString());
                        if (tag == null) {
                            // This tag name has no runtime definition. Retain the identity's
                            // remaining recognized tags instead of inserting an invalid tag value.
                            log.warn("Unknown block tag: {}", array.get(i).getAsString());
                            continue;
                        }

                        tags.add(tag);
                    }
                    map.put(id, tags);
                }
            });
        }
        try (var reader = new InputStreamReader(new BufferedInputStream(Utils.getResource("block_tags_custom.json")))) {
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var tag = BlockTags.getTagByName(entry.getKey());
                for (var obj : entry.getValue().getAsJsonArray()) {
                    var id = BlockId.fromIdentifier(new Identifier(obj.getAsString()));
                    if (id == null) {
                        // Custom memberships can refer to identities absent from this build.
                        // Keep recognized memberships and omit the unresolved identity from the map.
                        log.warn("Unknown block id: {}", obj.getAsString());
                        continue;
                    }

                    map.computeIfAbsent(id, k -> new HashSet<>()).add(tag);
                }
            });
        }

        return map;
    }
}
