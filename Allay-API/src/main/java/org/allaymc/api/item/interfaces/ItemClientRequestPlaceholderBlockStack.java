package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemClientRequestPlaceholderBlockStack extends ItemStack {
  ItemType<ItemClientRequestPlaceholderBlockStack> CLIENT_REQUEST_PLACEHOLDER_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemClientRequestPlaceholderBlockStack.class)
          .vanillaItem(VanillaItemId.CLIENT_REQUEST_PLACEHOLDER_BLOCK)
          .build();
}
