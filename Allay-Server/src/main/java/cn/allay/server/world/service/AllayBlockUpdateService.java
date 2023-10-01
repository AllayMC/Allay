package cn.allay.server.world.service;

import cn.allay.api.block.data.BlockFace;
import cn.allay.api.block.data.BlockStateWithPos;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.math.position.Position3i;
import cn.allay.api.world.World;
import cn.allay.api.world.service.BlockUpdateService;
import com.google.common.collect.Sets;
import org.joml.Vector3ic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AllayBlockUpdateService implements BlockUpdateService {
    protected final World world;
    protected final Map<Vector3ic, Long> scheduledUpdates;
    protected final Set<NeighborUpdate> neighborUpdates;

    public AllayBlockUpdateService(World world) {
        this.world = world;
        this.scheduledUpdates = new ConcurrentHashMap<>();
        this.neighborUpdates = Sets.newConcurrentHashSet();
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
            BlockState layer0 = world.getBlockState(p);
            BlockState layer1 = world.getBlockState(p, 1);
            BlockStateWithPos b0 = new BlockStateWithPos(layer0, new Position3i(p, world), 0);
            layer0.getBehavior().onScheduledUpdate(b0);
            if (layer1.getBlockAttributes().isLiquid()) {
                BlockStateWithPos b1 = new BlockStateWithPos(layer1, new Position3i(p, world), 1);
                layer1.getBehavior().onScheduledUpdate(b1);
            }
        }
        for (NeighborUpdate u : neighborUpdates) {
            var pos = u.pos;
            var neighborPos = u.neighborPos;
            var blockFace = u.blockFace;
            BlockState layer0 = world.getBlockState(pos);
            BlockState layer1 = world.getBlockState(pos, 1);
            layer0.getBehavior().onNeighborUpdate(pos, neighborPos, blockFace, world);
            if (layer1.getBlockAttributes().isLiquid()) {
                layer0.getBehavior().onNeighborUpdate(pos, neighborPos, blockFace, world);
            }
        }
    }

    @Override
    public void scheduleBlockUpdate(Vector3ic pos, Duration delay) {
        scheduledUpdates.putIfAbsent(pos, world.getTick() + delay.toNanos() / 50000000);
    }

    @Override
    public void neighborBlockUpdate(Vector3ic pos, Vector3ic neighborPos, BlockFace blockFace) {
        neighborUpdates.add(new NeighborUpdate(pos, neighborPos, blockFace));
    }

    protected record NeighborUpdate(Vector3ic pos, Vector3ic neighborPos, BlockFace blockFace) {
    }
}
