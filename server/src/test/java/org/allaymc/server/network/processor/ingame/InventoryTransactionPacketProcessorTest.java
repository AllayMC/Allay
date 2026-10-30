package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * {@code InventoryTransactionPacketProcessorTest} checks whether the shared
 * air-use path recognizes sustained-use items and skips unsupported items.
 *
 * @author Miroshka000
 */
@ExtendWith(AllayTestExtension.class)
class InventoryTransactionPacketProcessorTest {

    /**
     * Supplies an item that reports sustained air-use support for the entity.
     * The shared helper must return handled and activate the entity's use state.
     */
    @Test
    void shouldStartUsableItemThroughSharedAirUsePath() {
        var player = mock(EntityPlayer.class);
        var itemStack = mock(ItemStack.class);
        when(itemStack.canUseItemInAir(player)).thenReturn(true);

        assertTrue(new InventoryTransactionPacketProcessor().tryStartUsingItemInAir(player, itemStack));

        verify(player).setUsingItemInAir(true);
    }

    /**
     * Supplies an item with the default unsupported air-use response.
     * The helper must return unhandled and leave the entity's sustained-use flag
     * untouched for the caller's fallback decision.
     */
    @Test
    void shouldIgnoreItemsWithoutAirUseBehavior() {
        var player = mock(EntityPlayer.class);
        var itemStack = mock(ItemStack.class);

        assertFalse(new InventoryTransactionPacketProcessor().tryStartUsingItemInAir(player, itemStack));

        verify(player, never()).setUsingItemInAir(true);
    }
}
