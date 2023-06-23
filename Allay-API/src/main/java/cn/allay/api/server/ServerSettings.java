package cn.allay.api.server;

import cn.allay.api.network.NetworkSettings;
import lombok.Builder;

/**
 * Server settings
 */
@Builder
public record ServerSettings(
        NetworkSettings networkSettings
) {
}
