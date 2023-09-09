package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedSalmonStack extends ItemStack {
    ItemType<ItemCookedSalmonStack> COOKED_SALMON_TYPE = ItemTypeBuilder
            .builder(ItemCookedSalmonStack.class)
            .vanillaItem(VanillaItemId.COOKED_SALMON)
            .build();
}
