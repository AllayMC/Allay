package org.allaymc.server.network.processor;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.network.processor.PacketProcessor;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.chunk.ChunkSection;
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
    // The collection that holds the SubChunkRequestData sent in the last tick
    // private final Map<Long, Set<SubChunkRequestIndex>> sentSubChunks = new Long2ObjectOpenHashMap<>();

    // There is no need to explicitly mark Nullable because we ensure that result = success is not null
    private static void createSubChunkData(
            List<SubChunkData> response,
            SubChunkRequestResult result,
            org.cloudburstmc.math.vector.Vector3i offset,
            HeightMapDataType type,
            ByteBuf heightMapData,
            ChunkSection subchunk,
            Collection<BlockEntity> subChunkBlockEntities
    ) {
        var subChunkData = new SubChunkData();
        subChunkData.setPosition(offset);
        subChunkData.setResult(result);

        if (result == SubChunkRequestResult.SUCCESS) {
            var buffer = ByteBufAllocator.DEFAULT.ioBuffer();
            subchunk.writeToNetwork(buffer);
            subchunk.biomes().writeToNetwork(buffer, BiomeType::getId);
            // edu - border blocks
            buffer.writeByte(0);
            try (var writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(buffer))) {
                for (var blockEntity : subChunkBlockEntities) {
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
        if (type == HeightMapDataType.HAS_DATA)
            subChunkData.setHeightMapData(heightMapData);
        response.add(subChunkData);
    }

    @Override
    public void handleSync(EntityPlayer player, SubChunkRequestPacket packet, long receiveTime) {
        List<SubChunkData> responseData = new ArrayList<>();
        var centerPosition = packet.getSubChunkPosition();
        var positionOffsets = packet.getPositionOffsets();
        var dimensionInfo = Objects.requireNonNull(DimensionInfo.of(packet.getDimension()));
        for (var offset : positionOffsets) {
            var sectionY = centerPosition.getY() + offset.getY()/* - (dimensionInfo.minSectionY())*/;

            var hMapType = HeightMapDataType.NO_DATA;
            if (sectionY < dimensionInfo.minSectionY() || sectionY > dimensionInfo.maxSectionY()) {
                log.warn("Player {} requested sub chunk which is out of bounds", player.getOriginName());
                createSubChunkData(
                        responseData, SubChunkRequestResult.INDEX_OUT_OF_BOUNDS, offset, hMapType,
                        null, null, null
                );
                continue;
            }

            int
                    cx = centerPosition.getX() + offset.getX(),
                    cz = centerPosition.getZ() + offset.getZ();
            var chunk = player.getDimension().getChunkService().getChunk(cx, cz);
            if (chunk == null) {
                log.warn("Player {} requested sub chunk which is not loaded", player.getOriginName());
                createSubChunkData(
                        responseData, SubChunkRequestResult.CHUNK_NOT_FOUND, offset, hMapType,
                        null, null, null
                );
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

            var hMap = new byte[256];
            boolean
                    higher = true,
                    lower = true;
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    var y = chunk.getHeight(x, z);
                    var i = (z << 4) | x;
                    var hSectionY = y >> 4;
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
            if (subChunk.isEmpty()) subChunkRequestResult = SubChunkRequestResult.SUCCESS_ALL_AIR;
            else subChunkRequestResult = SubChunkRequestResult.SUCCESS;
            createSubChunkData(
                    responseData, subChunkRequestResult, offset, hMapType,
                    heightMapData, subChunk, chunk.getSectionBlockEntities(sectionY)
            );
        }

        var subChunkPacket = new SubChunkPacket();
        subChunkPacket.setSubChunks(responseData);
        subChunkPacket.setDimension(packet.getDimension());
        subChunkPacket.setCenterPosition(centerPosition);
        player.sendPacket(subChunkPacket);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SUB_CHUNK_REQUEST;
    }

    record SubChunkRequestIndex(
            org.cloudburstmc.math.vector.Vector3i centerPosition,
            org.cloudburstmc.math.vector.Vector3i offset
    ) {
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
            return Objects.hash(
                    centerPosition.getX(), centerPosition.getY(), centerPosition.getZ(),
                    offset.getX(), offset.getY(), offset.getZ()
            );
        }
    }
}
