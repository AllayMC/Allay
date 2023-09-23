package cn.allay.api.item.interfaces.bluewool;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueWoolStack extends ItemStack {
    ItemType<ItemBlueWoolStack> BLUE_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemBlueWoolStack.class)
            .vanillaItem(VanillaItemId.BLUE_WOOL)
            .build();
}
