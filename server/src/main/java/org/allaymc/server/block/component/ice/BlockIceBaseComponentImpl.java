package org.allaymc.server.block.component.ice;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.Set;

/**
 * {@code BlockIceBaseComponentImpl} implements ordinary ice melting and water-producing break
 * behavior. Random updates examine adjacent block light and propose water when any neighbor
 * exceeds the melt threshold. Ordinary drop calculation produces no item and writes water at
 * the broken ice position when the lower block is not air.
 *
 * @author IWareQ
 */
public class BlockIceBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the ice behavior component for the supplied block type.
     */
    public BlockIceBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Dispatches the inherited random-update hook and proposes melting when adjacent block
     * light exceeds eleven. The fade event can cancel or replace the proposed water state
     * before it is written at the ice position.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        if (isNeighborBlockLightBiggerThan(block.getDimension(), block.getPosition())) {
            var event = new BlockFadeEvent(block, BlockTypes.WATER.getDefaultState());
            if (event.call()) {
                block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
            }
        }
    }

    /**
     * Returns whether any of the six neighboring positions has block light above eleven. The
     * check uses emitted block light rather than internal or sky light when deciding the
     * melting threshold.
     */
    protected boolean isNeighborBlockLightBiggerThan(Dimension dimension, Vector3ic pos) {
        for (var face : BlockFace.VALUES) {
            // Only adjacent emitted light contributes to this ice-melting trigger.
            if (dimension.getLightEngine().getBlockLight(face.offsetPos(pos)) > 11) {
                return true;
            }
        }

        return false;
    }


    /**
     * Returns no item drops and may replace the broken ice with water. Any nonair lower block
     * triggers that write; this drop calculation therefore mutates the dimension rather than
     * serving as a pure query.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (block.offsetPos(BlockFace.DOWN).getBlockType() != BlockTypes.AIR) {
            block.getDimension().setBlockState(block.getPosition(), BlockTypes.WATER.getDefaultState());
        }

        return Set.of();
    }

    /**
     * Returns whether ice receives random updates for light-driven melting checks.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }
}
