package org.allaymc.server.block.component.multiface;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;

import java.util.Set;

/**
 * {@code BlockResinClumpBaseComponentImpl} selects resin item counts from the number
 * of attached faces in a resin-clump state. The inherited multi-face behavior manages
 * adding and removing those attachments, while ordinary drop lookup converts the
 * surviving direction bits into an equal item count.
 */
public class BlockResinClumpBaseComponentImpl extends BlockMultiFaceBaseComponentImpl {
    /**
     * Creates resin clump behavior for the supplied block type.
     */
    public BlockResinClumpBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns a resin item stack containing one item per set attachment bit. The
     * count comes from the supplied block snapshot, so it reflects attachments
     * remaining when drop selection occurs.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var count = Integer.bitCount(block.getPropertyValue(BlockPropertyTypes.MULTI_FACE_DIRECTION_BITS));
        return Set.of(blockType.getItemType().createItemStack(count));
    }
}
