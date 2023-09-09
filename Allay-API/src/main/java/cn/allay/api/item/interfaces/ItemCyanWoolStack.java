package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanWoolStack extends ItemStack {
    ItemType<ItemCyanWoolStack> CYAN_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemCyanWoolStack.class)
            .vanillaItem(VanillaItemId.CYAN_WOOL)
            .build();
}
