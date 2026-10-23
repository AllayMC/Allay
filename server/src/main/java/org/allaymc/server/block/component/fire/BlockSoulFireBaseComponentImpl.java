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
 * {@code BlockSoulFireBaseComponentImpl} implements soul-fire support conversion and
 * living-entity contact effects. A neighbor update changes soul fire to ordinary fire when
 * neither soul sand nor soul soil remains below. Entity contact proposes eight seconds of
 * combustion and separately attempts two points of fire damage.
 *
 * @author daoge_cmd
 */
public class BlockSoulFireBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the soul fire behavior component for the supplied block type.
     */
    public BlockSoulFireBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Converts soul fire to ordinary fire after loss of soul sand or soul soil beneath it. The
     * replacement copies compatible fire properties and does not dispatch the base neighbor
     * hook or a fade event.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        var downBlockType = block.offsetPos(BlockFace.DOWN).getBlockType();
        if (downBlockType != BlockTypes.SOUL_SAND && downBlockType != BlockTypes.SOUL_SOIL) {
            block.getDimension().setBlockState(block.getPosition(), BlockTypes.FIRE.copyPropertyValuesFrom(block.getBlockState()));
        }
    }

    /**
     * Proposes eight seconds of combustion for a living entity and attempts two points of fire
     * damage. Cancelling the combustion event suppresses its duration change while the
     * independent attack still uses ordinary damage handling.
     */
    @Override
    public void onEntityInside(Block block, Entity entity) {
        if (!(entity instanceof EntityLiving living)) {
            return;
        }

        // Convert the eight-second burn duration to twenty game ticks per second.
        var combustEvent = new EntityCombustEvent(entity, EntityCombustEvent.CombusterType.BLOCK, block, 20 * 8);
        if (combustEvent.call()) {
            living.setOnFireTicks(combustEvent.getOnFireTicks());
        }

        living.attack(new DamageContainer(block, DamageType.FIRE, 2));
    }
}
