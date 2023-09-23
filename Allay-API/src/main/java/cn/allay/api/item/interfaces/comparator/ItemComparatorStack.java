package cn.allay.api.item.interfaces.comparator;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemComparatorStack extends ItemStack {
    ItemType<ItemComparatorStack> COMPARATOR_TYPE = ItemTypeBuilder
            .builder(ItemComparatorStack.class)
            .vanillaItem(VanillaItemId.COMPARATOR)
            .build();
}
