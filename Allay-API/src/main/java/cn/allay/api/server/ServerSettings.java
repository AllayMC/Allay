package cn.allay.api.server;

import lombok.Builder;
import org.cloudburstmc.protocol.bedrock.data.GameType;

/**
 * Server settings
 */
@Builder
public record ServerSettings(
        String motd,
        String subMotd,
        String ip,
        int port,
        int maxClientCount,
        GameType gameType,
        boolean xboxAuth,
        int defaultTickingRadius,
        int defaultViewDistance
) {
}
