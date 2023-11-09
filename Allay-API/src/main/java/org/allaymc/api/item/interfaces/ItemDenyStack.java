package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDenyStack extends ItemStack {
  ItemType<ItemDenyStack> DENY_TYPE = ItemTypeBuilder
          .builder(ItemDenyStack.class)
          .vanillaItem(VanillaItemId.DENY)
          .build();
}
