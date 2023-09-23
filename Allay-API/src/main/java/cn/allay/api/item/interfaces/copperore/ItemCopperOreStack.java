package cn.allay.api.item.interfaces.copperore;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCopperOreStack extends ItemStack {
    ItemType<ItemCopperOreStack> COPPER_ORE_TYPE = ItemTypeBuilder
            .builder(ItemCopperOreStack.class)
            .vanillaItem(VanillaItemId.COPPER_ORE)
            .build();
}
