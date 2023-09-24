package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHoneyBottleStack extends ItemStack {
    ItemType<ItemHoneyBottleStack> HONEY_BOTTLE_TYPE = ItemTypeBuilder
            .builder(ItemHoneyBottleStack.class)
            .vanillaItem(VanillaItemId.HONEY_BOTTLE)
            .build();
}
