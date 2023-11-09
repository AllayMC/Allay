package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmallDripleafBlockStack extends ItemStack {
  ItemType<ItemSmallDripleafBlockStack> SMALL_DRIPLEAF_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemSmallDripleafBlockStack.class)
          .vanillaItem(VanillaItemId.SMALL_DRIPLEAF_BLOCK)
          .build();
}
