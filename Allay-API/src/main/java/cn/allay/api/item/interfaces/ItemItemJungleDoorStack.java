package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemJungleDoorStack extends ItemStack {
    ItemType<ItemItemJungleDoorStack> ITEM_JUNGLE_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemItemJungleDoorStack.class)
            .vanillaItem(VanillaItemId.ITEM_JUNGLE_DOOR)
            .build();
}
