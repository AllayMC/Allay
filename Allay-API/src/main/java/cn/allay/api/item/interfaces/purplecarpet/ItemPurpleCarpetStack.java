package cn.allay.api.item.interfaces.purplecarpet;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleCarpetStack extends ItemStack {
    ItemType<ItemPurpleCarpetStack> PURPLE_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemPurpleCarpetStack.class)
            .vanillaItem(VanillaItemId.PURPLE_CARPET)
            .build();
}
