package org.allaymc.api.world.chunk;

import com.google.common.base.Preconditions;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.common.utils.HashUtils;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.biome.BiomeType;
import org.jetbrains.annotations.ApiStatus;
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

    ChunkState getState();

    @ApiStatus.Internal
    void setState(ChunkState next);

    DimensionInfo getDimensionInfo();

    int getX();

    int getZ();

    Entity getEntity(long runtimeId);

    @UnmodifiableView
    Map<Long, Entity> getEntities();

    static int index(int x, int y, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(y >= 0 && y <= 15);
        Preconditions.checkArgument(z >= 0 && z <= 15);
        //The bedrock chunk order is xzy,the chunk order of java version is yzx
        return (x << 8) + (z << 4) + y;
    }

    BlockEntity removeBlockEntity(int x, int y, int z);

    BlockEntity getBlockEntity(int x, int y, int z);

    @UnmodifiableView
    Map<Integer, BlockEntity> getBlockEntities();

    void addBlockEntity(BlockEntity blockEntity);

    @ApiStatus.Internal
    ChunkSection[] getSections();

    @ApiStatus.Internal
    void init(Dimension dimension);

    /**
     * Gets Chunk section , range -1 -> -4 , 0 -> 59
     * <p>
     * Since the array index starts at 0, the maximum value is 59
     *
     * @param sectionY the sectionY
     * @return the section
     */
    @ApiStatus.Internal
    ChunkSection getSection(int sectionY);

    /**
     * Gets Chunk section , range -1 -> -4 , 0 -> 59
     * <p>
     * Since the array index starts at 0, the maximum value is 59
     *
     * @param sectionY the y
     * @return the section
     */
    @ApiStatus.Internal
    ChunkSection getOrCreateSection(int sectionY);

    /**
     * Gets Chunk section , range -1 -> -4 , 0 -> 59
     * <p>
     * Since the array index starts at 0, the maximum value is 59
     *
     * @param sectionY the y
     * @return the section
     */
    @UnmodifiableView
    Collection<BlockEntity> getSectionBlockEntities(int sectionY);

    void setBlockState(int x, int y, int z, BlockState blockState, int layer);

    default void setBlockState(int x, int y, int z, BlockState blockState) {
        setBlockState(x, y, z, blockState, 0);
    }

    BlockState getBlockState(int x, int y, int z, int layer);

    default BlockState getBlockState(int x, int y, int z) {
        return getBlockState(x, y, z, 0);
    }

    void setHeight(int x, int z, int height);

    /**
     * 不同于getHeight(), 此方法返回的short[]数组中的所有值都大于等于0（可以理解为getHeight() - minHeight()）
     *
     * @return 高度图
     */
    short[] getHeightArray();

    int getHeight(int x, int z);

    void setBiome(int x, int y, int z, BiomeType biomeType);

    BiomeType getBiome(int x, int y, int z);

    void setSkyLight(int x, int y, int z, int light);

    int getSkyLight(int x, int y, int z);

    void setBlockLight(int x, int y, int z, int light);

    default long computeChunkHash() {
        return HashUtils.hashXZ(getX(), getZ());
    }

    int getBlockLight(int x, int y, int z);
}
