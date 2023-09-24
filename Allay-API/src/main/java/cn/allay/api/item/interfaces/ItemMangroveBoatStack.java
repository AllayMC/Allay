package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveBoatStack extends ItemStack {
    ItemType<ItemMangroveBoatStack> MANGROVE_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemMangroveBoatStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_BOAT)
            .build();
}
