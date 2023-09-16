package cn.allay.server.world.chunk;

import cn.allay.api.block.interfaces.BlockAirBehavior;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.datastruct.collections.nb.Int2ObjectNonBlockingMap;
import cn.allay.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import cn.allay.api.entity.Entity;
import cn.allay.api.utils.HashUtils;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.biome.BiomeType;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.chunk.ChunkSection;
import cn.allay.api.world.chunk.ChunkState;
import cn.allay.api.world.chunk.UnsafeChunk;
import cn.allay.api.world.heightmap.HeightMap;
import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.Nullable;
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
        this.entities = new Long2ObjectNonBlockingMap<>();
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

    @ApiStatus.Internal
    @Nullable
    public ChunkSection getSection(@Range(from = 0, to = 63) int y) {
        return sections[y];
    }

    @ApiStatus.Internal
    @NotNull
    public ChunkSection getOrCreateSection(@Range(from = 0, to = 63) int y) {
        for (int i = 0; i <= y; i++) {
            if (sections[i] == null) {
                sections[i] = new ChunkSection(i);
            }
        }
        return sections[y];
    }

    @UnmodifiableView
    @Override
    public Collection<BlockEntity> getSectionBlockEntities(int sectionY) {
        var sectionBlockEntities = new HashSet<BlockEntity>();
        for (var entry : getBlockEntities().entrySet()) {
            var blockEntity = entry.getValue();
            if (normalY(blockEntity.getPosition().y()) >>> 4 == sectionY) {
                sectionBlockEntities.add(blockEntity);
            }
        }
        return Collections.unmodifiableCollection(sectionBlockEntities);
    }

    public int getHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z) {
        return this.heightMap.get(x, z);
    }

    public void setHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, @Range(from = -512, to = 511) int height) {
        this.heightMap.set(x, z, (short) height);
    }

    public BlockState getBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int layer) {
        ChunkSection section = this.getSection(normalY(y) >>> 4);
        BlockState blockState;
        if (section == null) {
            blockState = BlockAirBehavior.AIR_TYPE.getDefaultState();
        } else {
            blockState = section.getBlockState(x, y & 0xf, z, layer);
        }
        return blockState;
    }

    public void setBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BlockState blockState, int layer) {
        int sectionY = normalY(y) >>> 4;
        ChunkSection section = this.getSection(sectionY);
        if (section == null) {
            section = this.getOrCreateSection(sectionY);
        }
        section.setBlockState(x, y & 0xf, z, blockState, layer);
    }

    public @Range(from = 0, to = 15) int getBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        ChunkSection section = this.getSection(normalY(y) >>> 4);
        return section == null ? 0 : section.getBlockLight(x, y & 0xf, z);
    }

    @Override
    public short[] getHeights() {
        return this.heightMap.getHeights();
    }

    public @Range(from = 0, to = 15) int getSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        ChunkSection section = this.getSection(normalY(y) >>> 4);
        return section == null ? 0 : section.getSkyLight(x, y & 0xf, z);
    }

    public void setBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int light) {
        this.getOrCreateSection(normalY(y) >>> 4).setBlockLight(x, y & 0xf, z, (byte) light);
    }

    public void setSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int light) {
        this.getOrCreateSection(normalY(y) >>> 4).setSkyLight(x, y & 0xf, z, (byte) light);
    }

    @Override
    public void setBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BiomeType biomeType) {
        this.getOrCreateSection(normalY(y) >>> 4).setBiomeType(x, y & 0xf, z, biomeType);
    }

    @Override
    public BiomeType getBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        return this.getOrCreateSection(normalY(y) >>> 4).getBiomeType(x, y & 0xf, z);
    }

    @Override
    public void addEntity(@NotNull Entity entity) {
        entities.put(entity.getUniqueId(), entity);
    }

    @Override
    @Nullable
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
    public void addBlockEntity(@NotNull BlockEntity blockEntity) {
        var pos = blockEntity.getPosition();
        var key = HashUtils.hashChunkXYZ(pos.x(), pos.y(), pos.z());
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

    protected int normalY(int y) {
        return y - getDimensionInfo().minHeight();
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
