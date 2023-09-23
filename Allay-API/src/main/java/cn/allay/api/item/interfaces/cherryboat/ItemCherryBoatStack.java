package cn.allay.api.item.interfaces.cherryboat;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryBoatStack extends ItemStack {
    ItemType<ItemCherryBoatStack> CHERRY_BOAT_TYPE = ItemTypeBuilder
            .builder(ItemCherryBoatStack.class)
            .vanillaItem(VanillaItemId.CHERRY_BOAT)
            .build();
}
