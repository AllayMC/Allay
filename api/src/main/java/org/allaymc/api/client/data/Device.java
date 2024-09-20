package org.allaymc.api.client.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LucGamesYT | daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum Device {
    UNKNOWN(-1, "Unknown"),
    ANDROID(1, "Android"),
    IOS(2, "iOS"),
    OSX(3, "macOS"),
    AMAZON(4, "Fire OS"),
    GEAR_VR(5, "Gear VR"),
    HOLOLENS(6, "HoloLens"),
    WINDOWS(7, "Windows 10"),
    WINDOWS_32(8, "Windows"),
    DEDICATED(9, "Dedicated"),
    TVOS(10, "tvOS"),
    PLAYSTATION(11, "PlayStation"),
    NINTENDO(12, "Switch"),
    XBOX(13, "Xbox"),
    WINDOWS_PHONE(14, "Windows Phone");

    private final int id;
    private final String name;

    /**
     * Get device by id.
     *
     * @param id the id
     *
     * @return the device
     */
    public static Device getDevice(int id) {
        return switch (id) {
            case 1 -> ANDROID;
            case 2 -> IOS;
            case 3 -> OSX;
            case 4 -> AMAZON;
            case 5 -> GEAR_VR;
            case 6 -> HOLOLENS;
            case 7 -> WINDOWS;
            case 8 -> WINDOWS_32;
            case 9 -> DEDICATED;
            case 10 -> TVOS;
            case 11 -> PLAYSTATION;
            case 12 -> NINTENDO;
            case 13 -> XBOX;
            case 14 -> WINDOWS_PHONE;
            default -> null;
        };
    }

}
