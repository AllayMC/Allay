package cn.allay.api.item.interfaces.cobblestone;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCobblestoneStack extends ItemStack {
    ItemType<ItemCobblestoneStack> COBBLESTONE_TYPE = ItemTypeBuilder
            .builder(ItemCobblestoneStack.class)
            .vanillaItem(VanillaItemId.COBBLESTONE)
            .build();
}
