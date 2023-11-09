package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBeetrootSeedsStack extends ItemStack {
  ItemType<ItemBeetrootSeedsStack> BEETROOT_SEEDS_TYPE = ItemTypeBuilder
          .builder(ItemBeetrootSeedsStack.class)
          .vanillaItem(VanillaItemId.BEETROOT_SEEDS)
          .build();
}
