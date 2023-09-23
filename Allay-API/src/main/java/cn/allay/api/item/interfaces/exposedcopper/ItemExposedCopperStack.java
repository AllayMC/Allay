package cn.allay.api.item.interfaces.exposedcopper;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedCopperStack extends ItemStack {
    ItemType<ItemExposedCopperStack> EXPOSED_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemExposedCopperStack.class)
            .vanillaItem(VanillaItemId.EXPOSED_COPPER)
            .build();
}
