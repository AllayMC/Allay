package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOakChestBoatStack extends ItemStack {
    ItemType<ItemOakChestBoatStack> OAK_CHEST_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemOakChestBoatStack.class)
            .vanillaItem(VanillaItemId.OAK_CHEST_BOAT)
            .build();
}
