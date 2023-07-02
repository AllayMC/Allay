package cn.allay.server.world.chunk;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.chunk.ChunkSection;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Allay Project 5/30/2023
 *
 * @author Cool_Loong
 */
@ThreadSafe
public class AllayChunk extends AllayRawChunk {
    protected final ReadWriteLock readWriteLock;

    public AllayChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        super(chunkX, chunkZ, dimensionInfo);
        this.readWriteLock = new ReentrantReadWriteLock();
    }

    public AllayChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo, NbtMap data) {
        super(chunkX, chunkZ, dimensionInfo, data);
        this.readWriteLock = new ReentrantReadWriteLock();
    }

    protected @Nullable ChunkSection getSection(int y) {
        synchronized (this.sections) {
            return super.getSection(y);
        }
    }

    protected @NotNull ChunkSection getOrCreateSection(int y) {
        synchronized (this.sections) {
            return super.getOrCreateSection(y);
        }
    }

    @Override
    public void setSection(@Range(from = -32, to = 31) int y, ChunkSection section) {
        synchronized (this.sections) {
            super.setSection(y, section);
        }
    }

    @Override
    public int getHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z) {
        synchronized (this.heightMap) {
            return super.getHeight(x, z);
        }
    }

    @Override
    public void setHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, int height) {
        synchronized (this.heightMap) {
            super.setHeight(x, z, height);
        }
    }


    @Override
    public BlockState getBlock(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, boolean layer) {
        readWriteLock.readLock().lock();
        try {
            return super.getBlock(x, y, z, layer);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public void setBlock(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, boolean layer, BlockState blockState) {
        readWriteLock.writeLock().lock();
        try {
            super.setBlock(x, y, z, layer, blockState);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void compareAndSetBlock(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, boolean layer, BlockState expectedValue, BlockState newValue) {
        readWriteLock.writeLock().lock();
        try {
            super.compareAndSetBlock(x, y, z, layer, expectedValue, newValue);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public @Range(from = 0, to = 15) byte getBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        readWriteLock.readLock().lock();
        try {
            return super.getBlockLight(x, y, z);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public @Range(from = 0, to = 15) byte getSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        readWriteLock.readLock().lock();
        try {
            return super.getSkyLight(x, y, z);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public void setBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, byte light) {
        readWriteLock.writeLock().lock();
        try {
            super.setBlockLight(x, z, z, light);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void setSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, byte light) {
        readWriteLock.writeLock().lock();
        try {
            super.setSkyLight(x, z, z, light);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
