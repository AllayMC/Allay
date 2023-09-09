package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchDoorStack extends ItemStack {
    ItemType<ItemBirchDoorStack> BIRCH_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemBirchDoorStack.class)
            .vanillaItem(VanillaItemId.BIRCH_DOOR)
            .build();
}
