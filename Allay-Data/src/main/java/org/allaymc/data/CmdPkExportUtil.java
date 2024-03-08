package org.allaymc.data;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import org.allaymc.api.common.utils.JSONUtils;
import org.allaymc.api.network.ProtocolInfo;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Allay Project 2024/1/20
 *
 * @author daoge_cmd
 */
public class CmdPkExportUtil {
    private static final BedrockCodec CODEC = ProtocolInfo.PACKET_CODEC;

    @SneakyThrows
    public static void main(String[] args) {
        BedrockCodecHelper helper = CODEC.createHelper();
        try (InputStream resourceAsStream = RecipeExportUtil.class.getClassLoader().getResourceAsStream("available_commands_packet.bin")) {
            Preconditions.checkNotNull(resourceAsStream);
            ByteBuf byteBuf = Unpooled.wrappedBuffer(resourceAsStream.readAllBytes());
            // 跳过AvailableCommandsPacket的packet id
            byteBuf.skipBytes(1);
            var pk = new AvailableCommandsPacket();
            CODEC.getPacketDefinition(AvailableCommandsPacket.class).getSerializer().deserialize(byteBuf, helper, pk);
            Files.deleteIfExists(Path.of("Allay-Data/resources/unpacked/cmd_pk.json"));
            JSONUtils.toFile("./Allay-Data/resources/unpacked/cmd_pk.json", pk, writer -> writer.setIndent("  "));
        }
    }
}
