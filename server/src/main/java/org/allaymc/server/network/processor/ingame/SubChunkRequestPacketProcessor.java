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
import org.allaymc.server.player.AllayPlayer;
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
import java.util.List;
import java.util.Objects;

/**
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public class SubChunkRequestPacketProcessor extends PacketProcessor<SubChunkRequestPacket> {

    private record SubChunkInfo(
        Vector3i offset,
        SubChunkRequestResult result,
        HeightMapDataType hMapType,
        byte[] hMapData,
        byte[] sectionBlob,
        byte[] blockEntityBytes
    ) {}

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

        var allayPlayer = (AllayPlayer) player;
        var cache = allayPlayer.isCacheEffectivelyEnabled() ? allayPlayer.getChunkCache() : null;
        int cacheGen = cache != null ? cache.generation() : 0;

        var centerPosition = packet.getSubChunkPosition();

        // Phase 1: Collect raw data
        var infos = new ArrayList<SubChunkInfo>(packet.getPositionOffsets().size());
        for (var offset : packet.getPositionOffsets()) {
            infos.add(collectSubChunkInfo(player, dimensionInfo, centerPosition, offset));
        }

        // Phase 2: Decide caching + build SubChunkData
        var subChunkPacket = new SubChunkPacket();
        subChunkPacket.setDimension(packet.getDimension());
        subChunkPacket.setCenterPosition(centerPosition);

        // Collect non-null section blobs for caching
        List<byte[]> nonAirBlobs = new ArrayList<>();
        for (var info : infos) {
            if (info.sectionBlob != null) {
                nonAirBlobs.add(info.sectionBlob);
            }
        }

        long[] hashes = null;
        if (cache != null && !nonAirBlobs.isEmpty()) {
            hashes = cache.tryStoreBlobsAndOpenTransaction(cacheGen, nonAirBlobs.toArray(new byte[0][]));
        }

        if (hashes != null) {
            // Cached path
            subChunkPacket.setCacheEnabled(true);
            int hashIndex = 0;
            var responseData = new ArrayList<SubChunkData>(infos.size());

            for (var info : infos) {
                var subChunkData = new SubChunkData();
                subChunkData.setPosition(info.offset);
                subChunkData.setResult(info.result);

                // Height maps
                if (info.hMapData != null) {
                    ByteBuf hMapBuf = Unpooled.wrappedBuffer(info.hMapData);
                    subChunkData.setHeightMapType(info.hMapType);
                    subChunkData.setHeightMapData(hMapBuf);
                    subChunkData.setRenderHeightMapType(info.hMapType);
                    subChunkData.setRenderHeightMapData(hMapBuf);
                } else {
                    subChunkData.setHeightMapType(HeightMapDataType.NO_DATA);
                    subChunkData.setRenderHeightMapType(HeightMapDataType.NO_DATA);
                }

                // Data
                if (info.result == SubChunkRequestResult.SUCCESS) {
                    subChunkData.setBlobId(hashes[hashIndex++]);
                    if (info.blockEntityBytes != null) {
                        subChunkData.setData(Unpooled.wrappedBuffer(info.blockEntityBytes));
                    } else {
                        subChunkData.setData(Unpooled.EMPTY_BUFFER);
                    }
                } else {
                    // SUCCESS_ALL_AIR or error results
                    subChunkData.setBlobId(0);
                    subChunkData.setData(Unpooled.EMPTY_BUFFER);
                }

                responseData.add(subChunkData);
            }

            subChunkPacket.setSubChunks(responseData);
        } else {
            // Non-cached path
            subChunkPacket.setCacheEnabled(false);
            var responseData = new ArrayList<SubChunkData>(infos.size());

            for (var info : infos) {
                var subChunkData = new SubChunkData();
                subChunkData.setPosition(info.offset);
                subChunkData.setResult(info.result);

                // Height maps
                if (info.hMapData != null) {
                    ByteBuf hMapBuf = Unpooled.wrappedBuffer(info.hMapData);
                    subChunkData.setHeightMapType(info.hMapType);
                    subChunkData.setHeightMapData(hMapBuf);
                    subChunkData.setRenderHeightMapType(info.hMapType);
                    subChunkData.setRenderHeightMapData(hMapBuf);
                } else {
                    subChunkData.setHeightMapType(HeightMapDataType.NO_DATA);
                    subChunkData.setRenderHeightMapType(HeightMapDataType.NO_DATA);
                }

                // Data - combine section blob + block entities
                if (info.result == SubChunkRequestResult.SUCCESS) {
                    ByteBuf buffer = Unpooled.wrappedBuffer(info.sectionBlob);
                    if (info.blockEntityBytes != null) {
                        buffer = Unpooled.wrappedBuffer(info.sectionBlob, info.blockEntityBytes);
                    }
                    subChunkData.setData(buffer);
                } else {
                    // SUCCESS_ALL_AIR or error results
                    subChunkData.setData(Unpooled.EMPTY_BUFFER);
                }

                responseData.add(subChunkData);
            }

            subChunkPacket.setSubChunks(responseData);
        }

        player.sendPacket(subChunkPacket);
        return PacketSignal.HANDLED;
    }

    private SubChunkInfo collectSubChunkInfo(Player player, DimensionInfo dimensionInfo, Vector3i center, Vector3i offset) {
        var entity = player.getControlledEntity();

        int sectionY = center.getY() + offset.getY();
        if (sectionY < dimensionInfo.minSectionY() || sectionY > dimensionInfo.maxSectionY()) {
            log.warn("Player {} requested sub-chunk at y={} which is out of bounds ({}, {})", player.getOriginName(), sectionY, dimensionInfo.minSectionY(), dimensionInfo.maxSectionY());
            return new SubChunkInfo(offset, SubChunkRequestResult.INDEX_OUT_OF_BOUNDS, HeightMapDataType.NO_DATA, null, null, null);
        }

        int chunkX = center.getX() + offset.getX();
        int chunkZ = center.getZ() + offset.getZ();
        var chunk = entity.getDimension().getChunkManager().getChunk(chunkX, chunkZ);
        if (chunk == null) {
            return new SubChunkInfo(offset, SubChunkRequestResult.CHUNK_NOT_FOUND, HeightMapDataType.NO_DATA, null, null, null);
        }

        // Compute height map
        byte[][] heightMapHolder = new byte[1][];
        HeightMapDataType[] hMapTypeHolder = new HeightMapDataType[1];

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

            if (allHigher) {
                hMapTypeHolder[0] = HeightMapDataType.TOO_HIGH;
                heightMapHolder[0] = null;
            } else if (allLower) {
                hMapTypeHolder[0] = HeightMapDataType.TOO_LOW;
                heightMapHolder[0] = null;
            } else {
                hMapTypeHolder[0] = HeightMapDataType.HAS_DATA;
                heightMapHolder[0] = heightMap;
            }
        }, OperationType.READ, OperationType.NONE);

        // Encode section and block entities
        byte[][] sectionBlobHolder = new byte[1][];
        byte[][] blockEntityBytesHolder = new byte[1][];
        SubChunkRequestResult[] resultHolder = new SubChunkRequestResult[1];

        chunk.applyOperationInSection(sectionY, s -> {
            var subChunk = (AllayChunkSection) s;
            if (subChunk.isAirSection()) {
                resultHolder[0] = SubChunkRequestResult.SUCCESS_ALL_AIR;
                sectionBlobHolder[0] = null;
                blockEntityBytesHolder[0] = null;
            } else {
                resultHolder[0] = SubChunkRequestResult.SUCCESS;
                sectionBlobHolder[0] = ChunkEncoder.encodeSectionBlob(subChunk);

                var blockEntities = chunk.getSectionBlockEntities(sectionY);
                if (!blockEntities.isEmpty()) {
                    var buffer = ByteBufAllocator.DEFAULT.ioBuffer();
                    try (var writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(buffer))) {
                        for (var blockEntity : blockEntities) {
                            writer.writeTag(blockEntity.saveNBT());
                        }
                        byte[] beBytes = new byte[buffer.readableBytes()];
                        buffer.readBytes(beBytes);
                        blockEntityBytesHolder[0] = beBytes;
                    } catch (IOException e) {
                        log.error("Error while encoding block entity in sub-chunk ({}, {}, {})", chunkX, sectionY, chunkZ, e);
                    } finally {
                        buffer.release();
                    }
                } else {
                    blockEntityBytesHolder[0] = null;
                }
            }
        }, OperationType.READ, OperationType.NONE);

        return new SubChunkInfo(
            offset,
            resultHolder[0],
            hMapTypeHolder[0],
            heightMapHolder[0],
            sectionBlobHolder[0],
            blockEntityBytesHolder[0]
        );
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SUB_CHUNK_REQUEST;
    }
}