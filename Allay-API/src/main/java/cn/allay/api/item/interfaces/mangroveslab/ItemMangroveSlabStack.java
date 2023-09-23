package cn.allay.api.item.interfaces.mangroveslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveSlabStack extends ItemStack {
    ItemType<ItemMangroveSlabStack> MANGROVE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemMangroveSlabStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_SLAB)
            .build();
}
