package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemDarkOakDoorStack extends ItemStack {
    ItemType<ItemItemDarkOakDoorStack> ITEM_DARK_OAK_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemItemDarkOakDoorStack.class)
            .vanillaItem(VanillaItemId.ITEM_DARK_OAK_DOOR)
            .build();
}
