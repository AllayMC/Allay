package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemQuartzBlockStack extends ItemStack {
  ItemType<ItemQuartzBlockStack> QUARTZ_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemQuartzBlockStack.class)
          .vanillaItem(VanillaItemId.QUARTZ_BLOCK)
          .build();
}
