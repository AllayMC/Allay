package org.allaymc.api.client.data;

/**
 * Allay Project 2023/6/23
 *
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
