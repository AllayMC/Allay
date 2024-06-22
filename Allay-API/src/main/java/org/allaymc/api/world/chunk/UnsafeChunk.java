package org.allaymc.api.world.chunk;

import com.google.common.base.Preconditions;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.biome.BiomeType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Collection;
import java.util.Map;

/**
 * Allay Project 2023/7/4
 *
 * @author Cool_Loong
 */
@NotThreadSafe
public interface UnsafeChunk {
    int SECTION_SIZE = 16 * 16 * 16;
    int CHUNK_VERSION = 40;

    @ApiStatus.Internal
    static int index(int x, int y, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(y >= 0 && y <= 15);
        Preconditions.checkArgument(z >= 0 && z <= 15);
        // The bedrock chunk order is xzy,the chunk order of java version is yzx
        return (x << 8) + (z << 4) + y;
    }

    ChunkState getState();

    @ApiStatus.Internal
    void setState(ChunkState next);

    DimensionInfo getDimensionInfo();

    int getX();

    int getZ();

    Entity getEntity(long runtimeId);

    @UnmodifiableView
    Map<Long, Entity> getEntities();

    BlockEntity removeBlockEntity(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    BlockEntity getBlockEntity(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    @UnmodifiableView
    Map<Integer, BlockEntity> getBlockEntities();

    void addBlockEntity(BlockEntity blockEntity);

    @ApiStatus.Internal
    ChunkSection[] getSections();

    @ApiStatus.Internal
    void beforeSetChunk(Dimension dimension);

    @ApiStatus.Internal
    void afterSetChunk(Dimension dimension);

    /**
     * Gets Chunk section, range -1 -> -4, 0 -> 59
     * <p>
     * Since the array index starts at 0, the maximum value is 59
     *
     * @param sectionY the sectionY
     *
     * @return the section
     */
    @ApiStatus.Internal
    ChunkSection getSection(int sectionY);

    /**
     * Gets Chunk section, range -1 -> -4, 0 -> 59
     * <p>
     * Since the array index starts at 0, the maximum value is 59
     *
     * @param sectionY the y
     *
     * @return the section
     */
    @ApiStatus.Internal
    ChunkSection getOrCreateSection(int sectionY);

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

    void setBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BlockState blockState, int layer);

    default void setBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BlockState blockState) {
        setBlockState(x, y, z, blockState, 0);
    }

    BlockState getBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, int layer);

    default BlockState getBlockState(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z) {
        return getBlockState(x, y, z, 0);
    }

    /**
     * Set height.
     *
     * @param x      the x
     * @param z      the z
     * @param height the height -64~319
     */
    void setHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, int height);

    /**
     * @return Different from getHeight(), all values in the short[] array returned by this method are
     * greater than or equal to 0 (can be understood as getHeight() - minHeight())
     */
    short[] getHeightArray();

    /**
     * get height.
     *
     * @param x the x
     * @param z the z
     *
     * @return the height  -64~319
     */
    int getHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z);

    void setBiome(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, BiomeType biomeType);

    BiomeType getBiome(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    void setSkyLight(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int light);

    int getSkyLight(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);

    void setBlockLight(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int light);

    default long computeChunkHash() {
        return HashUtils.hashXZ(getX(), getZ());
    }

    int getBlockLight(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z);
}
