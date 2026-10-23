package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code BlockSeaLanternBaseComponentImpl} selects prismarine crystals for ordinary
 * sea lantern breaks. The random count includes two or three crystals, while
 * inherited silk touch handling selects the block item separately; this ordinary drop
 * selection does not consult Fortune levels.
 *
 * @author IWareQ
 */
public class BlockSeaLanternBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the sea lantern block behavior for the supplied block type.
     */
    public BlockSeaLanternBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns two or three prismarine crystals as the ordinary drop.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.PRISMARINE_CRYSTALS.createItemStack(ThreadLocalRandom.current().nextInt(2, 4)));
    }
}
