package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemUnknownStack extends ItemStack {
  ItemType<ItemUnknownStack> UNKNOWN_TYPE = ItemTypeBuilder
          .builder(ItemUnknownStack.class)
          .vanillaItem(VanillaItemId.UNKNOWN)
          .build();
}
