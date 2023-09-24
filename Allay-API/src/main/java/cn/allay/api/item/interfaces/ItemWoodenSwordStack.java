package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenSwordStack extends ItemStack {
    ItemType<ItemWoodenSwordStack> WOODEN_SWORD_TYPE = ItemTypeBuilder
            .builder(ItemWoodenSwordStack.class)
            .vanillaItem(VanillaItemId.WOODEN_SWORD)
            .build();
}
