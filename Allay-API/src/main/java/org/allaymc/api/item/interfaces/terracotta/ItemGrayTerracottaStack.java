package org.allaymc.api.item.interfaces.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayTerracottaStack extends ItemStack {
  ItemType<ItemGrayTerracottaStack> GRAY_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemGrayTerracottaStack.class)
          .vanillaItem(VanillaItemId.GRAY_TERRACOTTA)
          .build();
}
