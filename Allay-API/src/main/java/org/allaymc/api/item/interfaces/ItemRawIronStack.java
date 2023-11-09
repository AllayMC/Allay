package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRawIronStack extends ItemStack {
  ItemType<ItemRawIronStack> RAW_IRON_TYPE = ItemTypeBuilder
          .builder(ItemRawIronStack.class)
          .vanillaItem(VanillaItemId.RAW_IRON)
          .build();
}
