package cn.allay.api.item.interfaces.bluecarpet;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueCarpetStack extends ItemStack {
    ItemType<ItemBlueCarpetStack> BLUE_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemBlueCarpetStack.class)
            .vanillaItem(VanillaItemId.BLUE_CARPET)
            .build();
}
