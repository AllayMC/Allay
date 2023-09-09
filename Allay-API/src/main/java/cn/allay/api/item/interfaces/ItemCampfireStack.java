package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCampfireStack extends ItemStack {
    ItemType<ItemCampfireStack> CAMPFIRE_TYPE = ItemTypeBuilder
            .builder(ItemCampfireStack.class)
            .vanillaItem(VanillaItemId.CAMPFIRE)
            .build();
}
