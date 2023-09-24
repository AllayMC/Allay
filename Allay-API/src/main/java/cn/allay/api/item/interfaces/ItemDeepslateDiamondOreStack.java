package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateDiamondOreStack extends ItemStack {
    ItemType<ItemDeepslateDiamondOreStack> DEEPSLATE_DIAMOND_ORE_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateDiamondOreStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_DIAMOND_ORE)
            .build();
}
