package cn.allay.server.world.chunk;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.chunk.ChunkSection;
import cn.allay.api.world.heightmap.HeightMap;
import cn.allay.api.world.heightmap.HeightType;
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
public class AnvilChunk implements Chunk {
    private final List<ChunkSection> sections;
    //WORLD_SURFACE,OCEAN_FLOOR,MOTION_BLOCKING,MOTION_BLOCKING_NO_LEAVES
    private final HeightMap[] heightMaps;
    private final DimensionInfo dimensionData;
    private final ReadWriteLock readWriteLock;

    public AnvilChunk(NbtMap data, DimensionInfo dimensionData) {
        this.sections = new ArrayList<>(16);
        //Only one height map is used now, and more may be used in the future
        this.heightMaps = new HeightMap[]{new HeightMap(new long[37])};
        this.dimensionData = dimensionData;
        this.readWriteLock = new ReentrantReadWriteLock();
    }

    @Override
    public void tick() {
        //TODO
    }

    @Override
    public DimensionInfo getDimensionData() {
        return dimensionData;
    }

    @Override
    public @Nullable ChunkSection getSection(@Range(from = 0, to = 23) int y) {
        try {
            readWriteLock.readLock().lock();
            return sections.get(y);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public @NotNull ChunkSection getOrCreateSection(@Range(from = 0, to = 23) int y) {
        try {
            readWriteLock.writeLock().lock();
            ChunkSection chunkSection = sections.get(y);
            if (chunkSection == null) {
                chunkSection = new AnvilChunkSection(readWriteLock);
                this.sections.set(y, chunkSection);
            }
            return chunkSection;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public int getHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, HeightType type) {
        try {
            readWriteLock.readLock().lock();
            return this.heightMaps[type.ordinal()].get(x, z);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public void setHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, HeightType type, int height) {
        try {
            readWriteLock.writeLock().lock();
            this.heightMaps[type.ordinal()].set(x, z, height);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }


    @Override
    public BlockState getBlock(@Range(from = 0, to = 15) int x, @Range(from = -64, to = 319) int y, @Range(from = 0, to = 15) int z, boolean layer) {
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
    public void setBlock(@Range(from = 0, to = 15) int x, @Range(from = -64, to = 319) int y, @Range(from = 0, to = 15) int z, boolean layer, BlockState blockState) {
        y = normalY(y) >>> 4;
        ChunkSection section = this.getSection(y);
        if (section == null) {
            section = this.getOrCreateSection(y);
        }
        section.setBlock(x, y & 0xf, z, layer, blockState);
    }

    @Override
    public @Range(from = 0, to = 15) byte getBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -64, to = 319) int y, @Range(from = 0, to = 15) int z) {
        y = normalY(y) >>> 4;
        ChunkSection section = this.getSection(y);
        return section == null ? 0 : section.getBlockLight(x, y & 0xf, z);
    }

    @Override
    public @Range(from = 0, to = 15) byte getSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -64, to = 319) int y, @Range(from = 0, to = 15) int z) {
        y = normalY(y) >>> 4;
        ChunkSection section = this.getSection(y);
        return section == null ? 0 : section.getSkyLight(x, y & 0xf, z);
    }

    @Override
    public void setBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -64, to = 319) int y, @Range(from = 0, to = 15) int z, byte light) {
        y = normalY(y) >>> 4;
        this.getOrCreateSection(y).setBlockLight(x, y & 0xf, z, light);
    }

    @Override
    public void setSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -64, to = 319) int y, @Range(from = 0, to = 15) int z, byte light) {
        y = normalY(y) >>> 4;
        this.getOrCreateSection(y).setSkyLight(x, y & 0xf, z, light);
    }

    private int normalY(int y) {
        if (this.getDimensionData().chunkSectionSize() == 24) {
            return y + 64;
        } else return y;
    }
}
