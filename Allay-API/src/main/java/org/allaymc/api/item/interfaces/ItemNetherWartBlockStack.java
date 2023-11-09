package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherWartBlockStack extends ItemStack {
  ItemType<ItemNetherWartBlockStack> NETHER_WART_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemNetherWartBlockStack.class)
          .vanillaItem(VanillaItemId.NETHER_WART_BLOCK)
          .build();
}
