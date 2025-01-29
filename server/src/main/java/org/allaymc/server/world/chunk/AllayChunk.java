package org.allaymc.server.world.chunk;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.util.internal.PlatformDependent;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockRandomUpdateEvent;
import org.allaymc.api.eventbus.event.block.BlockScheduleUpdateEvent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.chunk.*;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.blockentity.impl.BlockEntityImpl;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.impl.EntityImpl;
import org.allaymc.server.world.service.AllayLightService;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.StampedLock;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public class AllayChunk implements Chunk {

    protected static final int LCG_CONSTANT = 1013904223;

    protected final AllayUnsafeChunk unsafeChunk;

    protected final StampedLock blockLock;
    protected final StampedLock heightAndBiomeLock;
    protected final Set<ChunkLoader> chunkLoaders;
    protected final Queue<ChunkPacketEntry> chunkPacketQueue;

    // The callback to be called when the chunk is loaded into the world
    // The provided boolean value indicated whether the chunk is set successfully
    @Setter
    protected Consumer<Boolean> chunkSetCallback;
    protected int autoSaveTimer = 0;
    protected int updateLCG = ThreadLocalRandom.current().nextInt();

    private static void checkXZ(int x, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(z >= 0 && z <= 15);
    }

    private static void checkXYZ(int x, int y, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(y >= -512 && y <= 511);
        Preconditions.checkArgument(z >= 0 && z <= 15);
    }

    AllayChunk(AllayUnsafeChunk unsafeChunk) {
        this.unsafeChunk = unsafeChunk;
        this.blockLock = new StampedLock();
        this.heightAndBiomeLock = new StampedLock();
        this.chunkLoaders = Sets.newConcurrentHashSet();
        this.chunkPacketQueue = PlatformDependent.newMpscQueue();
    }

    public void tick(long currentTick, Dimension dimension, WorldStorage worldStorage) {
        unsafeChunk.getBlockEntitiesUnsafe().values().forEach(blockEntity -> ((BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent()).tick(currentTick));
        unsafeChunk.getEntitiesUnsafe().values().forEach(entity -> ((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).tick(currentTick));
        tickScheduledUpdates(dimension);
        tickRandomUpdates(dimension);

        checkAutoSave(worldStorage);
    }

    protected void tickScheduledUpdates(Dimension dimension) {
        var scheduledUpdates = unsafeChunk.getScheduledUpdatesUnsafe();
        List<ScheduledUpdateInfo> positions = new ArrayList<>(scheduledUpdates.size() / 4);
        for (var entry : scheduledUpdates.fastEntrySet()) {
            if (entry.getValue().getDelay() <= 0) {
                positions.add(entry.getValue());
                scheduledUpdates.remove(entry.getIntKey());
            } else {
                entry.getValue().decreaseDelay();
            }
        }

        positions.forEach(info -> {
            var chunkXYZ = info.getChunkXYZ();
            var localX = HashUtils.getXFromHashChunkXYZ(chunkXYZ);
            var y = HashUtils.getYFromHashChunkXYZ(chunkXYZ);
            var localZ = HashUtils.getZFromHashChunkXYZ(chunkXYZ);
            var layer = info.getLayer();

            var blockState = getBlockState(localX, y, localZ, layer);
            var blockStateWithPos = new BlockStateWithPos(blockState, new Position3i(localX + (unsafeChunk.x << 4), y, localZ + (unsafeChunk.z << 4), dimension), layer);
            if (!new BlockScheduleUpdateEvent(blockStateWithPos).call()) {
                return;
            }

            blockState.getBehavior().onScheduledUpdate(blockStateWithPos);
        });
    }

    protected void tickRandomUpdates(Dimension dimension) {
        int randomTickSpeed = dimension.getWorld().getWorldData().getGameRuleValue(GameRule.RANDOM_TICK_SPEED);
        if (randomTickSpeed <= 0) {
            return;
        }

        for (var section : unsafeChunk.getSections()) {
            if (section.isAirSection()) {
                continue;
            }
            // Check the entry list of this section, and
            // if there is no block that support random tick
            // in this section, we can just skip this section
            if (section.blockLayers()[0].allEntriesMatch(blockState -> !blockState.getBehavior().canRandomUpdate())) {
                continue;
            }

            int sectionY = section.sectionY();
            for (int i = 0; i < randomTickSpeed * 3; i++) {
                int lcg = nextUpdateLCG();
                int localX = lcg & 0x0f;
                int localZ = lcg >>> 8 & 0x0f;
                int localY = lcg >>> 16 & 0x0f;
                // TODO: instead of get the block state from palette and check if it supports random tick,
                // we can add a bitset to every chunk section to mark whether a block pos contains a block
                // that supports random tick, this would be much quicker
                var blockState = getBlockState(localX, sectionY * 16 + localY, localZ, 0);
                if (blockState.getBehavior().canRandomUpdate()) {
                    var blockStateWithPos = new BlockStateWithPos(blockState, new Position3i(localX + (unsafeChunk.x << 4), localY + (sectionY << 4), localZ + (unsafeChunk.z << 4), dimension), 0);
                    if (new BlockRandomUpdateEvent(blockStateWithPos).call()) {
                        blockState.getBehavior().onRandomUpdate(blockStateWithPos);
                    }
                }
            }
        }
    }

    public int nextUpdateLCG() {
        return (this.updateLCG = (this.updateLCG * 3) ^ LCG_CONSTANT);
    }

    protected void checkAutoSave(WorldStorage worldStorage) {
        autoSaveTimer++;
        if (autoSaveTimer >= Server.SETTINGS.storageSettings().chunkAutoSaveCycle()) {
            worldStorage.writeChunk(this);
            autoSaveTimer = 0;
        }
    }

    @Override
    public short getHeight(int x, int z) {
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
    public void setHeight(int x, int z, short height) {
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
    public void compareAndSetHeight(int x, int z, short expectedValue, short newValue) {
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
                if (stamp != 0L) {
                    unsafeChunk.setHeight(x, z, newValue);
                    return;
                }
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
                if (blockLock.validate(stamp)) {
                    return result;
                }
            }
        } finally {
            if (StampedLock.isReadLockStamp(stamp)) blockLock.unlockRead(stamp);
        }
    }

    @Override
    public void setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send) {
        checkXYZ(x, y, z);
        var stamp = blockLock.writeLock();
        try {
            unsafeChunk.setBlockState(x, y, z, blockState, layer, send);
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
                if (heightAndBiomeLock.validate(stamp)) {
                    return biomeType;
                }
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
    public void batchProcess(UnsafeChunkOperate operate) {
        var stamp1 = blockLock.writeLock();
        var stamp2 = heightAndBiomeLock.writeLock();
        try {
            operate.run(this.unsafeChunk);
        } finally {
            blockLock.unlockWrite(stamp1);
            heightAndBiomeLock.unlockWrite(stamp2);
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
        // This value is used in the subchunk system to control the maximum value of sectionY requested by the client.
        levelChunkPacket.setSubChunkLimit(getDimensionInfo().chunkSectionCount());
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
        levelChunkPacket.setSubChunksLength(getDimensionInfo().chunkSectionCount());
        try {
            levelChunkPacket.setData(writeToNetwork());
        } catch (Throwable t) {
            levelChunkPacket.setData(Unpooled.EMPTY_BUFFER);
        }
        return levelChunkPacket;
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
        // Write blocks
        for (int i = getDimensionInfo().minSectionY(); i <= getDimensionInfo().maxSectionY(); i++) {
            getSection(i).writeToNetwork(byteBuf);
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
            } catch (Throwable t) {
                log.error("Error while encoding block entities in chunk(x={}, z={})!", getX(), getZ(), t);
            }
        }
    }

    @Override
    public boolean isLoaded() {
        return unsafeChunk.isLoaded();
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
                if (blockLock.validate(stamp)) {
                    return section;
                }
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

    public void afterSetChunk(Dimension dimension, boolean success) {
        if (chunkSetCallback != null) {
            chunkSetCallback.accept(success);
        }
        unsafeChunk.afterSetChunk(dimension, success);

        if (!success) {
            return;
        }

        unsafeChunk.setBlockChangeCallback((x, y, z, blockState, layer) -> {
            if (layer != 0) {
                return;
            }
            ((AllayLightService) dimension.getLightService()).onBlockChange(x + (unsafeChunk.x << 4), y, z + (unsafeChunk.z << 4), blockState.getBlockStateData().lightEmission(), blockState.getBlockStateData().lightDampening());
        });
        ((AllayLightService) dimension.getLightService()).onChunkLoad(this);
    }

    @Override
    public @UnmodifiableView Collection<BlockEntity> getSectionBlockEntities(int sectionY) {
        Preconditions.checkArgument(sectionY >= -32 && sectionY <= 31);
        return unsafeChunk.getSectionBlockEntities(sectionY);
    }

    @Override
    public void addScheduledUpdate(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, int delay, int layer) {
        unsafeChunk.addScheduledUpdate(x, y, z, delay, layer);
    }

    @Override
    public boolean hasScheduledUpdate(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, int layer) {
        return unsafeChunk.hasScheduledUpdate(x, y, z, layer);
    }

    @Override
    public void sendChunkPackets() {
        if (chunkLoaders.isEmpty()) {
            unsafeChunk.clearBlockChanges();
            chunkPacketQueue.clear();
            return;
        }

        // Send block updates
        var pks = unsafeChunk.encodeAndClearBlockChanges();
        // pks == null -> no block changes
        if (pks != null) {
            for (var pk : pks) {
                if (pk == null) {
                    continue;
                }

                sendChunkPacket(pk);
            }
        }

        // Send other chunk packets
        if (chunkPacketQueue.isEmpty()) {
            return;
        }
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
        chunkLoaders.forEach(chunkLoader -> chunkLoader.sendPacket(packet));
    }

    @Override
    public void sendChunkPacket(BedrockPacket packet, Predicate<ChunkLoader> chunkLoaderPredicate) {
        chunkLoaders.stream()
                .filter(chunkLoader -> chunkLoaderPredicate == null || chunkLoaderPredicate.test(chunkLoader))
                .forEach(chunkLoader -> chunkLoader.sendPacket(packet));
    }

    protected record ChunkPacketEntry(BedrockPacket packet, Predicate<ChunkLoader> chunkLoaderPredicate) {}
}
