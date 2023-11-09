package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedStemStack extends ItemStack {
  ItemType<ItemWarpedStemStack> WARPED_STEM_TYPE = ItemTypeBuilder
          .builder(ItemWarpedStemStack.class)
          .vanillaItem(VanillaItemId.WARPED_STEM)
          .build();
}
