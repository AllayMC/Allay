package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemWarpedDoorStack extends ItemStack {
    ItemType<ItemItemWarpedDoorStack> ITEM_WARPED_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemItemWarpedDoorStack.class)
            .vanillaItem(VanillaItemId.ITEM_WARPED_DOOR)
            .build();
}
