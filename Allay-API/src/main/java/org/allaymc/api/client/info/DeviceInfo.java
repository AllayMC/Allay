package org.allaymc.api.client.info;

import lombok.Getter;
import lombok.ToString;

/**
 * Allay Project 2023/6/23
 *
 * @author LucGamesYT | daoge_cmd
 */
@Getter
@ToString
public class DeviceInfo {

    private final String deviceName;
    private final String deviceId;
    private final long clientId;
    private final Device device;
    private final UIProfile UIProfile;

    public DeviceInfo(String deviceName, String deviceId, long clientId, Device device, UIProfile UIProfile) {
        this.deviceName = deviceName;
        this.deviceId = deviceId;
        this.clientId = clientId;
        this.device = device;
        this.UIProfile = UIProfile;
    }

}
