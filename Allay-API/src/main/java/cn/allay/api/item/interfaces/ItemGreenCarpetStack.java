package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenCarpetStack extends ItemStack {
    ItemType<ItemGreenCarpetStack> GREEN_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemGreenCarpetStack.class)
            .vanillaItem(VanillaItemId.GREEN_CARPET)
            .build();
}
