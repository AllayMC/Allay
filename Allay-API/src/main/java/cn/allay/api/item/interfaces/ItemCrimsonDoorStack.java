package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonDoorStack extends ItemStack {
    ItemType<ItemCrimsonDoorStack> CRIMSON_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonDoorStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_DOOR)
            .build();
}
