package cn.allay.api.item.interfaces.nethergoldore;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherGoldOreStack extends ItemStack {
    ItemType<ItemNetherGoldOreStack> NETHER_GOLD_ORE_TYPE = ItemTypeBuilder
            .builder(ItemNetherGoldOreStack.class)
            .vanillaItem(VanillaItemId.NETHER_GOLD_ORE)
            .build();
}
