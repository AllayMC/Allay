package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFlintAndSteelStack extends ItemStack {
    ItemType<ItemFlintAndSteelStack> FLINT_AND_STEEL_TYPE = ItemTypeBuilder
            .builder(ItemFlintAndSteelStack.class)
            .vanillaItem(VanillaItemId.FLINT_AND_STEEL)
            .build();
}
