package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaCarpetStack extends ItemStack {
    ItemType<ItemMagentaCarpetStack> MAGENTA_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemMagentaCarpetStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_CARPET)
            .build();
}
