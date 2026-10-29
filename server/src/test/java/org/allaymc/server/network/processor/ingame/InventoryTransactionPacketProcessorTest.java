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
 * Allay Project 2026/7/12
 *
 * @author Miroshka000
 */
@ExtendWith(AllayTestExtension.class)
class InventoryTransactionPacketProcessorTest {

    @Test
    void shouldStartUsableItemThroughSharedAirUsePath() {
        var player = mock(EntityPlayer.class);
        var itemStack = mock(ItemStack.class);
        when(itemStack.canUseItemInAir(player)).thenReturn(true);

        assertTrue(new InventoryTransactionPacketProcessor().tryStartUsingItemInAir(player, itemStack));

        verify(player).setUsingItemInAir(true);
    }

    @Test
    void shouldIgnoreItemsWithoutAirUseBehavior() {
        var player = mock(EntityPlayer.class);
        var itemStack = mock(ItemStack.class);

        assertFalse(new InventoryTransactionPacketProcessor().tryStartUsingItemInAir(player, itemStack));

        verify(player, never()).setUsingItemInAir(true);
    }
}
