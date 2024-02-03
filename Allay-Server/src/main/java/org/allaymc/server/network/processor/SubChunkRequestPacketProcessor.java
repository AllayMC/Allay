package org.allaymc.server.network.processor;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkSection;
import org.allaymc.api.network.processor.PacketProcessor;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.HeightMapDataType;
import org.cloudburstmc.protocol.bedrock.data.SubChunkData;
import org.cloudburstmc.protocol.bedrock.data.SubChunkRequestResult;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkPacket;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkRequestPacket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
@Slf4j
public class SubChunkRequestPacketProcessor extends PacketProcessor<SubChunkRequestPacket> {
    private static final ByteBuf EMPTY_BUFFER = Unpooled.wrappedBuffer(new byte[0]);

    // This collection is temporarily disabled because the data in the collection has not been cleaned up, and there is a serious memory leak in the case of a large number of chunk sending
    // TODO: We need a better solution to prevent hack client's DOS attack
//    //保存着上tick已经发送的SubChunkRequestData
//    private final Map<Long, Set<SubChunkRequestIndex>> sentSubChunks = new Long2ObjectOpenHashMap<>();

    record SubChunkRequestIndex(org.cloudburstmc.math.vector.Vector3i centerPosition,
                                org.cloudburstmc.math.vector.Vector3i offset) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SubChunkRequestIndex that)) return false;
            return centerPosition.getX() == that.centerPosition.getX() &&
                    centerPosition.getY() == that.centerPosition.getY() &&
                    centerPosition.getZ() == that.centerPosition.getZ() &&
                    offset.getX() == that.offset.getX() &&
                    offset.getY() == that.offset.getY() &&
                    offset.getZ() == that.offset.getZ();
        }

        @Override
        public int hashCode() {
            return Objects.hash(centerPosition.getX(), centerPosition.getY(), centerPosition.getZ(), offset.getX(), offset.getY(), offset.getZ());
        }
    }

    @Override
    public void handleSync(EntityPlayer player, SubChunkRequestPacket packet) {
        List<SubChunkData> responseData = new ArrayList<>();
        var centerPosition = packet.getSubChunkPosition();
        var positionOffsets = packet.getPositionOffsets();
        DimensionInfo dimensionInfo = DimensionInfo.of(packet.getDimension());
        for (var offset : positionOffsets) {
            int sectionY = centerPosition.getY() + offset.getY()/* - (dimensionInfo.minSectionY())*/;

            HeightMapDataType hMapType = HeightMapDataType.NO_DATA;
            if (sectionY < dimensionInfo.minSectionY() || sectionY > dimensionInfo.maxSectionY()) {
                log.warn("Player " + player.getOriginName() + " requested sub chunk which is out of bounds");
                createSubChunkData(responseData, SubChunkRequestResult.INDEX_OUT_OF_BOUNDS, offset, hMapType, null, null, null);
                continue;
            }

            int cx = centerPosition.getX() + offset.getX(), cz = centerPosition.getZ() + offset.getZ();
            Chunk chunk = player.getDimension().getChunkService().getChunk(cx, cz);
            if (chunk == null) {
                log.warn("Player " + player.getOriginName() + " requested sub chunk which is not loaded");
                createSubChunkData(responseData, SubChunkRequestResult.CHUNK_NOT_FOUND, offset, hMapType, null, null, null);
                continue;
            }

//            var chunkHash = chunk.computeChunkHash();
//            this.sentSubChunks.putIfAbsent(chunkHash, new HashSet<>());
//            var sent = this.sentSubChunks.get(chunkHash);
//            SubChunkRequestIndex requestIndex = new SubChunkRequestIndex(centerPosition, offset);
//            if (sent.contains(requestIndex)) {
//                log.trace("Player " + player.getOriginName() + " requested sub chunk which was already sent");
//                continue;
//            } else {
//                sent.add(requestIndex);
//            }

            byte[] hMap = new byte[256];
            boolean higher = true, lower = true;
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    int y = chunk.getHeight(x, z);
                    int i = (z << 4) | x;
                    int hSectionY = y >> 4;
                    if (hSectionY > sectionY) {
                        hMap[i] = 16;
                        lower = false;
                    } else if (hSectionY < sectionY) {
                        hMap[i] = -1;
                        higher = false;
                    } else {
                        hMap[i] = (byte) (y - (hSectionY << 4));
                        higher = false;
                        lower = false;
                    }
                }
            }
            ByteBuf heightMapData;
            if (higher) {
                hMapType = HeightMapDataType.TOO_HIGH;
                heightMapData = EMPTY_BUFFER;
            } else if (lower) {
                hMapType = HeightMapDataType.TOO_LOW;
                heightMapData = EMPTY_BUFFER;
            } else {
                hMapType = HeightMapDataType.HAS_DATA;
                heightMapData = Unpooled.wrappedBuffer(hMap);
            }
            var subChunk = chunk.getOrCreateSection(sectionY);
            SubChunkRequestResult subChunkRequestResult;
            if (subChunk.isEmpty()) {
                subChunkRequestResult = SubChunkRequestResult.SUCCESS_ALL_AIR;
            } else {
                subChunkRequestResult = SubChunkRequestResult.SUCCESS;
            }
            createSubChunkData(responseData, subChunkRequestResult, offset, hMapType, heightMapData, subChunk, chunk.getSectionBlockEntities(sectionY));
        }
        SubChunkPacket subChunkPacket = new SubChunkPacket();
        subChunkPacket.setSubChunks(responseData);
        subChunkPacket.setDimension(packet.getDimension());
        subChunkPacket.setCenterPosition(centerPosition);
        player.sendPacket(subChunkPacket);
    }

    //There is no need to explicitly mark Nullable because we ensure that result = success is not null
    private static void createSubChunkData(List<SubChunkData> response,
                                           SubChunkRequestResult result,
                                           org.cloudburstmc.math.vector.Vector3i offset,
                                           HeightMapDataType type,
                                           ByteBuf heightMapData,
                                           ChunkSection subchunk,
                                           Collection<BlockEntity> subChunkBlockEntities) {
        SubChunkData subChunkData = new SubChunkData();
        subChunkData.setPosition(offset);
        subChunkData.setResult(result);
        if (result == SubChunkRequestResult.SUCCESS) {
            ByteBuf buffer = ByteBufAllocator.DEFAULT.ioBuffer();
            subchunk.writeToNetwork(buffer);
            subchunk.biomes().writeToNetwork(buffer, BiomeType::getId);
            // edu - border blocks
            buffer.writeByte(0);
            try (var writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(buffer))) {
                for (BlockEntity blockEntity : subChunkBlockEntities) {
                    writer.writeTag(blockEntity.saveNBT());
                }
            } catch (IOException e) {
                log.error("Error while encoding block entity in sub chunk!", e);
            }
            subChunkData.setData(buffer);
        } else {
            subChunkData.setData(EMPTY_BUFFER);
        }
        subChunkData.setHeightMapType(type);
        if (type == HeightMapDataType.HAS_DATA) {
            subChunkData.setHeightMapData(heightMapData);
        }
        response.add(subChunkData);
    }


    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SUB_CHUNK_REQUEST;
    }
}
