package cn.allay.api.item.interfaces.itemirondoor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemIronDoorStack extends ItemStack {
    ItemType<ItemItemIronDoorStack> ITEM_IRON_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemItemIronDoorStack.class)
            .vanillaItem(VanillaItemId.ITEM_IRON_DOOR)
            .build();
}
