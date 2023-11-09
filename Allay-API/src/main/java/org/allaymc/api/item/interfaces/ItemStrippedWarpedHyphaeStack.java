package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedWarpedHyphaeStack extends ItemStack {
  ItemType<ItemStrippedWarpedHyphaeStack> STRIPPED_WARPED_HYPHAE_TYPE = ItemTypeBuilder
          .builder(ItemStrippedWarpedHyphaeStack.class)
          .vanillaItem(VanillaItemId.STRIPPED_WARPED_HYPHAE)
          .build();
}
