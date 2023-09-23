package cn.allay.api.item.interfaces.rawcopper;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRawCopperStack extends ItemStack {
    ItemType<ItemRawCopperStack> RAW_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemRawCopperStack.class)
            .vanillaItem(VanillaItemId.RAW_COPPER)
            .build();
}
