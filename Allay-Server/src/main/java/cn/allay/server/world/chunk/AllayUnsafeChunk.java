package cn.allay.server.world.chunk;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.data.VanillaBlockTypes;
import cn.allay.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import cn.allay.api.entity.Entity;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.biome.BiomeType;
import cn.allay.api.world.chunk.ChunkLoader;
import cn.allay.api.world.chunk.ChunkSection;
import cn.allay.api.world.chunk.UnsafeChunk;
import cn.allay.api.world.heightmap.HeightMap;
import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSets;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.Collections;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@NotThreadSafe
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AllayUnsafeChunk implements UnsafeChunk {
    private static final AtomicIntegerFieldUpdater<AllayUnsafeChunk> STATE_FIELD = AtomicIntegerFieldUpdater.newUpdater(AllayUnsafeChunk.class, "state");
    protected @Getter
    volatile int state;
    protected @Getter
    final int x;
    protected @Getter
    final int z;
    protected @Getter
    final DimensionInfo dimensionInfo;
    protected final ChunkSection[] sections;
    protected final HeightMap heightMap;
    protected final Queue<BedrockPacket> chunkPacketQueue;
    protected final Set<ChunkLoader> chunkLoaders;
    protected final Map<Long, Entity> entities;

    private AllayUnsafeChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        this.x = chunkX;
        this.z = chunkZ;
        this.dimensionInfo = dimensionInfo;
        this.state = UnsafeChunk.STATE_NEW;
        this.heightMap = new HeightMap();
        this.sections = new ChunkSection[dimensionInfo.chunkSectionSize()];
        this.chunkPacketQueue = new ConcurrentLinkedQueue<>();
        this.chunkLoaders = ObjectSets.synchronize(new ObjectOpenHashSet<>());
        this.entities = new Long2ObjectNonBlockingMap<>();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public void setState(int next) {
        int curr;
        do {
            curr = STATE_FIELD.get(this);
            Preconditions.checkArgument(next >= 0 && next <= STATE_FINISHED, "invalid state: %s", next);
            Preconditions.checkState(curr < next, "invalid state transition: %s => %s", curr, next);
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
            blockState = VanillaBlockTypes.AIR_TYPE.getDefaultState();
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
        section.setBlockState(x, y & 0xf, z, layer, blockState);
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
    public void addChunkPacket(BedrockPacket packet) {
        chunkPacketQueue.add(packet);
    }

    @Override
    public void sendChunkPacket(BedrockPacket packet) {
        for (ChunkLoader chunkLoader : chunkLoaders) {
            chunkLoader.sendPacket(packet);
        }
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
    public void tick() {
        tickEntities();
    }

    private void tickEntities() {
        entities.forEach((uniqueId, entity) -> entity.tick());
    }

    @Override
    public void addEntity(Entity entity) {
        entities.put(entity.getUniqueId(), entity);
    }

    @Override
    @Nullable
    public Entity removeEntity(Long uniqueId) {
        return entities.remove(uniqueId);
    }

    @Override
    public Map<Long, Entity> getEntities() {
        return Collections.unmodifiableMap(entities);
    }


    protected int normalY(int y) {
        return y - getDimensionInfo().minHeight();
    }

    @Getter
    public static class Builder {
        int state;
        int chunkZ;
        int chunkX;
        DimensionInfo dimensionInfo;
        ChunkSection[] sections;
        HeightMap heightMap;
        Map<Long, Entity> entities;

        public Builder chunkX(int chunkX) {
            this.chunkX = chunkX;
            return this;
        }

        public Builder chunkZ(int chunkZ) {
            this.chunkZ = chunkZ;
            return this;
        }

        public Builder state(int state) {
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

        public AllayUnsafeChunk build() {
            return new AllayUnsafeChunk(state, chunkX, chunkZ, dimensionInfo, sections, heightMap,
                    new ConcurrentLinkedQueue<>(),
                    ObjectSets.synchronize(new ObjectOpenHashSet<>()),
                    entities
            );
        }

        public AllayUnsafeChunk emptyChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
            return new AllayUnsafeChunk(chunkX, chunkZ, dimensionInfo);
        }
    }
}
