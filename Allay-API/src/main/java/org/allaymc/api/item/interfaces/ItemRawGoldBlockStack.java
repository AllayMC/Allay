package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRawGoldBlockStack extends ItemStack {
  ItemType<ItemRawGoldBlockStack> RAW_GOLD_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemRawGoldBlockStack.class)
          .vanillaItem(VanillaItemId.RAW_GOLD_BLOCK)
          .build();
}
