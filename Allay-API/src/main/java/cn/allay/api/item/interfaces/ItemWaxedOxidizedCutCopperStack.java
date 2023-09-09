package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedOxidizedCutCopperStack extends ItemStack {
    ItemType<ItemWaxedOxidizedCutCopperStack> WAXED_OXIDIZED_CUT_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWaxedOxidizedCutCopperStack.class)
            .vanillaItem(VanillaItemId.WAXED_OXIDIZED_CUT_COPPER)
            .build();
}
