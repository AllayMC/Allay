package cn.allay.api.network;

import cn.allay.api.world.GameMode;

/**
 * @author daoge_cmd
 * @date 2023/6/23
 * Allay Project
 */
public record NetworkSettings(
        String motd,
        String subMotd,
        String ip,
        int port,
        int maxClientCount,
        GameMode gameType
) {
}
