package org.allaymc.server.network.protocol.v898;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v898.serializer.TextSerializer_v898;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

/**
 * NetEase text serialization for 1.21.130.
 *
 * <p>NetEase builds append an extra string after chat and popup messages, which vanilla 1.21.130 does
 * not have. The base serializer must stay the 1.21.130 one because that version changed the text
 * packet layout; reusing the older NetEase serializer makes the client fail to read the packet.</p>
 *
 * @author daoge_cmd
 */
public class TextSerializer_v898_NetEase extends TextSerializer_v898 {

    /**
     * Shared instance.
     */
    public static final TextSerializer_v898_NetEase INSTANCE = new TextSerializer_v898_NetEase();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        super.serialize(buffer, helper, packet);
        if (packet.getType() == TextPacket.Type.CHAT || packet.getType() == TextPacket.Type.POPUP) {
            helper.writeString(buffer, packet.getUnknown());
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        super.deserialize(buffer, helper, packet);
        if (packet.getType() == TextPacket.Type.CHAT || packet.getType() == TextPacket.Type.POPUP) {
            packet.setUnknown(helper.readString(buffer));
        }
    }
}
