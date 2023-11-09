package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChainCommandBlockStack extends ItemStack {
  ItemType<ItemChainCommandBlockStack> CHAIN_COMMAND_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemChainCommandBlockStack.class)
          .vanillaItem(VanillaItemId.CHAIN_COMMAND_BLOCK)
          .build();
}
