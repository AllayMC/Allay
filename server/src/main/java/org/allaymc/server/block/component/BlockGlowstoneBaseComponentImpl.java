package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.block.FortuneDropHelper;

import java.util.Set;

/**
 * {@code BlockGlowstoneBaseComponentImpl} selects glowstone dust for ordinary
 * glowstone block breaks using a capped Fortune distribution. The minimum stays at
 * two dust, the maximum stays capped at four, and inherited silk touch handling uses
 * the block item instead.
 *
 * @author IWareQ
 */
public class BlockGlowstoneBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the glowstone block behavior for the supplied block type.
     */
    public BlockGlowstoneBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns two through four glowstone dust using the capped Fortune distribution.
     * A {@code null} tool contributes no Fortune levels, and silk touch selection
     * remains the inherited break handler's responsibility.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.GLOWSTONE_DUST.createItemStack(FortuneDropHelper.discrete(usedItem, 2, 4, 4)));
    }
}
