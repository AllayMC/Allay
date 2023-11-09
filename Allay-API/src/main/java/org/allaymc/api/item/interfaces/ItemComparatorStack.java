package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
