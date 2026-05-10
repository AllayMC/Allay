package org.allaymc.server.block.component.fire;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.eventbus.event.entity.EntityCombustEvent;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

/**
 * @author daoge_cmd
 */
public class BlockSoulFireBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockSoulFireBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        var downBlockType = block.offsetPos(BlockFace.DOWN).getBlockType();
        if (downBlockType != BlockTypes.SOUL_SAND && downBlockType != BlockTypes.SOUL_SOIL) {
            block.getDimension().setBlockState(block.getPosition(), BlockTypes.FIRE.copyPropertyValuesFrom(block.getBlockState()));
        }
    }

    @Override
    public void onEntityInside(Block block, Entity entity) {
        if (!(entity instanceof EntityLiving living)) {
            return;
        }

        var combustEvent = new EntityCombustEvent(entity, EntityCombustEvent.CombusterType.BLOCK, block, 20 * 8);
        if (combustEvent.call()) {
            living.setOnFireTicks(combustEvent.getOnFireTicks());
        }

        living.attack(new DamageContainer(block, DamageType.FIRE, 2));
    }
}
