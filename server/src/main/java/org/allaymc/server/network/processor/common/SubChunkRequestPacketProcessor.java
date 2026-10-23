package org.allaymc.server.network.processor.common;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.world.chunk.OperationType;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.server.player.ChunkCache;
import org.allaymc.server.world.chunk.AllayChunkSection;
import org.allaymc.server.world.chunk.ChunkEncoder;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168;
import org.cloudburstmc.protocol.bedrock.data.HeightMapDataType;
import org.cloudburstmc.protocol.bedrock.data.SubChunkData;
import org.cloudburstmc.protocol.bedrock.data.SubChunkRequestResult;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkRequestPacket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * {@code SubChunkRequestPacketProcessor} builds sub-chunk responses from requested
 * offsets, terrain sections, height maps, and block-entity data. A request naming
 * a missing or different
 * dimension receives an empty response. In the matching dimension, each offset
 * produces its own success, all-air, or error entry, preserving the
 * relationship between the client's requested position and the collected
 * result.
 *
 * <p>
 * Only nonnull section blobs enter cache admission. Returned hashes are
 * consumed in that same subset order, while all-air and error entries use
 * protocol-appropriate empty blob IDs. If cache admission fails or no blobs
 * qualify, the response instead carries inline section data. Logical and render
 * height maps share bytes through separately retained views so the packet can
 * release both safely. Height and section collection use separate read
 * operations, so the response does not promise one atomic snapshot across all
 * terrain and entity data.
 *
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public class SubChunkRequestPacketProcessor extends PacketProcessor<SubChunkRequestPacket> {

    /**
     * {@code SubChunkInfo} keeps one requested offset associated with its collected
     * terrain and auxiliary data.
     *
     * @param offset relative requested section offset
     * @param result collection outcome for this offset
     * @param hMapType relative column-height representation
     * @param hMapData column bytes for mixed heights, or {@code null} when the
     * height-map type requires no payload
     * @param sectionBlob encoded nonair terrain bytes, or {@code null} when the result
     * reports all-air data or an error
     * @param blockEntityBytes separately encoded block entities, or {@code null} when
     * the section has none or block-entity serialization fails
     */
    private record SubChunkInfo(
        Vector3i offset,
        SubChunkRequestResult result,
        HeightMapDataType hMapType,
        byte[] hMapData,
        byte[] sectionBlob,
        byte[] blockEntityBytes
    ) {}

    /**
     * Collects and sends a sub-chunk response for an {@code AllayPlayer} with a
     * controlled entity. A mismatched or missing requested dimension produces
     * an empty uncached response, and every completed path returns {@code HANDLED}.
     *
     * <p>
     * Cache generation is captured before data collection so admission can
     * reject a stale player cache state afterward. Only nonnull section blobs
     * are submitted, and successful result entries consume their hashes in the
     * same order while errors and all-air entries do not advance that index.
     * Refused or unavailable caching falls back to inline terrain plus
     * block-entity bytes. The generated packet owns its reference-counted
     * payloads when passed to the player's send path. This processor has no
     * cleanup path for payloads left unsent by encoding failures or send rejection.
     */
    @Override
    public PacketSignal handleAsync(Player player, SubChunkRequestPacket packet, long receiveTime) {
        var allayPlayer = (AllayPlayer) player;
        var encoder = allayPlayer.getProtocol().getEncoder();
        var entity = player.getControlledEntity();
        var dimensionType = Registries.DIMENSIONS.getByK1(packet.getDimension());
        if (dimensionType == null || dimensionType != entity.getDimension().getDimensionType()) {
            // A stale or unknown dimension must not read terrain from the
            // player's current dimension under the old request identity;
            // acknowledge it with no section entries.
            allayPlayer.sendPacket(encoder.encodeSubChunk(
                    packet.getDimension(),
                    packet.getSubChunkPosition(),
                    false,
                    List.of()
            ));
            return PacketSignal.HANDLED;
        }

        ChunkCache cache = null;
        UUID playerId = null;
        int cacheGen = 0;

        if (allayPlayer.isCacheEffectivelyEnabled()) {
            cache = ChunkCache.getInstance();
            playerId = allayPlayer.getLoginData().getUuid();
            cacheGen = cache.getGeneration(playerId);
        }

        var centerPosition = packet.getSubChunkPosition();

        // Retain every requested offset and result before choosing an encoding
        // path, so errors and all-air sections keep their positions alongside
        // cached data.
        var infos = new ArrayList<SubChunkInfo>(packet.getPositionOffsets().size());
        for (var offset : packet.getPositionOffsets()) {
            infos.add(collectSubChunkInfo(player, dimensionType, centerPosition, offset));
        }

        // Cache admission receives only actual section blobs; keep this subset
        // in request order so successful entries can consume its hashes
        // sequentially.
        List<byte[]> nonAirBlobs = new ArrayList<>();
        for (var info : infos) {
            if (info.sectionBlob != null) {
                nonAirBlobs.add(info.sectionBlob);
            }
        }

        long[] hashes = null;
        if (cache != null && !nonAirBlobs.isEmpty()) {
            // Admission checks the captured generation and transaction capacity
            // after collection; only an accepted transaction can support hash
            // references in the response.
            hashes = cache.tryStoreBlobsAndOpenTransaction(playerId, cacheGen, nonAirBlobs.toArray(new byte[0][]));
        }

        boolean cacheEnabled = hashes != null;
        var responseData = new ArrayList<SubChunkData>(infos.size());
        if (cacheEnabled) {
            // Advance the hash index only for successful nonair entries,
            // preserving alignment with the filtered blob list despite
            // intervening error or all-air results.
            int hashIndex = 0;

            for (var info : infos) {
                var subChunkData = new SubChunkData();
                subChunkData.setPosition(info.offset);
                subChunkData.setResult(info.result);

                setHeightMaps(subChunkData, info.hMapType, info.hMapData);

                if (info.result == SubChunkRequestResult.SUCCESS) {
                    subChunkData.setBlobId(hashes[hashIndex++]);
                    if (info.blockEntityBytes != null) {
                        subChunkData.setData(Unpooled.wrappedBuffer(info.blockEntityBytes));
                    } else {
                        subChunkData.setData(Unpooled.EMPTY_BUFFER);
                    }
                } else {
                    // These entries contributed no cached blob, so use the
                    // version-specific empty identifier without consuming the
                    // next successful entry's hash.
                    subChunkData.setBlobId(getEmptyBlobId(allayPlayer.getProtocol().getCodec()));
                    subChunkData.setData(Unpooled.EMPTY_BUFFER);
                }

                responseData.add(subChunkData);
            }
        } else {
            // Missing or refused cache admission requires inline terrain; keep
            // block entity bytes after the section blob so the response remains
            // usable without cache references.
            for (var info : infos) {
                var subChunkData = new SubChunkData();
                subChunkData.setPosition(info.offset);
                subChunkData.setResult(info.result);

                setHeightMaps(subChunkData, info.hMapType, info.hMapData);

                if (info.result == SubChunkRequestResult.SUCCESS) {
                    ByteBuf buffer = Unpooled.wrappedBuffer(info.sectionBlob);
                    if (info.blockEntityBytes != null) {
                        buffer = Unpooled.wrappedBuffer(info.sectionBlob, info.blockEntityBytes);
                    }
                    subChunkData.setData(buffer);
                } else {
                    // All-air and error outcomes carry no terrain payload;
                    // preserve their result codes while supplying an empty
                    // buffer for this uncached path.
                    subChunkData.setData(Unpooled.EMPTY_BUFFER);
                }

                responseData.add(subChunkData);
            }
        }

        allayPlayer.sendPacket(encoder.encodeSubChunk(
                packet.getDimension(),
                centerPosition,
                cacheEnabled,
                responseData
        ));
        return PacketSignal.HANDLED;
    }

    /**
     * Assigns logical and render height maps with separately releasable buffer
     * views. A {@code null} byte array leaves both payload fields {@code null} while retaining
     * the supplied height-map type, matching optional-payload serializers.
     *
     * <p>
     * For actual data, the logical field wraps the caller's array and the
     * render field receives a retained duplicate. Both fields therefore share
     * the same bytes, but each contributes one reference that {@code SubChunkData}
     * releases independently. The caller must not mutate the array while those
     * views are in use, and this helper assumes it is populating fields without
     * existing owned buffers to release.
     */
    static void setHeightMaps(SubChunkData subChunkData, HeightMapDataType type, byte[] data) {
        subChunkData.setHeightMapType(type);
        subChunkData.setRenderHeightMapType(type);
        if (data == null) {
            // Optional height-map serializers test for null rather than buffer
            // length; leave both payloads absent when the selected map type has
            // no bytes.
            subChunkData.setHeightMapData(null);
            subChunkData.setRenderHeightMapData(null);
            return;
        }

        ByteBuf heightMap = Unpooled.wrappedBuffer(data);
        subChunkData.setHeightMapData(heightMap);
        // SubChunkData releases the logical and render fields separately, so
        // the shared storage needs a second retained reference before
        // assignment.
        subChunkData.setRenderHeightMapData(heightMap.retainedDuplicate());
    }

    /**
     * Returns the absent-blob representation required by the selected codec.
     * Versions from {@code v2168} use a {@code null} optional ID, while earlier serializers
     * receive a numeric zero placeholder for entries with no cached terrain.
     */
    static Long getEmptyBlobId(BedrockCodec codec) {
        // New serializers encode optional blob IDs, while older ones still
        // write a mandatory long; choose absence or its zero placeholder at
        // that boundary.
        return codec.getProtocolVersion() >= Bedrock_v2168.CODEC.getProtocolVersion() ? null : 0L;
    }

    /**
     * Collects the result and encoded data for one requested section offset.
     * Out-of-range heights and unloaded chunks return explicit error results
     * with no payload, and this method never requests a chunk load to satisfy
     * the lookup.
     *
     * <p>
     * Column heights are classified relative to the requested section:
     * uniformly higher or lower terrain omits the byte map, while mixed columns
     * retain per-column values and sentinels. A separate section read
     * distinguishes all-air from encoded terrain. Block-entity {@code IOException} is
     * logged and omits that auxiliary payload without changing the terrain
     * result. The separate read operations do not establish an atomic snapshot
     * across height, terrain, and block entities.
     */
    private SubChunkInfo collectSubChunkInfo(Player player, DimensionType dimensionType, Vector3i center, Vector3i offset) {
        var entity = player.getControlledEntity();

        int sectionY = center.getY() + offset.getY();
        if (sectionY < dimensionType.minSectionY() || sectionY > dimensionType.maxSectionY()) {
            log.warn("Player {} requested sub-chunk at y={} which is out of bounds ({}, {})", player.getOriginName(), sectionY, dimensionType.minSectionY(), dimensionType.maxSectionY());
            return new SubChunkInfo(offset, SubChunkRequestResult.INDEX_OUT_OF_BOUNDS, HeightMapDataType.NO_DATA, null, null, null);
        }

        int chunkX = center.getX() + offset.getX();
        int chunkZ = center.getZ() + offset.getZ();
        var chunk = entity.getDimension().getChunkManager().getChunk(chunkX, chunkZ);
        if (chunk == null) {
            return new SubChunkInfo(offset, SubChunkRequestResult.CHUNK_NOT_FOUND, HeightMapDataType.NO_DATA, null, null, null);
        }

        // Classify columns relative to the requested section; uniform high or
        // low results omit map bytes, while mixed columns retain explicit
        // values and out-of-section sentinels.
        byte[][] heightMapHolder = new byte[1][];
        HeightMapDataType[] hMapTypeHolder = new HeightMapDataType[1];

        chunk.applyOperation(unsafeChunk -> {
            var heightMap = new byte[256];
            boolean allHigher = true;
            boolean allLower = true;

            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    int height = unsafeChunk.getHeight(x, z);
                    // Sixteen block heights share one absolute section
                    // coordinate.
                    int heightSectionY = height >> 4;
                    // Height-map bytes use Z-major columns with X varying
                    // fastest.
                    int index = (z << 4) | x;

                    if (heightSectionY > sectionY) {
                        // Sixteen marks terrain above this section's local
                        // height range.
                        heightMap[index] = 16;
                        allLower = false;
                    } else if (heightSectionY < sectionY) {
                        // Minus one marks terrain below this section's local
                        // height range.
                        heightMap[index] = -1;
                        allHigher = false;
                    } else {
                        // Keep only the height within this sixteen-block section.
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

        // Section data is read separately from heights, allowing all-air
        // results to omit both the terrain blob and block-entity payload
        // without implying a single combined terrain snapshot.
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
                        // Keep the section result but omit its failed
                        // block-entity payload; partial bytes are not assigned
                        // to the response after an IOException.
                        log.error("Error while encoding block entity in sub-chunk ({}, {}, {})", chunkX, sectionY, chunkZ, e);
                    } finally {
                        // The response retains a copied byte array rather than
                        // this temporary writer buffer, so release the buffer
                        // after either success or failure.
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

    /**
     * Returns the sub-chunk request packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SUB_CHUNK_REQUEST;
    }
}
