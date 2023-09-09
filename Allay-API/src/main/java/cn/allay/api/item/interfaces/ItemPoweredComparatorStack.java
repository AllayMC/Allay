package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPoweredComparatorStack extends ItemStack {
    ItemType<ItemPoweredComparatorStack> POWERED_COMPARATOR_TYPE = ItemTypeBuilder
            .builder(ItemPoweredComparatorStack.class)
            .vanillaItem(VanillaItemId.POWERED_COMPARATOR)
            .build();
}
