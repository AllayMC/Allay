package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateCopperOreStack extends ItemStack {
    ItemType<ItemDeepslateCopperOreStack> DEEPSLATE_COPPER_ORE_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateCopperOreStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_COPPER_ORE)
            .build();
}
