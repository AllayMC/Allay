package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeCarpetStack extends ItemStack {
    ItemType<ItemOrangeCarpetStack> ORANGE_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemOrangeCarpetStack.class)
            .vanillaItem(VanillaItemId.ORANGE_CARPET)
            .build();
}
