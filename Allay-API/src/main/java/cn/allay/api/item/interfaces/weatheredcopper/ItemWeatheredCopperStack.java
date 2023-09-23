package cn.allay.api.item.interfaces.weatheredcopper;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeatheredCopperStack extends ItemStack {
    ItemType<ItemWeatheredCopperStack> WEATHERED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWeatheredCopperStack.class)
            .vanillaItem(VanillaItemId.WEATHERED_COPPER)
            .build();
}
