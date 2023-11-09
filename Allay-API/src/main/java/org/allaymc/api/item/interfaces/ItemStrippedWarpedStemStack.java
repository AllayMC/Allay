package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedWarpedStemStack extends ItemStack {
  ItemType<ItemStrippedWarpedStemStack> STRIPPED_WARPED_STEM_TYPE = ItemTypeBuilder
          .builder(ItemStrippedWarpedStemStack.class)
          .vanillaItem(VanillaItemId.STRIPPED_WARPED_STEM)
          .build();
}
