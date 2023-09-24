package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBigDripleafStack extends ItemStack {
    ItemType<ItemBigDripleafStack> BIG_DRIPLEAF_TYPE = ItemTypeBuilder
            .builder(ItemBigDripleafStack.class)
            .vanillaItem(VanillaItemId.BIG_DRIPLEAF)
            .build();
}
