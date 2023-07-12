package cn.allay.server.world.chunk;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.data.VanillaBiomeId;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.biome.BiomeType;
import cn.allay.api.world.chunk.*;
import cn.allay.api.world.palette.Palette;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.locks.StampedLock;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Allay Project 5/30/2023
 *
 * @author Cool_Loong
 */
@ThreadSafe
public class AllayChunk extends AllayUnsafeChunk implements Chunk {
    protected final StampedLock sectionLock;
    protected final StampedLock heightLock;
    protected final StampedLock skyLightLock;
    protected final StampedLock blockLightLock;
    protected final Vector<ChunkLoader> chunkLoaders;

    public AllayChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        this(chunkX, chunkZ, dimensionInfo, NbtMap.EMPTY);
    }

    public AllayChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo, NbtMap data) {
        super(chunkX, chunkZ, dimensionInfo, data);
        this.sectionLock = new StampedLock();
        this.heightLock = new StampedLock();
        this.skyLightLock = new StampedLock();
        this.blockLightLock = new StampedLock();
        this.chunkLoaders = new Vector<>();
    }

    @Override
    public int getHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z) {
        long stamp = heightLock.tryOptimisticRead();
        try {
            for (; ; stamp = heightLock.readLock()) {
                if (stamp == 0L) continue;
                int result = super.getHeight(x, z);
                if (!heightLock.validate(stamp)) continue;
                return result;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) heightLock.unlockRead(stamp);
        }
    }

    @Override
    public void setHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, int height) {
        long stamp = heightLock.writeLock();
        try {
            super.setHeight(x, z, height);
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
                BlockState result = super.getBlockState(x, y, z, layer);
                if (!sectionLock.validate(stamp)) continue;
                return result;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) sectionLock.unlockRead(stamp);
        }
    }

    @Override
    public void setBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, boolean layer, BlockState blockState) {
        long stamp = sectionLock.writeLock();
        try {
            super.setBlockState(x, y, z, layer, blockState);
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
                BiomeType oldValue = super.getBiome(x, y, z);
                if (!sectionLock.validate(stamp)) continue;
                if (oldValue != expectedValue) break;
                stamp = sectionLock.tryConvertToWriteLock(stamp);
                if (stamp == 0L) continue;
                super.setBiome(x, y, z, newValue);
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
                var biomeType = super.getBiome(x, y, z);
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
            super.setBiome(x, y, z, biomeType);
        } finally {
            sectionLock.unlockWrite(stamp);
        }
    }

    @Override
    public void compareAndSetBlock(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, boolean layer, BlockState expectedValue, BlockState newValue) {
        long stamp = sectionLock.tryOptimisticRead();
        try {
            for (; ; stamp = sectionLock.writeLock()) {
                if (stamp == 0L) continue;
                BlockState oldValue = super.getBlockState(x, y, z, layer);
                if (!sectionLock.validate(stamp)) continue;
                if (oldValue != expectedValue) break;
                stamp = sectionLock.tryConvertToWriteLock(stamp);
                if (stamp == 0L) continue;
                super.setBlockState(x, y, z, layer, newValue);
                return;
            }
        } finally {
            if (StampedLock.isWriteLockStamp(stamp)) sectionLock.unlockWrite(stamp);
        }
    }

    @Override
    public @Range(from = 0, to = 15) int getBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        long stamp = blockLightLock.tryOptimisticRead();
        try {
            for (; ; stamp = blockLightLock.readLock()) {
                if (stamp == 0L) continue;
                int result = super.getBlockLight(x, y, z);
                if (!blockLightLock.validate(stamp)) continue;
                return result;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) blockLightLock.unlockRead(stamp);
        }
    }

    @Override
    public void setBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int light) {
        long stamp = blockLightLock.writeLock();
        try {
            super.setBlockLight(x, y, z, light);
        } finally {
            blockLightLock.unlockWrite(stamp);
        }
    }

    @Override
    public void compareAndSetBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int expectedValue, @Range(from = 0, to = 15) int newValue) {
        long stamp = blockLightLock.tryOptimisticRead();
        try {
            for (; ; stamp = blockLightLock.writeLock()) {
                if (stamp == 0L) continue;
                int oldValue = super.getBlockLight(x, y, z);
                if (!blockLightLock.validate(stamp)) continue;
                if (oldValue != expectedValue) break;
                stamp = blockLightLock.tryConvertToWriteLock(stamp);
                if (stamp == 0L) continue;
                super.setBlockLight(x, y, z, newValue);
                return;
            }
        } finally {
            if (StampedLock.isWriteLockStamp(stamp)) blockLightLock.unlockWrite(stamp);
        }
    }

    @Override
    public @Range(from = 0, to = 15) int getSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        long stamp = skyLightLock.tryOptimisticRead();
        try {
            for (; ; stamp = skyLightLock.readLock()) {
                if (stamp == 0L) continue;
                int result = super.getSkyLight(x, y, z);
                if (!skyLightLock.validate(stamp)) continue;
                return result;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) skyLightLock.unlockRead(stamp);
        }
    }

    @Override
    public void setSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int light) {
        long stamp = skyLightLock.writeLock();
        try {
            super.setSkyLight(x, y, z, light);
        } finally {
            skyLightLock.unlockWrite(stamp);
        }
    }

    @Override
    public void compareAndSetSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, @Range(from = 0, to = 15) int expectedValue, @Range(from = 0, to = 15) int newValue) {
        long stamp = skyLightLock.tryOptimisticRead();
        try {
            for (; ; stamp = skyLightLock.writeLock()) {
                if (stamp == 0L) continue;
                int oldValue = super.getSkyLight(x, y, z);
                if (!skyLightLock.validate(stamp)) continue;
                if (oldValue != expectedValue) break;
                stamp = skyLightLock.tryConvertToWriteLock(stamp);
                if (stamp == 0L) continue;
                super.setSkyLight(x, y, z, newValue);
                return;
            }
        } finally {
            if (StampedLock.isWriteLockStamp(stamp)) skyLightLock.unlockWrite(stamp);
        }
    }

    @Override
    public void batchProcess(Consumer<SectionOperate> sectionOperate,
                             Consumer<HeightOperate> heightOperate,
                             Consumer<SkyLightOperate> skyLightOperate,
                             Consumer<BlockLightOperate> blockLightOperate) {
        if (sectionOperate != null) {
            long stamp = sectionLock.writeLock();
            try {
                sectionOperate.accept(this);
            } finally {
                sectionLock.unlockWrite(stamp);
            }
        }
        if (heightOperate != null) {
            long stamp = heightLock.writeLock();
            try {
                heightOperate.accept(this);
            } finally {
                heightLock.unlockWrite(stamp);
            }
        }
        if (skyLightOperate != null) {
            long stamp = skyLightLock.writeLock();
            try {
                skyLightOperate.accept(this);
            } finally {
                skyLightLock.unlockWrite(stamp);
            }
        }
        if (blockLightOperate != null) {
            long stamp = blockLightLock.writeLock();
            try {
                blockLightOperate.accept(this);
            } finally {
                blockLightLock.unlockWrite(stamp);
            }
        }
    }

    @Override
    public LevelChunkPacket createLevelChunkPacket() {
        long stamp = sectionLock.writeLock();
        ByteBuf byteBuf = Unpooled.buffer();
        try {
            final LevelChunkPacket levelChunkPacket = new LevelChunkPacket();
            levelChunkPacket.setChunkX(this.chunkX);
            levelChunkPacket.setChunkZ(this.chunkZ);
            levelChunkPacket.setCachingEnabled(false);
            levelChunkPacket.setRequestSubChunks(false);
            levelChunkPacket.setSubChunksLength(computeNotNullSectionCount());
            writeChunkDataToBuffer(byteBuf.retain());
            levelChunkPacket.setData(byteBuf);
            return levelChunkPacket;
        } finally {
            byteBuf.release();
            sectionLock.unlockWrite(stamp);
        }
    }

    @Override
    @UnmodifiableView
    public Set<ChunkLoader> getChunkLoaders() {
        return chunkLoaders.stream().collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public void addChunkLoader(ChunkLoader chunkLoader) {
        chunkLoaders.add(chunkLoader);
    }

    @Override
    public void removeChunkLoader(ChunkLoader chunkLoader) {
        chunkLoaders.remove(chunkLoader);
    }

    @Override
    public int getChunkLoaderCount() {
        return chunkLoaders.size();
    }

    private void writeChunkDataToBuffer(ByteBuf retainedBuffer) {
        Palette<BiomeType> lastBiomes = new Palette<>(VanillaBiomeId.PLAINS);

        for (var sectionY = 0; sectionY < getDimensionInfo().chunkSectionSize(); sectionY++) {
            var section = getSection(sectionY);
            if (section == null) break;
            section.writeToNetwork(retainedBuffer);
        }

        for (var sectionY = 0; sectionY < getDimensionInfo().chunkSectionSize(); sectionY++) {
            var section = getSection(sectionY);
            if (section == null) {
                lastBiomes.writeToNetwork(retainedBuffer, BiomeType::getId, lastBiomes);
                continue;
            }

            section.biomes().writeToNetwork(retainedBuffer, BiomeType::getId);
            lastBiomes = section.biomes();
        }

        retainedBuffer.writeByte(0); // edu - border blocks

        //TODO: BlockEntity
//        Collection<BlockEntity> blockEntities = this.getBlockEntities();
//        if (!blockEntities.isEmpty()) {
//            try (NBTOutputStream writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(retainedBuffer))) {
//                for (BlockEntity blockEntity : blockEntities) {
//                    NbtMap tag = blockEntity.toCompound().build();
//                    writer.writeTag(tag);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    private int computeNotNullSectionCount() {
        for (var count = 0; count < getDimensionInfo().chunkSectionSize(); count++) {
            if (getSection(count) == null)
                return count;
        }
        return getDimensionInfo().chunkSectionSize();
    }
}
