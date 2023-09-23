package cn.allay.api.item.interfaces.mossycobblestone;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMossyCobblestoneStack extends ItemStack {
    ItemType<ItemMossyCobblestoneStack> MOSSY_COBBLESTONE_TYPE = ItemTypeBuilder
            .builder(ItemMossyCobblestoneStack.class)
            .vanillaItem(VanillaItemId.MOSSY_COBBLESTONE)
            .build();
}
