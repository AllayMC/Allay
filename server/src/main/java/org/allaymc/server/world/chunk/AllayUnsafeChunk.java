package org.allaymc.server.world.chunk;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.util.internal.PlatformDependent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.BlockEntityHelper;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityHelper;
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
import org.allaymc.server.world.service.AllayEntityPhysicsService;
import org.allaymc.server.world.service.AllayLightService;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.BlockChangeEntry;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.cloudburstmc.protocol.bedrock.packet.UpdateSubChunkBlocksPacket;
import org.jctools.maps.NonBlockingHashMap;
import org.jctools.maps.NonBlockingHashMapLong;
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

    // Constants used in UpdateSubChunkBlocksPacket
    protected static final int BLOCK_UPDATE_NEIGHBORS = 0b0001;
    protected static final int BLOCK_UPDATE_NETWORK = 0b0010;
    protected static final int BLOCK_UPDATE_NO_GRAPHICS = 0b0100;
    protected static final int BLOCK_UPDATE_PRIORITY = 0b1000;

    @Getter
    protected final int x, z;
    @Getter
    protected final DimensionInfo dimensionInfo;
    protected final AllayChunkSection[] sections;
    protected final HeightMap heightMap;
    @Getter
    protected final NonBlockingHashMap<Integer, ScheduledUpdateInfo> scheduledUpdates;
    @Getter
    @Setter
    protected volatile ChunkState state;
    protected List<NbtMap> entityNbtList;
    protected List<NbtMap> blockEntityNbtList;

    protected final ChunkBitMap heightMapDirtyFlags;
    protected final NonBlockingHashMapLong<Entity> entities;
    protected final NonBlockingHashMap<Integer, BlockEntity> blockEntities;
    protected final Set<ChunkLoader> chunkLoaders;
    protected final Queue<BlockChangeEntry> blockChangeEntries;
    protected final Queue<BlockChangeEntry> extraBlockChangeEntries;
    protected final Queue<ChunkPacketEntry> chunkPacketQueue;
    protected final AllayChunk safeChunk;

    @Setter
    protected Runnable chunkSetCallback;
    @Setter
    protected BlockChangeCallback blockChangeCallback;
    @Getter
    protected volatile boolean loaded;
    protected int autoSaveTimer = 0;
    protected int updateLCG = ThreadLocalRandom.current().nextInt();

    /**
     * Create a new {@link AllayUnsafeChunk}.
     *
     * @param x                  the x coordinate of the chunk.
     * @param z                  the z coordinate of the chunk.
     * @param dimensionInfo      the dimension info.
     * @param sections           the sections.
     * @param heightMap          the height map.
     * @param scheduledUpdates   the scheduled updates.
     * @param state              the state.
     * @param entityNbtList      the entity nbt list, can be {@code null}.
     * @param blockEntityNbtList the block entity nbt list, can be {@code null}
     */
    AllayUnsafeChunk(
            int x, int z, DimensionInfo dimensionInfo,
            AllayChunkSection[] sections, HeightMap heightMap,
            NonBlockingHashMap<Integer, ScheduledUpdateInfo> scheduledUpdates,
            ChunkState state, List<NbtMap> entityNbtList,
            List<NbtMap> blockEntityNbtList) {
        this.x = x;
        this.z = z;
        this.dimensionInfo = dimensionInfo;
        this.sections = sections;
        this.heightMap = heightMap;
        this.scheduledUpdates = scheduledUpdates;
        this.state = state;
        this.entityNbtList = entityNbtList;
        this.blockEntityNbtList = blockEntityNbtList;
        this.heightMapDirtyFlags = new ChunkBitMap();
        this.entities = new NonBlockingHashMapLong<>();
        this.blockEntities = new NonBlockingHashMap<>();
        this.chunkLoaders = Sets.newConcurrentHashSet();
        this.blockChangeEntries = PlatformDependent.newMpscQueue();
        this.extraBlockChangeEntries = PlatformDependent.newMpscQueue();
        this.chunkPacketQueue = PlatformDependent.newMpscQueue();
        this.safeChunk = new AllayChunk(this);
    }

    public static AllayChunkBuilder builder() {
        return new AllayChunkBuilder();
    }

    private static void checkXZ(int x, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(z >= 0 && z <= 15);
    }

    private void checkY(int y) {
        Preconditions.checkArgument(y >= dimensionInfo.minHeight() && y <= dimensionInfo.maxHeight());
    }

    private void checkXYZ(int x, int y, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        checkY(y);
        Preconditions.checkArgument(z >= 0 && z <= 15);
    }

    public void tick(long currentTick, Dimension dimension) {
        blockEntities.values().forEach(blockEntity -> ((BlockEntityBaseComponentImpl) ((BlockEntityImpl) blockEntity).getBaseComponent()).tick(currentTick));
        entities.values().forEach(entity -> ((EntityBaseComponentImpl) ((EntityImpl) entity).getBaseComponent()).tick(currentTick));
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

            var blockStateWithPos = new BlockStateWithPos(blockState, new Position3i(pos, dimension));
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
                    var blockStateWithPos = new BlockStateWithPos(blockState, new Position3i(localX + (this.x << 4), localY + (sectionY << 4), localZ + (this.z << 4), dimension), 0);
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

    public void onChunkLoad(Dimension dimension) {
        if (chunkSetCallback != null) {
            chunkSetCallback.run();
        }

        if (blockEntityNbtList != null && !blockEntityNbtList.isEmpty()) {
            for (var nbt : blockEntityNbtList) {
                BlockEntity blockEntity;
                try {
                    blockEntity = BlockEntityHelper.fromNBT(dimension, nbt);
                } catch (Throwable t) {
                    log.error("Error while loading block entity from NBT", t);
                    continue;
                }

                if (blockEntity == null) {
                    // blockEntity will be null if the entity type is unknown
                    continue;
                }

                var position = blockEntity.getPosition();
                var key = HashUtils.hashChunkXYZ(position.x() & 15, position.y(), position.z() & 15);
                this.blockEntities.put(key, blockEntity);
            }

            blockEntityNbtList = null;
        }

        if (entityNbtList != null && !entityNbtList.isEmpty()) {
            for (var nbt : entityNbtList) {
                Entity entity;
                try {
                    entity = EntityHelper.fromNBT(dimension, nbt);
                } catch (Throwable t) {
                    log.error("Error while loading entity from NBT", t);
                    continue;
                }

                if (entity == null) {
                    // entity will be null if the entity type is unknown
                    continue;
                }

                dimension.getEntityService().addEntity(entity);
            }
            entityNbtList = null;
        }

        ((AllayLightService) dimension.getLightService()).onChunkLoad(toSafeChunk());
        setBlockChangeCallback((x, y, z, blockState, layer) -> {
            if (layer == 0) {
                ((AllayLightService) dimension.getLightService()).onBlockChange(x + (this.x << 4), y, z + (this.z << 4), blockState.getBlockStateData().lightEmission(), blockState.getBlockStateData().lightDampening());
            }
        });

        loaded = true;
    }

    public void onChunkUnload(Dimension dimension) {
        entities.forEach((runtimeId, entity) -> {
            // NOTICE: shouldn't use despawn() method here, because entities should be kept in the chunk so that they will be saved
            entity.despawnFromAll();
            ((AllayEntityPhysicsService) dimension.getEntityPhysicsService()).removeEntity(entity);
        });
        ((AllayLightService) dimension.getLightService()).onChunkUnload(safeChunk);
    }

    @Override
    public AllayChunkSection getSection(int sectionY) {
        Preconditions.checkArgument(sectionY >= -32 && sectionY <= 31);
        return sections[sectionY - this.getDimensionInfo().minSectionY()];
    }

    @Override
    public List<ChunkSection> getSections() {
        return List.of(sections);
    }

    @Override
    public Collection<BlockEntity> getSectionBlockEntities(int sectionY) {
        Preconditions.checkArgument(sectionY >= -32 && sectionY <= 31);
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
            // updateFlags is a combination of flags that specify the way the block is updated client-side. It is a
            // combination of the flags above, but typically sending only the BLOCK_UPDATE_NETWORK flag is sufficient.
            var changeEntry = new BlockChangeEntry(
                    Vector3i.from((this.x << 4) + x, y, (this.z << 4) + z), blockState.toNetworkBlockDefinitionRuntime(),
                    BLOCK_UPDATE_NETWORK, -1, BlockChangeEntry.MessageType.NONE
            );
            switch (layer) {
                case 0 -> blockChangeEntries.offer(changeEntry);
                case 1 -> extraBlockChangeEntries.offer(changeEntry);
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

    public void clearBlockChanges() {
        blockChangeEntries.clear();
        extraBlockChangeEntries.clear();
    }

    public UpdateSubChunkBlocksPacket[] encodeAndClearBlockChanges() {
        if (blockChangeEntries.isEmpty() && extraBlockChangeEntries.isEmpty()) {
            return null;
        }

        var pks = new UpdateSubChunkBlocksPacket[sections.length];
        encodeBlockChangesInLayer(pks, blockChangeEntries, false);
        encodeBlockChangesInLayer(pks, extraBlockChangeEntries, true);

        return pks;
    }

    protected void encodeBlockChangesInLayer(UpdateSubChunkBlocksPacket[] pks, Queue<BlockChangeEntry> queue, boolean isExtraLayer) {
        BlockChangeEntry entry;
        while ((entry = queue.poll()) != null) {
            var sectionY = entry.getPosition().getY() >> 4;
            var index = sectionY - dimensionInfo.minSectionY();
            UpdateSubChunkBlocksPacket pk;

            if ((pk = pks[index]) == null) {
                pk = new UpdateSubChunkBlocksPacket();
                pk.setChunkX(this.x << 4);
                pk.setChunkY(sectionY << 4);
                pk.setChunkZ(this.z << 4);
                pks[index] = pk;
            }

            if (isExtraLayer) {
                pk.getExtraBlocks().add(entry);
            } else {
                pk.getStandardBlocks().add(entry);
            }
        }
    }

    @Override
    public void setBiome(int x, int y, int z, BiomeType biomeType) {
        checkXYZ(x, y, z);
        this.getSection(y >> 4).setBiomeType(x, y & 0xf, z, biomeType);
    }

    @Override
    public BiomeType getBiome(int x, int y, int z) {
        checkXYZ(x, y, z);
        return this.getSection(y >> 4).getBiomeType(x, y & 0xf, z);
    }

    public void addEntity(Entity entity) {
        Preconditions.checkNotNull(entity);
        entities.put(entity.getRuntimeId(), entity);
    }

    public Entity removeEntity(long runtimeId) {
        return entities.remove(runtimeId);
    }

    @Override
    public Entity getEntity(long runtimeId) {
        return entities.get(runtimeId);
    }

    @Override
    public Map<Long, Entity> getEntities() {
        return Collections.unmodifiableMap(entities);
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
        var key = HashUtils.hashChunkXYZ(x, y, z);
        return blockEntities.remove(key);
    }

    @Override
    public BlockEntity getBlockEntity(int x, int y, int z) {
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

    public void sendChunkPackets() {
        if (chunkLoaders.isEmpty()) {
            clearBlockChanges();
            chunkPacketQueue.clear();
            return;
        }

        // Send block updates
        var pks = encodeAndClearBlockChanges();
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
        for (var section : sections) {
            section.biomes().writeToNetwork(byteBuf, BiomeType::getId);
        }
        byteBuf.writeByte(0); // edu- border blocks

        // Write block entities
        var blockEntities = getBlockEntities().values();
        if (!blockEntities.isEmpty()) {
            try (var writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(byteBuf))) {
                for (var blockEntity : blockEntities) {
                    writer.writeTag(blockEntity.saveNBT());
                }
            } catch (Throwable t) {
                log.error("Error while encoding block entities in chunk {}, {}", getX(), getZ(), t);
            }
        }
    }

    protected record ChunkPacketEntry(BedrockPacket packet, Predicate<ChunkLoader> chunkLoaderPredicate) {}
}
