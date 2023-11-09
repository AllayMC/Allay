package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedSalmonStack extends ItemStack {
  ItemType<ItemCookedSalmonStack> COOKED_SALMON_TYPE = ItemTypeBuilder
          .builder(ItemCookedSalmonStack.class)
          .vanillaItem(VanillaItemId.COOKED_SALMON)
          .build();
}
