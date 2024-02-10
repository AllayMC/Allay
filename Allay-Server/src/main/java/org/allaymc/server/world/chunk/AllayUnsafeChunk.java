package org.allaymc.server.world.chunk;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.interfaces.BlockAirBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.datastruct.collections.nb.Int2ObjectNonBlockingMap;
import org.allaymc.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkSection;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.chunk.UnsafeChunk;
import org.allaymc.api.world.heightmap.HeightMap;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

@NotThreadSafe
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AllayUnsafeChunk implements UnsafeChunk {
    private static final AtomicReferenceFieldUpdater<AllayUnsafeChunk, ChunkState> STATE_FIELD = AtomicReferenceFieldUpdater.newUpdater(AllayUnsafeChunk.class, ChunkState.class, "state");
    @Getter
    protected volatile ChunkState state;
    @Getter
    protected final int x;
    @Getter
    protected final int z;
    @Getter
    protected final DimensionInfo dimensionInfo;
    protected final ChunkSection[] sections;
    protected final HeightMap heightMap;
    protected final Map<Long, Entity> entities;
    protected final Map<Integer, BlockEntity> blockEntities;

    private AllayUnsafeChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        this.x = chunkX;
        this.z = chunkZ;
        this.dimensionInfo = dimensionInfo;
        this.state = ChunkState.NEW;
        this.heightMap = new HeightMap();
        this.sections = new ChunkSection[dimensionInfo.chunkSectionSize()];
        this.entities = new Long2ObjectOpenHashMap<>();
        this.blockEntities = new Int2ObjectNonBlockingMap<>();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public void setState(ChunkState next) {
        ChunkState curr;
        do {
            curr = STATE_FIELD.get(this);
            Preconditions.checkState(curr.ordinal() <= next.ordinal(), "invalid state transition: %s => %s", curr, next);
        } while (!STATE_FIELD.compareAndSet(this, curr, next));
    }

    private static void checkXYZ(int x, int y, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(y >= -512 && y <= 511);
        Preconditions.checkArgument(z >= 0 && z <= 15);
    }

    private static void checkXZ(int x, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(z >= 0 && z <= 15);
    }

    @Override
    @ApiStatus.Internal
    public ChunkSection[] getSections() {
        return sections;
    }

    @ApiStatus.Internal

    public ChunkSection getSection(int sectionY) {
        Preconditions.checkArgument(sectionY >= -32 && sectionY <= 31);
        return sections[sectionY - this.getDimensionInfo().minSectionY()];
    }

    @ApiStatus.Internal
    public ChunkSection getOrCreateSection(int sectionY) {
        Preconditions.checkArgument(sectionY >= -32 && sectionY <= 31);
        int minSectionY = this.getDimensionInfo().minSectionY();
        int offsetY = sectionY - minSectionY;
        for (int i = 0; i <= offsetY; i++) {
            if (sections[i] == null) {
                sections[i] = new ChunkSection((byte)(i + minSectionY));
            }
        }
        return sections[offsetY];
    }

    //基岩版3d-data保存heightMap是以0为索引保存的，所以这里需要减去/加上世界最小值，详情查看
    //Bedrock Edition 3d-data saves the height map start from index of 0, so need to subtract/add the world minimum height here, see for details:
    //https://github.com/bedrock-dev/bedrock-level/blob/main/src/include/data_3d.h#L115

    @UnmodifiableView
    @Override
    public Collection<BlockEntity> getSectionBlockEntities(int sectionY) {
        Preconditions.checkArgument(sectionY >= -32 && sectionY <= 31);
        var sectionBlockEntities = new HashSet<BlockEntity>();
        for (var entry : getBlockEntities().entrySet()) {
            var blockEntity = entry.getValue();
            if (blockEntity.getPosition().y() >> 4 == sectionY) {
                sectionBlockEntities.add(blockEntity);
            }
        }
        return Collections.unmodifiableCollection(sectionBlockEntities);
    }

    public int getHeight(int x, int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(z >= 0 && z <= 15);
        return this.heightMap.get(x, z) + dimensionInfo.minHeight();
    }

    public void setHeight(int x, int z, int height) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(z >= 0 && z <= 15);
        Preconditions.checkArgument(height >= -512 && height <= 511);
        this.heightMap.set(x, z, (short) (height - dimensionInfo.minHeight()));
    }

    public BlockState getBlockState(int x, int y, int z, int layer) {
        if (y < dimensionInfo.minHeight() || y > dimensionInfo.maxHeight())
            return BlockAirBehavior.AIR_TYPE.getDefaultState();
        checkXZ(x, z);
        ChunkSection section = this.getSection(y >> 4);
        BlockState blockState;
        if (section == null) {
            blockState = BlockAirBehavior.AIR_TYPE.getDefaultState();
        } else {
            blockState = section.getBlockState(x, y & 0xf, z, layer);
        }
        return blockState;
    }

    public void setBlockState(int x, int y, int z, BlockState blockState, int layer) {
        checkXYZ(x, y, z);
        int sectionY = y >> 4;
        ChunkSection section = this.getSection(sectionY);
        if (section == null) {
            section = this.getOrCreateSection(sectionY);
        }
        section.setBlockState(x, y & 0xf, z, blockState, layer);
    }

    @Override
    public short[] getHeightArray() {
        return this.heightMap.getHeights();
    }

    public int getBlockLight(int x, int y, int z) {
        checkXYZ(x, y, z);
        ChunkSection section = this.getSection(y >> 4);
        return section == null ? 0 : section.getBlockLight(x, y & 0xf, z);
    }

    public int getSkyLight(int x, int y, int z) {
        checkXYZ(x, y, z);
        ChunkSection section = this.getSection(y >> 4);
        return section == null ? 0 : section.getSkyLight(x, y & 0xf, z);
    }

    public void setBlockLight(int x, int y, int z, int light) {
        this.getOrCreateSection(y >> 4).setBlockLight(x, y & 0xf, z, (byte) light);
    }

    public void setSkyLight(int x, int y, int z, int light) {
        checkXYZ(x, y, z);
        this.getOrCreateSection(y >> 4).setSkyLight(x, y & 0xf, z, (byte) light);
    }

    @Override
    public void setBiome(int x, int y, int z, BiomeType biomeType) {
        this.getOrCreateSection(y >> 4).setBiomeType(x, y & 0xf, z, biomeType);
    }

    @Override
    public BiomeType getBiome(int x, int y, int z) {
        checkXYZ(x, y, z);
        return this.getOrCreateSection(y >> 4).getBiomeType(x, y & 0xf, z);
    }

    public void addEntity(Entity entity) {
        Preconditions.checkNotNull(entity);
        entities.put(entity.getUniqueId(), entity);
    }


    public Entity removeEntity(long uniqueId) {
        return entities.remove(uniqueId);
    }

    @Override
    public Entity getEntity(long uniqueId) {
        return entities.get(uniqueId);
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
    public @UnmodifiableView Map<Integer, BlockEntity> getBlockEntities() {
        return Collections.unmodifiableMap(blockEntities);
    }

    public Chunk toSafeChunk() {
        return new AllayChunk(this);
    }

    @Getter
    public static class Builder {
        ChunkState state;
        int chunkZ;
        int chunkX;
        DimensionInfo dimensionInfo;
        ChunkSection[] sections;
        HeightMap heightMap;
        Map<Long, Entity> entities;
        Map<Integer, BlockEntity> blockEntities;

        public Builder chunkX(int chunkX) {
            this.chunkX = chunkX;
            return this;
        }

        public Builder chunkZ(int chunkZ) {
            this.chunkZ = chunkZ;
            return this;
        }

        public Builder state(ChunkState state) {
            this.state = state;
            return this;
        }

        public Builder dimensionInfo(DimensionInfo dimensionInfo) {
            this.dimensionInfo = dimensionInfo;
            return this;
        }

        public Builder sections(ChunkSection[] sections) {
            this.sections = sections;
            return this;
        }

        public Builder heightMap(HeightMap heightMap) {
            this.heightMap = heightMap;
            return this;
        }

        public Builder entities(Map<Long, Entity> entities) {
            this.entities = entities;
            return this;
        }

        public Builder blockEntities(Map<Integer, BlockEntity> blockEntities) {
            this.blockEntities = blockEntities;
            return this;
        }

        public AllayUnsafeChunk build() {
            Preconditions.checkNotNull(dimensionInfo);
            if (state == null) state = ChunkState.NEW;
            if (sections == null) sections = new ChunkSection[dimensionInfo.chunkSectionSize()];
            if (heightMap == null) heightMap = new HeightMap();
            if (entities == null) entities = new Long2ObjectNonBlockingMap<>();
            if (blockEntities == null) blockEntities = new Int2ObjectNonBlockingMap<>();
            return new AllayUnsafeChunk(
                    state,
                    chunkX,
                    chunkZ,
                    dimensionInfo,
                    sections,
                    heightMap,
                    entities,
                    blockEntities
            );
        }

        public AllayUnsafeChunk emptyChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
            var chunk = new AllayUnsafeChunk(chunkX, chunkZ, dimensionInfo);
            chunk.setState(ChunkState.NEW);
            return chunk;
        }
    }
}
