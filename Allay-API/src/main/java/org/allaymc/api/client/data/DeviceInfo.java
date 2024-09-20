package org.allaymc.api.client.data;

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
