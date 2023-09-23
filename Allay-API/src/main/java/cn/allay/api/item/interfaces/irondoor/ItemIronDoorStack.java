package cn.allay.api.item.interfaces.irondoor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronDoorStack extends ItemStack {
    ItemType<ItemIronDoorStack> IRON_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemIronDoorStack.class)
            .vanillaItem(VanillaItemId.IRON_DOOR)
            .build();
}
