package org.allaymc.server.world.service;

import io.netty.util.internal.PlatformDependent;
import lombok.RequiredArgsConstructor;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.eventbus.event.block.BlockNeighborUpdateEvent;
import org.allaymc.api.eventbus.event.block.BlockScheduleUpdateEvent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.service.BlockUpdateService;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author daoge_cmd
 */
@RequiredArgsConstructor
public class AllayBlockUpdateService implements BlockUpdateService {
    public static final int MAX_NEIGHBOR_UPDATE_PER_TICK = 65535;

    protected final Dimension dimension;
    protected final Map<Vector3ic, Long> scheduledUpdates = new ConcurrentHashMap<>();
    protected final Queue<NeighborUpdate> neighborUpdates = PlatformDependent.newMpscQueue();

    public void tick(long tick) {
        tickScheduledUpdates(tick);
        tickNeighborUpdates();
    }

    protected void tickScheduledUpdates(long tick) {
        List<Vector3ic> positions = new ArrayList<>(scheduledUpdates.size() / 4);
        for (var entry : scheduledUpdates.entrySet()) {
            if (entry.getValue() <= tick) {
                positions.add(entry.getKey());
                scheduledUpdates.remove(entry.getKey());
            }
        }

        positions.forEach(pos -> {
            var layer0 = dimension.getBlockState(pos);
            var layer1 = dimension.getBlockState(pos, 1);
            var block0 = new BlockStateWithPos(layer0, new Position3i(pos, dimension), 0);

            if (!callScheduleUpdateEvent(block0)) {
                return;
            }

            layer0.getBehavior().onScheduledUpdate(block0);

            // Only update second layer block if it's a liquid block for better performance,
            // because only liquid blocks need to be updated in the second layer.
            if (layer1.getBehavior() instanceof BlockLiquidBehavior) {
                var block1 = new BlockStateWithPos(layer1, new Position3i(pos, dimension), 1);

                if (!callScheduleUpdateEvent(block1)) {
                    return;
                }

                layer1.getBehavior().onScheduledUpdate(block1);
            }
        });
    }

    protected void tickNeighborUpdates() {
        int count = 0;
        while (!neighborUpdates.isEmpty() && count < MAX_NEIGHBOR_UPDATE_PER_TICK) {
            var update = neighborUpdates.poll();
            var blockFace = update.blockFace();
            var pos = update.pos();
            var neighborPos = update.neighborPos();

            var layer0 = dimension.getBlockState(pos);
            var layer1 = dimension.getBlockState(pos, 1);

            var block0 = new BlockStateWithPos(layer0, new Position3i(pos, dimension), 0);
            var neighborBlock0 = new BlockStateWithPos(dimension.getBlockState(neighborPos), new Position3i(neighborPos, dimension), 0);

            if (!callNeighborUpdateEvent(block0, neighborBlock0, blockFace)) {
                return;
            }

            layer0.getBehavior().onNeighborUpdate(
                    block0,
                    neighborBlock0,
                    blockFace
            );

            // Only update second layer block if it's a liquid block for better performance,
            // because only liquid blocks need to be updated in the second layer.
            if (layer1.getBehavior() instanceof BlockLiquidBehavior) {
                var block1 = new BlockStateWithPos(layer1, new Position3i(pos, dimension), 1);

                if (!callNeighborUpdateEvent(block1, neighborBlock0, blockFace)) {
                    return;
                }

                layer1.getBehavior().onNeighborUpdate(
                        block1,
                        neighborBlock0,
                        blockFace
                );
            }

            count++;
        }
    }

    protected boolean callScheduleUpdateEvent(BlockStateWithPos block) {
        return new BlockScheduleUpdateEvent(block).call();
    }

    protected boolean callNeighborUpdateEvent(BlockStateWithPos block, BlockStateWithPos neighborBlock, BlockFace blockFace) {
        return new BlockNeighborUpdateEvent(block, neighborBlock, blockFace).call();
    }

    @Override
    public void scheduleBlockUpdate(Vector3ic pos, long delay) {
        scheduledUpdates.putIfAbsent(pos, dimension.getWorld().getTick() + delay);
    }

    @Override
    public void neighborBlockUpdate(Vector3ic pos, Vector3ic changedNeighbour, BlockFace blockFace) {
        neighborUpdates.add(new NeighborUpdate(pos, changedNeighbour, blockFace));
    }

    protected record NeighborUpdate(Vector3ic pos, Vector3ic neighborPos, BlockFace blockFace) {}
}
