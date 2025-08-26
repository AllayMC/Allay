package org.allaymc.api.blockentity.data;

public enum BedColor {
    WHITE((byte) 0),
    ORANGE((byte) 1),
    MAGENTA((byte) 2),
    LIGHT_BLUE((byte) 3),
    YELLOW((byte) 4),
    LIME((byte) 5),
    PINK((byte) 6),
    GRAY((byte) 7),
    LIGHT_GRAY((byte) 8),
    CYAN((byte) 9),
    PURPLE((byte) 10),
    BLUE((byte) 11),
    BROWN((byte) 12),
    GREEN((byte) 13),
    RED((byte) 14),
    BLACK((byte) 15);

    private final byte dataValue;

    BedColor(byte dataValue) {
        this.dataValue = dataValue;
    }

    public byte toByte() {
        return dataValue;
    }

    public int toInt() {
        return dataValue;
    }

    public static BedColor fromByte(byte b) {
        for(BedColor color : BedColor.values()) {
            if(color.dataValue == b) {
                return color;
            }
        }
        return WHITE; // default
    }

    public  static BedColor fromInt(int i) {
        return fromByte((byte) i);
    }
}
