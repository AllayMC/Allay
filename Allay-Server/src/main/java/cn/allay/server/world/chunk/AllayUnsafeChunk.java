package cn.allay.server.world.chunk;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.data.VanillaBlockTypes;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.biome.BiomeType;
import cn.allay.api.world.chunk.ChunkSection;
import cn.allay.api.world.chunk.UnsafeChunk;
import cn.allay.api.world.heightmap.HeightMap;
import cn.allay.api.world.heightmap.HeightMapType;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.nbt.NbtMap;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class AllayUnsafeChunk implements UnsafeChunk {
    @Getter
    @Setter
    protected volatile int chunkX;
    @Getter
    @Setter
    protected volatile int chunkZ;
    @Getter
    protected final DimensionInfo dimensionInfo;
    protected final ChunkSection[] sections;
    protected final HeightMap[] heightMap;

    public AllayUnsafeChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        this(chunkX, chunkZ, dimensionInfo, NbtMap.EMPTY);
    }

    public AllayUnsafeChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo, NbtMap data) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.sections = new ChunkSection[dimensionInfo.chunkSectionSize()];
        this.heightMap = new HeightMap[]{new HeightMap()};
        this.dimensionInfo = dimensionInfo;
    }

    @ApiStatus.Internal
    @Nullable
    public ChunkSection getSection(int y) {
        return sections[y];
    }

    @ApiStatus.Internal
    @NotNull
    public ChunkSection getOrCreateSection(int y) {
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
            blockState = section.getBlock(x, y & 0xf, z, layer);
        }
        return blockState;
    }

    public void setBlockState(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, boolean layer, BlockState blockState) {
        int sectionY = normalY(y) >>> 4;
        ChunkSection section = this.getSection(sectionY);
        if (section == null) {
            section = this.getOrCreateSection(sectionY);
        }
        section.setBlock(x, y & 0xf, z, layer, blockState);
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
        this.getOrCreateSection(normalY(y) >>> 4).setBlockLight(x, y & 0xf, z, (byte) light);
    }

    public void setSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, int light) {
        this.getOrCreateSection(normalY(y) >>> 4).setSkyLight(x, y & 0xf, z, (byte) light);
    }

    protected int normalY(int y) {
        return y - getDimensionInfo().minHeight();
    }

    @Override
    public void setBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, BiomeType biomeType) {
        this.getOrCreateSection(normalY(y) >>> 4).setBiomeType(x, y & 0xf, z, biomeType);
    }

    @Override
    public BiomeType getBiome(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        return this.getOrCreateSection(normalY(y) >>> 4).getBiomeType(x, y & 0xf, z);
    }
}
