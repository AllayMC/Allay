package cn.allay.api.item.interfaces.woodendoor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenDoorStack extends ItemStack {
    ItemType<ItemWoodenDoorStack> WOODEN_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemWoodenDoorStack.class)
            .vanillaItem(VanillaItemId.WOODEN_DOOR)
            .build();
}
