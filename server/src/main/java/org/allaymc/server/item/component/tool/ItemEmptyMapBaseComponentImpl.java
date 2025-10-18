package org.allaymc.server.item.component.tool;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.item.component.ItemBaseComponentImpl;

/**
 * @author daoge_cmd
 */
public class ItemEmptyMapBaseComponentImpl extends ItemBaseComponentImpl {
    public ItemEmptyMapBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void rightClickItemInAir(EntityPlayer player) {
        player.tryConsumeItemInHand();
        var itemStack = ItemTypes.FILLED_MAP.createItemStack();
        player.getContainer(ContainerTypes.INVENTORY).tryAddItem(itemStack);
        if (itemStack.getCount() != 0) {
            player.dropItemInPlayerPos(itemStack);
        }
    }
}
