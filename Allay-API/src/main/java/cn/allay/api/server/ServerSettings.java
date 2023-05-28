package cn.allay.api.server;

import lombok.Builder;

/**
 * Server settings
 */
@Builder
public record ServerSettings(
        String motd,
        String subMotd,
        int maxPlayerCount,
        String ip,
        int port
) {
}
