package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateIronOreStack extends ItemStack {
    ItemType<ItemDeepslateIronOreStack> DEEPSLATE_IRON_ORE_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateIronOreStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_IRON_ORE)
            .build();
}
