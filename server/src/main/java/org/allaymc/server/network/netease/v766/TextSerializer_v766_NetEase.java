package org.allaymc.server.network.netease.v766;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v685.serializer.TextSerializer_v685;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

/**
 * @author daoge_cmd
 */
public class TextSerializer_v766_NetEase extends TextSerializer_v685 {

    public static final TextSerializer_v685 INSTANCE = new TextSerializer_v766_NetEase();

    @Override
    public void serialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        super.serialize(buffer, helper, packet);

        var type = packet.getType();
        if (type == TextPacket.Type.CHAT || type == TextPacket.Type.POPUP) {
            helper.writeString(buffer, "");
        }
    }

    @Override
    public void deserialize(ByteBuf buffer, BedrockCodecHelper helper, TextPacket packet) {
        super.deserialize(buffer, helper, packet);

        var type = packet.getType();
        if (type == TextPacket.Type.CHAT || type == TextPacket.Type.POPUP) {
            helper.readString(buffer);
        }
    }
}
