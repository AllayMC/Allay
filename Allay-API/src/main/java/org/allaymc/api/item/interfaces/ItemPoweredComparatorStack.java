package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
