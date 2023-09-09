package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCarpetStack extends ItemStack {
    ItemType<ItemCarpetStack> CARPET_TYPE = ItemTypeBuilder
            .builder(ItemCarpetStack.class)
            .vanillaItem(VanillaItemId.CARPET)
            .build();
}
