package cn.allay.api.item.interfaces.warpeddoor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedDoorStack extends ItemStack {
    ItemType<ItemWarpedDoorStack> WARPED_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemWarpedDoorStack.class)
            .vanillaItem(VanillaItemId.WARPED_DOOR)
            .build();
}
