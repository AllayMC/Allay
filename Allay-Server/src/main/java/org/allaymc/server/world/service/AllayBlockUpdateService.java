package org.allaymc.server.world.service;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.service.BlockUpdateService;
import org.joml.Vector3ic;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AllayBlockUpdateService implements BlockUpdateService {
    public static final int MAX_NU_PER_TICK = 65535;
    protected final Dimension dimension;
    protected final Map<Vector3ic, Long> scheduledUpdates;
    protected final Queue<NeighborUpdate> neighborUpdates;

    public AllayBlockUpdateService(Dimension dimension) {
        this.dimension = dimension;
        this.scheduledUpdates = new ConcurrentHashMap<>();
        this.neighborUpdates = new LinkedList<>();
    }

    @Override
    public void tick(long tick) {
        ArrayList<Vector3ic> positions = new ArrayList<>(scheduledUpdates.size() / 4);
        for (var entry : scheduledUpdates.entrySet()) {
            if (entry.getValue() <= tick) {
                positions.add(entry.getKey());
                scheduledUpdates.remove(entry.getKey());
            }
        }
        for (var p : positions) {
            BlockState layer0 = dimension.getBlockState(p);
            BlockState layer1 = dimension.getBlockState(p, 1);
            BlockStateWithPos b0 = new BlockStateWithPos(layer0, new Position3i(p, dimension), 0);
            layer0.getBehavior().onScheduledUpdate(b0);
            if (layer1.getBlockAttributes().isLiquid()) {
                BlockStateWithPos b1 = new BlockStateWithPos(layer1, new Position3i(p, dimension), 1);
                layer1.getBehavior().onScheduledUpdate(b1);
            }
        }
        int c = 0;
        while (!neighborUpdates.isEmpty() && c < MAX_NU_PER_TICK) {
            var u = neighborUpdates.poll();
            var pos = u.pos;
            var neighborPos = u.neighborPos;
            var blockFace = u.blockFace;
            BlockState layer0 = dimension.getBlockState(pos);
            BlockState layer1 = dimension.getBlockState(pos, 1);
            layer0.getBehavior().onNeighborUpdate(pos, neighborPos, blockFace, dimension);
            if (layer1.getBlockAttributes().isLiquid()) {
                layer1.getBehavior().onNeighborUpdate(pos, neighborPos, blockFace, dimension);
            }
            c++;
        }
    }

    @Override
    public void scheduleBlockUpdate(Vector3ic pos, Duration delay) {
        scheduledUpdates.putIfAbsent(pos, dimension.getWorld().getTick() + delay.toNanos() / 50000000);
    }

    @Override
    public void neighborBlockUpdate(Vector3ic pos, Vector3ic neighborPos, BlockFace blockFace) {
        neighborUpdates.add(new NeighborUpdate(pos, neighborPos, blockFace));
    }

    protected record NeighborUpdate(Vector3ic pos, Vector3ic neighborPos, BlockFace blockFace) {
    }
}
