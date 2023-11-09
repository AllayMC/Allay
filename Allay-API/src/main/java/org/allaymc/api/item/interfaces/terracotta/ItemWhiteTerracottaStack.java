package org.allaymc.api.item.interfaces.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteTerracottaStack extends ItemStack {
  ItemType<ItemWhiteTerracottaStack> WHITE_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemWhiteTerracottaStack.class)
          .vanillaItem(VanillaItemId.WHITE_TERRACOTTA)
          .build();
}
