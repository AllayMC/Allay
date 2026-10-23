package org.allaymc.api.entity.interfaces;

import com.google.common.base.Preconditions;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityPlayerBaseComponent;
import org.allaymc.api.entity.component.EntitySleepableComponent;
import org.allaymc.api.eventbus.event.player.PlayerDropItemEvent;
import org.allaymc.api.eventbus.event.player.PlayerItemHeldEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.Player;
import org.jetbrains.annotations.ApiStatus;
import org.joml.Vector3d;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * {@code EntityPlayer} represents a player entity with inventory, physics, survival,
 * and optional network-controller behavior. A player entity can exist without a
 * {@code Player} controller, so the default inventory helpers obtain client-opened
 * containers only when a controller exists and otherwise consult the entity's own
 * containers.
 *
 * <p>
 * The drop and hand-selection helpers dispatch cancellable events before changing
 * inventory state. A rejected client hand change resends the current selection,
 * while accepted changes notify viewers and remove any active fishing hook. These
 * helpers mutate shared item stacks and container state directly; callers must use
 * them in the entity's owning execution context and must not interpret a validated
 * drop attempt as proof that its event was accepted.
 */
public interface EntityPlayer extends
        EntityLiving,
        EntityContainerHolderComponent,
        EntityPhysicsComponent,
        EntityPlayerBaseComponent,
        EntitySleepableComponent {

    /**
     * Returns an opened or entity-owned container of the requested type. The
     * controller's opened container takes precedence; an absent controller or opened
     * container falls back to {@code getContainer}, which may return {@code null}.
     */
    default <T extends Container> T getReachableContainer(ContainerType<T> slotType) {
        T container = null;
        var controller = getController();
        if (controller != null) {
            container = controller.getOpenedContainer(slotType);
        }

        // An opened container takes precedence, but an unavailable controller window still permits
        // access to the entity-owned inventory of that type.
        if (container == null) {
            container = getContainer(slotType);
        }

        return container;
    }

    /**
     * Adds as much of {@code itemStack} as possible to the inventory and drops any
     * remainder. Insertion reduces the supplied stack's count; the result is
     * {@code true} only when no remainder needs dropping.
     */
    default boolean tryAddItem(ItemStack itemStack) {
        getContainer(ContainerTypes.INVENTORY).tryAddItem(itemStack);
        // Insertion can consume only part of the supplied stack; drop its remaining count so inventory
        // capacity does not discard those items.
        if (itemStack.getCount() != 0) {
            dropItemInPlayerPos(itemStack);
            return false;
        }

        return true;
    }

    /**
     * Attempts to drop items from the selected inventory slot using the normal drop
     * checks. Callers must supply a positive {@code count}; this wrapper does not
     * validate positivity, and {@code true} can still precede event cancellation.
     */
    default boolean tryDropItemInHand(int count) {
        return tryDropItem(ContainerTypes.INVENTORY, getContainer(ContainerTypes.INVENTORY).getHandSlot(), count);
    }

    /**
     * Checks container reachability, a nonair source item, and sufficient source
     * count before attempting a drop. Callers must supply a positive {@code count},
     * because this method does not validate positivity. A {@code true} result means
     * those checks passed; the drop event can still be cancelled.
     */
    default boolean tryDropItem(ContainerType<?> containerType, int slot, int count) {
        var container = getReachableContainer(containerType);
        if (container == null) {
            return false;
        }

        var item = container.getItemStack(slot);
        if (item.getItemType() == AIR) {
            return false;
        } else if (item.getCount() < count) {
            return false;
        }

        forceDropItem(container, slot, count);
        return true;
    }

    /**
     * Drops items from the supplied container after a cancellable event approves the
     * source stack. Callers provide a nonair stack and a positive {@code count};
     * this method does not perform those checks, and a count at least as large as
     * the stack removes the entire slot.
     *
     * <p>
     * Cancellation sends a slot notification without changing its contents. Partial
     * drops reduce the existing stack and create a separate stack for the dropped
     * count; full drops transfer the existing stack after clearing the slot.
     * Inventory mutation precedes world insertion, so a failure while dropping does
     * not roll back the slot.
     */
    default void forceDropItem(Container container, int slot, int count) {
        var item = container.getItemStack(slot);
        var event = new PlayerDropItemEvent(this, item);
        if (!event.call()) {
            // A rejected drop may already be predicted by the client; resend the unchanged slot so the
            // visible inventory returns to server state.
            container.notifySlotChange(slot);
            return;
        }

        // Keep the source slot and dropped stack from sharing the partial-drop count; full drops
        // instead transfer ownership after clearing the slot.
        ItemStack droppedItemStack;
        if (item.getCount() > count) {
            item.reduceCount(count);
            container.notifySlotChange(slot);
            droppedItemStack = item.copy();
            droppedItemStack.setCount(count);
        } else {
            droppedItemStack = item;
            container.clearSlot(slot);
        }

        dropItemInPlayerPos(droppedItemStack);
    }

    /**
     * Spawns the supplied stack near the player's eyes with forward motion and a
     * forty-tick pickup delay. The dimension receives the same stack reference, so
     * callers must relinquish mutable ownership after handing it to the dropped
     * entity.
     */
    default void dropItemInPlayerPos(ItemStack itemStack) {
        var playerLoc = getLocation();
        var dimension = playerLoc.dimension();
        // Use a point below eye level and forward motion so the dropped entity leaves the hand with a
        // forty-tick pickup delay.
        dimension.dropItem(
                itemStack,
                playerLoc.add(0, this.getEyeHeight() - 0.4f, 0, new Vector3d()),
                MathUtils.getDirectionVector(playerLoc.yaw(), playerLoc.pitch()).mul(0.4f),
                40
        );
    }

    /**
     * Returns the currently selected hotbar slot from the inventory container.
     */
    default int getHandSlot() {
        return getContainer(ContainerTypes.INVENTORY).getHandSlot();
    }

    /**
     * Requests a server-originated change to a hotbar slot between {@code 0} and
     * {@code 8}. The detailed overload dispatches the held-item event and sends the
     * accepted selection to viewers and the controller.
     */
    default void setHandSlot(int handSlot) {
        setHandSlot(handSlot, true);
    }

    /**
     * Changes hotbar selection after the held-item event accepts the proposed slot.
     * {@code handSlot} must be between {@code 0} and {@code 8}; selecting the
     * existing slot returns without dispatching an event.
     *
     * <p>
     * A cancelled client-originated change resends the current hand to its
     * controller so prediction cannot retain the rejected selection. Acceptance
     * stores the selection before notifying viewers; only server-originated changes
     * also notify the controller directly. An active fishing hook is removed after
     * the accepted change, so switching slots ends the old held item's fishing
     * association.
     *
     * @throws IllegalArgumentException if {@code handSlot} is outside the hotbar
     */
    @ApiStatus.Internal
    default void setHandSlot(int handSlot, boolean serverSide) {
        Preconditions.checkArgument(handSlot >= 0 && handSlot <= 8);
        var container = getContainer(ContainerTypes.INVENTORY);
        var oldHandSlot = container.getHandSlot();
        if (oldHandSlot == handSlot) {
            return;
        }

        var oldItemStack = container.getItemInHand();
        var newItemStack = container.getItemStack(handSlot);
        var event = new PlayerItemHeldEvent(this, oldItemStack, oldHandSlot, newItemStack, handSlot);
        if (!event.call()) {
            // The controlling client already predicted its requested selection; a rejected client
            // request must receive the authoritative hand to undo that prediction.
            if (!serverSide) {
                var controller = getController();
                if (controller != null) {
                    controller.viewEntityHand(this);
                }
            }
            return;
        }

        container.setHandSlot(handSlot);
        forEachViewers(viewer -> viewer.viewEntityHand(this));
        if (serverSide) {
            var controller = getController();
            if (controller != null) {
                controller.viewEntityHand(this);
            }
        }

        // Remove the existing hook after committing the selection so the player cannot retain a cast
        // associated with the previously held item.
        var fishingHook = getFishingHook();
        if (fishingHook != null) {
            fishingHook.remove();
            setFishingHook(null);
        }
    }

    /**
     * Returns the mutable item stack in the selected inventory slot.
     */
    default ItemStack getItemInHand() {
        return getContainer(ContainerTypes.INVENTORY).getItemInHand();
    }

    /**
     * Replaces the selected inventory slot with the supplied item stack. The
     * inventory container handles slot notification; this helper does not change
     * hotbar selection or dispatch a hand-selection event.
     */
    default void setItemInHand(ItemStack itemStack) {
        getContainer(ContainerTypes.INVENTORY).setItemInHand(itemStack);
    }

    /**
     * Clears the currently selected inventory slot through its container
     * implementation.
     */
    default void clearItemInHand() {
        getContainer(ContainerTypes.INVENTORY).clearItemInHand();
    }

    /**
     * Consumes one unit from the held stack unless the entity is in
     * {@code CREATIVE}. A single remaining item clears the slot; reducing a larger
     * stack does not itself send a slot notification.
     */
    default void tryConsumeItemInHand() {
        if (getGameMode() == GameMode.CREATIVE) {
            return;
        }

        var itemInHand = getItemInHand();
        if (itemInHand.getCount() == 1) {
            clearItemInHand();
        } else {
            itemInHand.reduceCount(1);
        }
    }

    /**
     * Synchronizes the held slot after a caller mutates its stack. A zero count
     * replaces the held item with {@code ItemAirStack.AIR_STACK}; a nonzero count
     * sends the container's slot-change notification.
     */
    default void notifyItemInHandChange() {
        var inv = getContainer(ContainerTypes.INVENTORY);
        var itemStack = inv.getItemInHand();
        if (itemStack.getCount() != 0) {
            inv.notifySlotChange(inv.getHandSlot());
        } else {
            inv.setItemInHand(ItemAirStack.AIR_STACK);
        }
    }
}

