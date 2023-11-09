package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
