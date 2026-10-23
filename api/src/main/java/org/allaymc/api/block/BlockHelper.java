package org.allaymc.api.block;

import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.data.ToolTier;

/**
 * {@code BlockHelper} resolves the minimum tool tier represented by block
 * destructibility tags. The lookup checks diamond, iron, and stone requirements in
 * that order, so a block with several matching tags uses the strongest listed
 * requirement.
 *
 * @author daoge_cmd
 */
public final class BlockHelper {
    /**
     * Returns the tool tier required by the block type's destructibility tags.
     * Diamond takes precedence over iron and stone when multiple tags match; a block
     * without those tags returns {@code null} to indicate no tier requirement.
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