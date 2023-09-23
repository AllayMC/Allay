package cn.allay.api.item.interfaces.deepslateredstoneore;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateRedstoneOreStack extends ItemStack {
    ItemType<ItemDeepslateRedstoneOreStack> DEEPSLATE_REDSTONE_ORE_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateRedstoneOreStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_REDSTONE_ORE)
            .build();
}
