package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRawIronBlockStack extends ItemStack {
  ItemType<ItemRawIronBlockStack> RAW_IRON_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemRawIronBlockStack.class)
          .vanillaItem(VanillaItemId.RAW_IRON_BLOCK)
          .build();
}
