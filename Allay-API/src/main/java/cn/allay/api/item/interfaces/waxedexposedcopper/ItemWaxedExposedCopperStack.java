package cn.allay.api.item.interfaces.waxedexposedcopper;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedExposedCopperStack extends ItemStack {
    ItemType<ItemWaxedExposedCopperStack> WAXED_EXPOSED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWaxedExposedCopperStack.class)
            .vanillaItem(VanillaItemId.WAXED_EXPOSED_COPPER)
            .build();
}
