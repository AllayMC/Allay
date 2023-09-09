package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownWoolStack extends ItemStack {
    ItemType<ItemBrownWoolStack> BROWN_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemBrownWoolStack.class)
            .vanillaItem(VanillaItemId.BROWN_WOOL)
            .build();
}
