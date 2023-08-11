package cn.allay.api.world;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.client.Client;
import cn.allay.api.data.VanillaBlockTypes;
import cn.allay.api.entity.Entity;
import cn.allay.api.math.Position3ic;
import cn.allay.api.scheduler.Scheduler;
import cn.allay.api.server.Server;
import cn.allay.api.world.chunk.ChunkAccessible;
import cn.allay.api.world.chunk.ChunkService;
import cn.allay.api.world.entity.EntityPhysicsService;
import cn.allay.api.world.entity.EntityService;
import cn.allay.api.world.generator.WorldGenerator;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.LevelEventType;
import org.cloudburstmc.protocol.bedrock.packet.LevelEventPacket;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Describe a world
 * <p>
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public interface World extends ChunkAccessible {

    Logger getLogger();

    void startTick();

    Server getServer();

    Scheduler getWorldScheduler();

    GameType getWorldGameType();

    void setWorldGameType(GameType gameType);

    String getName();

    DimensionInfo getDimensionInfo();

    WorldType getWorldType();

    int getTickingRadius();

    int getViewDistance();

    Position3ic getSpawnPosition();

    void setSpawnPosition(Position3ic newSpawn);

    Difficulty getDifficulty();

    WorldGenerator getWorldGenerator();

    ChunkService getChunkService();

    EntityService getEntityService();

    EntityPhysicsService getEntityPhysicsService();

    void addEntity(Entity entity);

    void removeEntity(Entity entity);

    @Nullable
    default Entity removeEntity(long entityUniqueId) {
        return getEntityService().removeEntity(entityUniqueId);
    }

    void addClient(Client client);

    void removeClient(Client client);

    @UnmodifiableView
    Collection<Client> getClients();

    @Override
    default int maxChunkX() {
        return Integer.MAX_VALUE;
    }

    @Override
    default int maxChunkZ() {
        return Integer.MAX_VALUE;
    }

    @Override
    default int minChunkX() {
        return Integer.MIN_VALUE;
    }

    @Override
    default int minChunkZ() {
        return Integer.MIN_VALUE;
    }

    default void setBlockState(int x, int y, int z, BlockState state) {
        setBlockState(x, y, z, state, false);
    }

    default void setBlockState(int x, int y, int z, BlockState state, boolean layer) {
        setBlockState(x, y, z, state, layer, true);
    }

    default void setBlockState(int x, int y, int z, BlockState state, boolean layer, boolean send) {
        setBlockState(x, y, z, state, layer, send, true);
    }

    default void setBlockState(int x, int y, int z, BlockState state, boolean layer, boolean send, boolean update) {
        var chunk = getChunk(x >> 4, z >> 4);
        if (chunk == null) return;
        chunk.setBlockState(x & 15, y, z & 15, state, layer, send, update);
    }

    @Nullable
    default BlockState getBlockState(int x, int y, int z) {
        return getBlockState(x, y, z, false);
    }

    default BlockState getBlockStateNonNull(int x, int y, int z) {
        return getBlockStateNonNull(x, y, z, false);
    }

    default BlockState getBlockStateNonNull(int x, int y, int z, boolean layer) {
        var blockState = getBlockState(x, y, z, layer);
        if (blockState == null)
            blockState = VanillaBlockTypes.AIR_TYPE.getDefaultState();
        return blockState;
    }

    @Nullable
    default BlockState getBlockState(int x, int y, int z, boolean layer) {
        var chunk = getChunk(x >> 4, z >> 4);
        if (chunk == null)
            return null;
        return chunk.getBlockState(x & 15, y, z & 15, layer);
    }

    default BlockState[][][] getBlockStates(int x, int y, int z, @Range(from = 1, to = Integer.MAX_VALUE) int sizeX, @Range(from = 1, to = Integer.MAX_VALUE) int sizeY, @Range(from = 1, to = Integer.MAX_VALUE) int sizeZ, boolean layer) {
        BlockState[][][] blockStates = new BlockState[sizeX][sizeY][sizeZ];

        int startX = x >> 4;
        int endX = (x + sizeX - 1) >> 4;
        int startZ = z >> 4;
        int endZ = (z + sizeZ - 1) >> 4;

        for (int chunkX = startX; chunkX <= endX; chunkX++) {
            for (int chunkZ = startZ; chunkZ <= endZ; chunkZ++) {
                final int cX = chunkX << 4;
                final int cZ = chunkZ << 4;
                int localStartX = Math.max(x - cX, 0);
                int localStartZ = Math.max(z - cZ, 0);
                int localEndX = Math.min(x + sizeX - cX, 16);
                int localEndZ = Math.min(z + sizeZ - cZ, 16);

                var chunk = getChunk(chunkX, chunkZ);
                if (chunk != null) {
                    chunk.batchProcess(
                            sectionOperate -> {
                                for (int localX = localStartX; localX < localEndX; localX++) {
                                    for (int globalY = y; globalY < y + sizeY; globalY++) {
                                        for (int localZ = localStartZ; localZ < localEndZ; localZ++) {
                                            int globalX = cX + localX;
                                            int globalZ = cZ + localZ;
                                            blockStates[globalX - x][globalY - y][globalZ - z] =
                                                    sectionOperate.getBlockState(localX, globalY, localZ, layer);
                                        }
                                    }
                                }
                            },
                            null,
                            null
                    );
                }
            }
        }

        return blockStates;
    }

    @Nullable
    default BlockState[][][] getCollidingBlocks(AABBdc aabb) {
        return getCollidingBlocks(aabb, false);
    }

    @Nullable
    default BlockState[][][] getCollidingBlocks(AABBdc aabb, boolean layer) {
        return getCollidingBlocks(aabb, layer, false);
    }

    @Nullable
    default BlockState[][][] getCollidingBlocks(AABBdc aabb, boolean layer, boolean ignoreCollision) {
        int maxX = (int) Math.ceil(aabb.maxX());
        int maxY = (int) Math.ceil(aabb.maxY());
        int maxZ = (int) Math.ceil(aabb.maxZ());
        int minX = (int) Math.floor(aabb.minX());
        int minY = (int) Math.floor(aabb.minY());
        int minZ = (int) Math.floor(aabb.minZ());
        var blockStates = getBlockStates(minX, minY, minZ, maxX - minX, maxY - minY, maxZ - minZ, layer);
        boolean notEmpty = false;
        if (!ignoreCollision) {
            //过滤掉没有碰撞的方块
            for (int x = 0; x < blockStates.length; x++) {
                for (int y = 0; y < blockStates[x].length; y++) {
                    for (int z = 0; z < blockStates[x][y].length; z++) {
                        var blockState = blockStates[x][y][z];
                        if (blockState == null) continue;
                        var attributes = blockState.blockType().getBlockBehavior().getBlockAttributes(blockState);
                        if (!attributes.hasCollision() || !aabb.intersectsAABB(attributes.aabbCollision().translate(minX + x, minY + y, minZ + z, new AABBd()))) {
                            blockStates[x][y][z] = null;
                        } else {
                            notEmpty = true;
                        }
                    }
                }
            }
        }
        return notEmpty ? blockStates : null;
    }

    default void sendLevelEventPacket(Vector3i pos, LevelEventType blockStopBreak, int data) {
        var chunk = getChunk(pos.getX() >> 4, pos.getZ() >> 4);
        if (chunk == null) return;
        var levelEventPacket = new LevelEventPacket();
        levelEventPacket.setPosition(pos.toFloat());
        levelEventPacket.setType(blockStopBreak);
        levelEventPacket.setData(data);
        chunk.sendChunkPacket(levelEventPacket);
    }
}
