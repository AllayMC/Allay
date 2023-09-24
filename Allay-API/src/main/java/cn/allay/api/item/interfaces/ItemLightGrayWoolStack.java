package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayWoolStack extends ItemStack {
    ItemType<ItemLightGrayWoolStack> LIGHT_GRAY_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemLightGrayWoolStack.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_WOOL)
            .build();
}
