package cn.allay.server.world.chunk;

import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.world.chunk.ChunkSection;
import cn.allay.api.world.palette.Palette;
import cn.allay.server.utils.NibbleArray;
import org.jetbrains.annotations.Range;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Allay Project 2023/5/30
 *
 * @author Cool_Loong
 */
@NotThreadSafe
public class AnvilChunkSection implements ChunkSection {
    private final Palette<Integer> blockLayer0;
    private final Palette<Integer> blockLayer1;
    private final NibbleArray blockLights;
    private final NibbleArray skyLights;
    private final ReadWriteLock parentChunkLock;
    //todo biome

    public AnvilChunkSection(ReadWriteLock parentChunkLock) {
        blockLayer0 = new Palette<>(BlockType.AIR.getDefaultState().blockStateHash());
        blockLayer1 = new Palette<>(BlockType.AIR.getDefaultState().blockStateHash());
        blockLights = new NibbleArray(2048);
        skyLights = new NibbleArray(2048);
        this.parentChunkLock = parentChunkLock;
    }

    @Override
    public BlockState getBlock(int x, int y, int z, boolean layer) {
        try {
            parentChunkLock.readLock().lock();
            if (layer) {
                return BlockStateHashPalette.getRegistry().get(blockLayer1.get(index(x, y, z)));
            } else {
                return BlockStateHashPalette.getRegistry().get(blockLayer0.get(index(x, y, z)));
            }
        } finally {
            parentChunkLock.readLock().unlock();
        }
    }

    @Override
    public void setBlock(int x, int y, int z, boolean layer, BlockState blockState) {
        try {
            parentChunkLock.writeLock().lock();
            if (layer) {
                blockLayer1.set(index(x, y, z), blockState.blockStateHash());
            } else {
                blockLayer0.set(index(x, y, z), blockState.blockStateHash());
            }
        } finally {
            parentChunkLock.writeLock().unlock();
        }
    }

    @Override
    public byte getBlockLight(int x, int y, int z) {
        try {
            parentChunkLock.readLock().lock();
            return blockLights.get(index(x, y, z));
        } finally {
            parentChunkLock.readLock().unlock();
        }
    }

    @Override
    public byte getSkyLight(int x, int y, int z) {
        try {
            parentChunkLock.readLock().lock();
            return skyLights.get(index(x, y, z));
        } finally {
            parentChunkLock.readLock().unlock();
        }
    }

    @Override
    public void setBlockLight(int x, int y, int z, byte light) {
        try {
            parentChunkLock.writeLock().lock();
            blockLights.set(index(x, y, z), light);
        } finally {
            parentChunkLock.writeLock().unlock();
        }
    }

    @Override
    public void setSkyLight(int x, int y, int z, byte light) {
        try {
            parentChunkLock.writeLock().lock();
            skyLights.set(index(x, y, z), light);
        } finally {
            parentChunkLock.writeLock().unlock();
        }
    }

    private @Range(from = 0, to = 4095) int index(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z) {
        //The chunk block order of je 1.19 is yzx
        return (y << 8) + (z << 4) + x;
    }
}
