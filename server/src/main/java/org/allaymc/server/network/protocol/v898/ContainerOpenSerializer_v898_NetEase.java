package org.allaymc.server.network.protocol.v898;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v860_netease.serializer.ContainerOpenSerializer_v860_NetEase;
import org.cloudburstmc.protocol.bedrock.packet.ContainerOpenPacket;

/**
 * NetEase container open serialization for 1.21.130.
 *
 * <p>On top of the {@code ignoreBlock} boolean that 1.21.124 added, the 1.21.130 client also reads
 * two strings and a boolean. Omitting them makes the client read past the end of the packet and abort
 * the connection as soon as a container is opened.</p>
 *
 * @author daoge_cmd
 */
public class ContainerOpenSerializer_v898_NetEase extends ContainerOpenSerializer_v860_NetEase {

    /**
     * Shared instance.
     */
    public static final ContainerOpenSerializer_v898_NetEase INSTANCE = new ContainerOpenSerializer_v898_NetEase();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, ContainerOpenPacket packet) {
        super.serialize(buffer, helper, packet);
        helper.writeString(buffer, "");
        helper.writeString(buffer, "");
        buffer.writeBoolean(false);
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, ContainerOpenPacket packet) {
        super.deserialize(buffer, helper, packet);
        if (!buffer.isReadable()) {
            return;
        }

        int readerIndex = buffer.readerIndex();
        try {
            helper.readString(buffer);
            helper.readString(buffer);
            buffer.readBoolean();
        } catch (RuntimeException e) {
            // Older NetEase layouts omit this tail entirely; leave the bytes for the frame to discard.
            buffer.readerIndex(readerIndex);
        }
    }
}
