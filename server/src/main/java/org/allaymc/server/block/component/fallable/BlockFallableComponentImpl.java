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
 * {@code BlockFallableComponentImpl} turns unsupported blocks into falling entities
 * and places their states again on landing. Neighbor and placement component events
 * invoke the same support test, which accepts replaceable lower blocks and bubble
 * columns retaining liquid in their second layer. The configured landing sound
 * accompanies the ordinary landing state write.
 *
 * <p>
 * Falling requests use deferred entity addition and remove the source block in its
 * callback. Eligibility or entity-state rejection before queueing skips that callback
 * and leaves the source unchanged. For queued requests, the callback follows the
 * addition helper's return, including a duplicate-runtime-ID return that inserts no
 * new entity. The source remains visible until callback execution, so repeated
 * updates can request additional entities during that interval. Source removal and
 * entity membership are not atomic, and failures do not roll back either
 * representation.
 *
 * @author IWareQ
 */
@AllArgsConstructor
public class BlockFallableComponentImpl implements BlockFallableComponent {

    /**
     * {@code IDENTIFIER} names the generic falling-block implementation when
     * providers and dependency injection identify its component capability.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_fallable_component");

    /**
     * Configured landing sound name.
     */
    protected final String landingSound;

    /**
     * Attempts to place the supplied falling state and plays the configured landing
     * sound. The state-write result is ignored, so the sound still occurs when the
     * dimension rejects placement.
     */
    @Override
    public void onLanded(Location3d location, double fallDistance, BlockState blockState) {
        location.dimension().setBlockState(location, blockState);
        location.dimension().addSound(location, new CustomSound(landingSound));
    }

    /**
     * Tests whether the updated block should begin falling using its current
     * snapshot. The handler forwards the snapshot's dimension, position, and state
     * into the shared deferred falling path.
     */
    @EventHandler
    public void onBlockOnNeighborUpdateContext(CBlockOnNeighborUpdateEvent event) {
        var current = event.getCurrent();
        tryFall(current.getDimension(), current.getPosition(), current.getBlockState());
    }

    /**
     * Tests whether the newly placed state should begin falling at the old snapshot's
     * position. The incoming state supplies the falling representation while the
     * position retains its dimension context.
     */
    @EventHandler
    protected void onBlockAfterPlaced(CBlockAfterPlacedEvent event) {
        var oldBlockState = event.getOldBlock();
        tryFall(oldBlockState.getDimension(), oldBlockState.getPosition(), event.getNewBlockState());
    }

    /**
     * Requests a falling entity when the lower support test permits falling. The
     * block fall notification runs before entity construction and deferred addition;
     * this notification type does not support cancellation.
     *
     * <p>
     * Eligibility or entity-state rejection before queueing leaves the source intact.
     * For queued requests, the callback clears the source after the addition helper
     * returns, including a duplicate-runtime-ID early return that adds no new member.
     * Pending requests leave the source visible until that callback, and the clear
     * result is ignored, so neither entity admission nor exclusive representation is
     * guaranteed.
     */
    protected void tryFall(Dimension dimension, Vector3ic pos, BlockState blockState) {
        if (!shouldFall(dimension, pos)) return;

        var event = new BlockFallEvent(new Block(blockState, new Position3i(pos, dimension), 0));
        if (!event.call()) {
            return;
        }

        dimension.getEntityManager().addEntity(createFallingBlock(dimension, pos, blockState), () -> {
            // Clear the source after the addition helper returns, even when a
            // duplicate runtime ID skipped insertion. Rejection before queueing
            // leaves this callback unrun.
            dimension.setBlockState(pos.x(), pos.y(), pos.z(), BlockTypes.AIR.getDefaultState());
        });
    }

    /**
     * Returns whether lower support permits this block to fall. Replaceable
     * foreground blocks qualify, as do foreground bubble columns whose second-layer
     * type still exposes liquid behavior for the falling block.
     */
    protected boolean shouldFall(Dimension dimension, Vector3ic pos) {
        var down0 = dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType();
        var down1 = dimension.getBlockState(BlockFace.DOWN.offsetPos(pos), 1).getBlockType();
        return down0.hasBlockTag(BlockTags.REPLACEABLE) ||
               (down0 == BlockTypes.BUBBLE_COLUMN && down1.getBlockBehavior() instanceof BlockLiquidBehavior);
    }

    /**
     * Creates an unregistered falling entity centered horizontally at the supplied
     * block position. The entity retains the supplied block state for landing, and
     * the caller submits it to the entity manager separately.
     */
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
