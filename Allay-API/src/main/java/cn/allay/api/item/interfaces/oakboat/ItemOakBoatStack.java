package cn.allay.api.item.interfaces.oakboat;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOakBoatStack extends ItemStack {
    ItemType<ItemOakBoatStack> OAK_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemOakBoatStack.class)
            .vanillaItem(VanillaItemId.OAK_BOAT)
            .build();
}
