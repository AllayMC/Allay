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
     * Chunk New
     */
    int STATE_NEW = 0;
    /**
     * Chunk has generated terrain
     */
    int STATE_GENERATED = 1;
    /**
     * Chunk have been filled with terrain
     */
    int STATE_POPULATED = 2;
    /**
     * Chunk Completion
     */
    int STATE_FINISHED = 3;

    /**
     * Gets or create section.
     *
     * @param y the section Y
     * @return the section or create section
     */
    ChunkSection getOrCreateSection(@NonNegative int y);

    /**
     * Get section.
     *
     * @param y the section Y
     * @return the section
     */
    @Nullable
    ChunkSection getSection(@NonNegative int y);

    /**
     * Set the section.
     *
     * @param y       the section Y
     * @param section the section
     * @return is success set the section
     */
    boolean setSection(@NonNegative float y, ChunkSection section);

    /**
     * Get sections
     *
     * @return the chunk all sections
     */
    ChunkSection[] getSections();

    /**
     * Get a BlockState.
     *
     * @param x the chunkX(0-15)
     * @param y the y
     * @param z the chunkZ(0-15)
     * @return the BlockState
     */
    default BlockState getBlock(int x, int y, int z) {
        return this.getBlock(x, y, z, 0);
    }

    /**
     * Get a BlockState.
     *
     * @param x     the chunkX(0-15)
     * @param y     the y
     * @param z     the chunkZ(0-15)
     * @param layer the data layer(0 or 1)
     * @return the BlockState
     */
    BlockState getBlock(int x, int y, int z, @NonNegative int layer);

    /**
     * Get and Set BlockState.
     *
     * @param x          the chunkX(0-15)
     * @param y          the y
     * @param z          the chunkZ(0-15)
     * @param blockState the BlockState to be set
     * @return the BlockState
     */
    default BlockState getAndSetBlock(int x, int y, int z, BlockState blockState) {
        return this.getAndSetBlock(x, y, z, 0, blockState);
    }

    /**
     * Get and Set BlockState.
     *
     * @param x          the chunkX(0-15)
     * @param y          the y
     * @param z          the chunkZ(0-15)
     * @param layer      the data layer(0 or 1)
     * @param blockState the BlockState to be set
     * @return the BlockState
     */
    BlockState getAndSetBlock(int x, int y, int z, @NonNegative int layer, BlockState blockState);

    /**
     * Set a BlockState.
     *
     * @param x          the chunkX(0-15)
     * @param y          the y
     * @param z          the chunkZ(0-15)
     * @param blockState the BlockState to be set
     */
    default void setBlock(int x, int y, int z, BlockState blockState) {
        this.setBlock(x, y, z, 0, blockState);
    }

    /**
     * Set a BlockState.
     *
     * @param x          the chunkX(0-15)
     * @param y          the y
     * @param z          the chunkZ(0-15)
     * @param layer      the data layer(0 or 1)
     * @param blockState the BlockState to be set
     */
    void setBlock(int x, int y, int z, @NonNegative int layer, BlockState blockState);

    /**
     * Get a biome id at pos
     *
     * @param x the chunkX(0-15)
     * @param y the y
     * @param z the chunkZ(0-15)
     * @return the biome id
     */
    int getBiomeId(int x, int y, int z);

    /**
     * set a biome id at pos
     *
     * @param x       the chunkX(0-15)
     * @param y       the y
     * @param z       the chunkZ(0-15)
     * @param biomeId the biomeId
     */
    void setBiomeId(int x, int y, int z, int biomeId);

    /**
     * set a biome at pos
     *
     * @param x     the chunkX(0-15)
     * @param y     the y
     * @param z     the chunkZ(0-15)
     * @param biome the biome
     */
    void setBiome(int x, int y, int z, Biome biome);

    /**
     * get a SkyLight at pos
     *
     * @param x the chunkX(0-15)
     * @param y the y
     * @param z the chunkZ(0-15)
     * @return SkyLight(0 - 15)
     */
    byte getSkyLight(int x, int y, int z);

    /**
     * set a SkyLight at pos
     *
     * @param x     the chunkX(0-15)
     * @param y     the y
     * @param z     the chunkZ(0-15)
     * @param level the SkyLight level(0 - 15)
     */
    void setSkyLight(int x, int y, int z, @NonNegative int level);

    /**
     * get a BlockLight at pos
     *
     * @param x the chunkX(0-15)
     * @param y the y
     * @param z the chunkZ(0-15)
     * @return BlockLight(0 - 15)
     */
    byte getBlockLight(int x, int y, int z);

    /**
     * set a SkyLight at pos
     *
     * @param x     the chunkX(0-15)
     * @param y     the y
     * @param z     the chunkZ(0-15)
     * @param level the BlockLight level(0 - 15)
     */
    void setBlockLight(int x, int y, int z, @NonNegative int level);

    /**
     * Get the highest block at pos.
     *
     * @param x the chunkX(0-15)
     * @param z the chunkZ(0-15)
     * @return the highest block
     */
    int getHighestBlock(int x, int z);

    /**
     * Add entity.
     *
     * @param entity the entity
     */
    void addEntity(@NotNull Entity entity);

    /**
     * Remove entity.
     *
     * @param entity the entity
     */
    void removeEntity(Entity entity);

    /**
     * Add block entity.
     *
     * @param blockEntity the block entity
     */
    void addBlockEntity(BlockEntity blockEntity);

    /**
     * Remove block entity.
     *
     * @param blockEntity the block entity
     */
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

    /**
     * Get a copy of the SkyLight array.
     *
     * @return height map
     */
    byte[] getBlockSkyLightArray();

    /**
     * Get a copy of the BlockLight array.
     *
     * @return the byte [ ]
     */
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

    int getMaxHeight();

    int getMinHeight();

    Set<ChunkLoader> getLoaders();

    Set<Player> getPlayerLoaders();

    /**
     * @return this chunk's key
     */
    default long key() {
        return (((long) getX()) << 32) | (getZ() & 0xffffffffL);
    }

    @Override
    default int compareTo(Chunk o) {
        //compare x positions, and use z position to break ties
        int x = Integer.compare(this.getX(), o.getX());
        return x != 0 ? x : Integer.compare(this.getZ(), o.getZ());
    }
}
