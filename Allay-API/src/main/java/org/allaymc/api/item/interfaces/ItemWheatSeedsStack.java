package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWheatSeedsStack extends ItemStack {
  ItemType<ItemWheatSeedsStack> WHEAT_SEEDS_TYPE = ItemTypeBuilder
          .builder(ItemWheatSeedsStack.class)
          .vanillaItem(VanillaItemId.WHEAT_SEEDS)
          .build();
}
