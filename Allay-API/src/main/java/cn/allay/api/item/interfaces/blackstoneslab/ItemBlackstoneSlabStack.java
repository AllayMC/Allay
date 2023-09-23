package cn.allay.api.item.interfaces.blackstoneslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackstoneSlabStack extends ItemStack {
    ItemType<ItemBlackstoneSlabStack> BLACKSTONE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemBlackstoneSlabStack.class)
            .vanillaItem(VanillaItemId.BLACKSTONE_SLAB)
            .build();
}
