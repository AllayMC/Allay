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
import cn.allay.api.world.heightmap.HeightMapType;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Getter
@NotThreadSafe
public class AllayUnsafeChunk implements UnsafeChunk {
    @Setter
    protected volatile int chunkX;
    @Getter
    @Setter
    protected volatile int chunkZ;
    @Getter
    protected final DimensionInfo dimensionInfo;
    protected final ChunkSection[] sections;
    protected final HeightMap[] heightMap;
    protected final Set<ChunkLoader> chunkLoaders;
    protected final Queue<BedrockPacket> chunkPacketQueue;
    protected final Map<Long, Entity> entities;

    public AllayUnsafeChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        this(chunkX, chunkZ, dimensionInfo, NbtMap.EMPTY);
    }

    public AllayUnsafeChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo, NbtMap data) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.sections = new ChunkSection[dimensionInfo.chunkSectionSize()];
        this.heightMap = new HeightMap[]{new HeightMap()};
        this.dimensionInfo = dimensionInfo;
        this.chunkLoaders = Collections.newSetFromMap(new ConcurrentHashMap<>());
        this.chunkPacketQueue = new ConcurrentLinkedQueue<>();
        this.entities = new Long2ObjectNonBlockingMap<>();
    }

    @ApiStatus.Internal
    @Nullable
    public ChunkSection getSection(int y) {
        return sections[y];
    }

    @ApiStatus.Internal
    @NotNull
    public ChunkSection createAndGetSection(int y) {
        for (int i = 0; i <= y; i++) {
            if (sections[i] == null) {
                sections[i] = new ChunkSection(i);
            }
        }
        return sections[y];
    }

    public int getHeight(HeightMapType type, @Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z) {
        return this.heightMap[type.ordinal()].get(x, z);
    }

    public void setHeight(HeightMapType type, @Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, int height) {
        this.heightMap[type.ordinal()].set(x, z, height);
    }

    public BlockState getBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, boolean layer) {
        ChunkSection section = this.getSection(normalY(y) >>> 4);
        BlockState blockState;
        if (section == null) {
            blockState = VanillaBlockTypes.AIR_TYPE.getDefaultState();
        } else {
            blockState = section.getBlockState(x, y & 0xf, z, layer);
        }
        return blockState;
    }

    public void setBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BlockState blockState, boolean layer) {
        int sectionY = normalY(y) >>> 4;
        ChunkSection section = this.getSection(sectionY);
        if (section == null) {
            section = this.createAndGetSection(sectionY);
        }
        section.setBlockState(x, y & 0xf, z, layer, blockState);
    }

    public @Range(from = 0, to = 15) int getBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        ChunkSection section = this.getSection(normalY(y) >>> 4);
        return section == null ? 0 : section.getBlockLight(x, y & 0xf, z);
    }

    public @Range(from = 0, to = 15) int getSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        ChunkSection section = this.getSection(normalY(y) >>> 4);
        return section == null ? 0 : section.getSkyLight(x, y & 0xf, z);
    }

    public void setBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int light) {
        this.createAndGetSection(normalY(y) >>> 4).setBlockLight(x, y & 0xf, z, (byte) light);
    }

    public void setSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int light) {
        this.createAndGetSection(normalY(y) >>> 4).setSkyLight(x, y & 0xf, z, (byte) light);
    }

    protected int normalY(int y) {
        return y - getDimensionInfo().minHeight();
    }

    @Override
    public void setBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BiomeType biomeType) {
        this.createAndGetSection(normalY(y) >>> 4).setBiomeType(x, y & 0xf, z, biomeType);
    }

    @Override
    public BiomeType getBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        return this.createAndGetSection(normalY(y) >>> 4).getBiomeType(x, y & 0xf, z);
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
}
