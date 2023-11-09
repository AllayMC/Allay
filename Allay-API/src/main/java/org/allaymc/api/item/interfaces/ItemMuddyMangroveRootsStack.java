package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMuddyMangroveRootsStack extends ItemStack {
  ItemType<ItemMuddyMangroveRootsStack> MUDDY_MANGROVE_ROOTS_TYPE = ItemTypeBuilder
          .builder(ItemMuddyMangroveRootsStack.class)
          .vanillaItem(VanillaItemId.MUDDY_MANGROVE_ROOTS)
          .build();
}
