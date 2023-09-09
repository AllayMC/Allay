package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenSlabStack extends ItemStack {
    ItemType<ItemWoodenSlabStack> WOODEN_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemWoodenSlabStack.class)
            .vanillaItem(VanillaItemId.WOODEN_SLAB)
            .build();
}
