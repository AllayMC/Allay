package org.allaymc.server.utils;

import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.item.data.ItemId;
import org.allaymc.api.utils.Identifier;

/**
 * A class that handle the mapping relation between block and item id.
 * See more details in `block-and-item-id.md`.
 *
 * @author daoge_cmd
 */
public final class BlockAndItemIdMapper {

    /**
     * Naming conflict prefix
     */
    public static final String NAMING_CONFLICT_PATH_PREFIX = "item.";

    /**
     * Try to get the block id of an item.
     *
     * @param itemId the id of the item
     * @return the block id, or {@code null} if the item does not have correspond block (`pure item`)
     */
    public static Identifier itemIdToPossibleBlockId(Identifier itemId) {
        // Special case: sugar cane
        if (itemId.equals(ItemId.SUGAR_CANE.getIdentifier())) {
            return BlockId.REEDS.getIdentifier();
        }
        var blockId = itemId.clone();
        if (blockId.path().contains(NAMING_CONFLICT_PATH_PREFIX)) {
            blockId = new Identifier(blockId.namespace(), blockId.path().replace(NAMING_CONFLICT_PATH_PREFIX, ""));
        }
        return blockId;
    }

    /**
     * Get the `actual block item` id of a block.
     *
     * @param blockId the id of the block
     * @return the `actual block item` id
     */
    public static Identifier blockIdToActualBlockItemId(Identifier blockId) {
        // Special case: sugar cane
        if (blockId.equals(BlockId.REEDS.getIdentifier())) {
            return ItemId.SUGAR_CANE.getIdentifier();
        }
        return blockId;
    }
}
