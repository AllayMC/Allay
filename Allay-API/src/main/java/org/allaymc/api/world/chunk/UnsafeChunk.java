package org.allaymc.api.world.chunk;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.biome.BiomeType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.Nullable;
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

    ChunkState getState();

    @ApiStatus.Internal
    void setState(ChunkState next);

    DimensionInfo getDimensionInfo();

    int getX();

    int getZ();

    Entity getEntity(long uniqueId);

    @UnmodifiableView
    Map<Long, Entity> getEntities();

    void addBlockEntity(@NotNull BlockEntity blockEntity);

    BlockEntity removeBlockEntity(int x, int y, int z);

    BlockEntity getBlockEntity(int x, int y, int z);

    @UnmodifiableView
    Map<Integer, BlockEntity> getBlockEntities();

    /**
     * Gets Chunk section , range -1 -> -4 , 0 -> 59
     * <p>
     * Since the array index starts at 0, the maximum value is 59
     *
     * @param sectionY the sectionY
     * @return the section
     */
    @ApiStatus.Internal
    @Nullable
    ChunkSection getSection(@Range(from = -32, to = 31) int sectionY);

    @ApiStatus.Internal
    ChunkSection[] getSections();

    /**
     * Gets Chunk section , range -1 -> -4 , 0 -> 59
     * <p>
     * Since the array index starts at 0, the maximum value is 59
     *
     * @param sectionY the y
     * @return the section
     */
    @ApiStatus.Internal
    @NotNull
    ChunkSection getOrCreateSection(@Range(from = -32, to = 31) int sectionY);

    /**
     * Gets Chunk section , range -1 -> -4 , 0 -> 59
     * <p>
     * Since the array index starts at 0, the maximum value is 59
     *
     * @param sectionY the y
     * @return the section
     */
    @UnmodifiableView
    Collection<BlockEntity> getSectionBlockEntities(@Range(from = -32, to = 31) int sectionY);

    void setBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BlockState blockState, int layer);

    default void setBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BlockState blockState) {
        setBlockState(x, y, z, blockState, 0);
    }

    BlockState getBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int layer);

    default BlockState getBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        return getBlockState(x, y, z, 0);
    }

    void setHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, @Range(from = -512, to = 511) int height);

    int getHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z);

    short[] getHeights();

    void setBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BiomeType biomeType);

    BiomeType getBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z);

    void setSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int light);

    @Range(from = 0, to = 15) int getSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z);

    void setBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int light);

    @Range(from = 0, to = 15) int getBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z);

    default long computeChunkHash() {
        return HashUtils.hashXZ(getX(), getZ());
    }

    static @Range(from = 0, to = 4095) int index(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z) {
        //The bedrock chunk order is xzy,the chunk order of java version is yzx
        return (x << 8) + (z << 4) + y;
    }
}
