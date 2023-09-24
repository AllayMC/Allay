package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakChestBoatStack extends ItemStack {
    ItemType<ItemDarkOakChestBoatStack> DARK_OAK_CHEST_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakChestBoatStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_CHEST_BOAT)
            .build();
}
