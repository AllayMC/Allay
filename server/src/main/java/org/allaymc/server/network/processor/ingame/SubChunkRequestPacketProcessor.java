package org.allaymc.server.network.processor.ingame;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.api.world.chunk.OperationType;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.world.chunk.AllayChunkSection;
import org.allaymc.server.world.chunk.ChunkEncoder;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.HeightMapDataType;
import org.cloudburstmc.protocol.bedrock.data.SubChunkData;
import org.cloudburstmc.protocol.bedrock.data.SubChunkRequestResult;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkPacket;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkRequestPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public class SubChunkRequestPacketProcessor extends PacketProcessor<SubChunkRequestPacket> {

    @Override
    public PacketSignal handleAsync(Player player, SubChunkRequestPacket packet, long receiveTime) {
        var entity = player.getControlledEntity();
        var dimensionInfo = Objects.requireNonNull(DimensionInfo.of(packet.getDimension()));
        if (dimensionInfo != entity.getDimension().getDimensionInfo()) {
            // Outdated sub chunk request from a previous dimension
            var subChunkPacket = new SubChunkPacket();
            subChunkPacket.setDimension(packet.getDimension());
            subChunkPacket.setCenterPosition(packet.getSubChunkPosition());
            player.sendPacket(subChunkPacket);
            return PacketSignal.HANDLED;
        }

        var centerPosition = packet.getSubChunkPosition();
        var responseData = new ArrayList<SubChunkData>(packet.getPositionOffsets().size());
        for (var offset : packet.getPositionOffsets()) {
            responseData.add(createSubChunkDataForPlayer(player, dimensionInfo, centerPosition, offset));
        }

        var subChunkPacket = new SubChunkPacket();
        subChunkPacket.setDimension(packet.getDimension());
        subChunkPacket.setCenterPosition(centerPosition);
        subChunkPacket.setSubChunks(responseData);
        player.sendPacket(subChunkPacket);
        return PacketSignal.HANDLED;
    }

    private SubChunkData createSubChunkDataForPlayer(Player player, DimensionInfo dimensionInfo, Vector3i center, Vector3i offset) {
        var entity = player.getControlledEntity();
        var subChunkData = new SubChunkData();
        subChunkData.setPosition(offset);

        int sectionY = center.getY() + offset.getY();
        if (sectionY < dimensionInfo.minSectionY() || sectionY > dimensionInfo.maxSectionY()) {
            log.warn("Player {} requested sub-chunk at y={} which is out of bounds ({}, {})", player.getOriginName(), sectionY, dimensionInfo.minSectionY(), dimensionInfo.maxSectionY());
            subChunkData.setResult(SubChunkRequestResult.INDEX_OUT_OF_BOUNDS);
            subChunkData.setHeightMapType(HeightMapDataType.NO_DATA);
            subChunkData.setRenderHeightMapType(HeightMapDataType.NO_DATA);
            return subChunkData;
        }

        int chunkX = center.getX() + offset.getX();
        int chunkZ = center.getZ() + offset.getZ();
        var chunk = entity.getDimension().getChunkManager().getChunk(chunkX, chunkZ);
        if (chunk == null) {
            // This is possible since the chunk may still remain loading
            subChunkData.setResult(SubChunkRequestResult.CHUNK_NOT_FOUND);
            subChunkData.setHeightMapType(HeightMapDataType.NO_DATA);
            subChunkData.setRenderHeightMapType(HeightMapDataType.NO_DATA);
            return subChunkData;
        }

        // Height map
        chunk.applyOperation(unsafeChunk -> {
            var heightMap = new byte[256];
            boolean allHigher = true;
            boolean allLower = true;

            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    int height = unsafeChunk.getHeight(x, z);
                    int heightSectionY = height >> 4;
                    int index = (z << 4) | x;

                    if (heightSectionY > sectionY) {
                        heightMap[index] = 16;
                        allLower = false;
                    } else if (heightSectionY < sectionY) {
                        heightMap[index] = -1;
                        allHigher = false;
                    } else {
                        heightMap[index] = (byte) (height & 0xf);
                        allHigher = false;
                        allLower = false;
                    }
                }
            }

            HeightMapDataType hMapType;
            ByteBuf heightMapData;
            if (allHigher) {
                hMapType = HeightMapDataType.TOO_HIGH;
                heightMapData = Unpooled.EMPTY_BUFFER;
            } else if (allLower) {
                hMapType = HeightMapDataType.TOO_LOW;
                heightMapData = Unpooled.EMPTY_BUFFER;
            } else {
                hMapType = HeightMapDataType.HAS_DATA;
                heightMapData = Unpooled.wrappedBuffer(heightMap);
            }
            subChunkData.setHeightMapType(hMapType);
            subChunkData.setHeightMapData(heightMapData);
            subChunkData.setRenderHeightMapType(hMapType);
            subChunkData.setRenderHeightMapData(heightMapData);
        }, OperationType.READ, OperationType.NONE);

        // Blocks and block entities
        chunk.applyOperationInSection(sectionY, s -> {
            var subChunk = (AllayChunkSection) s;
            if (subChunk.isAirSection()) {
                subChunkData.setResult(SubChunkRequestResult.SUCCESS_ALL_AIR);
                subChunkData.setData(Unpooled.EMPTY_BUFFER);
            } else {
                subChunkData.setResult(SubChunkRequestResult.SUCCESS);

                var buffer = ByteBufAllocator.DEFAULT.ioBuffer();
                ChunkEncoder.writeToNetwork(subChunk, buffer);

                var blockEntities = chunk.getSectionBlockEntities(sectionY);
                if (!blockEntities.isEmpty()) {
                    try (var writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(buffer))) {
                        for (var blockEntity : blockEntities) {
                            writer.writeTag(blockEntity.saveNBT());
                        }
                    } catch (IOException e) {
                        log.error("Error while encoding block entity in sub-chunk ({}, {}, {})", chunkX, sectionY, chunkZ, e);
                    }
                }
                subChunkData.setData(buffer);
            }
        }, OperationType.READ, OperationType.NONE);

        return subChunkData;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SUB_CHUNK_REQUEST;
    }
}