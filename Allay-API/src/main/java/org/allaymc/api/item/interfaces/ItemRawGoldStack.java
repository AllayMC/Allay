package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRawGoldStack extends ItemStack {
  ItemType<ItemRawGoldStack> RAW_GOLD_TYPE = ItemTypeBuilder
          .builder(ItemRawGoldStack.class)
          .vanillaItem(VanillaItemId.RAW_GOLD)
          .build();
}
