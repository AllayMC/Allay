package cn.allay.api.item.interfaces.deepslategoldore;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateGoldOreStack extends ItemStack {
    ItemType<ItemDeepslateGoldOreStack> DEEPSLATE_GOLD_ORE_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateGoldOreStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_GOLD_ORE)
            .build();
}
