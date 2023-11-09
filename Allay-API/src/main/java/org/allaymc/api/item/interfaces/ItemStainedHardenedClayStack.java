package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStainedHardenedClayStack extends ItemStack {
  ItemType<ItemStainedHardenedClayStack> STAINED_HARDENED_CLAY_TYPE = ItemTypeBuilder
          .builder(ItemStainedHardenedClayStack.class)
          .vanillaItem(VanillaItemId.STAINED_HARDENED_CLAY)
          .build();
}
