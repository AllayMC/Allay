package org.allaymc.server.world.chunk;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import io.netty.util.internal.PlatformDependent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.eventbus.event.block.BlockRandomUpdateEvent;
import org.allaymc.api.eventbus.event.block.BlockScheduleUpdateEvent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.api.world.biome.BiomeId;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.chunk.*;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.blockentity.impl.BlockEntityImpl;
import org.allaymc.server.world.light.AllayLightEngine;
import org.allaymc.server.world.manager.AllayEntityManager;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jctools.maps.NonBlockingHashMap;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.VisibleForTesting;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public class AllayUnsafeChunk implements UnsafeChunk {

    protected static final int LCG_CONSTANT = 1013904223;

    @Getter
    protected final int x, z;
    @Getter
    protected final DimensionInfo dimensionInfo;
    protected final AllayChunkSection[] sections;
    protected final HeightMap heightMap;
    @Getter
    protected final NonBlockingHashMap<Integer, ScheduledUpdateInfo> scheduledUpdates;
    protected final NonBlockingHashMap<Integer, BlockEntity> blockEntities;
    protected final ChunkBitMap heightMapDirtyFlags;
    protected final Set<ChunkLoader> chunkLoaders;
    protected final Queue<WorldViewer.BlockUpdate> blockUpdates;
    protected final Queue<WorldViewer.BlockUpdate> extraBlockUpdates;
    protected final Queue<ChunkPacketEntry> chunkPacketQueue; // TODO: remove it?
    protected final Queue<Runnable> chunkTaskQueue;
    protected final AllayChunk safeChunk;
    @Getter
    @Setter
    protected volatile ChunkState state;
    @Setter
    protected BlockChangeCallback blockChangeCallback;
    @Getter
    protected volatile boolean loaded;
    protected int autoSaveTimer = 0;
    protected int updateLCG = ThreadLocalRandom.current().nextInt();

    /**
     * Create a new {@link AllayUnsafeChunk}.
     *
     * @param x                the x coordinate of the chunk
     * @param z                the z coordinate of the chunk
     * @param dimensionInfo    the dimension info
     * @param sections         the sections
     * @param heightMap        the height map
     * @param scheduledUpdates the scheduled updates
     * @param state            the state
     * @param blockEntities    the block entities in the chunk
     */
    AllayUnsafeChunk(
            int x, int z, DimensionInfo dimensionInfo,
            AllayChunkSection[] sections, HeightMap heightMap,
            NonBlockingHashMap<Integer, ScheduledUpdateInfo> scheduledUpdates,
            ChunkState state, NonBlockingHashMap<Integer, BlockEntity> blockEntities) {
        this.x = x;
        this.z = z;
        this.dimensionInfo = dimensionInfo;
        this.sections = sections;
        this.heightMap = heightMap;
        this.scheduledUpdates = scheduledUpdates;
        this.state = state;
        this.blockEntities = blockEntities;
        this.heightMapDirtyFlags = new ChunkBitMap();
        this.chunkLoaders = Sets.newConcurrentHashSet();
        this.blockUpdates = PlatformDependent.newMpscQueue();
        this.extraBlockUpdates = PlatformDependent.newMpscQueue();
        this.chunkPacketQueue = PlatformDependent.newMpscQueue();
        this.chunkTaskQueue = PlatformDependent.newMpscQueue();
        this.safeChunk = new AllayChunk(this);
    }

    public static AllayChunkBuilder builder() {
        return new AllayChunkBuilder();
    }

    public static void checkXZ(int x, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(z >= 0 && z <= 15);
    }

    public void checkY(int y) {
        Preconditions.checkArgument(y >= dimensionInfo.minHeight() && y <= dimensionInfo.maxHeight());
    }

    public void checkSectionY(int sectionY) {
        Preconditions.checkArgument(sectionY >= dimensionInfo.minSectionY() && sectionY <= dimensionInfo.maxSectionY());
    }

    public void checkXYZ(int x, int y, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        checkY(y);
        Preconditions.checkArgument(z >= 0 && z <= 15);
    }

    public void tick(long currentTick, Dimension dimension) {
        blockEntities.values().forEach(blockEntity -> ((BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent()).tick(currentTick));
        tickScheduledUpdates(currentTick, dimension);
        tickRandomUpdates(dimension);
    }

    public void checkAutoSave(WorldStorage worldStorage) {
        autoSaveTimer++;
        if (autoSaveTimer >= Server.SETTINGS.storageSettings().chunkAutoSaveCycle()) {
            worldStorage.writeChunk(safeChunk);
            autoSaveTimer = 0;
        }
    }

    protected void tickScheduledUpdates(long currentTick, Dimension dimension) {
        List<ScheduledUpdateInfo> positions = new ArrayList<>(scheduledUpdates.size());
        for (var entry : scheduledUpdates.entrySet()) {
            if (entry.getValue().getTime() <= currentTick) {
                positions.add(entry.getValue());
                scheduledUpdates.remove(entry.getKey());
            }
        }

        positions.forEach(info -> {
            var pos = info.getPos();
            var blockState = getBlockState(pos.x() & 15, pos.y(), pos.z() & 15);

            var block = new Block(blockState, new Position3i(pos, dimension));
            if (!new BlockScheduleUpdateEvent(block).call()) {
                return;
            }

            blockState.getBehavior().onScheduledUpdate(block);
        });
    }

    protected void tickRandomUpdates(Dimension dimension) {
        int randomTickSpeed = dimension.getWorld().getWorldData().getGameRuleValue(GameRule.RANDOM_TICK_SPEED);
        if (randomTickSpeed <= 0) {
            return;
        }

        for (var section : sections) {
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
                    var block = new Block(blockState, new Position3i(localX + (this.x << 4), localY + (sectionY << 4), localZ + (this.z << 4), dimension), 0);
                    if (new BlockRandomUpdateEvent(block).call()) {
                        blockState.getBehavior().onRandomUpdate(block);
                    }
                }
            }
        }
    }

    public int nextUpdateLCG() {
        return (this.updateLCG = (this.updateLCG * 3) ^ LCG_CONSTANT);
    }

    public void onChunkLoad(Dimension dimension) {
        ((AllayLightEngine) dimension.getLightEngine()).onChunkLoad(toSafeChunk());
        setBlockChangeCallback((x, y, z, blockState, layer) -> {
            if (layer == 0) {
                ((AllayLightEngine) dimension.getLightEngine()).onBlockChange(x + (this.x << 4), y, z + (this.z << 4), blockState.getBlockStateData().lightEmission(), blockState.getBlockStateData().lightDampening());
            }
        });
        ((AllayEntityManager) dimension.getEntityManager()).onChunkLoad(this.x, this.z);

        loaded = true;
    }

    public void onChunkUnload(Dimension dimension) {
        ((AllayLightEngine) dimension.getLightEngine()).onChunkUnload(safeChunk);
        ((AllayEntityManager) dimension.getEntityManager()).onChunkUnload(this.x, this.z);
        blockChangeCallback = null;
    }

    @Override
    public AllayChunkSection getSection(int sectionY) {
        checkSectionY(sectionY);
        return sections[sectionY - this.getDimensionInfo().minSectionY()];
    }

    @Override
    public List<ChunkSection> getSections() {
        return List.of(sections);
    }

    @Override
    public Collection<BlockEntity> getSectionBlockEntities(int sectionY) {
        checkSectionY(sectionY);
        return getBlockEntities().values().stream()
                .filter(blockEntity -> blockEntity.getPosition().y() >> 4 == sectionY)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public void addScheduledUpdate(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, long time) {
        checkXYZ(x, y, z);
        var key = HashUtils.hashChunkXYZ(x, y, z);
        scheduledUpdates.put(key, new ScheduledUpdateInfo(new org.joml.Vector3i((this.x << 4) + x, y, (this.z << 4) + z), time));
    }

    @Override
    public boolean hasScheduledUpdate(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z) {
        checkXYZ(x, y, z);
        var scheduledUpdateInfo = scheduledUpdates.get(HashUtils.hashChunkXYZ(x, y, z));
        return scheduledUpdateInfo != null;
    }

    @Override
    public BlockState getBlockState(int x, int y, int z, int layer) {
        if (y < dimensionInfo.minHeight() || y > dimensionInfo.maxHeight()) {
            return BlockTypes.AIR.getDefaultState();
        }

        checkXZ(x, z);
        return this.getSection(y >> 4).getBlockState(x, y & 0xf, z, layer);
    }

    @Override
    public void setBlockState(int x, int y, int z, BlockState blockState, int layer, boolean send) {
        checkXYZ(x, y, z);
        this.getSection(y >> 4).setBlockState(x, y & 0xf, z, blockState, layer);

        if (layer == 0) {
            // Mark the height map at this position as dirty
            heightMapDirtyFlags.set(x, z, true);
        }

        if (blockChangeCallback != null) {
            blockChangeCallback.onBlockChange(x, y, z, blockState, layer);
        }

        if (send && loaded) {
            var update = new WorldViewer.BlockUpdate((this.x << 4) + x, y, (this.z << 4) + z, blockState);
            switch (layer) {
                case 0 -> blockUpdates.offer(update);
                case 1 -> extraBlockUpdates.offer(update);
                default -> throw new IllegalArgumentException("Unsupported layer: " + layer);
            }
        }
    }

    @Override
    public short getHeight(int x, int z) {
        checkXZ(x, z);
        short height;
        if (heightMapDirtyFlags.get(x, z)) {
            height = (short) calculateHeight(x, z);
            setHeightUnsafe(HeightMap.computeIndex(x, z), height);
            heightMapDirtyFlags.set(x, z, false);
            return height;
        } else {
            return getHeightUnsafe(HeightMap.computeIndex(x, z));
        }
    }

    protected short getHeightUnsafe(int index) {
        return this.heightMap.get(index);
    }

    @VisibleForTesting
    public void setHeight(int x, int z, short height) {
        checkXYZ(x, height, z);
        setHeightUnsafe(HeightMap.computeIndex(x, z), height);
    }

    protected void setHeightUnsafe(int index, short height) {
        this.heightMap.set(index, height);
    }

    public HeightMap calculateAndGetHeightMap() {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                if (heightMapDirtyFlags.get(x, z)) {
                    setHeightUnsafe(HeightMap.computeIndex(x, z), (short) calculateHeight(x, z));
                    heightMapDirtyFlags.set(x, z, false);
                }
            }
        }

        return heightMap;
    }

    protected int calculateHeight(int x, int z) {
        // If there are no blocks in the (x, z) position, the height will be the min height of the current dimension
        var newHeight = dimensionInfo.minHeight();
        for (int sectionY = dimensionInfo.maxSectionY(); sectionY >= dimensionInfo.minSectionY(); sectionY--) {
            var section = getSection(sectionY);
            if (section.isAirSection()) {
                continue;
            }

            boolean found = false;
            for (int localY = 15; localY >= 0; localY--) {
                if (section.getBlockState(x, localY, z, 0).getBlockType() != BlockTypes.AIR) {
                    newHeight = localY + (sectionY << 4);
                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            }
        }

        return newHeight;
    }

    protected Collection<WorldViewer.BlockUpdate> collectUpdates(Queue<WorldViewer.BlockUpdate> queue) {
        var list = new ArrayList<WorldViewer.BlockUpdate>();
        WorldViewer.BlockUpdate update;
        while ((update = queue.poll()) != null) {
            list.add(update);
        }

        return list;
    }

    @Override
    public void setBiome(int x, int y, int z, BiomeType biomeType) {
        checkXYZ(x, y, z);
        this.getSection(y >> 4).setBiomeType(x, y & 0xf, z, biomeType);
    }

    @Override
    public BiomeType getBiome(int x, int y, int z) {
        if (y < getDimensionInfo().minHeight() || y > getDimensionInfo().maxHeight()) {
            return BiomeId.PLAINS;
        }

        AllayUnsafeChunk.checkXZ(x, z);
        return this.getSection(y >> 4).getBiomeType(x, y & 0xf, z);
    }

    @Override
    public void addBlockEntity(BlockEntity blockEntity) {
        Preconditions.checkNotNull(blockEntity);
        var pos = blockEntity.getPosition();
        var key = HashUtils.hashChunkXYZ(pos.x() & 15, pos.y(), pos.z() & 15);
        blockEntities.put(key, blockEntity);
    }

    @Override
    public BlockEntity removeBlockEntity(int x, int y, int z) {
        checkXYZ(x, y, z);
        var key = HashUtils.hashChunkXYZ(x, y, z);
        return blockEntities.remove(key);
    }

    @Override
    public BlockEntity getBlockEntity(int x, int y, int z) {
        checkXYZ(x, y, z);
        return blockEntities.get(HashUtils.hashChunkXYZ(x, y, z));
    }

    @Override
    public Map<Integer, BlockEntity> getBlockEntities() {
        return Collections.unmodifiableMap(blockEntities);
    }

    @Override
    public Chunk toSafeChunk() {
        return safeChunk;
    }

    // TODO: remove it
    public void sendChunkPackets() {
        if (chunkLoaders.isEmpty()) {
            chunkPacketQueue.clear();
            return;
        }

        if (!chunkPacketQueue.isEmpty()) {
            ChunkPacketEntry entry;
            while ((entry = chunkPacketQueue.poll()) != null) {
                sendChunkPacket(entry.packet(), entry.chunkLoaderPredicate());
            }
        }
    }

    public void sendBlockUpdates() {
        if (chunkLoaders.isEmpty()) {
            blockUpdates.clear();
            extraBlockUpdates.clear();
            return;
        }

        var collectedBlockUpdates = collectUpdates(blockUpdates);
        var collectedExtraBlockUpdates = collectUpdates(extraBlockUpdates);
        chunkLoaders.forEach(chunkLoader -> chunkLoader.viewBlockUpdates(toSafeChunk(), collectedBlockUpdates, collectedExtraBlockUpdates));
    }

    public void performChunkTasks() {
        if (chunkLoaders.isEmpty()) {
            chunkTaskQueue.clear();
            return;
        }

        if (!chunkTaskQueue.isEmpty()) {
            Runnable task;
            while ((task = chunkTaskQueue.poll()) != null) {
                task.run();
            }
        }
    }

    @Override
    public void addChunkTask(Runnable task) {
        chunkTaskQueue.add(task);
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
    public void sendChunkPacket(BedrockPacket packet) {
        chunkLoaders.forEach(chunkLoader -> chunkLoader.sendPacket(packet));
    }

    @Override
    public void sendChunkPacket(BedrockPacket packet, Predicate<ChunkLoader> chunkLoaderPredicate) {
        chunkLoaders.stream()
                .filter(chunkLoader -> chunkLoaderPredicate == null || chunkLoaderPredicate.test(chunkLoader))
                .forEach(chunkLoader -> chunkLoader.sendPacket(packet));
    }

    protected record ChunkPacketEntry(BedrockPacket packet, Predicate<ChunkLoader> chunkLoaderPredicate) {
    }
}
