package cn.allay.api.item.interfaces.itemcampfire;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemCampfireStack extends ItemStack {
    ItemType<ItemItemCampfireStack> ITEM_CAMPFIRE_TYPE = ItemTypeBuilder
            .builder(ItemItemCampfireStack.class)
            .vanillaItem(VanillaItemId.ITEM_CAMPFIRE)
            .build();
}
