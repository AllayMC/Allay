package cn.allay.api.item.interfaces.mangrovedoubleslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveDoubleSlabStack extends ItemStack {
    ItemType<ItemMangroveDoubleSlabStack> MANGROVE_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemMangroveDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_DOUBLE_SLAB)
            .build();
}
