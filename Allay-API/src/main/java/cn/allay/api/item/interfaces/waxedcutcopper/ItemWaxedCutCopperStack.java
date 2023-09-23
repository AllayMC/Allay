package cn.allay.api.item.interfaces.waxedcutcopper;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedCutCopperStack extends ItemStack {
    ItemType<ItemWaxedCutCopperStack> WAXED_CUT_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWaxedCutCopperStack.class)
            .vanillaItem(VanillaItemId.WAXED_CUT_COPPER)
            .build();
}
