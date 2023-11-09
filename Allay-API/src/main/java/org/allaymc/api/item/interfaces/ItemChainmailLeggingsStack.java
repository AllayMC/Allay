package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChainmailLeggingsStack extends ItemStack {
  ItemType<ItemChainmailLeggingsStack> CHAINMAIL_LEGGINGS_TYPE = ItemTypeBuilder
          .builder(ItemChainmailLeggingsStack.class)
          .vanillaItem(VanillaItemId.CHAINMAIL_LEGGINGS)
          .build();
}
