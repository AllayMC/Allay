package org.allaymc.server.network.protocol.v898;

import io.netty.buffer.Unpooled;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.protocol.bedrock.codec.v860_netease.serializer.ContainerOpenSerializer_v860_NetEase;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerType;
import org.cloudburstmc.protocol.bedrock.packet.ContainerOpenPacket;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code NetEase898SerializersTest} checks the 1.21.130 NetEase wire differences that are not
 * expressible through the plain packet fields: the extra container open tail and the text tail.
 */
class NetEase898SerializersTest {

    /**
     * The 1.21.130 client reads two strings and a boolean after the container open fields, so the
     * serializer must emit three bytes more than the 1.21.124 layout.
     */
    @Test
    void containerOpenAppendsTheNetEaseTail() {
        var helper = NetEaseCodec898.CODEC.createHelper();
        var packet = containerOpenPacket();

        var previous = Unpooled.buffer();
        ContainerOpenSerializer_v860_NetEase.INSTANCE.serialize(previous, helper, packet);
        var current = Unpooled.buffer();
        ContainerOpenSerializer_v898_NetEase.INSTANCE.serialize(current, helper, packet);

        assertEquals(previous.readableBytes() + 3, current.readableBytes());
        previous.release();
        current.release();
    }

    /**
     * Round tripping a container open packet must preserve every field and consume the whole buffer,
     * which is what proves the trailing strings and boolean are framed correctly.
     */
    @Test
    void containerOpenRoundTripsWithTheTail() {
        var helper = NetEaseCodec898.CODEC.createHelper();
        var packet = containerOpenPacket();

        var buffer = Unpooled.buffer();
        ContainerOpenSerializer_v898_NetEase.INSTANCE.serialize(buffer, helper, packet);

        var decoded = new ContainerOpenPacket();
        ContainerOpenSerializer_v898_NetEase.INSTANCE.deserialize(buffer, helper, decoded);

        assertEquals(packet.getId(), decoded.getId());
        assertEquals(packet.getType(), decoded.getType());
        assertEquals(packet.getBlockPosition(), decoded.getBlockPosition());
        assertEquals(packet.getUniqueEntityId(), decoded.getUniqueEntityId());
        assertEquals(packet.isIgnoreBlock(), decoded.isIgnoreBlock());
        assertFalse(buffer.isReadable());
        buffer.release();
    }

    /**
     * A buffer produced by the 1.21.124 layout carries no tail; the reader must accept it and keep the
     * vanilla fields instead of failing the whole packet.
     */
    @Test
    void containerOpenAcceptsTheOlderLayout() {
        var helper = NetEaseCodec898.CODEC.createHelper();
        var packet = containerOpenPacket();

        var buffer = Unpooled.buffer();
        ContainerOpenSerializer_v860_NetEase.INSTANCE.serialize(buffer, helper, packet);

        var decoded = new ContainerOpenPacket();
        ContainerOpenSerializer_v898_NetEase.INSTANCE.deserialize(buffer, helper, decoded);

        assertEquals(packet.getId(), decoded.getId());
        assertEquals(packet.getUniqueEntityId(), decoded.getUniqueEntityId());
        assertTrue(buffer.readableBytes() <= 0);
        buffer.release();
    }

    /**
     * Chat messages carry a NetEase trailing string that must survive a round trip.
     */
    @Test
    void textRoundTripsWithTheNetEaseTail() {
        var helper = NetEaseCodec898.CODEC.createHelper();
        var packet = textPacket(TextPacket.Type.CHAT);
        packet.setUnknown("marker");

        var buffer = Unpooled.buffer();
        TextSerializer_v898_NetEase.INSTANCE.serialize(buffer, helper, packet);

        var decoded = new TextPacket();
        TextSerializer_v898_NetEase.INSTANCE.deserialize(buffer, helper, decoded);

        assertEquals(TextPacket.Type.CHAT, decoded.getType());
        assertEquals("hello", decoded.getMessage());
        assertEquals("marker", decoded.getUnknown());
        assertFalse(buffer.isReadable());
        buffer.release();
    }

    /**
     * Text types without the NetEase tail must not gain one, so the encoded size equals the plain
     * 1.21.130 layout.
     */
    @Test
    void textWithoutTailKeepsThePlainLayout() {
        var helper = NetEaseCodec898.CODEC.createHelper();
        var packet = textPacket(TextPacket.Type.TIP);

        var buffer = Unpooled.buffer();
        TextSerializer_v898_NetEase.INSTANCE.serialize(buffer, helper, packet);

        var decoded = new TextPacket();
        TextSerializer_v898_NetEase.INSTANCE.deserialize(buffer, helper, decoded);

        assertEquals(TextPacket.Type.TIP, decoded.getType());
        assertEquals("hello", decoded.getMessage());
        assertFalse(buffer.isReadable());
        buffer.release();
    }

    /**
     * The NetEase 1.21.130 codec must announce RakNet protocol version 8 so the connection is accepted
     * as a NetEase client.
     */
    @Test
    void codecDeclaresRakNetProtocolVersionEight() {
        assertEquals(8, NetEaseCodec898.CODEC.getRaknetProtocolVersion());
    }

    /**
     * Builds a container open packet with non-default values so a mixed up field order cannot pass.
     *
     * @return the packet used by the container open tests
     */
    private static ContainerOpenPacket containerOpenPacket() {
        var packet = new ContainerOpenPacket();
        packet.setId((byte) 7);
        packet.setType(ContainerType.CONTAINER);
        packet.setBlockPosition(Vector3i.from(12, 64, -3));
        packet.setUniqueEntityId(4242L);
        packet.setIgnoreBlock(true);
        return packet;
    }

    /**
     * Builds a text packet with every string field set, because the 1.21.130 serializer writes each of
     * them unconditionally.
     *
     * @param type text type under test
     * @return the packet used by the text tests
     */
    private static TextPacket textPacket(TextPacket.Type type) {
        var packet = new TextPacket();
        packet.setType(type);
        packet.setSourceName("source");
        packet.setMessage("hello");
        packet.setXuid("xuid");
        packet.setPlatformChatId("platform");
        packet.setFilteredMessage("");
        packet.setUnknown("");
        return packet;
    }
}
