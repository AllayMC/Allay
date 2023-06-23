package cn.allay.api.server;

import cn.allay.api.world.GameMode;
import lombok.Builder;

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
        GameMode gameType,
        boolean xboxAuth
) {
}
