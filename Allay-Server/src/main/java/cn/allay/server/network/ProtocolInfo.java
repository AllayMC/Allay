package cn.allay.server.network;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v589.Bedrock_v589;
import org.cloudburstmc.protocol.bedrock.codec.v594.Bedrock_v594;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class ProtocolInfo {

    private static final Set<BedrockCodec> PACKET_CODECS = new HashSet<>();
    private static final Set<BedrockCodec> UNMODIFIABLE_PACKET_CODECS = Collections.unmodifiableSet(PACKET_CODECS);

    private static BedrockCodec DEFAULT_PACKET_CODEC;

    static {
        registerDefaultPacketCodec(Bedrock_v594.CODEC);
    }

    private ProtocolInfo() {
    }

    @Nullable
    public static BedrockCodec getPacketCodec(@Nonnegative int protocolVersion) {
        for (BedrockCodec packetCodec : PACKET_CODECS) {
            if (packetCodec.getProtocolVersion() == protocolVersion) {
                return packetCodec;
            }
        }
        return null;
    }

    @Nonnull
    public static BedrockCodec getDefaultPacketCodec() {
        return DEFAULT_PACKET_CODEC;
    }

    public static Set<BedrockCodec> getAvailableCodecs() {
        return UNMODIFIABLE_PACKET_CODECS;
    }

    private static void registerPacketCodec(BedrockCodec codec) {
        PACKET_CODECS.add(codec);
    }

    private static void registerDefaultPacketCodec(BedrockCodec codec) {
        DEFAULT_PACKET_CODEC = codec;
        registerPacketCodec(codec);
    }
}
