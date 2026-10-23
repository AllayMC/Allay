package org.allaymc.server.container.impl;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.InventoryContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;

import java.util.function.Supplier;

/**
 * {@code InventoryContainerImpl} stores the player's thirty-six inventory slots and
 * tracks the slot selected for main-hand access. Its generated hand-slot setter only
 * records an index; slot notification publishes main-hand equipment when that selected
 * slot changes, in addition to ordinary player-container synchronization.
 *
 * @author daoge_cmd
 */
@Getter
public class InventoryContainerImpl extends AbstractPlayerContainer implements InventoryContainer {

    /**
     * {@code handSlot} selects the inventory entry used by hand accessors and equipment
     * refresh. It starts at zero; its generated setter performs no range validation or
     * equipment publication.
     */
    @Setter
    protected int handSlot = 0;

    /**
     * Creates empty player inventory storage with hand access initially targeting slot
     * zero. The retained owner supplier supports later built-in inventory
     * synchronization and equipment publication when that slot changes.
     */
    public InventoryContainerImpl(Supplier<EntityPlayer> playerSupplier) {
        super(ContainerTypes.INVENTORY, playerSupplier);
    }

    /**
     * Returns the shared stack at {@code handSlot}. The selected index must address the
     * inventory array; callers must notify that slot after changing the returned stack
     * directly so equipment viewers refresh.
     */
    @Override
    public ItemStack getItemInHand() {
        return getItemStack(handSlot);
    }

    /**
     * Assigns the supplied non-{@code null} stack to {@code handSlot}. Ordinary slot
     * notification follows assignment, including built-in inventory synchronization and
     * the selected-slot equipment update when an owner is available.
     */
    @Override
    public void setItemInHand(ItemStack itemStack) {
        setItemStack(handSlot, itemStack);
    }

    /**
     * Clears the selected inventory slot through the ordinary assignment path. The
     * shared air sentinel replaces its stack, and subsequent notification publishes the
     * selected-slot equipment change to available owner viewers.
     */
    @Override
    public void clearItemInHand() {
        clearSlot(handSlot);
    }

    /**
     * Publishes ordinary player-container changes and then refreshes held equipment when
     * {@code slot} equals {@code handSlot}. The equipment refresh still runs when {@code
     * send} is {@code false}, provided the supplier resolves an owner.
     */
    @Override
    public void notifySlotChange(int slot, boolean send) {
        super.notifySlotChange(slot, send);
        if (slot == handSlot) {
            var player = playerSupplier.get();
            if (player != null) {
                player.forEachViewers(viewer -> viewer.viewEntityHand(player));
            }
        }
    }

    /**
     * Returns the fixed player-inventory ID used for built-in content updates.
     */
    @Override
    public int getUnopenedContainerId() {
        return UnopenedContainerId.PLAYER_INVENTORY;
    }
}
