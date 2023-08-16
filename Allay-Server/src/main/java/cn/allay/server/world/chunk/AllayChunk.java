package cn.allay.server.world.chunk;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.biome.BiomeType;
import cn.allay.api.world.chunk.*;
import cn.allay.api.world.heightmap.HeightMapType;
import io.netty.buffer.Unpooled;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Set;
import java.util.concurrent.locks.StampedLock;
import java.util.function.Consumer;

/**
 * Allay Project 5/30/2023
 *
 * @author Cool_Loong
 */
@ThreadSafe
public class AllayChunk implements Chunk {
    protected final UnsafeChunk unsafeChunk;
    protected final StampedLock sectionLock;
    protected final StampedLock heightLock;
    protected final StampedLock lightLock;

    public AllayChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        this(chunkX, chunkZ, dimensionInfo, NbtMap.EMPTY);
    }

    public AllayChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo, NbtMap data) {
        this.unsafeChunk = new AllayUnsafeChunk(chunkX, chunkZ, dimensionInfo, data);
        this.sectionLock = new StampedLock();
        this.heightLock = new StampedLock();
        this.lightLock = new StampedLock();
    }

    @Override
    public int getHeight(HeightMapType type, @Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z) {
        long stamp = heightLock.tryOptimisticRead();
        try {
            for (; ; stamp = heightLock.readLock()) {
                if (stamp == 0L) continue;
                int result = unsafeChunk.getHeight(type, x, z);
                if (!heightLock.validate(stamp)) continue;
                return result;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) heightLock.unlockRead(stamp);
        }
    }

    @Override
    public void setHeight(HeightMapType type, @Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, int height) {
        long stamp = heightLock.writeLock();
        try {
            unsafeChunk.setHeight(type, x, z, height);
        } finally {
            heightLock.unlockWrite(stamp);
        }
    }

    @Override
    public BlockState getBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, boolean layer) {
        long stamp = sectionLock.tryOptimisticRead();
        try {
            for (; ; stamp = sectionLock.readLock()) {
                if (stamp == 0L) continue;
                BlockState result = unsafeChunk.getBlockState(x, y, z, layer);
                if (!sectionLock.validate(stamp)) continue;
                return result;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) sectionLock.unlockRead(stamp);
        }
    }

    @Override
    public void setBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BlockState blockState, boolean layer) {
        long stamp = sectionLock.writeLock();
        try {
            unsafeChunk.setBlockState(x, y, z, blockState, layer);
        } finally {
            sectionLock.unlockWrite(stamp);
        }
    }

    @Override
    public void compareAndSetBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BiomeType expectedValue, BiomeType newValue) {
        long stamp = sectionLock.tryOptimisticRead();
        try {
            for (; ; stamp = sectionLock.writeLock()) {
                if (stamp == 0L) continue;
                BiomeType oldValue = unsafeChunk.getBiome(x, y, z);
                if (!sectionLock.validate(stamp)) continue;
                if (oldValue != expectedValue) break;
                stamp = sectionLock.tryConvertToWriteLock(stamp);
                if (stamp == 0L) continue;
                unsafeChunk.setBiome(x, y, z, newValue);
                return;
            }
        } finally {
            if (StampedLock.isWriteLockStamp(stamp)) sectionLock.unlockWrite(stamp);
        }
    }

    @Override
    public BiomeType getBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        long stamp = sectionLock.tryOptimisticRead();
        try {
            for (; ; stamp = sectionLock.readLock()) {
                if (stamp == 0L) continue;
                var biomeType = unsafeChunk.getBiome(x, y, z);
                if (!sectionLock.validate(stamp)) continue;
                return biomeType;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) sectionLock.unlockRead(stamp);
        }
    }

    @Override
    public void setBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BiomeType biomeType) {
        long stamp = sectionLock.writeLock();
        try {
            unsafeChunk.setBiome(x, y, z, biomeType);
        } finally {
            sectionLock.unlockWrite(stamp);
        }
    }

    @Override
    public void compareAndSetBlock(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BlockState expectedValue, BlockState newValue, boolean layer) {
        long stamp = sectionLock.tryOptimisticRead();
        try {
            for (; ; stamp = sectionLock.writeLock()) {
                if (stamp == 0L) continue;
                BlockState oldValue = unsafeChunk.getBlockState(x, y, z, layer);
                if (!sectionLock.validate(stamp)) continue;
                if (oldValue != expectedValue) break;
                stamp = sectionLock.tryConvertToWriteLock(stamp);
                if (stamp == 0L) continue;
                unsafeChunk.setBlockState(x, y, z, newValue, layer);
                return;
            }
        } finally {
            if (StampedLock.isWriteLockStamp(stamp)) sectionLock.unlockWrite(stamp);
        }
    }

    @Override
    public @Range(from = 0, to = 15) int getBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        long stamp = lightLock.tryOptimisticRead();
        try {
            for (; ; stamp = lightLock.readLock()) {
                if (stamp == 0L) continue;
                int result = unsafeChunk.getBlockLight(x, y, z);
                if (!lightLock.validate(stamp)) continue;
                return result;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) lightLock.unlockRead(stamp);
        }
    }

    @Override
    public void setBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int light) {
        long stamp = lightLock.writeLock();
        try {
            unsafeChunk.setBlockLight(x, y, z, light);
        } finally {
            lightLock.unlockWrite(stamp);
        }
    }

    @Override
    public void compareAndSetBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int expectedValue, @Range(from = 0, to = 15) int newValue) {
        long stamp = lightLock.tryOptimisticRead();
        try {
            for (; ; stamp = lightLock.writeLock()) {
                if (stamp == 0L) continue;
                int oldValue = unsafeChunk.getBlockLight(x, y, z);
                if (!lightLock.validate(stamp)) continue;
                if (oldValue != expectedValue) break;
                stamp = lightLock.tryConvertToWriteLock(stamp);
                if (stamp == 0L) continue;
                unsafeChunk.setBlockLight(x, y, z, newValue);
                return;
            }
        } finally {
            if (StampedLock.isWriteLockStamp(stamp)) lightLock.unlockWrite(stamp);
        }
    }

    @Override
    public @Range(from = 0, to = 15) int getSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        long stamp = lightLock.tryOptimisticRead();
        try {
            for (; ; stamp = lightLock.readLock()) {
                if (stamp == 0L) continue;
                int result = unsafeChunk.getSkyLight(x, y, z);
                if (!lightLock.validate(stamp)) continue;
                return result;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) lightLock.unlockRead(stamp);
        }
    }

    @Override
    public void setSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int light) {
        long stamp = lightLock.writeLock();
        try {
            unsafeChunk.setSkyLight(x, y, z, light);
        } finally {
            lightLock.unlockWrite(stamp);
        }
    }

    @Override
    public void compareAndSetSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int expectedValue, @Range(from = 0, to = 15) int newValue) {
        long stamp = lightLock.tryOptimisticRead();
        try {
            for (; ; stamp = lightLock.writeLock()) {
                if (stamp == 0L) continue;
                int oldValue = unsafeChunk.getSkyLight(x, y, z);
                if (!lightLock.validate(stamp)) continue;
                if (oldValue != expectedValue) break;
                stamp = lightLock.tryConvertToWriteLock(stamp);
                if (stamp == 0L) continue;
                unsafeChunk.setSkyLight(x, y, z, newValue);
                return;
            }
        } finally {
            if (StampedLock.isWriteLockStamp(stamp)) lightLock.unlockWrite(stamp);
        }
    }

    @Override
    public void batchProcess(Consumer<SectionOperate> sectionOperate,
                             Consumer<HeightOperate> heightOperate,
                             Consumer<LightOperate> lightOperate) {
        if (sectionOperate != null) {
            long stamp = sectionLock.writeLock();
            try {
                sectionOperate.accept(unsafeChunk);
            } finally {
                sectionLock.unlockWrite(stamp);
            }
        }
        if (heightOperate != null) {
            long stamp = heightLock.writeLock();
            try {
                heightOperate.accept(unsafeChunk);
            } finally {
                heightLock.unlockWrite(stamp);
            }
        }
        if (lightOperate != null) {
            long stamp = lightLock.writeLock();
            try {
                lightOperate.accept(unsafeChunk);
            } finally {
                lightLock.unlockWrite(stamp);
            }
        }
    }

    @Override
    public LevelChunkPacket createLevelChunkPacket() {
        final LevelChunkPacket levelChunkPacket = new LevelChunkPacket();
        levelChunkPacket.setChunkX(this.getChunkX());
        levelChunkPacket.setChunkZ(this.getChunkZ());
        levelChunkPacket.setCachingEnabled(false);
        levelChunkPacket.setRequestSubChunks(true);
        levelChunkPacket.setSubChunkLimit(8);
        levelChunkPacket.setData(Unpooled.EMPTY_BUFFER);
        return levelChunkPacket;
    }

    @Override
    public DimensionInfo getDimensionInfo() {
        return unsafeChunk.getDimensionInfo();
    }

    @Override
    public int getChunkX() {
        return unsafeChunk.getChunkX();
    }

    @Override
    public void setChunkX(int chunkX) {
        unsafeChunk.setChunkX(chunkX);
    }

    @Override
    public int getChunkZ() {
        return unsafeChunk.getChunkZ();
    }

    @Override
    public void setChunkZ(int chunkZ) {
        unsafeChunk.setChunkZ(chunkZ);
    }

    @Override
    @Nullable
    @ApiStatus.Internal
    public ChunkSection getSection(int y) {
        long stamp = sectionLock.tryOptimisticRead();
        try {
            for (; ; stamp = sectionLock.readLock()) {
                if (stamp == 0L) continue;
                ChunkSection section = unsafeChunk.getSection(y);
                if (!sectionLock.validate(stamp)) continue;
                return section;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) sectionLock.unlockRead(stamp);
        }
    }

    @Override
    @ApiStatus.Internal
    @NotNull
    public ChunkSection createAndGetSection(int y) {
        long stamp = sectionLock.writeLock();
        try {
            return unsafeChunk.createAndGetSection(y);
        } finally {
            sectionLock.unlockWrite(stamp);
        }
    }

    @Override
    public @UnmodifiableView Set<ChunkLoader> getChunkLoaders() {
        return unsafeChunk.getChunkLoaders();
    }

    @Override
    public void addChunkLoader(ChunkLoader chunkLoader) {
        unsafeChunk.addChunkLoader(chunkLoader);
    }

    @Override
    public void removeChunkLoader(ChunkLoader chunkLoader) {
        unsafeChunk.removeChunkLoader(chunkLoader);
    }

    @Override
    public int getChunkLoaderCount() {
        return unsafeChunk.getChunkLoaderCount();
    }

    @Override
    public void addChunkPacket(BedrockPacket packet) {
        unsafeChunk.addChunkPacket(packet);
    }

    @Override
    public void sendChunkPacket(BedrockPacket packet) {
        unsafeChunk.sendChunkPacket(packet);
    }

    @Override
    public void sendChunkPackets() {
        unsafeChunk.sendChunkPackets();
    }
}
