package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenLeggingsStack extends ItemStack {
    ItemType<ItemGoldenLeggingsStack> GOLDEN_LEGGINGS_TYPE = ItemTypeBuilder
            .builder(ItemGoldenLeggingsStack.class)
            .vanillaItem(VanillaItemId.GOLDEN_LEGGINGS)
            .build();
}
