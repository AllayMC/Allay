package org.allaymc.api.entity.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BoatVariantTest {
    @Test
    void networkIdsMatchBedrockVariants() {
        for (var variant : BoatVariant.values()) {
            assertEquals(variant, BoatVariant.fromNetworkId(variant.getNetworkId()));
        }
        assertEquals(7, BoatVariant.BAMBOO.getNetworkId());
        assertNull(BoatVariant.fromNetworkId(-1));
    }
}
