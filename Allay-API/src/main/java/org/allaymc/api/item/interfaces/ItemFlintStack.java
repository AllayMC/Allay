package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFlintStack extends ItemStack {
  ItemType<ItemFlintStack> FLINT_TYPE = ItemTypeBuilder
          .builder(ItemFlintStack.class)
          .vanillaItem(VanillaItemId.FLINT)
          .build();
}
