package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardenedClayStack extends ItemStack {
  ItemType<ItemHardenedClayStack> HARDENED_CLAY_TYPE = ItemTypeBuilder
          .builder(ItemHardenedClayStack.class)
          .vanillaItem(VanillaItemId.HARDENED_CLAY)
          .build();
}
