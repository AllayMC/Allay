package cn.allay.api.item.interfaces.lightgraycarpet;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayCarpetStack extends ItemStack {
    ItemType<ItemLightGrayCarpetStack> LIGHT_GRAY_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemLightGrayCarpetStack.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_CARPET)
            .build();
}
