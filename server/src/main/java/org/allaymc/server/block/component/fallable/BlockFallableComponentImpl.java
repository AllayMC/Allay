package org.allaymc.server.block.component.fallable;

import lombok.AllArgsConstructor;
import org.allaymc.api.block.component.BlockFallableComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityFallingBlock;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.block.BlockFallEvent;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.CustomSound;
import org.allaymc.server.block.component.event.CBlockAfterPlacedEvent;
import org.allaymc.server.block.component.event.CBlockOnNeighborUpdateEvent;
import org.joml.Vector3ic;

/**
 * @author IWareQ
 */
@AllArgsConstructor
public class BlockFallableComponentImpl implements BlockFallableComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_fallable_component");

    protected final String landingSound;

    @Override
    public void onLanded(Location3d location, double fallDistance, BlockState blockState) {
        location.dimension().setBlockState(location, blockState);
        location.dimension().addSound(location, new CustomSound(landingSound));
    }

    @EventHandler
    public void onBlockOnNeighborUpdate(CBlockOnNeighborUpdateEvent event) {
        var current = event.getCurrent();
        tryFall(current.getDimension(), current.getPosition(), current.getBlockState());
    }

    @EventHandler
    protected void onBlockAfterPlaced(CBlockAfterPlacedEvent event) {
        var oldBlockState = event.getOldBlock();
        tryFall(oldBlockState.getDimension(), oldBlockState.getPosition(), event.getNewBlockState());
    }

    protected void tryFall(Dimension dimension, Vector3ic pos, BlockState blockState) {
        if (!shouldFall(dimension, pos)) return;

        var event = new BlockFallEvent(new Block(blockState, new Position3i(pos, dimension), 0));
        if (!event.call()) {
            return;
        }

        dimension.getEntityManager().addEntity(createFallingBlock(dimension, pos, blockState), () -> {
            // Set the block state to air immediately if the falling block
            // entity will be spawned, so multiple neighbor updates won't
            // cause the falling block entity being spawned multiple times
            dimension.setBlockState(pos.x(), pos.y(), pos.z(), BlockTypes.AIR.getDefaultState());
        });
    }

    protected boolean shouldFall(Dimension dimension, Vector3ic pos) {
        var down0 = dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType();
        var down1 = dimension.getBlockState(BlockFace.DOWN.offsetPos(pos), 1).getBlockType();
        return down0.hasBlockTag(BlockTags.REPLACEABLE) ||
               (down0 == BlockTypes.BUBBLE_COLUMN && down1.getBlockBehavior() instanceof BlockLiquidBehavior);
    }

    protected EntityFallingBlock createFallingBlock(Dimension dimension, Vector3ic pos, BlockState blockState) {
        var fallingBlock = EntityTypes.FALLING_BLOCK.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(pos.x() + 0.5f, pos.y(), pos.z() + 0.5f)
                        .build()
        );
        fallingBlock.setBlockState(blockState);
        return fallingBlock;
    }
}
