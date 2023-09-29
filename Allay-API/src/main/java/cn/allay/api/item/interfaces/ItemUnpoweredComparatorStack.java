package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemUnpoweredComparatorStack extends ItemStack {
  ItemType<ItemUnpoweredComparatorStack> UNPOWERED_COMPARATOR_TYPE = ItemTypeBuilder
          .builder(ItemUnpoweredComparatorStack.class)
          .vanillaItem(VanillaItemId.UNPOWERED_COMPARATOR)
          .build();
}
