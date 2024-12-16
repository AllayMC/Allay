package org.allaymc.api.block;

import org.allaymc.api.block.tag.BlockTags;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.data.ToolTier;

/**
 * @author daoge_cmd
 */
public final class BlockHelper {

    /**
     * Get the required tool tier to break the block.
     *
     * @param blockType the block type.
     *
     * @return the required tool tier, or {@code null} if no tool tier is required.
     */
    public static ToolTier getRequiredToolTier(BlockType<?> blockType) {
        if (blockType.hasBlockTag(BlockTags.DIAMOND_TIER_DESTRUCTIBLE)) {
            return ToolTier.DIAMOND;
        }
        if (blockType.hasBlockTag(BlockTags.IRON_TIER_DESTRUCTIBLE)) {
            return ToolTier.IRON;
        }
        if (blockType.hasBlockTag(BlockTags.STONE_TIER_DESTRUCTIBLE)) {
            return ToolTier.STONE;
        }
        return null;
    }
}