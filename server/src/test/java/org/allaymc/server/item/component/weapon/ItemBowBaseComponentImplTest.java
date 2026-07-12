package org.allaymc.server.item.component.weapon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Allay Project 2026/7/12
 *
 * @author Miroshka000
 */
class ItemBowBaseComponentImplTest {

    @Test
    void infinityShouldDisablePickupOnlyForNormalArrows() {
        assertTrue(ItemBowBaseComponentImpl.shouldDisableArrowPickup(false, true, false));
        assertFalse(ItemBowBaseComponentImpl.shouldDisableArrowPickup(false, true, true));
    }

    @Test
    void creativeArrowsShouldAlwaysHavePickupDisabled() {
        assertTrue(ItemBowBaseComponentImpl.shouldDisableArrowPickup(true, false, false));
        assertTrue(ItemBowBaseComponentImpl.shouldDisableArrowPickup(true, false, true));
    }

    @Test
    void regularSurvivalArrowsShouldRemainPickable() {
        assertFalse(ItemBowBaseComponentImpl.shouldDisableArrowPickup(false, false, false));
        assertFalse(ItemBowBaseComponentImpl.shouldDisableArrowPickup(false, false, true));
    }
}
