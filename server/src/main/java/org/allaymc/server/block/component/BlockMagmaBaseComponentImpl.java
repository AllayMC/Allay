package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * {@code BlockMagmaBaseComponentImpl} refreshes downward bubble columns above magma
 * and applies contact damage to living entities. Placement and upper-neighbor changes
 * trigger column maintenance, while sneaking players bypass the one-point magma
 * attack applied to other living contacts.
 *
 * @author IWareQ
 */
public class BlockMagmaBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the magma block behavior for the supplied block type.
     */
    public BlockMagmaBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Forwards placement and refreshes bubble columns starting above the new magma
     * block. The incoming state uses the old snapshot's position and layer for the
     * column update.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        BlockBubbleColumnBaseComponentImpl.updateAbove(new Block(newBlockState, oldBlock.getPosition(), oldBlock.getLayer()));
    }

    /**
     * Forwards the neighbor event and refreshes columns only for upper-neighbor
     * changes. Other faces leave column maintenance unchanged, while the inherited
     * dispatch still reaches companion component handlers.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.UP) {
            BlockBubbleColumnBaseComponentImpl.updateAbove(block);
        }
    }

    /**
     * Applies one point of magma damage to a colliding living entity. Sneaking
     * players bypass the attack, and nonliving entities receive no damage from this
     * collision hook.
     */
    @Override
    public void onCollideWithEntity(Block block, Entity entity) {
        if (entity instanceof EntityLiving living) {
            if (entity instanceof EntityPlayer player && player.isSneaking()) {
                return;
            }

            living.attack(DamageContainer.magma(1));
        }
    }
}
