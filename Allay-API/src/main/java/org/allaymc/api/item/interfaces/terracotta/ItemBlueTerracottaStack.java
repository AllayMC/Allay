package org.allaymc.api.item.interfaces.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueTerracottaStack extends ItemStack {
  ItemType<ItemBlueTerracottaStack> BLUE_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemBlueTerracottaStack.class)
          .vanillaItem(VanillaItemId.BLUE_TERRACOTTA)
          .build();
}
