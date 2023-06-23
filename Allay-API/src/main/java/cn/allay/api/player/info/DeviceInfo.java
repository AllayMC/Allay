package cn.allay.api.player.info;

import lombok.ToString;

/**
 * @author LucGamesYT | daoge_cmd
 * @date 2023/6/23
 * Allay Project
 */
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

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public long getClientId() {
        return this.clientId;
    }

    public Device getDevice() {
        return this.device;
    }

    public UIProfile getUIProfile() {
        return this.UIProfile;
    }
}
