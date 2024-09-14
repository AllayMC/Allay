package org.allaymc.api.world;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.initinfo.SimpleEntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.interfaces.EntityXpOrb;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.utils.MathUtils;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.generator.WorldGenerator;
import org.allaymc.api.world.service.BlockUpdateService;
import org.allaymc.api.world.service.ChunkService;
import org.allaymc.api.world.service.EntityPhysicsService;
import org.allaymc.api.world.service.EntityService;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.data.LevelEventType;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.cloudburstmc.protocol.bedrock.packet.*;
import org.jetbrains.annotations.Unmodifiable;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3fc;
import org.joml.Vector3ic;
import org.joml.primitives.AABBfc;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.type.BlockTypes.AIR;

/**
 * Represents a dimension in the world
 *
 * @author daoge_cmd | Cool_Loong
 */
public interface Dimension {

    /**
     * Create a block update packet.
     *
     * @param newBlockState the new block state.
     * @param x             the x coordinate of the block.
     * @param y             the y coordinate of the block.
     * @param z             the z coordinate of the block.
     * @param layer         the layer which contains the block.
     *
     * @return the created block update packet.
     */
    static UpdateBlockPacket createBlockUpdatePacket(BlockState newBlockState, int x, int y, int z, int layer) {
        var updateBlockPacket = new UpdateBlockPacket();
        updateBlockPacket.setBlockPosition(Vector3i.from(x, y, z));
        updateBlockPacket.setDefinition(newBlockState.toNetworkBlockDefinitionRuntime());
        updateBlockPacket.setDataLayer(layer);
        updateBlockPacket.getFlags().addAll(UpdateBlockPacket.FLAG_ALL_PRIORITY);
        return updateBlockPacket;
    }

    /**
     * Get the world generator of this dimension.
     *
     * @return the world generator.
     */
    WorldGenerator getWorldGenerator();

    /**
     * Get the chunk service of this dimension.
     *
     * @return the chunk service.
     */
    ChunkService getChunkService();

    /**
     * Get the entity physics service of this dimension.
     *
     * @return the block base component.
     */
    EntityPhysicsService getEntityPhysicsService();

    /**
     * Get the block update service of this dimension.
     *
     * @return the block update service.
     */
    BlockUpdateService getBlockUpdateService();

    /**
     * Get the entity service of this dimension.
     *
     * @return the entity service.
     */
    EntityService getEntityService();

    /**
     * Get the dimension info of this dimension.
     *
     * @return the dimension info.
     */
    DimensionInfo getDimensionInfo();

    /**
     * Get the world which contains this dimension.
     *
     * @return the world which contains this dimension.
     */
    World getWorld();

    /**
     * Get all the entities in this dimension.
     *
     * @return all the entities in this dimension.
     */
    @Unmodifiable
    default Map<Long, Entity> getEntities() {
        var entities = new Long2ObjectOpenHashMap<Entity>();
        getChunkService().forEachLoadedChunks(chunk -> entities.putAll(chunk.getEntities()));
        return Collections.unmodifiableMap(entities);
    }

    /**
     * Get the entity count of this dimension.
     *
     * @return the entity count of this dimension.
     */
    default int getEntityCount() {
        return getChunkService().getLoadedChunks().stream().mapToInt(chunk -> chunk.getEntities().size()).sum();
    }

    /**
     * Get the entity by its runtime id.
     *
     * @param runtimeId the runtime id of the entity.
     *
     * @return the entity with the specified runtime id, or {@code null} if not found.
     */
    default Entity getEntityByRuntimeId(long runtimeId) {
        return getChunkService().getLoadedChunks().stream()
                .map(chunk -> chunk.getEntities().get(runtimeId))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    default void addPlayer(EntityPlayer player) {
        addPlayer(player, () -> {});
    }

    /**
     * Add a player to this dimension.
     *
     * @param player   the player to add.
     * @param runnable the callback to run after the player is added.
     */
    void addPlayer(EntityPlayer player, Runnable runnable);

    default void removePlayer(EntityPlayer player) {
        removePlayer(player, () -> {});
    }

    /**
     * Remove a player from this dimension.
     *
     * @param player   the player to remove.
     * @param runnable the callback to run after the player is removed.
     */
    void removePlayer(EntityPlayer player, Runnable runnable);

    /**
     * Get all the players in this dimension.
     *
     * @return all the players in this dimension.
     */
    @UnmodifiableView
    Set<EntityPlayer> getPlayers();

    default void setBlockState(Vector3fc pos, BlockState blockState) {
        setBlockState(pos, blockState, 0);
    }

    default void setBlockState(Vector3fc pos, BlockState blockState, int layer) {
        pos = pos.floor(new org.joml.Vector3f());
        setBlockState((int) pos.x(), (int) pos.y(), (int) pos.z(), blockState, layer);
    }

    default void setBlockState(float x, float y, float z, BlockState blockState) {
        setBlockState(x, y, z, blockState, 0);
    }

    default void setBlockState(float x, float y, float z, BlockState blockState, int layer) {
        setBlockState((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z), blockState, layer);
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
        setBlockState(x, y, z, blockState, layer, send, update, true, null);
    }

    default void setBlockState(Vector3ic pos, BlockState blockState, int layer, boolean send, boolean update, boolean callBlockBehavior) {
        setBlockState(pos.x(), pos.y(), pos.z(), blockState, layer, send, update, callBlockBehavior);
    }

    default void setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send, boolean update, boolean callBlockBehavior) {
        setBlockState(x, y, z, blockState, layer, send, update, callBlockBehavior, null);
    }

    default void setBlockState(int x, int y, int z, BlockState blockState, PlayerInteractInfo placementInfo) {
        setBlockState(x, y, z, blockState, 0, true, true, true, placementInfo);
    }

    default void setBlockState(int x, int y, int z, BlockState blockState, int layer, PlayerInteractInfo placementInfo) {
        setBlockState(x, y, z, blockState, layer, true, true, true, placementInfo);
    }

    default void setBlockState(Vector3ic pos, BlockState blockState, int layer, boolean send, boolean update, boolean callBlockBehavior, PlayerInteractInfo placementInfo) {
        setBlockState(pos.x(), pos.y(), pos.z(), blockState, layer, send, update, callBlockBehavior, placementInfo);
    }

    /**
     * Set the block state at the specified pos.
     *
     * @param x                 the x coordinate of the block.
     * @param y                 the y coordinate of the block.
     * @param z                 the z coordinate of the block.
     * @param blockState        the block state to set.
     * @param layer             the layer which contains the block.
     * @param send              whether to send the block update packet.
     * @param update            whether to update the blocks around the block.
     * @param callBlockBehavior whether to call the block behavior.
     * @param placementInfo     the placement info.
     */
    void setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send, boolean update, boolean callBlockBehavior, PlayerInteractInfo placementInfo);

    default void sendBlockUpdateTo(BlockState blockState, Vector3ic pos, int layer, EntityPlayer player) {
        sendBlockUpdateTo(blockState, pos.x(), pos.y(), pos.z(), layer, player);
    }

    default void sendBlockUpdateTo(BlockState blockState, int x, int y, int z, int layer, EntityPlayer player) {
        player.sendPacket(createBlockUpdatePacket(blockState, x, y, z, layer));
    }

    default BlockState getBlockState(Vector3fc pos) {
        return getBlockState(pos, 0);
    }

    default BlockState getBlockState(Vector3fc pos, int layer) {
        pos = pos.floor(new org.joml.Vector3f());
        return getBlockState((int) pos.x(), (int) pos.y(), (int) pos.z(), layer);
    }

    default BlockState getBlockState(float x, float y, float z) {
        return getBlockState(x, y, z, 0);
    }

    default BlockState getBlockState(float x, float y, float z, int layer) {
        return getBlockState((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z), layer);
    }

    default BlockState getBlockState(Vector3ic pos) {
        return getBlockState(pos, 0);
    }

    default BlockState getBlockState(Vector3ic pos, int layer) {
        return getBlockState(pos.x(), pos.y(), pos.z(), layer);
    }

    default BlockState getBlockState(int x, int y, int z) {
        return getBlockState(x, y, z, 0);
    }

    /**
     * Get the block state at the specified pos.
     *
     * @param x     the x coordinate of the block.
     * @param y     the y coordinate of the block.
     * @param z     the z coordinate of the block.
     * @param layer the layer which contains the block.
     *
     * @return the block state at the specified pos, or {@code BlockTypes.AIR.getDefaultState()} if not found.
     */
    default BlockState getBlockState(int x, int y, int z, int layer) {
        if (y < this.getDimensionInfo().minHeight() || y > getDimensionInfo().maxHeight())
            return AIR.getDefaultState();

        var chunk = getChunkService().getChunkByDimensionPos(x, z);
        if (chunk == null) chunk = getChunkService().getOrLoadChunkSync(x >> 4, z >> 4);
        return chunk.getBlockState(x & 15, y, z & 15, layer);
    }

    /**
     * Get the block state at the specified region.
     *
     * @param x     the start x coordinate of the region.
     * @param y     the start y coordinate of the region.
     * @param z     the start z coordinate of the region.
     * @param sizeX the size of the region in the x-axis.
     * @param sizeY the size of the region in the y-axis.
     * @param sizeZ the size of the region in the z-axis.
     * @param layer the layer which contains the block.
     *
     * @return the block states at the specified region.
     */
    default BlockState[][][] getBlockStates(int x, int y, int z, int sizeX, int sizeY, int sizeZ, int layer) {
        if (sizeX < 1 || sizeY < 1 || sizeZ < 1) return Utils.EMPTY_BLOCK_STATE_ARRAY_3D;

        var blockStates = new BlockState[sizeX][sizeY][sizeZ];

        var startX = x >> 4;
        var endX = (x + sizeX - 1) >> 4;
        var startZ = z >> 4;
        var endZ = (z + sizeZ - 1) >> 4;
        for (int chunkX = startX; chunkX <= endX; chunkX++) {
            for (int chunkZ = startZ; chunkZ <= endZ; chunkZ++) {
                var cX = chunkX << 4;
                var cZ = chunkZ << 4;
                var localStartX = Math.max(x - cX, 0);
                var localStartZ = Math.max(z - cZ, 0);
                var localEndX = Math.min(x + sizeX - cX, 16);
                var localEndZ = Math.min(z + sizeZ - cZ, 16);

                var chunk = getChunkService().getChunk(chunkX, chunkZ);
                if (chunk != null) {
                    chunk.batchProcess(c -> {
                        for (int localX = localStartX; localX < localEndX; localX++) {
                            for (int globalY = y; globalY < y + sizeY; globalY++) {
                                for (int localZ = localStartZ; localZ < localEndZ; localZ++) {
                                    var globalX = cX + localX;
                                    var globalZ = cZ + localZ;
                                    blockStates[globalX - x][globalY - y][globalZ - z] = c.getBlockState(localX, globalY, localZ, layer);
                                }
                            }
                        }
                    });
                } else {
                    var air = AIR.getDefaultState();
                    for (int localX = localStartX; localX < localEndX; localX++) {
                        for (int globalY = y; globalY < y + sizeY; globalY++) {
                            for (int localZ = localStartZ; localZ < localEndZ; localZ++) {
                                var globalX = cX + localX;
                                var globalZ = cZ + localZ;
                                blockStates[globalX - x][globalY - y][globalZ - z] = air;
                            }
                        }
                    }
                }
            }
        }
        return blockStates;
    }

    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, int x, int y, int z) {
        updateBlockProperty(propertyType, value, x, y, z, 0);
    }

    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, Vector3ic pos) {
        updateBlockProperty(propertyType, value, pos.x(), pos.y(), pos.z(), 0);
    }

    /**
     * Update a specific property of a specific block.
     *
     * @param propertyType the property type needs to be updated.
     * @param value        the new property value.
     * @param x            block's x coordinate.
     * @param y            block's y coordinate.
     * @param z            block's z coordinate.
     * @param layer        the layer which contains the block.
     */
    default <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value, int x, int y, int z, int layer) {
        var chunk = getChunkService().getChunkByDimensionPos(x, z);
        if (chunk == null) return;

        var xIndex = x & 15;
        var zIndex = z & 15;
        var oldBlockState = chunk.getBlockState(xIndex, y, zIndex, layer);

        var newBlockState = oldBlockState.setProperty(propertyType, value);
        if (oldBlockState == newBlockState) return;

        chunk.setBlockState(xIndex, y, zIndex, newBlockState, layer);
        chunk.sendChunkPacket(createBlockUpdatePacket(newBlockState, x, y, z, layer));
    }

    default BlockState[][][] getCollidingBlocks(AABBfc aabb) {
        return getCollidingBlocks(aabb, 0);
    }

    default BlockState[][][] getCollidingBlocks(AABBfc aabb, int layer) {
        return getCollidingBlocks(aabb, layer, false);
    }

    /**
     * Get the block states that collide with the specified AABB.
     *
     * @param aabb            the AABB to check.
     * @param layer           the layer which contains the block.
     * @param ignoreCollision include blocks that don't have collision.
     *
     * @return the block states that collide with the specified AABB.
     */
    default BlockState[][][] getCollidingBlocks(AABBfc aabb, int layer, boolean ignoreCollision) {
        var maxX = (int) Math.ceil(aabb.maxX());
        var maxY = (int) Math.ceil(aabb.maxY());
        var maxZ = (int) Math.ceil(aabb.maxZ());
        var minX = (int) Math.floor(aabb.minX());
        var minY = (int) Math.floor(aabb.minY());
        var minZ = (int) Math.floor(aabb.minZ());
        var blockStates = getBlockStates(minX, minY, minZ, maxX - minX, maxY - minY, maxZ - minZ, layer);
        boolean notEmpty = false;
        if (!ignoreCollision) {
            // Filter out blocks without collision
            for (int x = 0; x < blockStates.length; x++) {
                for (int y = 0; y < blockStates[x].length; y++) {
                    for (int z = 0; z < blockStates[x][y].length; z++) {
                        var blockState = blockStates[x][y][z];
                        var blockStateData = blockState.getBlockType().getBlockBehavior().getBlockStateData(blockState);
                        if (!blockStateData.hasCollision() || !blockStateData.collisionShape().translate(minX + x, minY + y, minZ + z).intersectsAABB(aabb)) {
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

    default void addLevelEvent(Vector3fc pos, LevelEventType levelEventType) {
        addLevelEvent(pos.x(), pos.y(), pos.z(), levelEventType, 0);
    }

    default void addLevelEvent(Vector3fc pos, LevelEventType levelEventType, int data) {
        addLevelEvent(pos.x(), pos.y(), pos.z(), levelEventType, data);
    }

    default void addLevelEvent(float x, float y, float z, LevelEventType levelEventType) {
        addLevelEvent(x, y, z, levelEventType, 0);
    }

    /**
     * Add a level event at the specified position.
     *
     * @param x              the x coordinate of the position.
     * @param y              the y coordinate of the position.
     * @param z              the z coordinate of the position.
     * @param levelEventType the level event type.
     * @param data           the data of the level event.
     */
    default void addLevelEvent(float x, float y, float z, LevelEventType levelEventType, int data) {
        var chunk = getChunkService().getChunkByDimensionPos((int) x, (int) z);
        if (chunk == null) return;

        var levelEventPacket = new LevelEventPacket();
        levelEventPacket.setPosition(Vector3f.from(x, y, z));
        levelEventPacket.setType(levelEventType);
        levelEventPacket.setData(data);
        chunk.sendChunkPacket(levelEventPacket);
    }

    default void addLevelSoundEvent(Vector3fc pos, SoundEvent soundEvent) {
        addLevelSoundEvent(pos.x(), pos.y(), pos.z(), soundEvent);
    }

    default void addLevelSoundEvent(float x, float y, float z, SoundEvent soundEvent) {
        addLevelSoundEvent(x, y, z, soundEvent, -1);
    }

    default void addLevelSoundEvent(float x, float y, float z, SoundEvent soundEvent, int extraData) {
        addLevelSoundEvent(x, y, z, soundEvent, extraData, "", false, false);
    }

    /**
     * Add a level sound event at the specified position.
     *
     * @param x                      the x coordinate of the position.
     * @param y                      the y coordinate of the position.
     * @param z                      the z coordinate of the position.
     * @param soundEvent             the sound event.
     * @param extraData              the extra data of the sound event.
     * @param identifier             the identifier of the sound event.
     * @param babySound              whether the sound is a baby sound.
     * @param relativeVolumeDisabled whether the relative volume is disabled.
     */
    default void addLevelSoundEvent(float x, float y, float z, SoundEvent soundEvent, int extraData, String identifier, boolean babySound, boolean relativeVolumeDisabled) {
        var chunk = getChunkService().getChunk((int) x >> 4, (int) z >> 4);
        if (chunk == null) return;

        var levelSoundEventPacket = new LevelSoundEventPacket();
        levelSoundEventPacket.setSound(soundEvent);
        levelSoundEventPacket.setPosition(Vector3f.from(x, y, z));
        levelSoundEventPacket.setExtraData(extraData);
        levelSoundEventPacket.setIdentifier(identifier);
        levelSoundEventPacket.setBabySound(babySound);
        levelSoundEventPacket.setRelativeVolumeDisabled(relativeVolumeDisabled);
        chunk.sendChunkPacket(levelSoundEventPacket);
    }

    default void updateAroundIgnoreFace(int x, int y, int z, BlockFace... ignoreFaces) {
        updateAroundIgnoreFace(new org.joml.Vector3i(x, y, z), ignoreFaces);
    }

    /**
     * Update the blocks around a pos, ignoring some faces.
     *
     * @param pos         the specified pos.
     * @param ignoreFaces the faces to ignore.
     */
    default void updateAroundIgnoreFace(Vector3ic pos, BlockFace... ignoreFaces) {
        for (var face : BlockFace.values()) {
            if (ignoreFaces != null && ignoreFaces.length > 0) {
                var ignore = Arrays.stream(ignoreFaces).anyMatch(ignoreFace -> ignoreFace == face);
                if (ignore) continue;
            }

            updateAtFace(pos, face);
        }
    }

    /**
     * Update the blocks around a block.
     *
     * @param x the x coordinate of the block.
     * @param y the y coordinate of the block.
     * @param z the z coordinate of the block.
     */
    default void updateAround(int x, int y, int z) {
        for (var face : BlockFace.values()) updateAtFace(x, y, z, face);
    }

    /**
     * Update the blocks around a block.
     *
     * @param pos the pos.
     */
    default void updateAround(Vector3ic pos) {
        for (var face : BlockFace.values()) updateAtFace(pos, face);
    }

    /**
     * Update the specific face of a block.
     *
     * @param x    the x coordinate of the block.
     * @param y    the y coordinate of the block.
     * @param z    the z coordinate of the block.
     * @param face the face of the block.
     */
    default void updateAtFace(int x, int y, int z, BlockFace face) {
        updateAtFace(new org.joml.Vector3i(x, y, z), face);
    }

    /**
     * Update the block at the specified face of the specified block.
     *
     * @param pos  the pos of the block.
     * @param face the face of the block.
     */
    default void updateAtFace(Vector3ic pos, BlockFace face) {
        var offsetPos = face.offsetPos(pos);
        getBlockUpdateService().neighborBlockUpdate(offsetPos, pos, face.opposite());
    }

    /**
     * Get blocks around a pos.
     *
     * @param pos the pos.
     *
     * @return the blocks around the pos.
     */
    default BlockStateWithPos[] getNeighborsBlockState(Vector3ic pos) {
        return getNeighborsBlockState(pos.x(), pos.y(), pos.z());
    }

    /**
     * Get blocks around a pos.
     *
     * @param x the x coordinate of the pos.
     * @param y the y coordinate of the pos.
     * @param z the z coordinate of the pos.
     *
     * @return the blocks around the pos.
     */
    default BlockStateWithPos[] getNeighborsBlockState(int x, int y, int z) {
        var result = new BlockStateWithPos[6];
        for (int i = 0; i < BlockFace.values().length; i++) {
            var offsetPos = BlockFace.values()[i].offsetPos(x, y, z);
            var neighborBlockState = getBlockState(offsetPos.x(), offsetPos.y(), offsetPos.z(), 0);
            result[i] = new BlockStateWithPos(neighborBlockState, new Position3i(offsetPos, this), 0);
        }
        return result;
    }

    /**
     * Check if the y coordinate is in the range of this dimension.
     *
     * @param y the y coordinate.
     *
     * @return {@code true} if the y coordinate is in the range of this dimension, otherwise {@code false}.
     */
    default boolean isYInRange(float y) {
        return y >= getDimensionInfo().minHeight() && y <= getDimensionInfo().maxHeight();
    }

    /**
     * Check if the specified pos is in a valid and loaded region.
     *
     * @param x the x coordinate of the pos.
     * @param y the y coordinate of the pos.
     * @param z the z coordinate of the pos.
     *
     * @return {@code true} if the pos is in a valid and loaded region, otherwise {@code false}.
     */
    default boolean isInWorld(float x, float y, float z) {
        return isYInRange(y) && getChunkService().isChunkLoaded((int) x >> 4, (int) z >> 4);
    }

    /**
     * Check if the aabb is in a valid and loaded region.
     *
     * @param aabb the aabb.
     *
     * @return {@code true} if the aabb is in a valid and loaded region, otherwise {@code false}.
     */
    default boolean isAABBInWorld(AABBfc aabb) {
        return isInWorld(aabb.maxX(), aabb.maxY(), aabb.maxZ()) && isInWorld(aabb.minX(), aabb.minY(), aabb.minZ());
    }

    /**
     * Get the block entity at the specified pos.
     *
     * @param x the x coordinate of the pos.
     * @param y the y coordinate of the pos.
     * @param z the z coordinate of the pos.
     *
     * @return the block entity at the specified pos, or {@code null} if not found.
     */
    default BlockEntity getBlockEntity(int x, int y, int z) {
        var chunk = getChunkService().getChunkByDimensionPos(x, z);
        if (chunk == null) chunk = getChunkService().getOrLoadChunkSync(x >> 4, z >> 4);
        return chunk.getBlockEntity(x & 15, y, z & 15);
    }

    default BlockEntity getBlockEntity(Vector3ic pos) {
        return getBlockEntity(pos.x(), pos.y(), pos.z());
    }

    /**
     * Get the count of block entities in this dimension.
     *
     * @return the count of block entities in this dimension.
     */
    default int getBlockEntityCount() {
        return getChunkService().getLoadedChunks().stream().mapToInt(chunk -> chunk.getBlockEntities().size()).sum();
    }

    default void addParticle(ParticleType particleType, Vector3fc pos) {
        addParticle(particleType, pos, 0);
    }


    /**
     * Add particle at the specified pos.
     *
     * @param particleType the particle type.
     * @param pos          the pos.
     * @param data         the data of the particle.
     */
    default void addParticle(ParticleType particleType, Vector3fc pos, int data) {
        var pk = new LevelEventPacket();
        pk.setType(particleType);
        pk.setPosition(MathUtils.JOMLVecToCBVec(pos));
        pk.setData(data);
        getChunkService().getChunkByDimensionPos((int) pos.x(), (int) pos.z()).addChunkPacket(pk);
    }

    /**
     * Broadcast a packet to all players in this dimension.
     *
     * @param packet the packet to broadcast.
     */
    default void broadcastPacket(BedrockPacket packet) {
        getPlayers().forEach(player -> player.sendPacket(packet));
    }

    default void addSound(Vector3fc pos, String sound) {
        addSound(pos.x(), pos.y(), pos.z(), sound);
    }

    default void addSound(float x, float y, float z, String sound) {
        addSound(x, y, z, sound, 1, 1);
    }

    /**
     * Add a sound at the specified pos.
     *
     * @param x      the x coordinate of the pos.
     * @param y      the y coordinate of the pos.
     * @param z      the z coordinate of the pos.
     * @param sound  the sound.
     * @param volume the volume of the sound.
     * @param pitch  the pitch of the sound.
     */
    default void addSound(float x, float y, float z, String sound, float volume, float pitch) {
        Preconditions.checkArgument(volume >= 0 && volume <= 1, "Sound volume must be between 0 and 1");
        Preconditions.checkArgument(pitch >= 0, "Sound pitch must be higher than 0");

        var packet = new PlaySoundPacket();
        packet.setSound(sound);
        packet.setVolume(volume);
        packet.setPitch(pitch);
        packet.setPosition(Vector3f.from(x, y, z));

        getChunkService().getChunkByDimensionPos((int) x, (int) z).addChunkPacket(packet);
    }

    /**
     * Drop an item at the specified pos.
     * <p>
     * This method will add a random motion to the item entity.
     *
     * @param itemStack the item stack to drop.
     * @param pos       the pos to drop the item.
     */
    default void dropItem(ItemStack itemStack, Vector3fc pos) {
        var rand = ThreadLocalRandom.current();
        dropItem(itemStack, pos, new org.joml.Vector3f(rand.nextFloat(0.2f) - 0.1f, 0.2f, rand.nextFloat(0.2f) - 0.1f));
    }

    default void dropItem(ItemStack itemStack, Vector3fc pos, Vector3fc motion) {
        dropItem(itemStack, pos, motion, 10);
    }

    /**
     * Drop an item at the specified pos with the specified motion and pickup delay.
     *
     * @param itemStack   the item stack to drop.
     * @param pos         the pos to drop the item.
     * @param motion      the motion of the item entity.
     * @param pickupDelay the pickup delay of the item entity.
     */
    default void dropItem(ItemStack itemStack, Vector3fc pos, Vector3fc motion, int pickupDelay) {
        var entityItem = EntityTypes.ITEM.createEntity(
                SimpleEntityInitInfo.builder()
                        .dimension(this)
                        .pos(pos)
                        .motion(motion)
                        .build()
        );
        entityItem.setItemStack(itemStack);
        entityItem.setPickupDelay(pickupDelay);
        getEntityService().addEntity(entityItem);
    }

    /**
     * Drop a specified amount of xp orbs at the specified pos.
     * <p>
     * This method will split the xp into multiple xp orbs before dropping,
     * which means that it won't drop a single xp orb with a large amount of xp.
     *
     * @param pos the pos to drop the xp orbs.
     * @param xp  the amount of xp to drop.
     */
    default void splitAndDropXpOrb(Vector3fc pos, int xp) {
        for (var split : EntityXpOrb.splitIntoOrbSizes(xp)) {
            dropXpOrb(pos, split);
        }
    }

    /**
     * Drop a xp orb at the specified pos with the specified xp amount.
     * <p>
     * This method will add a random motion to the xp orb entity.
     *
     * @param pos the pos to drop the xp orb.
     * @param xp  the amount of xp to drop.
     */
    default void dropXpOrb(Vector3fc pos, int xp) {
        var rand = ThreadLocalRandom.current();
        var motion = new org.joml.Vector3f(
                (rand.nextFloat() * 0.2f - 0.1f) * 2f,
                rand.nextFloat() * 0.4f,
                (rand.nextFloat() * 0.2f - 0.1f) * 2f
        );
        dropXpOrb(pos, xp, motion);
    }

    default void dropXpOrb(Vector3fc pos, int xp, Vector3fc motion) {
        dropXpOrb(pos, xp, motion, 10);
    }

    /**
     * Drop a xp orb at the specified pos with the specified xp amount, motion and pickup delay.
     *
     * @param pos         the pos to drop the xp orb.
     * @param xp          the amount of xp to drop.
     * @param motion      the motion of the xp orb entity.
     * @param pickupDelay the pickup delay of the xp orb entity.
     */
    default void dropXpOrb(Vector3fc pos, int xp, Vector3fc motion, int pickupDelay) {
        var rand = ThreadLocalRandom.current();
        var entityXpOrb = EntityTypes.XP_ORB.createEntity(
                SimpleEntityInitInfo.builder()
                        .dimension(this)
                        .pos(pos)
                        .motion(motion)
                        .rot(rand.nextFloat() * 360f, 0)
                        .build()
        );
        entityXpOrb.setExperienceValue(xp);
        entityXpOrb.setPickupDelay(pickupDelay);
        getEntityService().addEntity(entityXpOrb);
    }

    default void breakBlock(Vector3ic pos, ItemStack usedItem, EntityPlayer player) {
        breakBlock(pos.x(), pos.y(), pos.z(), usedItem, player);
    }

    /**
     * Break a block at the specified position
     *
     * @param x        The x coordinate of the block
     * @param y        The y coordinate of the block
     * @param z        The z coordinate of the block
     * @param usedItem The item used to break the block, can be null
     * @param player   The player who breaks the block, can be null
     *
     * @return Whether the block is successfully broken
     */
    boolean breakBlock(int x, int y, int z, ItemStack usedItem, EntityPlayer player);
}
