package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeatheredCutCopperStack extends ItemStack {
    ItemType<ItemWeatheredCutCopperStack> WEATHERED_CUT_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWeatheredCutCopperStack.class)
            .vanillaItem(VanillaItemId.WEATHERED_CUT_COPPER)
            .build();
}
