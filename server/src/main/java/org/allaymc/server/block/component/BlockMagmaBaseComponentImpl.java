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
 * @author IWareQ
 */
public class BlockMagmaBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockMagmaBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        BlockBubbleColumnBaseComponentImpl.updateAbove(new Block(newBlockState, oldBlock.getPosition(), oldBlock.getLayer()));
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.UP) {
            BlockBubbleColumnBaseComponentImpl.updateAbove(block);
        }
    }

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
