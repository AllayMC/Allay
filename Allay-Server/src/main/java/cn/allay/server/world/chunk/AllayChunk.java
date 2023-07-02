package cn.allay.server.world.chunk;

import cn.allay.api.block.type.BlockState;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.world.DimensionInfo;
import cn.allay.api.world.biome.Biome;
import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.chunk.ChunkSection;
import cn.allay.api.world.heightmap.HeightMap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.packet.LevelChunkPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Allay Project 5/30/2023
 *
 * @author Cool_Loong
 */
@ThreadSafe
public class AllayChunk implements Chunk {
    private final int chunkX;
    private final int chunkZ;
    private final List<ChunkSection> sections;
    private final HeightMap heightMap;
    private final DimensionInfo dimensionInfo;
    private final ReadWriteLock readWriteLock;

    public AllayChunk(DimensionInfo dimensionInfo, int chunkX, int chunkZ) {
        this(dimensionInfo, chunkX, chunkZ, null);
    }

    public AllayChunk(DimensionInfo dimensionInfo, int chunkX, int chunkZ, @Nullable NbtMap data) {
        //todo complete create chunk from nbt data
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.sections = new ArrayList<>(dimensionInfo.chunkSectionSize());
        this.heightMap = new HeightMap();
        this.dimensionInfo = dimensionInfo;
        this.readWriteLock = new ReentrantReadWriteLock();
    }

    @Override
    public DimensionInfo getDimensionInfo() {
        return dimensionInfo;
    }

    @Override
    public @Nullable ChunkSection getSection(@Range(from = 0, to = 63) int y) {
        try {
            readWriteLock.readLock().lock();
            return sections.get(y);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public @NotNull ChunkSection getOrCreateSection(@Range(from = 0, to = 63) int y) {
        try {
            readWriteLock.writeLock().lock();
            ChunkSection chunkSection = sections.get(y);
            if (chunkSection == null) {
                chunkSection = new AllayChunkSection(readWriteLock, y);
                this.sections.set(y, chunkSection);
            }
            return chunkSection;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public int getHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z) {
        try {
            readWriteLock.readLock().lock();
            return this.heightMap.get(x, z);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public void setHeight(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int z, int height) {
        try {
            readWriteLock.writeLock().lock();
            this.heightMap.set(x, z, height);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }


    @Override
    public BlockState getBlock(@Range(from = 0, to = 15) int x, @Range(from = -512, to = 511) int y, @Range(from = 0, to = 15) int z, boolean layer) {
        y = normalY(y) >>> 4;
        ChunkSection section = this.getSection(y);
        BlockState blockState;
        if (section == null) {
            blockState = BlockType.AIR.getDefaultState();
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

    private int normalY(int y) {
        return y - getDimensionInfo().minHeight();
    }
}
