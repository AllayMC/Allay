package org.allaymc.server.world.chunk;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.chunk.*;
import org.allaymc.api.world.palette.Palette;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.StampedLock;

/**
 * Allay Project 5/30/2023
 *
 * @author Cool_Loong
 */
@ThreadSafe
@Slf4j
public class AllayChunk implements Chunk {
    protected final AllayUnsafeChunk unsafeChunk;
    protected final StampedLock blockLock;
    protected final StampedLock heightAndBiomeLock;
    protected final StampedLock lightLock;
    protected final Set<ChunkLoader> chunkLoaders;
    protected final Queue<BedrockPacket> chunkPacketQueue;

    public AllayChunk(AllayUnsafeChunk unsafeChunk) {
        this.unsafeChunk = unsafeChunk;
        this.blockLock = new StampedLock();
        this.heightAndBiomeLock = new StampedLock();
        this.lightLock = new StampedLock();
        this.chunkPacketQueue = new ConcurrentLinkedQueue<>();
        this.chunkLoaders = ObjectSets.synchronize(new ObjectOpenHashSet<>());
    }

    @Override
    public int getHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z) {
        long stamp = heightAndBiomeLock.tryOptimisticRead();
        try {
            for (; ; stamp = heightAndBiomeLock.readLock()) {
                if (stamp == 0L) continue;
                int result = unsafeChunk.getHeight(x, z);
                if (!heightAndBiomeLock.validate(stamp)) continue;
                return result;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) heightAndBiomeLock.unlockRead(stamp);
        }
    }

    @Override
    public short[] getHeights() {
        long stamp = heightAndBiomeLock.tryOptimisticRead();
        try {
            for (; ; stamp = heightAndBiomeLock.readLock()) {
                if (stamp == 0L) continue;
                short[] result = unsafeChunk.getHeights();
                if (!heightAndBiomeLock.validate(stamp)) continue;
                return result;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) heightAndBiomeLock.unlockRead(stamp);
        }
    }

    @Override
    public void setHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, @Range(from = -512, to = 511) int height) {
        long stamp = heightAndBiomeLock.writeLock();
        try {
            unsafeChunk.setHeight(x, z, height);
        } finally {
            heightAndBiomeLock.unlockWrite(stamp);
        }
    }

    @Override
    public void compareAndSetHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, @Range(from = -512, to = 511) int expectedValue, @Range(from = -512, to = 511) int newValue) {
        long stamp = heightAndBiomeLock.tryOptimisticRead();
        try {
            for (; ; stamp = heightAndBiomeLock.writeLock()) {
                if (stamp == 0L) continue;
                int oldValue = unsafeChunk.getHeight(x, z);
                if (!heightAndBiomeLock.validate(stamp)) continue;
                if (oldValue != expectedValue) break;
                stamp = heightAndBiomeLock.tryConvertToWriteLock(stamp);
                if (stamp == 0L) continue;
                unsafeChunk.setHeight(x, z, newValue);
                return;
            }
        } finally {
            if (StampedLock.isWriteLockStamp(stamp)) heightAndBiomeLock.unlockWrite(stamp);
        }
    }

    @Override
    public BlockState getBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int layer) {
        long stamp = blockLock.tryOptimisticRead();
        try {
            for (; ; stamp = blockLock.readLock()) {
                if (stamp == 0L) continue;
                BlockState result = unsafeChunk.getBlockState(x, y, z, layer);
                if (!blockLock.validate(stamp)) continue;
                return result;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) blockLock.unlockRead(stamp);
        }
    }

    @Override
    public void setBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BlockState blockState, int layer) {
        long stamp = blockLock.writeLock();
        try {
            unsafeChunk.setBlockState(x, y, z, blockState, layer);
        } finally {
            blockLock.unlockWrite(stamp);
        }
    }

    @Override
    public void compareAndSetBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BiomeType expectedValue, BiomeType newValue) {
        long stamp = heightAndBiomeLock.tryOptimisticRead();
        try {
            for (; ; stamp = heightAndBiomeLock.writeLock()) {
                if (stamp == 0L) continue;
                BiomeType oldValue = unsafeChunk.getBiome(x, y, z);
                if (!heightAndBiomeLock.validate(stamp)) continue;
                if (oldValue != expectedValue) break;
                stamp = heightAndBiomeLock.tryConvertToWriteLock(stamp);
                if (stamp == 0L) continue;
                unsafeChunk.setBiome(x, y, z, newValue);
                return;
            }
        } finally {
            if (StampedLock.isWriteLockStamp(stamp)) heightAndBiomeLock.unlockWrite(stamp);
        }
    }

    @Override
    public BiomeType getBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        long stamp = heightAndBiomeLock.tryOptimisticRead();
        try {
            for (; ; stamp = heightAndBiomeLock.readLock()) {
                if (stamp == 0L) continue;
                var biomeType = unsafeChunk.getBiome(x, y, z);
                if (!heightAndBiomeLock.validate(stamp)) continue;
                return biomeType;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) heightAndBiomeLock.unlockRead(stamp);
        }
    }

    @Override
    public void setBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BiomeType biomeType) {
        long stamp = heightAndBiomeLock.writeLock();
        try {
            unsafeChunk.setBiome(x, y, z, biomeType);
        } finally {
            heightAndBiomeLock.unlockWrite(stamp);
        }
    }

    @Override
    public void compareAndSetBlock(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BlockState expectedValue, BlockState newValue, int layer) {
        long stamp = blockLock.tryOptimisticRead();
        try {
            for (; ; stamp = blockLock.writeLock()) {
                if (stamp == 0L) continue;
                BlockState oldValue = unsafeChunk.getBlockState(x, y, z, layer);
                if (!blockLock.validate(stamp)) continue;
                if (oldValue != expectedValue) break;
                stamp = blockLock.tryConvertToWriteLock(stamp);
                if (stamp == 0L) continue;
                unsafeChunk.setBlockState(x, y, z, newValue, layer);
                return;
            }
        } finally {
            if (StampedLock.isWriteLockStamp(stamp)) blockLock.unlockWrite(stamp);
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
    public void batchProcess(UnsafeChunkOperate operate) {
        long stamp1 = blockLock.writeLock();
        long stamp2 = heightAndBiomeLock.writeLock();
        long stamp3 = lightLock.writeLock();
        try {
            operate.run(this.unsafeChunk);
        } finally {
            blockLock.unlockWrite(stamp1);
            heightAndBiomeLock.unlockWrite(stamp2);
            lightLock.unlockWrite(stamp3);
        }
    }

    @Override
    public UnsafeChunk toUnsafeChunk() {
        return unsafeChunk;
    }

    @Override
    public LevelChunkPacket createLevelChunkPacket() {
        if (Server.getInstance().getServerSettings().worldSettings().useSubChunkSendingSystem())
            return createLevelChunkPacketSubChunk();
        else return createLevelChunkPacketFullChunk();
    }

    private LevelChunkPacket createLevelChunkPacketFullChunk() {
        var levelChunkPacket = new LevelChunkPacket();
        levelChunkPacket.setChunkX(this.getX());
        levelChunkPacket.setChunkZ(this.getZ());
        levelChunkPacket.setCachingEnabled(false);
        levelChunkPacket.setRequestSubChunks(false);
        levelChunkPacket.setSubChunksLength(getDimensionInfo().chunkSectionSize());
        //Chunk encoding
        levelChunkPacket.setData(writeToNetwork());
        return levelChunkPacket;
    }

    private ByteBuf writeToNetwork() {
        var byteBuf = ByteBufAllocator.DEFAULT.buffer();
        // Write blocks
        for (int i = 0; i < getDimensionInfo().chunkSectionSize(); i++) {
            ChunkSection section = getOrCreateSection(i);
            section.writeToNetwork(byteBuf);
        }
        // Write biomes
        Palette<BiomeType> lastBiomes = null;
        for (var section : getSections()) {
            section.biomes().writeToNetwork(byteBuf, BiomeType::getId, lastBiomes);
            lastBiomes = section.biomes();
        }
        byteBuf.writeByte(0); // edu- border blocks
        // Write block entities
        var blockEntities = getBlockEntities().values();
        if (!blockEntities.isEmpty()) {
            try (var writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(byteBuf))) {
                for (var blockEntity : blockEntities) {
                    writer.writeTag(blockEntity.saveNBT());
                }
            } catch (IOException e) {
                log.error("Error while encoding block entities in chunk(x=" + getX() + ", z=" + getZ() + ")!", e);
            }
        }
        return byteBuf;
    }

    private LevelChunkPacket createLevelChunkPacketSubChunk() {
        var levelChunkPacket = new LevelChunkPacket();
        levelChunkPacket.setChunkX(this.getX());
        levelChunkPacket.setChunkZ(this.getZ());
        levelChunkPacket.setCachingEnabled(false);
        levelChunkPacket.setRequestSubChunks(true);
        //This value is used in the subchunk system to control the maximum value of sectionY requested by the client.
        levelChunkPacket.setSubChunkLimit(getDimensionInfo().chunkSectionSize());
        levelChunkPacket.setData(Unpooled.EMPTY_BUFFER);
        return levelChunkPacket;
    }

    @Override
    public ChunkState getState() {
        return unsafeChunk.getState();
    }

    @Override
    public void setState(ChunkState next) {
        unsafeChunk.setState(next);
    }

    @Override
    public void tick() {
        getBlockEntities().values().forEach(BlockEntity::tick);
        getEntities().values().forEach(Entity::tick);
    }

    @Override
    public DimensionInfo getDimensionInfo() {
        return unsafeChunk.getDimensionInfo();
    }

    @Override
    public int getX() {
        return unsafeChunk.getX();
    }

    @Override
    public int getZ() {
        return unsafeChunk.getZ();
    }

    @Override
    public void addEntity(Entity entity) {
        unsafeChunk.addEntity(entity);
    }

    @Nullable
    @Override
    public Entity removeEntity(long uniqueId) {
        return unsafeChunk.removeEntity(uniqueId);
    }

    @Override
    public @UnmodifiableView Map<Long, Entity> getEntities() {
        return unsafeChunk.getEntities();
    }

    @Override
    public void addBlockEntity(@NotNull BlockEntity blockEntity) {
        unsafeChunk.addBlockEntity(blockEntity);
    }

    @Override
    public BlockEntity removeBlockEntity(int x, int y, int z) {
        return unsafeChunk.removeBlockEntity(x, y, z);
    }

    @Override
    public BlockEntity getBlockEntity(int x, int y, int z) {
        return unsafeChunk.getBlockEntity(x, y, z);
    }

    @Override
    public @UnmodifiableView Map<Integer, BlockEntity> getBlockEntities() {
        return unsafeChunk.getBlockEntities();
    }

    @Override
    public Entity getEntity(long uniqueId) {
        return unsafeChunk.getEntity(uniqueId);
    }

    @Override
    @Nullable
    @ApiStatus.Internal
    public ChunkSection getSection(@Range(from = 0, to = 63) int y) {
        long stamp = blockLock.tryOptimisticRead();
        try {
            for (; ; stamp = blockLock.readLock()) {
                if (stamp == 0L) continue;
                ChunkSection section = unsafeChunk.getSection(y);
                if (!blockLock.validate(stamp)) continue;
                return section;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) blockLock.unlockRead(stamp);
        }
    }

    @Override
    @ApiStatus.Internal
    public ChunkSection[] getSections() {
        return unsafeChunk.getSections();
    }

    @Override
    @ApiStatus.Internal
    @NotNull
    public ChunkSection getOrCreateSection(@Range(from = 0, to = 63) int y) {
        long stamp = blockLock.writeLock();
        try {
            return unsafeChunk.getOrCreateSection(y);
        } finally {
            blockLock.unlockWrite(stamp);
        }
    }

    @Override
    public @UnmodifiableView Collection<BlockEntity> getSectionBlockEntities(int sectionY) {
        return unsafeChunk.getSectionBlockEntities(sectionY);
    }

    @Override
    public void sendChunkPackets() {
        if (chunkPacketQueue.isEmpty()) return;
        BedrockPacket packet;
        while ((packet = chunkPacketQueue.poll()) != null) {
            sendChunkPacket(packet);
        }
    }

    @Override
    public void addChunkPacket(BedrockPacket packet) {
        chunkPacketQueue.add(packet);
    }

    @Override
    @UnmodifiableView
    public Set<ChunkLoader> getChunkLoaders() {
        return Collections.unmodifiableSet(chunkLoaders);
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

    @Override
    public void sendChunkPacket(BedrockPacket packet) {
        for (ChunkLoader chunkLoader : chunkLoaders) {
            chunkLoader.handleChunkPacket(packet);
        }
    }
}
