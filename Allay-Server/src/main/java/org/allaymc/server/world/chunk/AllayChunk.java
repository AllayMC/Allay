package org.allaymc.server.world.chunk;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.util.internal.PlatformDependent;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.chunk.*;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.StampedLock;
import java.util.function.Predicate;

/**
 * Allay Project 5/30/2023
 *
 * @author Cool_Loong
 */
@ThreadSafe
@Slf4j
@RequiredArgsConstructor
public class AllayChunk implements Chunk {
    protected final AllayUnsafeChunk unsafeChunk;

    protected final StampedLock blockLock = new StampedLock();
    protected final StampedLock heightAndBiomeLock = new StampedLock();
    protected final StampedLock lightLock = new StampedLock();
    // No need to use concurrent-safe set as addChunkLoader() & removeChunkLoader() are only used in AllayChunkService which is single-thread
    protected final Set<ChunkLoader> chunkLoaders = new ObjectOpenHashSet<>();
    protected final Queue<ChunkPacketEntry> chunkPacketQueue = PlatformDependent.newMpscQueue();

    // Whether the chunk has been loaded into the world
    @Getter
    protected boolean loaded = false;
    // The callback to be called when the chunk is loaded into the world
    @Setter
    protected Runnable chunkSetCallback = () -> {};

    private static void checkXZ(int x, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(z >= 0 && z <= 15);
    }

    private static void checkXYZ(int x, int y, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(y >= -512 && y <= 511);
        Preconditions.checkArgument(z >= 0 && z <= 15);
    }

    public void tick(long currentTick) {
        getBlockEntities().values().forEach(blockEntity -> blockEntity.tick(currentTick));
        getEntities().values().forEach(entity -> entity.tick(currentTick));
    }

    @Override
    public short[] getHeightArray() {
        var stamp = heightAndBiomeLock.tryOptimisticRead();
        try {
            for (; ; stamp = heightAndBiomeLock.readLock()) {
                if (stamp == 0L) continue;
                var result = unsafeChunk.getHeightArray();
                if (!heightAndBiomeLock.validate(stamp)) continue;
                return result;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) heightAndBiomeLock.unlockRead(stamp);
        }
    }

    @Override
    public int getHeight(int x, int z) {
        checkXZ(x, z);
        var stamp = heightAndBiomeLock.tryOptimisticRead();
        try {
            for (; ; stamp = heightAndBiomeLock.readLock()) {
                if (stamp == 0L) continue;
                var result = unsafeChunk.getHeight(x, z);
                if (!heightAndBiomeLock.validate(stamp)) continue;
                return result;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) heightAndBiomeLock.unlockRead(stamp);
        }
    }

    @Override
    public void setHeight(int x, int z, int height) {
        checkXZ(x, z);
        Preconditions.checkArgument(height >= -512 && height <= 511);
        var stamp = heightAndBiomeLock.writeLock();
        try {
            unsafeChunk.setHeight(x, z, height);
        } finally {
            heightAndBiomeLock.unlockWrite(stamp);
        }
    }

    @Override
    public void compareAndSetHeight(int x, int z, int expectedValue, int newValue) {
        checkXZ(x, z);
        Preconditions.checkArgument(expectedValue >= -512 && expectedValue <= 511);
        Preconditions.checkArgument(newValue >= -512 && newValue <= 511);
        var stamp = heightAndBiomeLock.tryOptimisticRead();
        try {
            for (; ; stamp = heightAndBiomeLock.writeLock()) {
                if (stamp == 0L) continue;
                var oldValue = unsafeChunk.getHeight(x, z);
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
    public BlockState getBlockState(int x, int y, int z, int layer) {
        // Do not check y here, as if y is invalid we will return air
        // See test AllayChunkTest.testInvalidGetBlockStateMethodCall()
        checkXZ(x, z);
        var stamp = blockLock.tryOptimisticRead();
        try {
            for (; ; stamp = blockLock.readLock()) {
                if (stamp == 0L) continue;
                var result = unsafeChunk.getBlockState(x, y, z, layer);
                if (!blockLock.validate(stamp)) continue;
                return result;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) blockLock.unlockRead(stamp);
        }
    }

    @Override
    public void setBlockState(int x, int y, int z, BlockState blockState, int layer) {
        checkXYZ(x, y, z);
        var stamp = blockLock.writeLock();
        try {
            unsafeChunk.setBlockState(x, y, z, blockState, layer);
        } finally {
            blockLock.unlockWrite(stamp);
        }
    }

    @Override
    public void compareAndSetBiome(int x, int y, int z, BiomeType expectedValue, BiomeType newValue) {
        checkXYZ(x, y, z);
        var stamp = heightAndBiomeLock.tryOptimisticRead();
        try {
            for (; ; stamp = heightAndBiomeLock.writeLock()) {
                if (stamp == 0L) continue;
                var oldValue = unsafeChunk.getBiome(x, y, z);
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
    public BiomeType getBiome(int x, int y, int z) {
        checkXYZ(x, y, z);
        var stamp = heightAndBiomeLock.tryOptimisticRead();
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
    public void setBiome(int x, int y, int z, BiomeType biomeType) {
        checkXYZ(x, y, z);
        var stamp = heightAndBiomeLock.writeLock();
        try {
            unsafeChunk.setBiome(x, y, z, biomeType);
        } finally {
            heightAndBiomeLock.unlockWrite(stamp);
        }
    }

    @Override
    public void compareAndSetBlock(int x, int y, int z, BlockState expectedValue, BlockState newValue, int layer) {
        checkXYZ(x, y, z);
        var stamp = blockLock.tryOptimisticRead();
        try {
            for (; ; stamp = blockLock.writeLock()) {
                if (stamp == 0L) continue;
                var oldValue = unsafeChunk.getBlockState(x, y, z, layer);
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
    public int getBlockLight(int x, int y, int z) {
        checkXYZ(x, y, z);
        var stamp = lightLock.tryOptimisticRead();
        try {
            for (; ; stamp = lightLock.readLock()) {
                if (stamp == 0L) continue;
                var result = unsafeChunk.getBlockLight(x, y, z);
                if (!lightLock.validate(stamp)) continue;
                return result;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) lightLock.unlockRead(stamp);
        }
    }

    @Override
    public void setBlockLight(int x, int y, int z, int light) {
        checkXYZ(x, y, z);
        var stamp = lightLock.writeLock();
        try {
            unsafeChunk.setBlockLight(x, y, z, light);
        } finally {
            lightLock.unlockWrite(stamp);
        }
    }

    @Override
    public void compareAndSetBlockLight(int x, int y, int z, int expectedValue, int newValue) {
        checkXYZ(x, y, z);
        checkXZ(expectedValue, newValue);
        var stamp = lightLock.tryOptimisticRead();
        try {
            for (; ; stamp = lightLock.writeLock()) {
                if (stamp == 0L) continue;
                var oldValue = unsafeChunk.getBlockLight(x, y, z);
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
    public int getSkyLight(int x, int y, int z) {
        checkXYZ(x, y, z);
        var stamp = lightLock.tryOptimisticRead();
        try {
            for (; ; stamp = lightLock.readLock()) {
                if (stamp == 0L) continue;
                var result = unsafeChunk.getSkyLight(x, y, z);
                if (!lightLock.validate(stamp)) continue;
                return result;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) lightLock.unlockRead(stamp);
        }
    }

    @Override
    public void setSkyLight(int x, int y, int z, int light) {
        checkXYZ(x, y, z);
        var stamp = lightLock.writeLock();
        try {
            unsafeChunk.setSkyLight(x, y, z, light);
        } finally {
            lightLock.unlockWrite(stamp);
        }
    }

    @Override
    public void compareAndSetSkyLight(int x, int y, int z, int expectedValue, int newValue) {
        checkXYZ(x, y, z);
        checkXZ(expectedValue, newValue);
        var stamp = lightLock.tryOptimisticRead();
        try {
            for (; ; stamp = lightLock.writeLock()) {
                if (stamp == 0L) continue;
                var oldValue = unsafeChunk.getSkyLight(x, y, z);
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
        var stamp1 = blockLock.writeLock();
        var stamp2 = heightAndBiomeLock.writeLock();
        var stamp3 = lightLock.writeLock();
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

    public LevelChunkPacket createSubChunkLevelChunkPacket() {
        var levelChunkPacket = new LevelChunkPacket();
        levelChunkPacket.setDimension(getDimensionInfo().dimensionId());
        levelChunkPacket.setChunkX(this.getX());
        levelChunkPacket.setChunkZ(this.getZ());
        levelChunkPacket.setCachingEnabled(false);
        levelChunkPacket.setRequestSubChunks(true);
        //This value is used in the subchunk system to control the maximum value of sectionY requested by the client.
        levelChunkPacket.setSubChunkLimit(getDimensionInfo().chunkSectionSize());
        levelChunkPacket.setData(Unpooled.EMPTY_BUFFER);
        return levelChunkPacket;
    }

    public LevelChunkPacket createFullLevelChunkPacketChunk() {
        var levelChunkPacket = new LevelChunkPacket();
        levelChunkPacket.setDimension(getDimensionInfo().dimensionId());
        levelChunkPacket.setChunkX(this.getX());
        levelChunkPacket.setChunkZ(this.getZ());
        levelChunkPacket.setCachingEnabled(false);
        levelChunkPacket.setRequestSubChunks(false);
        levelChunkPacket.setSubChunksLength(getDimensionInfo().chunkSectionSize());
        try {
            levelChunkPacket.setData(writeToNetwork());
        } catch (Throwable t) {
            levelChunkPacket.setData(Unpooled.EMPTY_BUFFER);
        }
        return levelChunkPacket;
    }

    private void fillNullSections() {
        var stamp = blockLock.writeLock();
        try {
            var dimensionInfo = getDimensionInfo();
            for (int i = dimensionInfo.minSectionY(); i <= dimensionInfo.maxSectionY(); i++) {
                if (unsafeChunk.getSection(i) == null) {
                    unsafeChunk.getSections()[i - dimensionInfo.minSectionY()] = new ChunkSection((byte) i);
                }
            }
        } finally {
            blockLock.unlockWrite(stamp);
        }
    }

    private ByteBuf writeToNetwork() {
        var byteBuf = ByteBufAllocator.DEFAULT.buffer();
        try {
            writeToNetwork0(byteBuf);
            return byteBuf;
        } catch (Throwable t) {
            log.error("Error while encoding chunk(x={}, z={})!", getX(), getZ(), t);
            byteBuf.release();
            throw t;
        }
    }

    private void writeToNetwork0(ByteBuf byteBuf) {
        // Prevent null section
        fillNullSections();
        // Write blocks
        for (int i = getDimensionInfo().minSectionY(); i <= getDimensionInfo().maxSectionY(); i++) {
            Objects.requireNonNull(getSection(i)).writeToNetwork(byteBuf);
        }
        // Write biomes
        Arrays.stream(getSections()).forEach(section -> section.biomes().writeToNetwork(byteBuf, BiomeType::getId));
        byteBuf.writeByte(0); // edu- border blocks
        // Write block entities
        var blockEntities = getBlockEntities().values();
        if (!blockEntities.isEmpty()) {
            try (var writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(byteBuf))) {
                for (var blockEntity : blockEntities) {
                    writer.writeTag(blockEntity.saveNBT());
                }
            } catch (IOException e) {
                log.error("Error while encoding block entities in chunk(x={}, z={})!", getX(), getZ(), e);
            }
        }
    }

    @Override
    public ChunkState getState() {
        return unsafeChunk.getState();
    }

    public void setState(ChunkState next) {
        unsafeChunk.setState(next);
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

    public void addEntity(Entity entity) {
        unsafeChunk.addEntity(entity);
    }

    public Entity removeEntity(long runtimeId) {
        return unsafeChunk.removeEntity(runtimeId);
    }

    @Override
    public @UnmodifiableView Map<Long, Entity> getEntities() {
        return unsafeChunk.getEntities();
    }

    @Override
    public void addBlockEntity(BlockEntity blockEntity) {
        Preconditions.checkNotNull(blockEntity);
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
    public Entity getEntity(long runtimeId) {
        return unsafeChunk.getEntity(runtimeId);
    }

    public ChunkSection getSection(int sectionY) {
        Preconditions.checkArgument(sectionY >= -32 && sectionY <= 31);
        var stamp = blockLock.tryOptimisticRead();
        try {
            for (; ; stamp = blockLock.readLock()) {
                if (stamp == 0L) continue;
                var section = unsafeChunk.getSection(sectionY);
                if (!blockLock.validate(stamp)) continue;
                return section;
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) blockLock.unlockRead(stamp);
        }
    }

    public ChunkSection[] getSections() {
        return unsafeChunk.getSections();
    }

    public void beforeSetChunk(Dimension dimension) {
        unsafeChunk.beforeSetChunk(dimension);
    }

    public void afterSetChunk(Dimension dimension) {
        chunkSetCallback.run();
        loaded = true;
        unsafeChunk.afterSetChunk(dimension);
    }

    public ChunkSection getOrCreateSection(int sectionY) {
        Preconditions.checkArgument(sectionY >= -32 && sectionY <= 31);
        var stamp = blockLock.writeLock();
        try {
            return unsafeChunk.getOrCreateSection(sectionY);
        } finally {
            blockLock.unlockWrite(stamp);
        }
    }

    @Override
    public @UnmodifiableView Collection<BlockEntity> getSectionBlockEntities(int sectionY) {
        Preconditions.checkArgument(sectionY >= -32 && sectionY <= 31);
        return unsafeChunk.getSectionBlockEntities(sectionY);
    }

    @Override
    public void sendChunkPackets() {
        if (chunkPacketQueue.isEmpty()) return;
        ChunkPacketEntry entry;
        while ((entry = chunkPacketQueue.poll()) != null) {
            sendChunkPacket(entry.packet(), entry.chunkLoaderPredicate());
        }
    }

    @Override
    public void addChunkPacket(BedrockPacket packet) {
        chunkPacketQueue.add(new ChunkPacketEntry(packet, null));
    }

    @Override
    public void addChunkPacket(BedrockPacket packet, Predicate<ChunkLoader> chunkLoaderPredicate) {
        chunkPacketQueue.add(new ChunkPacketEntry(packet, chunkLoaderPredicate));
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
        chunkLoaders.forEach(chunkLoader -> chunkLoader.handleChunkPacket(packet));
    }

    @Override
    public void sendChunkPacket(BedrockPacket packet, Predicate<ChunkLoader> chunkLoaderPredicate) {
        chunkLoaders.stream()
                .filter(chunkLoader -> chunkLoaderPredicate == null || chunkLoaderPredicate.test(chunkLoader))
                .forEach(chunkLoader -> chunkLoader.handleChunkPacket(packet));
    }

    protected record ChunkPacketEntry(BedrockPacket packet, Predicate<ChunkLoader> chunkLoaderPredicate) {}
}
