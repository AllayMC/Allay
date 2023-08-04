package cn.allay.api.client.info;

import lombok.Getter;

/**
 * Allay Project 2023/6/23
 *
 * @author LucGamesYT | daoge_cmd
 */
@Getter
public enum Device {

    UNKNOWN(-1),
    ANDROID(1),
    IOS(2),
    OSX(3),
    AMAZON(4),
    GEAR_VR(5),
    HOLOLENS(6),
    WINDOWS(7),
    WINDOWS_32(8),
    DEDICATED(9),
    TVOS(10),
    PLAYSTATION(11),
    NINTENDO(12),
    XBOX(13),
    WINDOWS_PHONE(14);

    private final int id;

    Device(int id) {
        this.id = id;
    }

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
