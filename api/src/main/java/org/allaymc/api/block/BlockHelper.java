package org.allaymc.api.block;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.data.ToolTier;

/**
 * @author daoge_cmd
 */
public final class BlockHelper {
    /**
     * Gets the tool tier required to break a block type.
     *
     * @param blockType the {@link BlockType} to check
     * @return the required {@link ToolTier}, or {@code null} if none is needed
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