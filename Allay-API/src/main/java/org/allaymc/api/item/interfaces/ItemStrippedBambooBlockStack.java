package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedBambooBlockStack extends ItemStack {
  ItemType<ItemStrippedBambooBlockStack> STRIPPED_BAMBOO_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemStrippedBambooBlockStack.class)
          .vanillaItem(VanillaItemId.STRIPPED_BAMBOO_BLOCK)
          .build();
}
