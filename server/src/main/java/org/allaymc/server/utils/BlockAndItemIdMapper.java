package org.allaymc.server.utils;

import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.block.data.BlockId;
import org.allaymc.server.item.data.ItemId;

/**
 * {@code BlockAndItemIdMapper} translates identifier naming conventions between blocks and
 * their item representations. A block's hard item can carry {@code "item."} to avoid a
 * name conflict with its actual item, while sugar cane uses the distinct block identity
 * {@code "minecraft:reeds"}.
 *
 * <p>
 * Item-to-block conversion removes every occurrence of the conflict marker from the path and
 * handles sugar cane explicitly, but does not consult the block registry. A pure item
 * therefore still produces a candidate identifier that callers must resolve themselves.
 * Block-to-actual-item conversion retains ordinary identifiers and changes only reeds to sugar
 * cane, so the two operations express naming rules rather than a general bidirectional registry.
 *
 * @author daoge_cmd
 */
public final class BlockAndItemIdMapper {

    /**
     * {@code NAMING_CONFLICT_PATH_PREFIX} identifies the hard-item path marker removed when
     * deriving
     * a candidate block identity. The conversion removes all occurrences rather than checking only
     * the beginning of the path.
     */
    public static final String NAMING_CONFLICT_PATH_PREFIX = "item.";

    /**
     * Derives a candidate block identity without checking whether a block is registered. Sugar
     * cane maps to reeds; other paths have every {@code "item."} occurrence removed, and the
     * original namespace is retained.
     *
     * <p>
     * A pure item still produces an identifier, so callers perform registry lookup to decide
     * whether a corresponding block exists. This operation cannot establish that the input is a
     * hard block item or reverse every actual-item association.
     */
    public static Identifier itemIdToPossibleBlockId(Identifier itemId) {
        // Sugar cane's actual item and block use different paths. Preserve the reeds block
        // identity before applying the ordinary hard-item conflict-marker normalization.
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
     * Returns the actual-item identity for a block naming convention. Reeds maps to sugar cane,
     * while every other identifier is returned unchanged; this operation does not verify that
     * the resulting item exists in a registry.
     */
    public static Identifier blockIdToActualBlockItemId(Identifier blockId) {
        // Reeds has no same-named actual item in the naming table. Return sugar cane so
        // block-to-item lookup reaches its ordinary inventory item rather than a hard-item alias.
        if (blockId.equals(BlockId.REEDS.getIdentifier())) {
            return ItemId.SUGAR_CANE.getIdentifier();
        }
        return blockId;
    }
}
