package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeWoolStack extends ItemStack {
    ItemType<ItemOrangeWoolStack> ORANGE_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemOrangeWoolStack.class)
            .vanillaItem(VanillaItemId.ORANGE_WOOL)
            .build();
}
