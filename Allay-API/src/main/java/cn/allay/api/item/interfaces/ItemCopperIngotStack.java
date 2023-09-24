package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCopperIngotStack extends ItemStack {
    ItemType<ItemCopperIngotStack> COPPER_INGOT_TYPE = ItemTypeBuilder
            .builder(ItemCopperIngotStack.class)
            .vanillaItem(VanillaItemId.COPPER_INGOT)
            .build();
}
