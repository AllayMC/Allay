package org.allaymc.api.world.chunk;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.biome.BiomeType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Chunk represents a 16x16 area in a world.
 * <p>
 * All methods in this class are thread-safe. However, Frequent calls to methods in this class
 * result in huge lock overhead. If you are sure that the instance won't be accessed by multiple threads,
 * you can operate on unsafe chunk directly. To get the unsafe chunk, use {@link #toUnsafeChunk()}.
 * <p>
 * Compared to {@link UnsafeChunk}, this class contains more methods, which are also guaranteed to be thread-safe.
 *
 * @author Cool_Loong | daoge_cmd
 */
@ThreadSafe
public interface Chunk extends UnsafeChunk {
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
     * Send all chunk packets which are added since last tick to all chunk loaders.
     */
    void sendChunkPackets();

    /**
     * Compare and set block at the specified position.
     *
     * @param x             the x coordinate.
     * @param y             the y coordinate.
     * @param z             the z coordinate.
     * @param expectedValue the expected block state.
     * @param newValue      the new block state.
     * @param layer         the layer to compare and set.
     */
    void compareAndSetBlock(int x, int y, int z, BlockState expectedValue, BlockState newValue, int layer);

    /**
     * Compare and set biome at the specified position.
     *
     * @param x             the x coordinate.
     * @param y             the y coordinate.
     * @param z             the z coordinate.
     * @param expectedValue the expected biome type.
     * @param newValue      the new biome type.
     */
    void compareAndSetBiome(int x, int y, int z, BiomeType expectedValue, BiomeType newValue);

    /**
     * Compare and set height at the specified position.
     *
     * @param x             the x coordinate.
     * @param z             the z coordinate.
     * @param expectedValue the expected height.
     * @param newValue      the new height.
     */
    void compareAndSetHeight(int x, int z, short expectedValue, short newValue);

    /**
     * Process the chunk with the specified operation.
     * <p>
     * This method will only add a lock once, so it is more efficient than calling other methods
     * in this class frequently.
     * If you are going to get a range of blocks in the chunk, using this method will be an ideal choice
     * to avoid lock overhead.
     *
     * @param operate the operation to process the chunk
     */
    void batchProcess(UnsafeChunkOperate operate);

    /**
     * Get the unsafe chunk of this chunk.
     *
     * @return the unsafe chunk
     */
    UnsafeChunk toUnsafeChunk();

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
}
