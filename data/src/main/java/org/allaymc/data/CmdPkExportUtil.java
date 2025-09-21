package org.allaymc.data;

import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.server.utils.JSONUtils;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.data.EncodingSettings;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author daoge_cmd
 */
public class CmdPkExportUtil {
    private static final BedrockCodec CODEC = ProtocolInfo.getLatestCodec();

    @SneakyThrows
    public static void main(String[] args) {
        BedrockCodecHelper helper = CODEC.createHelper();
        helper.setEncodingSettings(EncodingSettings.UNLIMITED);
        try (InputStream resourceAsStream = CmdPkExportUtil.class.getClassLoader().getResourceAsStream("available_commands_packet.bin")) {
            Preconditions.checkNotNull(resourceAsStream);
            ByteBuf byteBuf = Unpooled.wrappedBuffer(resourceAsStream.readAllBytes());
            // 跳过AvailableCommandsPacket的packet id
            byteBuf.skipBytes(1);
            var pk = new AvailableCommandsPacket();
            CODEC.getPacketDefinition(AvailableCommandsPacket.class).getSerializer().deserialize(byteBuf, helper, pk);
            Files.deleteIfExists(Path.of("data/resources/unpacked/cmd_pk.json"));
            JSONUtils.toFile("./data/resources/unpacked/cmd_pk.json", pk, writer -> writer.setIndent("  "));
        }
    }
}
