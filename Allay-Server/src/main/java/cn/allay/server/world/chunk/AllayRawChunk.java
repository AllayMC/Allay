package cn.allay.server.world.chunk;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.VanillaBlockTypes;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.chunk.ChunkSection;
import cn.allay.api.world.heightmap.HeightMap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class AllayRawChunk implements Chunk {
    private final int chunkX;
    private final int chunkZ;
    protected final ChunkSection[] sections;
    protected final HeightMap heightMap;
    protected final DimensionInfo dimensionInfo;

    public AllayRawChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.sections = new ChunkSection[dimensionInfo.chunkSectionSize()];
        this.heightMap = new HeightMap();
        this.dimensionInfo = dimensionInfo;
    }

    public AllayRawChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo, NbtMap data) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.sections = new ChunkSection[dimensionInfo.chunkSectionSize()];
        this.heightMap = new HeightMap();
        this.dimensionInfo = dimensionInfo;
    }


    @Override
    public DimensionInfo getDimensionInfo() {
        return dimensionInfo;
    }

    protected @Nullable ChunkSection getSection(int y) {
        return sections[y];
    }

    protected @NotNull ChunkSection getOrCreateSection(int y) {
        ChunkSection chunkSection = sections[y];
        if (chunkSection == null) {
            chunkSection = new ChunkSection();
            this.sections[y] = chunkSection;
        }
        return chunkSection;
    }

    @Override
    public void setSection(@Range(from = -32, to = 31) int y, ChunkSection section) {
        this.sections[y - (this.dimensionInfo.minHeight() >>> 4)] = section;
    }

    @Override
    public int getHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z) {
        return this.heightMap.get(x, z);
    }

    @Override
    public void setHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, int height) {
        this.heightMap.set(x, z, height);
    }


    @Override
    public BlockState getBlock(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, boolean layer) {
        y = normalY(y) >>> 4;
        ChunkSection section = this.getSection(y);
        BlockState blockState;
        if (section == null) {
            blockState = VanillaBlockTypes.AIR_TYPE.getDefaultState();
        } else {
            blockState = section.getBlock(x, y & 0xf, z, layer);
        }
        return blockState;
    }

    @Override
    public void setBlock(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, boolean layer, BlockState blockState) {
        y = normalY(y) >>> 4;
        ChunkSection section = this.getSection(y);
        if (section == null) {
            section = this.getOrCreateSection(y);
        }
        section.setBlock(x, y & 0xf, z, layer, blockState);
    }

    @Override
    public void compareAndSetBlock(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, boolean layer, BlockState expectedValue, BlockState newValue) {
        if (this.getBlock(x, y, z) == expectedValue) {
            y = normalY(y) >>> 4;
            ChunkSection section = this.getSection(y);
            if (section == null) {
                section = this.getOrCreateSection(y);
            }
            section.setBlock(x, y & 0xf, z, layer, newValue);
        }
    }

    @Override
    public @Range(from = 0, to = 15) byte getBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        y = normalY(y) >>> 4;
        ChunkSection section = this.getSection(y);
        return section == null ? 0 : section.getBlockLight(x, y & 0xf, z);
    }

    @Override
    public @Range(from = 0, to = 15) byte getSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z) {
        y = normalY(y) >>> 4;
        ChunkSection section = this.getSection(y);
        return section == null ? 0 : section.getSkyLight(x, y & 0xf, z);
    }

    @Override
    public void setBlockLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, byte light) {
        y = normalY(y) >>> 4;
        this.getOrCreateSection(y).setBlockLight(x, y & 0xf, z, light);
    }

    @Override
    public void setSkyLight(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, byte light) {
        y = normalY(y) >>> 4;
        this.getOrCreateSection(y).setSkyLight(x, y & 0xf, z, light);
    }

    protected int normalY(int y) {
        return y - getDimensionInfo().minHeight();
    }

    private void writeTo(ByteBuf byteBuf) {
        //TODO
////        Palette<Biome> lastBiomes = new Palette<>( Biome.PLAINS );
//
//        for (ChunkSection section : this.sections) {
////            if (subChunk == null) break;
//            section.writeToNetwork(byteBuf);
//        }
//
//        for (ChunkSection section : this.sections) {
//            if (section == null) {
//                lastBiomes.writeToNetwork(byteBuf, Biome::getId, lastBiomes);
//                continue;
//            }
//
//            section.getBiomes().writeToNetwork(byteBuf, Biome::getId);
//            lastBiomes = section.getBiomes();
//        }
//
//        byteBuf.writeByte(0); // edu - border blocks
//
//        Collection<BlockEntity> blockEntities = this.getBlockEntities();
//        if (!blockEntities.isEmpty()) {
//            try (NBTOutputStream writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(byteBuf))) {
//                for (BlockEntity blockEntity : blockEntities) {
//                    NbtMap tag = blockEntity.toCompound().build();
//                    writer.writeTag(tag);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public LevelChunkPacket createLevelChunkPacket() {
        ByteBuf byteBuf = Unpooled.buffer();
        try {
            final LevelChunkPacket levelChunkPacket = new LevelChunkPacket();
            levelChunkPacket.setChunkX(this.chunkX);
            levelChunkPacket.setChunkZ(this.chunkZ);
            levelChunkPacket.setCachingEnabled(false);
            levelChunkPacket.setRequestSubChunks(false);
            levelChunkPacket.setSubChunksLength(this.dimensionInfo.chunkSectionSize());
            this.writeTo(byteBuf.retain());
            levelChunkPacket.setData(byteBuf);
            return levelChunkPacket;
        } finally {
            byteBuf.release();
        }
    }
}
