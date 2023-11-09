package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedHyphaeStack extends ItemStack {
  ItemType<ItemWarpedHyphaeStack> WARPED_HYPHAE_TYPE = ItemTypeBuilder
          .builder(ItemWarpedHyphaeStack.class)
          .vanillaItem(VanillaItemId.WARPED_HYPHAE)
          .build();
}
