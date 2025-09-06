package org.allaymc.api.player.data;

/**
 * @author LucGamesYT | daoge_cmd
 */
public record DeviceInfo(
        String deviceName,
        String deviceId,
        long clientId,
        Device device,
        UIProfile UIProfile
) {
}
