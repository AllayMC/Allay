package cn.allay.api.item.interfaces.ironore;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronOreStack extends ItemStack {
    ItemType<ItemIronOreStack> IRON_ORE_TYPE = ItemTypeBuilder
            .builder(ItemIronOreStack.class)
            .vanillaItem(VanillaItemId.IRON_ORE)
            .build();
}
