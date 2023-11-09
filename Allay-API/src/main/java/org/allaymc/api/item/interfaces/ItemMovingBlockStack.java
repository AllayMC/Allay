package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMovingBlockStack extends ItemStack {
  ItemType<ItemMovingBlockStack> MOVING_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemMovingBlockStack.class)
          .vanillaItem(VanillaItemId.MOVING_BLOCK)
          .build();
}
