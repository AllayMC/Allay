package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAppleStack extends ItemStack {
  ItemType<ItemAppleStack> APPLE_TYPE = ItemTypeBuilder
          .builder(ItemAppleStack.class)
          .vanillaItem(VanillaItemId.APPLE)
          .build();
}
