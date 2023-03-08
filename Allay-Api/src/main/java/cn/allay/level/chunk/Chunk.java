package cn.allay.level.chunk;

import cn.allay.Player;
import cn.allay.block.BlockState;
import cn.allay.blockentity.BlockEntity;
import cn.allay.entity.Entity;
import cn.allay.level.ChunkLoader;
import cn.allay.level.Level;
import cn.allay.level.biome.Biome;
import org.checkerframework.checker.index.qual.NonNegative;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public interface Chunk extends Comparable<Chunk> {
    /**
     * 代表块被新建
     */
    int STATE_NEW = 0;
    int STATE_GENERATED = 1;
    int STATE_POPULATED = 2;
    int STATE_FINISHED = 3;

    ChunkSection getOrCreateSection(@NonNegative int fY);

    @Nullable
    ChunkSection getSection(@NonNegative int fY);

    boolean setSection(@NonNegative float fY, ChunkSection section);

    ChunkSection[] getSections();

    default BlockState getBlock(int x, int y, int z) {
        return this.getBlock(x, y, z, 0);
    }

    BlockState getBlock(int x, int y, int z, @NonNegative int layer);

    default BlockState getAndSetBlock(int x, int y, int z, BlockState blockState) {
        return this.getAndSetBlock(x, y, z, 0, blockState);
    }

    BlockState getAndSetBlock(int x, int y, int z, @NonNegative int layer, BlockState blockState);

    default void setBlock(int x, int y, int z, BlockState blockState) {
        this.setBlock(x, y, z, 0, blockState);
    }

    void setBlock(int x, int y, int z, @NonNegative int layer, BlockState blockState);

    int getBiomeId(int x, int z);

    void setBiomeId(int x, int z, int biome);

    void setBiome(int x, int z, Biome biome);

    byte getSkyLight(int x, int y, int z);

    void setSkyLight(int x, int y, int z, @NonNegative int level);

    byte getBlockLight(int x, int y, int z);

    void setBlockLight(int x, int y, int z, @NonNegative int level);

    int getHighestBlock(int x, int z);

    void addEntity(@NotNull Entity entity);

    void removeEntity(Entity entity);

    void addBlockEntity(BlockEntity blockEntity);

    void removeBlockEntity(BlockEntity blockEntity);

    /**
     * Get the chunk's X coordinate in the level it was loaded.
     *
     * @return chunk x
     */
    int getX();

    /**
     * Get the chunk's Z coordinate in the level it was loaded.
     *
     * @return chunk z
     */
    int getZ();

    /**
     * Get the level the chunk was loaded in.
     *
     * @return chunk level
     */
    Level getLevel();

    /**
     * Get the copy of the biome array.
     *
     * @return biome array
     */
    byte[] getBiomeIdArray();

    /**
     * Get a copy of the height map array.
     *
     * @return height map
     */
    int[] getHeightMapArray();

    byte[] getBlockSkyLightArray();

    byte[] getBlockLightArray();

    /**
     * Gets an immutable copy of players currently in this chunk
     *
     * @return player set
     */
    Set<Player> getPlayers();

    /**
     * Gets an immutable copy of entities currently in this chunk
     *
     * @return entity set
     */
    Set<Entity> getEntities();

    /**
     * Gets an immutable copy of all block entities within the current chunk.
     *
     * @return block entity set
     */
    Set<BlockEntity> getBlockEntities();

    /**
     * Gets this chunk's current state.
     */
    int getState();

    /**
     * Atomically updates this chunk's state.
     *
     * @param next the new state to set
     * @return the chunk's previous state
     * @throws IllegalStateException if the new state is invalid, or the same as or lower than the current state
     */
    int setState(int next);

    default boolean isGenerated() {
        return this.getState() >= STATE_GENERATED;
    }

    default boolean isPopulated() {
        return this.getState() >= STATE_POPULATED;
    }

    default boolean isFinished() {
        return this.getState() >= STATE_FINISHED;
    }

    /**
     * Whether the chunk has changed since it was last loaded or saved.
     *
     * @return dirty
     */
    boolean isDirty();

    /**
     * Sets the chunk's dirty status.
     */
    default void setDirty() {
        this.setDirty(true);
    }

    /**
     * Sets the chunk's dirty status.
     *
     * @param dirty true if chunk is dirty
     */
    void setDirty(boolean dirty);

    /**
     * Clear chunk to a state as if it was not generated.
     */
    void initChunk();

    void clear();

    void save();

    /**
     * @return this chunk's key
     */
    default long key() {
        return (((long) getX()) << 32) | (getZ() & 0xffffffffL);
    }

    int getMaxHeight();

    int getMinHeight();

    Set<ChunkLoader> getLoaders();

    Set<Player> getPlayerLoaders();

    @Override
    default int compareTo(Chunk o) {
        //compare x positions, and use z position to break ties
        int x = Integer.compare(this.getX(), o.getX());
        return x != 0 ? x : Integer.compare(this.getZ(), o.getZ());
    }
}
