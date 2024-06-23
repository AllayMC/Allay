package org.allaymc.api.entity.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.common.EntityContainerViewerComponent;
import org.allaymc.api.entity.component.common.EntityDamageComponent;
import org.allaymc.api.entity.component.player.EntityPlayerAttributeComponent;
import org.allaymc.api.entity.component.player.EntityPlayerBaseComponent;
import org.allaymc.api.entity.component.player.EntityPlayerContainerHolderComponent;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.eventbus.event.world.player.PlayerThrowItemEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.utils.MathUtils;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.joml.Vector3f;

import static org.allaymc.api.container.Container.EMPTY_SLOT_PLACE_HOLDER;
import static org.allaymc.api.item.type.ItemTypes.AIR_TYPE;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPlayer extends
        Entity,
        EntityPlayerBaseComponent,
        EntityPlayerNetworkComponent,
        EntityPlayerAttributeComponent,
        EntityPlayerContainerHolderComponent,
        EntityContainerViewerComponent,
        EntityDamageComponent {

    default <T extends Container> T getReachableContainer(FullContainerType<?> slotType) {
        var container = getOpenedContainer(slotType);
        if (container == null) container = getContainer(slotType);
        return (T) container;
    }

    default <T extends Container> T getReachableContainerBySlotType(ContainerSlotType slotType) {
        var container = getOpenedContainerBySlotType(slotType);
        if (container == null) container = getContainerBySlotType(slotType);
        return (T) container;
    }

    default boolean tryDropItemInHand(int count) {
        return tryDropItem(FullContainerType.PLAYER_INVENTORY, getContainer(FullContainerType.PLAYER_INVENTORY).getHandSlot(), count);
    }

    default boolean tryDropItem(FullContainerType<?> containerType, int slot, int count) {
        var container = getReachableContainer(containerType);
        if (container == null) return false;
        var item = container.getItemStack(slot);
        if (item.getItemType() == AIR_TYPE) {
            return false;
        }
        if (item.getCount() < count) {
            return false;
        }
        forceDropItem(container, slot, count);
        return true;
    }

    default void forceDropItem(Container container, int slot, int count) {
        var item = container.getItemStack(slot);
        var event = new PlayerThrowItemEvent(this, item);
        getWorld().getEventBus().callEvent(event);
        if (event.isCancelled()) {
            return;
        }
        ItemStack droppedItemStack;
        if (item.getCount() > count) {
            item.setCount(item.getCount() - count);
            container.onSlotChange(slot);
            droppedItemStack = item.copy();
            droppedItemStack.setCount(count);
        } else {
            droppedItemStack = item;
            item = EMPTY_SLOT_PLACE_HOLDER;
            container.setItemStack(slot, item);
        }
        dropItemInPlayerPos(droppedItemStack);
    }

    default void dropItemInPlayerPos(ItemStack itemStack) {
        var playerLoc = getLocation();
        var dimension = playerLoc.dimension();
        dimension.dropItem(itemStack, playerLoc.add(0, this.getEyeHeight() - 0.25f, 0, new Vector3f()), MathUtils.getDirectionVector(playerLoc.yaw(), playerLoc.pitch()).mul(0.5f), 40);
    }

    default ItemStack getItemInHand() {
        return getContainer(FullContainerType.PLAYER_INVENTORY).getItemInHand();
    }

    default void setItemInHand(ItemStack itemStack) {
        getContainer(FullContainerType.PLAYER_INVENTORY).setItemInHand(itemStack);
    }

    default void sendItemInHandUpdate() {
        var inv = getContainer(FullContainerType.PLAYER_INVENTORY);
        var itemStack = inv.getItemInHand();
        if (itemStack.getCount() != 0) inv.onSlotChange(inv.getHandSlot());
        else inv.setItemInHand(Container.EMPTY_SLOT_PLACE_HOLDER);
    }
}

