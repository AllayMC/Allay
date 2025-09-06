package org.allaymc.api.entity.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityContainerViewerComponent;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.player.*;
import org.allaymc.api.eventbus.event.player.PlayerDropItemEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.MathUtils;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;
import org.joml.Vector3d;

import static org.allaymc.api.item.type.ItemTypes.AIR;

public interface EntityPlayer extends
        Entity,
        EntityPlayerBaseComponent,
        EntityPlayerNetworkComponent,
        EntityPlayerAttributeComponent,
        EntityPlayerContainerHolderComponent,
        EntityContainerViewerComponent,
        EntityDamageComponent,
        EntityPhysicsComponent,
        EntityPlayerScoreboardViewerComponent {

    /**
     * Returns the reachable container for the given full container type.
     * This includes opened containers and containers that the player holds.
     *
     * @param slotType the full container type
     * @param <T>      the container type
     * @return the reachable container, or {@code null} if none
     */
    default <T extends Container> T getReachableContainer(FullContainerType<?> slotType) {
        var container = getOpenedContainer(slotType);
        if (container == null) container = getContainer(slotType);
        return (T) container;
    }

    /**
     * Returns the reachable container for the given container slot type.
     * This includes opened containers and containers that the player holds.
     *
     * @param slotType the container slot type
     * @param <T>      the container type
     * @return the reachable container, or {@code null} if none
     */
    default <T extends Container> T getReachableContainerBySlotType(ContainerSlotType slotType) {
        var container = getOpenedContainerBySlotType(slotType);
        if (container == null) container = getContainerBySlotType(slotType);
        return (T) container;
    }

    /**
     * Attempts to add an item stack to the player's inventory.
     * If it cannot be fully added, the remaining items are dropped at the player's position.
     *
     * @param itemStack the item stack to add
     * @return {@code true} if all items were added successfully, {@code false} if some were dropped.
     */
    default boolean tryAddItem(ItemStack itemStack) {
        getContainer(FullContainerType.PLAYER_INVENTORY).tryAddItem(itemStack);
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
        return tryDropItem(FullContainerType.PLAYER_INVENTORY, getContainer(FullContainerType.PLAYER_INVENTORY).getHandSlot(), count);
    }

    /**
     * Attempts to drop an item from a specific slot in a container.
     *
     * @param containerType the container type
     * @param slot          the slot index
     * @param count         the number of items to drop
     * @return {@code true} if the item was successfully dropped, {@code false} otherwise.
     */
    default boolean tryDropItem(FullContainerType<?> containerType, int slot, int count) {
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
     * Gets the item currently held in the player's hand.
     *
     * @return the item in hand
     */
    default ItemStack getItemInHand() {
        return getContainer(FullContainerType.PLAYER_INVENTORY).getItemInHand();
    }

    /**
     * Sets the item in the player's hand.
     *
     * @param itemStack the item to set in hand
     */
    default void setItemInHand(ItemStack itemStack) {
        getContainer(FullContainerType.PLAYER_INVENTORY).setItemInHand(itemStack);
    }

    /**
     * Clears the item in the player's hand.
     */
    default void clearItemInHand() {
        getContainer(FullContainerType.PLAYER_INVENTORY).clearItemInHand();
    }

    /**
     * Attempts to consume one unit of the item in hand.
     * Does nothing in Creative mode.
     */
    default void tryConsumeItemInHand() {
        if (getGameType() == GameType.CREATIVE) {
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
        var inv = getContainer(FullContainerType.PLAYER_INVENTORY);
        var itemStack = inv.getItemInHand();
        if (itemStack.getCount() != 0) {
            inv.notifySlotChange(inv.getHandSlot());
        } else {
            inv.setItemInHand(ItemAirStack.AIR_STACK);
        }
    }

    /**
     * Sends a swing arm animation packet to the player and nearby viewers.
     */
    default void swingArm() {
        var packet = new AnimatePacket();
        packet.setAction(AnimatePacket.Action.SWING_ARM);
        packet.setRuntimeEntityId(getRuntimeId());

        sendPacket(packet);
        sendPacketToViewers(packet);
    }
}

