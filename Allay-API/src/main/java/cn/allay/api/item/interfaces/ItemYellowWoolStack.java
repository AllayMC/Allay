package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowWoolStack extends ItemStack {
    ItemType<ItemYellowWoolStack> YELLOW_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemYellowWoolStack.class)
            .vanillaItem(VanillaItemId.YELLOW_WOOL)
            .build();
}
