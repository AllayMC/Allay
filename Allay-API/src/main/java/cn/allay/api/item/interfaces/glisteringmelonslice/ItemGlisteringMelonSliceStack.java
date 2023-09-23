package cn.allay.api.item.interfaces.glisteringmelonslice;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlisteringMelonSliceStack extends ItemStack {
    ItemType<ItemGlisteringMelonSliceStack> GLISTERING_MELON_SLICE_TYPE = ItemTypeBuilder
            .builder(ItemGlisteringMelonSliceStack.class)
            .vanillaItem(VanillaItemId.GLISTERING_MELON_SLICE)
            .build();
}
