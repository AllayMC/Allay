package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCompoundStack extends ItemStack {
    ItemType<ItemCompoundStack> COMPOUND_TYPE = ItemTypeBuilder
            .builder(ItemCompoundStack.class)
            .vanillaItem(VanillaItemId.COMPOUND)
            .build();
}
