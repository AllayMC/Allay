package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateLapisOreStack extends ItemStack {
    ItemType<ItemDeepslateLapisOreStack> DEEPSLATE_LAPIS_ORE_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateLapisOreStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_LAPIS_ORE)
            .build();
}
