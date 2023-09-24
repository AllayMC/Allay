package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBoatStack extends ItemStack {
    ItemType<ItemBoatStack> BOAT_TYPE = ItemTypeBuilder
            .builder(ItemBoatStack.class)
            .vanillaItem(VanillaItemId.BOAT)
            .build();
}
