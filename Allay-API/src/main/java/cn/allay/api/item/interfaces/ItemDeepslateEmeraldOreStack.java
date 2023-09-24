package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateEmeraldOreStack extends ItemStack {
    ItemType<ItemDeepslateEmeraldOreStack> DEEPSLATE_EMERALD_ORE_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateEmeraldOreStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_EMERALD_ORE)
            .build();
}
