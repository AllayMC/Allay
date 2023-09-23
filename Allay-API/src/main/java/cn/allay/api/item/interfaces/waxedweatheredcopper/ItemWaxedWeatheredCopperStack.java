package cn.allay.api.item.interfaces.waxedweatheredcopper;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedWeatheredCopperStack extends ItemStack {
    ItemType<ItemWaxedWeatheredCopperStack> WAXED_WEATHERED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWaxedWeatheredCopperStack.class)
            .vanillaItem(VanillaItemId.WAXED_WEATHERED_COPPER)
            .build();
}
