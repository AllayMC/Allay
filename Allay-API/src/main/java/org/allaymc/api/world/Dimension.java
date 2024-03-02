package org.allaymc.api.world;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.utils.MathUtils;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.service.BlockUpdateService;
import org.allaymc.api.world.service.ChunkService;
import org.allaymc.api.world.service.EntityPhysicsService;
import org.allaymc.api.world.service.EntityService;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.LevelEventType;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.LevelEventPacket;
import org.cloudburstmc.protocol.bedrock.packet.UpdateBlockPacket;
import org.jetbrains.annotations.Unmodifiable;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3fc;
import org.joml.Vector3ic;
import org.joml.primitives.AABBfc;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import static org.allaymc.api.block.type.BlockTypes.AIR_TYPE;

/**
 * Allay Project 11/12/2023
 *
 * @author Cool_Loong
 */
public interface Dimension {

    void tick(long currentTick);

    void close();

    WorldGenerator getWorldGenerator();

    ChunkService getChunkService();

    EntityPhysicsService getEntityPhysicsService();

    BlockUpdateService getBlockUpdateService();

    EntityService getEntityService();

    DimensionInfo getDimensionInfo();

    World getWorld();

    @Unmodifiable
    default Map<Long, Entity> getEntities() {
        var entities = new Long2ObjectOpenHashMap<Entity>();
        getChunkService().forEachLoadedChunks(chunk -> entities.putAll(chunk.getEntities()));
        return Collections.unmodifiableMap(entities);
    }

    default int getEntityCount() {
        return getChunkService().getLoadedChunks().stream().mapToInt(chunk -> chunk.getEntities().size()).sum();
    }

    default Entity getEntityByRuntimeId(long runtimeId) {
        for (var chunk : getChunkService().getLoadedChunks()) {
            var entity = chunk.getEntities().get(runtimeId);
            if (entity != null) {
                return entity;
            }
        }
        return null;
    }

    default void addPlayer(EntityPlayer player) {
        addPlayer(player, () -> {});
    }

    void addPlayer(EntityPlayer player, Runnable runnable);

    default void removePlayer(EntityPlayer player) {
        removePlayer(player, () -> {});
    }

    void removePlayer(EntityPlayer player, Runnable runnable);

    @UnmodifiableView
    Set<EntityPlayer> getPlayers();

    static UpdateBlockPacket createBlockUpdatePacket(BlockState blockState, int x, int y, int z, int layer) {
        var updateBlockPacket = new UpdateBlockPacket();
        updateBlockPacket.setBlockPosition(Vector3i.from(x, y, z));
        updateBlockPacket.setDefinition(blockState.toNetworkBlockDefinitionRuntime());
        updateBlockPacket.setDataLayer(layer);
        updateBlockPacket.getFlags().addAll(UpdateBlockPacket.FLAG_ALL_PRIORITY);
        return updateBlockPacket;
    }

    default void setBlockState(int x, int y, int z, BlockState blockState) {
        setBlockState(x, y, z, blockState, 0, true, true);
    }

    default void setBlockState(Vector3ic pos, BlockState blockState) {
        setBlockState(pos, blockState, 0, true, true);
    }

    default void setBlockState(int x, int y, int z, BlockState blockState, int layer) {
        setBlockState(x, y, z, blockState, layer, true, true);
    }

    default void setBlockState(Vector3ic pos, BlockState blockState, int layer) {
        setBlockState(pos, blockState, layer, true, true);
    }

    default void setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send) {
        setBlockState(x, y, z, blockState, layer, send, true);
    }

    default void setBlockState(Vector3ic pos, BlockState blockState, int layer, boolean send) {
        setBlockState(pos, blockState, layer, send, true);
    }

    default void setBlockState(Vector3ic pos, BlockState blockState, int layer, boolean send, boolean update) {
        setBlockState(pos.x(), pos.y(), pos.z(), blockState, layer, send, update);
    }

    default void setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send, boolean update) {
        var chunk = getChunkService().getChunkByLevelPos(x, z);
        if (chunk == null) {
            chunk = getChunkService().getChunkImmediately(x >> 4, z >> 4);
        }
        int xIndex = x & 15;
        int zIndex = z & 15;
        BlockState oldBlockState = chunk.getBlockState(xIndex, y, zIndex, layer);

        Position3i blockPos = new Position3i(x, y, z, this);
        blockState.getBehavior().onPlace(new BlockStateWithPos(oldBlockState, blockPos, layer), blockState);
        oldBlockState.getBehavior().onReplace(new BlockStateWithPos(oldBlockState, blockPos, layer), blockState);
        chunk.setBlockState(xIndex, y, zIndex, blockState, layer);

        if (update) {
            updateAround(x, y, z);
        }
        if (send) {
            chunk.sendChunkPacket(createBlockUpdatePacket(blockState, x, y, z, layer));
        }
    }

    default void sendBlockUpdateTo(BlockState blockState, int x, int y, int z, int layer, EntityPlayer player) {
        player.sendPacket(createBlockUpdatePacket(blockState, x, y, z, layer));
    }

    default BlockState getBlockState(Vector3ic pos) {
        return getBlockState(pos.x(), pos.y(), pos.z(), 0);
    }

    default BlockState getBlockState(Vector3ic pos, int layer) {
        return getBlockState(pos.x(), pos.y(), pos.z(), layer);
    }

    default BlockState getBlockState(int x, int y, int z) {
        return getBlockState(x, y, z, 0);
    }

    default BlockState getBlockState(int x, int y, int z, int layer) {
        if (y < this.getDimensionInfo().minHeight() || y > getDimensionInfo().maxHeight())
            return AIR_TYPE.getDefaultState();
        var chunk = getChunkService().getChunkByLevelPos(x, z);
        if (chunk == null) {
            chunk = getChunkService().getChunkImmediately(x >> 4, z >> 4);
        }
        return chunk.getBlockState(x & 15, y, z & 15, layer);
    }

    default BlockState[][][] getBlockStates(int x, int y, int z, int sizeX, int sizeY, int sizeZ, int layer) {
        if (sizeX < 1 || sizeY < 1 || sizeZ < 1) return Utils.EMPTY_BLOCK_STATE_ARRAY_3D;
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

                var chunk = getChunkService().getChunk(chunkX, chunkZ);
                if (chunk != null) {
                    chunk.batchProcess(c -> {
                        for (int localX = localStartX; localX < localEndX; localX++) {
                            for (int globalY = y; globalY < y + sizeY; globalY++) {
                                for (int localZ = localStartZ; localZ < localEndZ; localZ++) {
                                    int globalX = cX + localX;
                                    int globalZ = cZ + localZ;
                                    blockStates[globalX - x][globalY - y][globalZ - z] = c.getBlockState(localX, globalY, localZ, layer);
                                }
                            }
                        }
                    });
                } else {
                    var air = AIR_TYPE.getDefaultState();
                    for (int localX = localStartX; localX < localEndX; localX++) {
                        for (int globalY = y; globalY < y + sizeY; globalY++) {
                            for (int localZ = localStartZ; localZ < localEndZ; localZ++) {
                                int globalX = cX + localX;
                                int globalZ = cZ + localZ;
                                blockStates[globalX - x][globalY - y][globalZ - z] = air;
                            }
                        }
                    }
                }
            }
        }
        return blockStates;
    }


    default BlockState[][][] getCollidingBlocks(AABBfc aabb) {
        return getCollidingBlocks(aabb, 0);
    }

    default BlockState[][][] getCollidingBlocks(AABBfc aabb, int layer) {
        return getCollidingBlocks(aabb, layer, false);
    }

    default BlockState[][][] getCollidingBlocks(AABBfc aabb, int layer, boolean ignoreCollision) {
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
                        var attributes = blockState.getBlockType().getBlockBehavior().getBlockAttributes(blockState);
                        if (!attributes.hasCollision() || !attributes.voxelShape().translate(minX + x, minY + y, minZ + z).intersectsAABB(aabb)) {
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

    default void sendLevelEventPacket(Vector3i pos, LevelEventType levelEventType, int data) {
        var chunk = getChunkService().getChunk(pos.getX() >> 4, pos.getZ() >> 4);
        if (chunk == null) return;
        var levelEventPacket = new LevelEventPacket();
        levelEventPacket.setPosition(pos.toFloat());
        levelEventPacket.setType(levelEventType);
        levelEventPacket.setData(data);
        chunk.sendChunkPacket(levelEventPacket);
    }

    default void updateAroundIgnoreFace(int x, int y, int z, BlockFace... ignoreFaces) {
        updateAroundIgnoreFace(new org.joml.Vector3i(x, y, z), ignoreFaces);
    }

    default void updateAroundIgnoreFace(Vector3ic pos, BlockFace... ignoreFaces) {
        for (var face : BlockFace.values()) {
            if (ignoreFaces != null && ignoreFaces.length > 0) {
                var ignore = false;
                for (var ignoreFace : ignoreFaces) {
                    if (ignoreFace == face) {
                        ignore = true;
                        break;
                    }
                }
                if (ignore) {
                    continue;
                }
            }
            updateAtFace(pos, face);
        }
    }

    default void updateAround(int x, int y, int z) {
        for (var face : BlockFace.values()) {
            updateAtFace(x, y, z, face);
        }
    }

    default void updateAround(Vector3ic pos) {
        for (var face : BlockFace.values()) {
            updateAtFace(pos, face);
        }
    }

    default void updateAtFace(int x, int y, int z, BlockFace face) {
        updateAtFace(new org.joml.Vector3i(x, y, z), face);
    }

    default void updateAtFace(Vector3ic pos, BlockFace face) {
        var offsetPos = face.offsetPos(pos);
        getBlockUpdateService().neighborBlockUpdate(offsetPos, pos, face.opposite());
    }

    /**
     * Traverse all the blockstate around a pos,In the order of <br>DOWN->UP>NORTH->SOUTH->WEST->EAST
     *
     * @param pos The specified pos
     * @return An array of neighbour blockstate
     */
    default BlockStateWithPos[] getNeighboursBlockState(Vector3ic pos) {
        return getNeighboursBlockState(pos.x(), pos.y(), pos.z());
    }

    /**
     * @see #getNeighboursBlockState(org.joml.Vector3ic)
     */
    default BlockStateWithPos[] getNeighboursBlockState(int x, int y, int z) {
        BlockStateWithPos[] result = new BlockStateWithPos[6];
        for (int i = 0; i < BlockFace.values().length; i++) {
            var offsetPos = BlockFace.values()[i].offsetPos(x, y, z);
            var neighborBlockState = getBlockState(offsetPos.x(), offsetPos.y(), offsetPos.z(), 0);
            result[i] = new BlockStateWithPos(neighborBlockState, new Position3i(offsetPos, this), 0);
        }
        return result;
    }

    default boolean isYInRange(float y) {
        return y >= getDimensionInfo().minHeight() && y <= getDimensionInfo().maxHeight();
    }

    default boolean isInWorld(float x, float y, float z) {
        return isYInRange(y) && getChunkService().isChunkLoaded((int) x >> 4, (int) z >> 4);
    }

    default boolean isAABBInWorld(AABBfc aabb) {
        return isInWorld(aabb.maxX(), aabb.maxY(), aabb.maxZ()) && isInWorld(aabb.minX(), aabb.minY(), aabb.minZ());
    }

    default BlockEntity getBlockEntity(int x, int y, int z) {
        var chunk = getChunkService().getChunkByLevelPos(x, z);
        if (chunk == null) {
            chunk = getChunkService().getChunkImmediately(x >> 4, z >> 4);
        }
        return chunk.getBlockEntity(x & 15, y, z & 15);
    }

    default BlockEntity getBlockEntity(Vector3ic pos) {
        return getBlockEntity(pos.x(), pos.y(), pos.z());
    }

    default int getBlockEntityCount() {
        return getChunkService().getLoadedChunks().stream().mapToInt(chunk -> chunk.getBlockEntities().size()).sum();
    }

    default void addParticle(ParticleType particleType, Vector3fc pos) {
        addParticle(particleType, pos, 0);
    }

    default void addParticle(ParticleType particleType, Vector3fc pos, int data) {
        var pk = new LevelEventPacket();
        pk.setType(particleType);
        pk.setPosition(MathUtils.JOMLVecToCBVec(pos));
        pk.setData(data);
        getChunkService().getChunkByLevelPos((int) pos.x(), (int) pos.z()).addChunkPacket(pk);
    }

    default void broadcastPacket(BedrockPacket packet) {
        for (var player : getPlayers()) {
            player.sendPacket(packet);
        }
    }
}
