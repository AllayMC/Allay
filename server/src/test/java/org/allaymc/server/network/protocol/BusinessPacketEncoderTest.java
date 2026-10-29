package org.allaymc.server.network.protocol;

import org.allaymc.server.network.protocol.v766.PacketEncoder_v766;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.SubChunkData;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponse;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseStatus;
import org.cloudburstmc.protocol.bedrock.packet.RespawnPacket;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class BusinessPacketEncoderTest {

    private final PacketEncoder encoder = new PacketEncoder_v766(mock(ProtocolData.class));

    @Test
    void simpleBusinessPacketsAreFreshAndCarryTheirInputs() {
        var domainPosition = new org.joml.Vector3i(1, 2, 3);
        var networkPosition = Vector3i.from(1, 2, 3);
        var blockData = NbtMap.builder().putString("id", "Chest").build();
        var firstBlockEntity = encoder.encodeBlockEntityData(domainPosition, blockData);
        var secondBlockEntity = encoder.encodeBlockEntityData(domainPosition, blockData);
        assertNotSame(firstBlockEntity, secondBlockEntity);
        assertEquals(networkPosition, firstBlockEntity.getBlockPosition());
        assertEquals(blockData, firstBlockEntity.getData());
        assertNotSame(blockData, firstBlockEntity.getData());

        var firstRadius = encoder.encodeChunkRadiusUpdated(12);
        var secondRadius = encoder.encodeChunkRadiusUpdated(12);
        assertNotSame(firstRadius, secondRadius);
        assertEquals(12, firstRadius.getRadius());

        var subChunks = new ArrayList<SubChunkData>();
        var firstSubChunk = encoder.encodeSubChunk(2, networkPosition, true, subChunks);
        var secondSubChunk = encoder.encodeSubChunk(2, networkPosition, true, subChunks);
        try {
            subChunks.add(new SubChunkData());
            assertNotSame(firstSubChunk, secondSubChunk);
            assertEquals(2, firstSubChunk.getDimension());
            assertEquals(networkPosition, firstSubChunk.getCenterPosition());
            assertTrue(firstSubChunk.isCacheEnabled());
            assertTrue(firstSubChunk.getSubChunks().isEmpty());
        } finally {
            assertTrue(firstSubChunk.release());
            assertTrue(secondSubChunk.release());
        }

        var firstClose = encoder.encodeContainerClose((byte) 4, ContainerType.CONTAINER, false);
        var secondClose = encoder.encodeContainerClose((byte) 4, ContainerType.CONTAINER, false);
        assertNotSame(firstClose, secondClose);
        assertEquals(4, firstClose.getId());
        assertEquals(ContainerType.CONTAINER, firstClose.getType());
        assertFalse(firstClose.isServerInitiated());

        var response = new ItemStackResponse(ItemStackResponseStatus.ERROR, 7, null);
        var responses = new ArrayList<>(List.of(response));
        var firstResponse = encoder.encodeItemStackResponse(responses);
        var secondResponse = encoder.encodeItemStackResponse(responses);
        responses.clear();
        assertNotSame(firstResponse, secondResponse);
        assertEquals(1, firstResponse.getEntries().size());
        assertSame(response, firstResponse.getEntries().getFirst());

        var firstRespawn = encoder.encodeRespawn(domainPosition);
        var secondRespawn = encoder.encodeRespawn(domainPosition);
        assertNotSame(firstRespawn, secondRespawn);
        assertEquals(org.cloudburstmc.math.vector.Vector3f.from(1, 2, 3), firstRespawn.getPosition());
        assertEquals(RespawnPacket.State.SERVER_READY, firstRespawn.getState());

        var firstSettings = encoder.encodeServerSettingsResponse(9, "{\"type\":\"custom_form\"}");
        var secondSettings = encoder.encodeServerSettingsResponse(9, "{\"type\":\"custom_form\"}");
        assertNotSame(firstSettings, secondSettings);
        assertEquals(9, firstSettings.getFormId());
        assertEquals("{\"type\":\"custom_form\"}", firstSettings.getFormData());
    }

    @Test
    void cacheMissPacketsOwnIndependentBlobBuffers() {
        var blob = new byte[]{1, 2, 3, 4};
        var blobs = new LinkedHashMap<Long, byte[]>();
        blobs.put(11L, blob);

        var first = encoder.encodeClientCacheMissResponse(blobs);
        var second = encoder.encodeClientCacheMissResponse(blobs);
        try {
            blob[0] = 8;
            first.getBlobs().get(11L).setByte(1, 9);
            assertEquals(1, first.getBlobs().get(11L).getByte(0));
            assertEquals(2, second.getBlobs().get(11L).getByte(1));
            assertNotSame(first, second);
            assertNotSame(first.getBlobs().get(11L), second.getBlobs().get(11L));
        } finally {
            assertTrue(first.release());
            assertTrue(second.release());
        }
    }
}
