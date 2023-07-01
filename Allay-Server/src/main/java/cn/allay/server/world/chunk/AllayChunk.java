package cn.allay.server.world.chunk;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.chunk.ChunkSection;
import cn.allay.api.world.heightmap.HeightMap;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Allay Project 5/30/2023
 *
 * @author Cool_Loong
 */
@ThreadSafe
public class AllayChunk implements Chunk {
    private final List<ChunkSection> sections;
    private final HeightMap heightMap;
    private final DimensionInfo dimensionInfo;
    private final ReadWriteLock readWriteLock;

    public AllayChunk(DimensionInfo dimensionInfo) {
        this.sections = new ArrayList<>(dimensionInfo.chunkSectionSize());
        this.heightMap = new HeightMap();
        this.dimensionInfo = dimensionInfo;
        this.readWriteLock = new ReentrantReadWriteLock();
    }

    public AllayChunk(NbtMap data, DimensionInfo dimensionInfo) {
        //todo complete create chunk from nbt data
        this.sections = new ArrayList<>(dimensionInfo.chunkSectionSize());
        this.heightMap = new HeightMap();
        this.dimensionInfo = dimensionInfo;
        this.readWriteLock = new ReentrantReadWriteLock();
    }

    @Override
    public DimensionInfo getDimensionInfo() {
        return dimensionInfo;
    }

    @Override
    public @Nullable ChunkSection getSection(@Range(from = 0, to = 63) int y) {
        try {
            readWriteLock.readLock().lock();
            return sections.get(y);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public @NotNull ChunkSection getOrCreateSection(@Range(from = 0, to = 63) int y) {
        try {
            readWriteLock.writeLock().lock();
            ChunkSection chunkSection = sections.get(y);
            if (chunkSection == null) {
                chunkSection = new AllayChunkSection(readWriteLock);
                this.sections.set(y, chunkSection);
            }
            return chunkSection;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public int getHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z) {
        try {
            readWriteLock.readLock().lock();
            return this.heightMap.get(x, z);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public void setHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, int height) {
        try {
            readWriteLock.writeLock().lock();
            this.heightMap.set(x, z, height);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }


    @Override
    public BlockState getBlock(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, boolean layer) {
        y = normalY(y) >>> 4;
        ChunkSection section = this.getSection(y);
        BlockState blockState;
        if (section == null) {
            blockState = BlockType.AIR.getDefaultState();
        } else {
            blockState = section.getBlock(x, y & 0xf, z, layer);
        }
        return blockState;
    }

    @Override
    public void setBlock(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, boolean layer, BlockState blockState) {
        y = normalY(y) >>> 4;
        ChunkSection section = this.getSection(y);
        if (section == null) {
            section = this.getOrCreateSection(y);
        }
        section.setBlock(x, y & 0xf, z, layer, blockState);
    }

    @Override
    public @Range(from = 0, to = 15) byte getBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        y = normalY(y) >>> 4;
        ChunkSection section = this.getSection(y);
        return section == null ? 0 : section.getBlockLight(x, y & 0xf, z);
    }

    @Override
    public @Range(from = 0, to = 15) byte getSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        y = normalY(y) >>> 4;
        ChunkSection section = this.getSection(y);
        return section == null ? 0 : section.getSkyLight(x, y & 0xf, z);
    }

    @Override
    public void setBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, byte light) {
        y = normalY(y) >>> 4;
        this.getOrCreateSection(y).setBlockLight(x, y & 0xf, z, light);
    }

    @Override
    public void setSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, byte light) {
        y = normalY(y) >>> 4;
        this.getOrCreateSection(y).setSkyLight(x, y & 0xf, z, light);
    }

    private int normalY(int y) {
        return y - getDimensionInfo().minHeight();
    }
}
