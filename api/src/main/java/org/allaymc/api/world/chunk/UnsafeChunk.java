package org.allaymc.api.world.chunk;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.biome.BiomeType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Unsafe is similar to {@link Chunk} but is not thread-safe.
 * <p>
 * Compared to {@link Chunk}, unsafe chunk is very fast but is not thread-safe.
 * All the methods in this class should be considered as thread-unsafe, although
 * some of them may be thread-safe depending on the implementation. If you are sure
 * that the instance won't be accessed by multiple threads, you can operate on unsafe
 * chunk directly. However, this may become very dangerous if you do not have enough
 * experience in multithreaded programming.
 *
 * @author Cool_Loong | daoge_cmd
 */
@NotThreadSafe
public interface UnsafeChunk {

    /**
     * Check if the chunk is loaded.
     *
     * @return {@code true} if the chunk is loaded, {@code false} otherwise
     */
    boolean isLoaded();

    /**
     * Get the chunk loaders that load this chunk
     *
     * @return the chunk loaders
     */
    @UnmodifiableView
    Set<ChunkLoader> getChunkLoaders();

    /**
     * Get the player chunk loaders that load this chunk
     *
     * @return the player chunk loaders
     */
    @UnmodifiableView
    default Set<EntityPlayer> getPlayerChunkLoaders() {
        return getChunkLoaders().stream()
                .filter(EntityPlayer.class::isInstance)
                .map(EntityPlayer.class::cast)
                .collect(Collectors.toSet());
    }

    /**
     * Add a chunk loader to this chunk.
     *
     * @param chunkLoader the chunk loader to add
     */
    void addChunkLoader(ChunkLoader chunkLoader);

    /**
     * Remove a chunk loader from this chunk.
     *
     * @param chunkLoader the chunk loader to remove
     */
    void removeChunkLoader(ChunkLoader chunkLoader);

    /**
     * Get the number of chunk loaders that load this chunk.
     *
     * @return the number of chunk loaders
     */
    int getChunkLoaderCount();

    /**
     * Add a chunk packet to the chunk.
     * <p>
     * Chunk packet will be sent to all chunk loaders every tick.
     *
     * @param packet the packet to add
     */
    void addChunkPacket(BedrockPacket packet);

    /**
     * Add a chunk packet to the chunk.
     * <p>
     * Chunk packet will be sent to chunk loaders that match the predicate every tick.
     *
     * @param packet               the packet to add
     * @param chunkLoaderPredicate the predicate to match chunk loaders
     */
    void addChunkPacket(BedrockPacket packet, Predicate<ChunkLoader> chunkLoaderPredicate);

    /**
     * Send packet to all chunk loaders.
     */
    void sendChunkPacket(BedrockPacket packet);

    /**
     * Send packet to chunk loaders that match the predicate.
     *
     * @param packet               the packet to send
     * @param chunkLoaderPredicate the predicate to match chunk loaders
     */
    void sendChunkPacket(BedrockPacket packet, Predicate<ChunkLoader> chunkLoaderPredicate);

    /**
     * Get the state of the chunk.
     *
     * @return the state of the chunk.
     */
    ChunkState getState();

    /**
     * Get the dimension info of the chunk.
     *
     * @return the dimension info of the chunk.
     */
    DimensionInfo getDimensionInfo();

    /**
     * Get the x coordinate of the chunk.
     *
     * @return the x coordinate of the chunk.
     */
    int getX();

    /**
     * Get the z coordinate of the chunk.
     *
     * @return the z coordinate of the chunk.
     */
    int getZ();

    /**
     * Get the entity in the chunk by its runtime id.
     *
     * @param runtimeId the runtime id of the entity.
     *
     * @return the entity in the chunk, or {@code null} if not found.
     */
    Entity getEntity(long runtimeId);

    /**
     * Get all entities in the chunk.
     *
     * @return all entities in the chunk.
     */
    @UnmodifiableView
    Map<Long, Entity> getEntities();

    /**
     * Spawn entities in this chunk to the specified player.
     *
     * @param player the player to spawn entities to
     */
    default void spawnEntitiesTo(EntityPlayer player) {
        getEntities().values().forEach(player::spawnEntity);
    }

    /**
     * Despawn entities in this chunk from the specified player.
     *
     * @param player the player to despawn entities from
     */
    default void despawnEntitiesFrom(EntityPlayer player) {
        getEntities().values().forEach(player::despawnEntity);
    }

    /**
     * Remove the block entity in this chunk.
     *
     * @param x the x coordinate of the block entity.
     * @param y the y coordinate of the block entity.
     * @param z the z coordinate of the block entity.
     *
     * @return the removed block entity, or {@code null} if not found.
     */
    BlockEntity removeBlockEntity(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Get the block entity in this chunk.
     *
     * @param x the x coordinate of the block entity.
     * @param y the y coordinate of the block entity.
     * @param z the z coordinate of the block entity.
     *
     * @return the block entity in this chunk, or {@code null} if not found.
     */
    BlockEntity getBlockEntity(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Get all block entities in this chunk.
     *
     * @return all block entities in this chunk.
     */
    @UnmodifiableView
    Map<Integer, BlockEntity> getBlockEntities();

    /**
     * Add a block entity to this chunk.
     *
     * @param blockEntity the block entity to add.
     */
    void addBlockEntity(BlockEntity blockEntity);

    /**
     * Gets Chunk section, range -1 -> -4, 0 -> 59
     * <p>
     * Since the array index starts at 0, the maximum value is 59
     *
     * @param sectionY the y
     *
     * @return the section
     */
    @UnmodifiableView
    Collection<BlockEntity> getSectionBlockEntities(int sectionY);

    /**
     * Add a scheduled update to a pos in the chunk.
     *
     * @param x     the x coordinate of the pos.
     * @param y     the y coordinate of the pos.
     * @param z     the z coordinate of the pos.
     * @param delay the delay of the scheduled update.
     * @param layer the layer of the scheduled update.
     */
    void addScheduledUpdate(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, int delay, int layer);

    /**
     * Check if a pos in the chunk has a scheduled update.
     *
     * @param x     the x coordinate of the pos.
     * @param y     the y coordinate of the pos.
     * @param z     the z coordinate of the pos.
     * @param layer the layer of the pos.
     *
     * @return {@code true} if the pos has a scheduled update, otherwise {@code false}.
     */
    boolean hasScheduledUpdate(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, int layer);

    /**
     * Set block state in this chunk.
     *
     * @param x          the x coordinate of the block.
     * @param y          the y coordinate of the block.
     * @param z          the z coordinate of the block.
     * @param blockState the block state to set.
     * @param layer      the layer of the block.
     */
    void setBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BlockState blockState, int layer, boolean send);

    /**
     * @see #setBlockState(int, int, int, BlockState, int)
     */
    default void setBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BlockState blockState, int layer) {
        setBlockState(x, y, z, blockState, layer, true);
    }

    /**
     * @see #setBlockState(int, int, int, BlockState, int)
     */
    default void setBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BlockState blockState) {
        setBlockState(x, y, z, blockState, 0);
    }

    /**
     * Get block state in this chunk.
     *
     * @param x     the x coordinate of the block.
     * @param y     the y coordinate of the block.
     * @param z     the z coordinate of the block.
     * @param layer the layer of the block.
     *
     * @return the block state in this chunk.
     */
    BlockState getBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, int layer);

    /**
     * @see #getBlockState(int, int, int, int)
     */
    default BlockState getBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z) {
        return getBlockState(x, y, z, 0);
    }

    /**
     * Set height of the specified position.
     *
     * @param x      the x coordinate of the position.
     * @param z      the z coordinate of the position.
     * @param height the height.
     */
    void setHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, short height);

    /**
     * Get height of the specified position.
     *
     * @param x the x coordinate of the position.
     * @param z the z coordinate of the position.
     *
     * @return the height.
     */
    short getHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z);

    /**
     * Set biome of the specified position.
     *
     * @param x         the x coordinate of the position.
     * @param y         the y coordinate of the position.
     * @param z         the z coordinate of the position.
     * @param biomeType the biome type.
     */
    void setBiome(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BiomeType biomeType);

    /**
     * Get biome of the specified position.
     *
     * @param x the x coordinate of the position.
     * @param y the y coordinate of the position.
     * @param z the z coordinate of the position.
     *
     * @return the biome type.
     */
    BiomeType getBiome(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    /**
     * Get a specific chunk section in this chunk.
     *
     * @param sectionY the sectionY of the chunk section.
     *
     * @return the section, should never be {@code null}
     */
    ChunkSection getSection(int sectionY);

    /**
     * Get all chunk sections in this chunk.
     *
     * @return all chunk sections in this chunk.
     */
    @UnmodifiableView
    List<ChunkSection> getSections();

    /**
     * Get the hash of the chunk.
     *
     * @return the hash of the chunk.
     */
    default long computeChunkHash() {
        return HashUtils.hashXZ(getX(), getZ());
    }

    /**
     * Convert this unsafe chunk to a {@link Chunk} which is safe in multithreaded environment.
     *
     * @return the safe chunk.
     */
    Chunk toSafeChunk();
}
