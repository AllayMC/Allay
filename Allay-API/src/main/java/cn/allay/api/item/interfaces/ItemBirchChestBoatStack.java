package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchChestBoatStack extends ItemStack {
    ItemType<ItemBirchChestBoatStack> BIRCH_CHEST_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemBirchChestBoatStack.class)
            .vanillaItem(VanillaItemId.BIRCH_CHEST_BOAT)
            .build();
}
