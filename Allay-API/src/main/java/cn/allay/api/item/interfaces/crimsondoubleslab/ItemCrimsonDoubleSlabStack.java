package cn.allay.api.item.interfaces.crimsondoubleslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonDoubleSlabStack extends ItemStack {
    ItemType<ItemCrimsonDoubleSlabStack> CRIMSON_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_DOUBLE_SLAB)
            .build();
}
