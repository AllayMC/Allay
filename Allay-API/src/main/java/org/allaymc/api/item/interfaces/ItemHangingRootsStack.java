package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHangingRootsStack extends ItemStack {
  ItemType<ItemHangingRootsStack> HANGING_ROOTS_TYPE = ItemTypeBuilder
          .builder(ItemHangingRootsStack.class)
          .vanillaItem(VanillaItemId.HANGING_ROOTS)
          .build();
}
