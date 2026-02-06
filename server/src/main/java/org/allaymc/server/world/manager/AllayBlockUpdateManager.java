package org.allaymc.server.world.manager;

import io.netty.util.internal.PlatformDependent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.NeighborUpdate;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.eventbus.event.block.BlockNeighborUpdateEvent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.manager.BlockUpdateManager;
import org.joml.Vector3ic;

import java.util.Queue;

/**
 * @author daoge_cmd
 */
@Slf4j
@RequiredArgsConstructor
public class AllayBlockUpdateManager implements BlockUpdateManager {
    public static final int MAX_NEIGHBOR_UPDATE_PER_TICK = 65535;

    protected final Dimension dimension;
    protected final Queue<NeighborUpdate> neighborUpdates = PlatformDependent.newMpscQueue();

    public void tick() {
        tickNeighborUpdates();
    }

    @Override
    public void scheduleBlockUpdateInDelay(Vector3ic pos, long delay) {
        scheduleBlockUpdate(pos, dimension.getWorld().getTick() + delay);
    }

    @Override
    public void scheduleBlockUpdate(Vector3ic pos, long time) {
        var chunk = dimension.getChunkManager().getChunkByDimensionPos(pos.x(), pos.z());
        if (chunk == null) {
            log.warn("Failed to schedule block update at {} because chunk is not loaded", pos);
            return;
        }

        chunk.addScheduledUpdate(pos.x() & 15, pos.y(), pos.z() & 15, time);
    }

    @Override
    public void scheduleRandomBlockUpdateInDelay(Vector3ic pos, long delay) {
        scheduleBlockUpdateInDelay(pos, (long) ((float) delay / (float) dimension.getWorld().getWorldData().<Integer>getGameRuleValue(GameRule.RANDOM_TICK_SPEED)));
    }

    @Override
    public boolean hasScheduledBlockUpdate(Vector3ic pos) {
        var chunk = dimension.getChunkManager().getChunkByDimensionPos(pos.x(), pos.z());
        if (chunk == null) {
            return false;
        }

        return chunk.hasScheduledUpdate(pos.x() & 15, pos.y(), pos.z() & 15);
    }

    @Override
    public void neighborBlockUpdate(Vector3ic pos, Vector3ic changedNeighbour, BlockFace blockFace, BlockState oldNeighborState) {
        neighborUpdates.add(new NeighborUpdate(pos, changedNeighbour, blockFace, oldNeighborState));
    }

    protected void tickNeighborUpdates() {
        int count = 0;
        // There may be new neighbor updates being added into the queue during the
        // loop, however these updates should be processed in the next tick.
        int initialNeighborUpdateCount = neighborUpdates.size();
        NeighborUpdate update;
        while (count < initialNeighborUpdateCount && count < MAX_NEIGHBOR_UPDATE_PER_TICK && (update = neighborUpdates.poll()) != null) {
            var blockFace = update.blockFace();
            var pos = update.pos();
            var neighborPos = update.neighborPos();
            var oldNeighborState = update.oldNeighborState();

            var layer0 = dimension.getBlockState(pos);
            var layer1 = dimension.getBlockState(pos, 1);

            var block0 = new Block(layer0, new Position3i(pos, dimension), 0);
            var neighborBlock0 = new Block(dimension.getBlockState(neighborPos), new Position3i(neighborPos, dimension), 0);

            var context0 = new NeighborUpdate(block0, neighborBlock0, blockFace, oldNeighborState);
            if (!callNeighborUpdateEvent(context0)) {
                return;
            }

            layer0.getBehavior().onNeighborUpdate(context0);

            // Only update second layer block if it's a liquid block for better performance,
            // because only liquid blocks need to be updated in the second layer.
            if (layer1.getBehavior() instanceof BlockLiquidBehavior) {
                var block1 = new Block(layer1, new Position3i(pos, dimension), 1);

                var context1 = new NeighborUpdate(block1, neighborBlock0, blockFace, oldNeighborState);
                if (!callNeighborUpdateEvent(context1)) {
                    return;
                }

                layer1.getBehavior().onNeighborUpdate(context1);
            }

            count++;
        }
    }

    protected boolean callNeighborUpdateEvent(NeighborUpdate context) {
        return new BlockNeighborUpdateEvent(context.block(), context.neighbor(), context.face(), context.oldNeighborState()).call();
    }

    protected record NeighborUpdate(Vector3ic pos, Vector3ic neighborPos, BlockFace blockFace, BlockState oldNeighborState) {
    }
}
