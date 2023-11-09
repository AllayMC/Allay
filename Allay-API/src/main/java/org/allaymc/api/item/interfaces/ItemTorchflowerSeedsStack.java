package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTorchflowerSeedsStack extends ItemStack {
  ItemType<ItemTorchflowerSeedsStack> TORCHFLOWER_SEEDS_TYPE = ItemTypeBuilder
          .builder(ItemTorchflowerSeedsStack.class)
          .vanillaItem(VanillaItemId.TORCHFLOWER_SEEDS)
          .build();
}
