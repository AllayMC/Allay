package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayCarpetStack extends ItemStack {
    ItemType<ItemGrayCarpetStack> GRAY_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemGrayCarpetStack.class)
            .vanillaItem(VanillaItemId.GRAY_CARPET)
            .build();
}
