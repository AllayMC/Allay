package org.allaymc.server.item.component.map;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.ItemBaseComponentImpl;

/**
 * {@code ItemEmptyMapBaseComponentImpl} converts an empty map use into a newly
 * created filled-map stack. The operation first attempts held-item
 * consumption, then inserts the new map into the player inventory and drops
 * any remaining stack at the player's position.
 *
 * @author daoge_cmd
 */
public class ItemEmptyMapBaseComponentImpl extends ItemBaseComponentImpl {
    /**
     * Creates empty-map behavior from the supplied item initialization data.
     */
    public ItemEmptyMapBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Attempts to consume the held item, then gives the player a new filled
     * map. Creative consumption keeps the held item; any new-map inventory
     * remainder is dropped at the player's position.
     */
    @Override
    public void rightClickItemInAir(EntityPlayer player) {
        player.tryConsumeItemInHand();
        var itemStack = ItemTypes.FILLED_MAP.createItemStack();
        player.getContainer(ContainerTypes.INVENTORY).tryAddItem(itemStack);
        // Inventory insertion reduces the new stack's count. Drop its remainder so
        // limited inventory space does not discard the newly created filled map.
        if (itemStack.getCount() != 0) {
            player.dropItemInPlayerPos(itemStack);
        }
    }
}
