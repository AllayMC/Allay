package cn.allay.api.item.interfaces.graywool;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayWoolStack extends ItemStack {
    ItemType<ItemGrayWoolStack> GRAY_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemGrayWoolStack.class)
            .vanillaItem(VanillaItemId.GRAY_WOOL)
            .build();
}
