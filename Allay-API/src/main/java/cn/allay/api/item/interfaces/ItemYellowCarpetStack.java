package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowCarpetStack extends ItemStack {
    ItemType<ItemYellowCarpetStack> YELLOW_CARPET_TYPE = ItemTypeBuilder
            .builder(ItemYellowCarpetStack.class)
            .vanillaItem(VanillaItemId.YELLOW_CARPET)
            .build();
}
