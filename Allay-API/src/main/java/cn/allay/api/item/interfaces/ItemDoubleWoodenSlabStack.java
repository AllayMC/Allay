package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoubleWoodenSlabStack extends ItemStack {
    ItemType<ItemDoubleWoodenSlabStack> DOUBLE_WOODEN_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemDoubleWoodenSlabStack.class)
            .vanillaItem(VanillaItemId.DOUBLE_WOODEN_SLAB)
            .build();
}
