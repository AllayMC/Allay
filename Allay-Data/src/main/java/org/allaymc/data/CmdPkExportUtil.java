package org.allaymc.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v685.Bedrock_v685;
import org.cloudburstmc.protocol.bedrock.packet.AvailableCommandsPacket;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Allay Project 2024/1/20
 *
 * @author daoge_cmd
 */
public class CmdPkExportUtil {
    private static final BedrockCodec CODEC = Bedrock_v685.CODEC;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    @SneakyThrows
    public static void main(String[] args) {
        BedrockCodecHelper helper = CODEC.createHelper();
        try (InputStream resourceAsStream = CmdPkExportUtil.class.getClassLoader().getResourceAsStream("available_commands_packet.bin")) {
            assert resourceAsStream != null;
            ByteBuf byteBuf = Unpooled.wrappedBuffer(resourceAsStream.readAllBytes());
            // 跳过AvailableCommandsPacket的packet id
            byteBuf.skipBytes(1);
            var pk = new AvailableCommandsPacket();
            CODEC.getPacketDefinition(AvailableCommandsPacket.class).getSerializer().deserialize(byteBuf, helper, pk);
            Files.deleteIfExists(Path.of("Allay-Data/resources/unpacked/cmd_pk.json"));

            Files.writeString(Path.of("./Allay-Data/resources/unpacked/cmd_pk.json"), GSON.toJson(pk), StandardCharsets.UTF_8, StandardOpenOption.WRITE);
        }
    }
}
