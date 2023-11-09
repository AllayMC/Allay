package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSugarCaneStack extends ItemStack {
  ItemType<ItemSugarCaneStack> SUGAR_CANE_TYPE = ItemTypeBuilder
          .builder(ItemSugarCaneStack.class)
          .vanillaItem(VanillaItemId.SUGAR_CANE)
          .build();
}
