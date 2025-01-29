package org.allaymc.server.world.chunk;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.BlockEntityHelper;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityHelper;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.chunk.UnsafeChunk;
import org.allaymc.server.datastruct.collections.nb.Int2ObjectNonBlockingMap;
import org.allaymc.server.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import org.allaymc.server.world.HeightMap;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.BlockChangeEntry;
import org.cloudburstmc.protocol.bedrock.packet.UpdateSubChunkBlocksPacket;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.stream.Collectors;

/**
 * @author Cool_Loong | daoge_cmd
 */
public class AllayUnsafeChunk implements UnsafeChunk {

    // Constants used in UpdateSubChunkBlocksPacket
    private static final int BLOCK_UPDATE_NEIGHBORS = 0b0001;
    private static final int BLOCK_UPDATE_NETWORK = 0b0010;
    private static final int BLOCK_UPDATE_NO_GRAPHICS = 0b0100;
    private static final int BLOCK_UPDATE_PRIORITY = 0b1000;

    private static final AtomicReferenceFieldUpdater<AllayUnsafeChunk, ChunkState> STATE_FIELD = AtomicReferenceFieldUpdater.newUpdater(AllayUnsafeChunk.class, ChunkState.class, "state");

    @Getter
    protected final int x, z;
    @Getter
    protected final DimensionInfo dimensionInfo;
    @Getter
    protected final ChunkSection[] sections;
    @Getter
    protected final HeightMap heightMap;
    protected final Long2ObjectNonBlockingMap<Entity> entities;
    protected final Int2ObjectNonBlockingMap<BlockEntity> blockEntities;
    protected final Int2ObjectNonBlockingMap<ScheduledUpdateInfo> scheduledUpdates;
    protected final Int2ObjectOpenHashMap<BlockChangeEntry> blockChangeEntries;
    protected final Int2ObjectOpenHashMap<BlockChangeEntry> extraBlockChangeEntries;
    @Getter
    protected volatile ChunkState state;
    @Getter
    protected volatile boolean loaded;

    protected List<NbtMap> entityNbtList;
    protected List<NbtMap> blockEntityNbtList;
    @Setter
    protected BlockChangeCallback blockChangeCallback;

    /**
     * Create a new {@link AllayUnsafeChunk}.
     *
     * @param x                  the x.
     * @param z                  the z.
     * @param dimensionInfo      the dimension info.
     * @param sections           the sections.
     * @param heightMap          the height map.
     * @param state              the state.
     * @param entityNbtList      the entity nbt list, can be {@code null}.
     * @param blockEntityNbtList the block entity nbt list, can be {@code null}
     */
    AllayUnsafeChunk(
            int x, int z, DimensionInfo dimensionInfo,
            ChunkSection[] sections, HeightMap heightMap,
            Int2ObjectNonBlockingMap<ScheduledUpdateInfo> scheduledUpdates,
            ChunkState state, List<NbtMap> entityNbtList,
            List<NbtMap> blockEntityNbtList) {
        this.x = x;
        this.z = z;
        this.dimensionInfo = dimensionInfo;
        this.sections = sections;
        this.heightMap = heightMap;
        this.scheduledUpdates = scheduledUpdates;
        this.blockChangeEntries = new Int2ObjectOpenHashMap<>();
        this.extraBlockChangeEntries = new Int2ObjectOpenHashMap<>();
        this.entities = new Long2ObjectNonBlockingMap<>();
        this.blockEntities = new Int2ObjectNonBlockingMap<>();
        this.state = state;
        this.entityNbtList = entityNbtList;
        this.blockEntityNbtList = blockEntityNbtList;
    }

    public static AllayChunkBuilder builder() {
        return new AllayChunkBuilder();
    }

    private static void checkXZ(int x, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(z >= 0 && z <= 15);
    }

    public void beforeSetChunk(Dimension dimension) {
        if (blockEntityNbtList != null && !blockEntityNbtList.isEmpty()) {
            for (var nbt : blockEntityNbtList) {
                var blockEntity = BlockEntityHelper.fromNBT(dimension, nbt);
                if (blockEntity == null) continue;

                var position = blockEntity.getPosition();
                var key = HashUtils.hashChunkXYZ(position.x() & 15, position.y(), position.z() & 15);
                this.blockEntities.put(key, blockEntity);
            }

            blockEntityNbtList = null;
        }
    }

    public void afterSetChunk(Dimension dimension, boolean success) {
        if (!success) {
            return;
        }

        if (entityNbtList != null && !entityNbtList.isEmpty()) {
            for (var nbt : entityNbtList) {
                var entity = EntityHelper.fromNBT(dimension, nbt);
                if (entity == null) continue;
                dimension.getEntityService().addEntity(entity);
            }
            entityNbtList = null;
        }

        loaded = true;
    }

    private void checkXYZ(int x, int y, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(y >= dimensionInfo.minHeight() && y <= dimensionInfo.maxHeight());
        Preconditions.checkArgument(z >= 0 && z <= 15);
    }

    /**
     * Get Chunk section.
     *
     * @param sectionY the sectionY.
     *
     * @return the section, or {@code null} if not exist.
     */
    public ChunkSection getSection(int sectionY) {
        Preconditions.checkArgument(sectionY >= -32 && sectionY <= 31);
        return sections[sectionY - this.getDimensionInfo().minSectionY()];
    }


    @UnmodifiableView
    @Override
    public Collection<BlockEntity> getSectionBlockEntities(int sectionY) {
        Preconditions.checkArgument(sectionY >= -32 && sectionY <= 31);
        return getBlockEntities().values().stream()
                .filter(blockEntity -> blockEntity.getPosition().y() >> 4 == sectionY)
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public void addScheduledUpdate(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, int delay, int layer) {
        checkXYZ(x, y, z);
        var key = HashUtils.hashChunkXYZ(x, y, z);
        scheduledUpdates.put(key, new ScheduledUpdateInfo(key, layer, delay));
    }

    @Override
    public boolean hasScheduledUpdate(@Range(from = 0, to = 15) int x, int y, @Range(from = 0, to = 15) int z, int layer) {
        var scheduledUpdateInfo = scheduledUpdates.get(HashUtils.hashChunkXYZ(x, y, z));
        return scheduledUpdateInfo != null && scheduledUpdateInfo.getLayer() == layer;
    }

    public Int2ObjectNonBlockingMap<ScheduledUpdateInfo> getScheduledUpdatesUnsafe() {
        return scheduledUpdates;
    }

    @Override
    public short getHeight(int x, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(z >= 0 && z <= 15);
        return getHeightUnsafe(HeightMap.computeIndex(x, z));
    }

    protected short getHeightUnsafe(int index) {
        return this.heightMap.get(index);
    }

    @Override
    public void setHeight(int x, int z, short height) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(z >= 0 && z <= 15);
        Preconditions.checkArgument(height >= -512 && height <= 511);
        setHeightUnsafe(HeightMap.computeIndex(x, z), height);
    }

    protected void setHeightUnsafe(int index, int height) {
        this.heightMap.set(index, (short) height);
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
        var section = this.getSection(y >> 4);
        section.setBlockState(x, y & 0xf, z, blockState, layer);

        if (layer == 0) {
            // Update height map
            var index = HeightMap.computeIndex(x, z);
            var currentHeight = getHeightUnsafe(index);
            if (blockState.getBlockType() == BlockTypes.AIR && currentHeight == y) {
                // If there are no blocks in the (x, z) position, the height will be the min height of the current dimension
                int newHeight = dimensionInfo.minHeight();
                for (int i = y - 1; i >= dimensionInfo.minHeight(); i--) {
                    if (getBlockState(x, i, z, 0).getBlockType() != BlockTypes.AIR) {
                        newHeight = i;
                        break;
                    }
                }
                setHeightUnsafe(index, newHeight);
            } else if (currentHeight < y) {
                setHeightUnsafe(index, y);
            }
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
            var hash = HashUtils.hashChunkXYZ(x, y, z);
            switch (layer) {
                case 0 -> blockChangeEntries.put(hash, changeEntry);
                case 1 -> extraBlockChangeEntries.put(hash, changeEntry);
                default -> throw new IllegalArgumentException("Unsupported layer: " + layer);
            }
        }
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
        clearBlockChanges();

        return pks;
    }

    protected void encodeBlockChangesInLayer(UpdateSubChunkBlocksPacket[] pks, Int2ObjectOpenHashMap<BlockChangeEntry> entries, boolean isExtraLayer) {
        entries.forEach((encoded, entry) -> {
            var y = HashUtils.getYFromHashChunkXYZ(encoded);
            var sectionY = y >> 4;
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
        });
    }

    @Override
    public void setBiome(int x, int y, int z, BiomeType biomeType) {
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

    public Long2ObjectNonBlockingMap<Entity> getEntitiesUnsafe() {
        return entities;
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
    public @UnmodifiableView Map<Integer, BlockEntity> getBlockEntities() {
        return Collections.unmodifiableMap(blockEntities);
    }

    public Int2ObjectNonBlockingMap<BlockEntity> getBlockEntitiesUnsafe() {
        return blockEntities;
    }

    public Chunk toSafeChunk() {
        return new AllayChunk(this);
    }

    public void setState(ChunkState next) {
        ChunkState curr;
        do {
            curr = STATE_FIELD.get(this);
            Preconditions.checkState(curr.ordinal() <= next.ordinal(), "invalid state transition: %s => %s", curr, next);
        } while (!STATE_FIELD.compareAndSet(this, curr, next));
    }
}
