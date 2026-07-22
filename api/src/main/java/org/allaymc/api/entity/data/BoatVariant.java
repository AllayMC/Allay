package org.allaymc.api.entity.data;

import org.jetbrains.annotations.Nullable;

/**
 * Visual/material variants used by vanilla boat entities.
 */
public enum BoatVariant {
    OAK(0),
    SPRUCE(1),
    BIRCH(2),
    JUNGLE(3),
    ACACIA(4),
    DARK_OAK(5),
    MANGROVE(6),
    BAMBOO(7),
    CHERRY(8),
    PALE_OAK(9);

    private final int networkId;

    BoatVariant(int networkId) {
        this.networkId = networkId;
    }

    public int getNetworkId() {
        return networkId;
    }

    public boolean isRaft() {
        return this == BAMBOO;
    }

    public static @Nullable BoatVariant fromNetworkId(int networkId) {
        for (var variant : values()) {
            if (variant.networkId == networkId) {
                return variant;
            }
        }
        return null;
    }
}
