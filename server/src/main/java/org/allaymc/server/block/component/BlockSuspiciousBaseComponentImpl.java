package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;

import java.util.Set;

/**
 * {@code BlockSuspiciousBaseComponentImpl} suppresses ordinary item drops for
 * suspicious sand and gravel behavior. The shared base still handles the block
 * lifecycle and chooses silk touch separately, while ordinary breaking returns an
 * empty set rather than the block's item representation.
 *
 * @author Kanelucky
 */
public class BlockSuspiciousBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the suspicious block behavior for the supplied block type.
     */
    public BlockSuspiciousBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns an empty set for suspicious blocks' ordinary break drops. The shared
     * break handler performs eligibility checks first and handles silk touch through
     * its separate item selection path.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of();
    }
}