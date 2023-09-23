package cn.allay.api.item.interfaces.melonslice;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMelonSliceStack extends ItemStack {
    ItemType<ItemMelonSliceStack> MELON_SLICE_TYPE = ItemTypeBuilder
            .builder(ItemMelonSliceStack.class)
            .vanillaItem(VanillaItemId.MELON_SLICE)
            .build();
}
