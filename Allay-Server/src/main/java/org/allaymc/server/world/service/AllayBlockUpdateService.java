package org.allaymc.server.world.service;

import lombok.RequiredArgsConstructor;
import org.allaymc.api.block.component.BlockLiquidComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.eventbus.event.block.BlockNeighborUpdateEvent;
import org.allaymc.api.eventbus.event.block.BlockScheduleUpdateEvent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.service.BlockUpdateService;
import org.joml.Vector3ic;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class AllayBlockUpdateService implements BlockUpdateService {
    public static final int MAX_NU_PER_TICK = 65535;

    protected final Dimension dimension;
    protected final Map<Vector3ic, Long> scheduledUpdates = new ConcurrentHashMap<>();
    protected final Queue<NeighborUpdate> neighborUpdates = new LinkedList<>();

    public void tick(long tick) {
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

            if (callScheduleUpdateEvent(block0)) {
                return;
            }

            layer0.getBehavior().onScheduledUpdate(block0);

            if (layer1.getBehavior() instanceof BlockLiquidComponent) {
                var block1 = new BlockStateWithPos(layer1, new Position3i(pos, dimension), 1);

                if (callScheduleUpdateEvent(block1)) {
                    return;
                }

                layer1.getBehavior().onScheduledUpdate(block1);
            }
        });

        int count = 0;
        while (!neighborUpdates.isEmpty() && count < MAX_NU_PER_TICK) {
            var update = neighborUpdates.poll();
            var blockFace = update.blockFace();
            var pos = update.pos();
            var neighborPos = update.neighborPos();

            var layer0 = dimension.getBlockState(pos);
            var layer1 = dimension.getBlockState(pos, 1);

            var block0 = new BlockStateWithPos(layer0, new Position3i(pos, dimension), 0);
            var neighborBlock0 = new BlockStateWithPos(dimension.getBlockState(neighborPos), new Position3i(neighborPos, dimension), 0);

            if (callNeighborUpdateEvent(block0, neighborBlock0, blockFace)) {
                return;
            }

            layer0.getBehavior().onNeighborUpdate(
                    block0,
                    neighborBlock0,
                    blockFace
            );

            if (layer1.getBehavior() instanceof BlockLiquidComponent) {
                var block1 = new BlockStateWithPos(layer1, new Position3i(pos, dimension), 1);

                if (callNeighborUpdateEvent(block1, neighborBlock0, blockFace)) {
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
        var event = new BlockScheduleUpdateEvent(block);
        event.call();
        return event.isCancelled();
    }

    protected boolean callNeighborUpdateEvent(BlockStateWithPos block, BlockStateWithPos neighborBlock, BlockFace blockFace) {
        var event = new BlockNeighborUpdateEvent(block, neighborBlock, blockFace);
        event.call();
        return event.isCancelled();
    }

    @Override
    public void scheduleBlockUpdate(Vector3ic pos, Duration delay) {
        scheduledUpdates.putIfAbsent(pos, dimension.getWorld().getTick() + delay.toNanos() / 50_000_000);
    }

    @Override
    public void neighborBlockUpdate(Vector3ic pos, Vector3ic neighborPos, BlockFace blockFace) {
        neighborUpdates.add(new NeighborUpdate(pos, neighborPos, blockFace));
    }

    protected record NeighborUpdate(Vector3ic pos, Vector3ic neighborPos, BlockFace blockFace) {}
}
