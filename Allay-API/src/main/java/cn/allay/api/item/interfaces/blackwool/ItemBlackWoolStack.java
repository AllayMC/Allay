package cn.allay.api.item.interfaces.blackwool;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackWoolStack extends ItemStack {
    ItemType<ItemBlackWoolStack> BLACK_WOOL_TYPE = ItemTypeBuilder
            .builder(ItemBlackWoolStack.class)
            .vanillaItem(VanillaItemId.BLACK_WOOL)
            .build();
}
