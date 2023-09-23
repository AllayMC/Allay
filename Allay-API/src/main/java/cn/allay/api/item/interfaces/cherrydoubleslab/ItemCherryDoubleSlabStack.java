package cn.allay.api.item.interfaces.cherrydoubleslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryDoubleSlabStack extends ItemStack {
    ItemType<ItemCherryDoubleSlabStack> CHERRY_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemCherryDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.CHERRY_DOUBLE_SLAB)
            .build();
}
