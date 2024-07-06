package org.allaymc.server.block.type;

import com.google.gson.JsonParser;
import org.allaymc.api.block.material.MaterialType;
import org.allaymc.api.block.tag.BlockTag;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockTags;
import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.data.VanillaMaterialTypes;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.utils.ResourceUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2024/7/2
 *
 * @author daoge_cmd
 */
public final class InternalBlockTypeData {

    // Use array instead of set to reduce memory usage
    private static final Map<VanillaBlockId, BlockTag[]> VANILLA_BLOCK_TAGS = new HashMap<>();

    private static final Map<VanillaBlockId, MaterialType> VANILLA_BLOCK_MATERIAL_TYPES = new HashMap<>();

    // Stores the correct tool sets for blocks that require tool quality
    private static final EnumMap<VanillaBlockId, VanillaItemId[]> VANILLA_BLOCK_SPECIAL_TOOLS = new EnumMap<>(VanillaBlockId.class);

    static {
        try (var reader = new InputStreamReader(new BufferedInputStream(ResourceUtils.getResource("block_types.json")))) {
            JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
                var id = VanillaBlockId.fromIdentifier(new Identifier(entry.getKey()));
                var obj = entry.getValue().getAsJsonObject();
                // Material
                var materialType = VanillaMaterialTypes.getMaterialTypeByName(obj.get("material").getAsString());
                VANILLA_BLOCK_MATERIAL_TYPES.put(id, materialType);
                // Tags (can be null)
                if (obj.has("tags")) {
                    var tags = obj.get("tags").getAsJsonArray();
                    var blockTags = new BlockTag[tags.size()];
                    for (int i = 0; i < tags.size(); i++) {
                        blockTags[i] = VanillaBlockTags.getTagByName(tags.get(i).getAsString());
                    }
                    VANILLA_BLOCK_TAGS.put(id, blockTags);
                } else {
                    VANILLA_BLOCK_TAGS.put(id, Utils.EMPTY_BLOCK_TAG_ARRAY);
                }
                // Special tools (can be null)
                if (obj.has("specialTools")) {
                    var tools = obj.get("specialTools").getAsJsonArray();
                    var specialTools = new VanillaItemId[tools.size()];
                    for (int i = 0; i < tools.size(); i++) {
                        specialTools[i] = VanillaItemId.fromIdentifier(new Identifier(tools.get(i).getAsString()));
                    }
                    VANILLA_BLOCK_SPECIAL_TOOLS.put(id, specialTools);
                } else {
                    VANILLA_BLOCK_SPECIAL_TOOLS.put(id, Utils.EMPTY_VANILLA_ITEM_ID_ARRAY);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static MaterialType getMaterialType(VanillaBlockId id) {
        return VANILLA_BLOCK_MATERIAL_TYPES.get(id);
    }

    public static BlockTag[] getBlockTags(VanillaBlockId id) {
        return VANILLA_BLOCK_TAGS.get(id);
    }

    public static VanillaItemId[] getSpecialTools(VanillaBlockId id) {
        return VANILLA_BLOCK_SPECIAL_TOOLS.get(id);
    }
}
