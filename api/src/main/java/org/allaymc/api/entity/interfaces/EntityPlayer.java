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
 * EntityPlayer represents a player entity. An {@link EntityPlayer} may be (or may not be) controlled
 * by a {@link Player} object.
 */
public interface EntityPlayer extends
        EntityLiving,
        EntityContainerHolderComponent,
        EntityPhysicsComponent,
        EntityPlayerBaseComponent,
        EntitySleepableComponent {

    /**
     * Returns the reachable container for the given full container type. This
     * includes opened containers and containers that the player holds.
     *
     * @param slotType the full container type
     * @param <T>      the container type
     * @return the reachable container, or {@code null} if none
     */
    default <T extends Container> T getReachableContainer(ContainerType<T> slotType) {
        T container = null;
        var controller = getController();
        if (controller != null) {
            container = controller.getOpenedContainer(slotType);
        }

        if (container == null) {
            container = getContainer(slotType);
        }

        return container;
    }

    /**
     * Attempts to add an item stack to the player's inventory.
     * If it cannot be fully added, the remaining items are dropped at the player's position.
     *
     * @param itemStack the item stack to add
     * @return {@code true} if all items were added successfully, {@code false} if some were dropped.
     */
    default boolean tryAddItem(ItemStack itemStack) {
        getContainer(ContainerTypes.INVENTORY).tryAddItem(itemStack);
        if (itemStack.getCount() != 0) {
            dropItemInPlayerPos(itemStack);
            return false;
        }

        return true;
    }

    /**
     * Attempts to drop the item currently held in hand.
     *
     * @param count the number of items to drop
     * @return {@code true} if the item was successfully dropped, {@code false} otherwise.
     */
    default boolean tryDropItemInHand(int count) {
        return tryDropItem(ContainerTypes.INVENTORY, getContainer(ContainerTypes.INVENTORY).getHandSlot(), count);
    }

    /**
     * Attempts to drop an item from a specific slot in a container.
     *
     * @param containerType the container type
     * @param slot          the slot index
     * @param count         the number of items to drop
     * @return {@code true} if the item was successfully dropped, {@code false} otherwise.
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
     * Forces the player to drop an item from a container slot.
     * Will trigger a PlayerDropItemEvent and update the container accordingly.
     * <p>
     * Please note that this method won't check if the dropped item is valid. The
     * user should be sure that the item in given slot is not an air and have enough
     * count.
     *
     * @param container the container to drop from
     * @param slot      the slot index
     * @param count     the number of items to drop. If the count is bigger than the item currently
     *                  has, the whole item stack will be dropped.
     */
    default void forceDropItem(Container container, int slot, int count) {
        var item = container.getItemStack(slot);
        var event = new PlayerDropItemEvent(this, item);
        if (!event.call()) {
            return;
        }

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
     * Drops the given item stack at the player's position with forward motion.
     *
     * @param itemStack the item stack to drop
     */
    default void dropItemInPlayerPos(ItemStack itemStack) {
        var playerLoc = getLocation();
        var dimension = playerLoc.dimension();
        dimension.dropItem(
                itemStack,
                playerLoc.add(0, this.getEyeHeight() - 0.4f, 0, new Vector3d()),
                MathUtils.getDirectionVector(playerLoc.yaw(), playerLoc.pitch()).mul(0.4f),
                40
        );
    }

    /**
     * Get the hand slot of the player.
     *
     * @return The hand slot of the player
     */
    default int getHandSlot() {
        return getContainer(ContainerTypes.INVENTORY).getHandSlot();
    }

    /**
     * Set the hand slot of the player.
     *
     * @param handSlot the hand slot of the player
     */
    default void setHandSlot(int handSlot) {
        setHandSlot(handSlot, true);
    }

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

        // Clear fishing hook when switching items
        var fishingHook = getFishingHook();
        if (fishingHook != null) {
            fishingHook.remove();
            setFishingHook(null);
        }
    }

    /**
     * Gets the item currently held in the player's hand.
     *
     * @return the item in hand
     */
    default ItemStack getItemInHand() {
        return getContainer(ContainerTypes.INVENTORY).getItemInHand();
    }

    /**
     * Sets the item in the player's hand.
     *
     * @param itemStack the item to set in hand
     */
    default void setItemInHand(ItemStack itemStack) {
        getContainer(ContainerTypes.INVENTORY).setItemInHand(itemStack);
    }

    /**
     * Clears the item in the player's hand.
     */
    default void clearItemInHand() {
        getContainer(ContainerTypes.INVENTORY).clearItemInHand();
    }

    /**
     * Attempts to consume one unit of the item in hand.
     * Does nothing in Creative mode.
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
     * Notifies that the item in hand has changed.
     * Will update the inventory slot or clear it if the item count is zero.
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

