package org.allaymc.api.item.interfaces.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteGlazedTerracottaStack extends ItemStack {
  ItemType<ItemWhiteGlazedTerracottaStack> WHITE_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemWhiteGlazedTerracottaStack.class)
          .vanillaItem(VanillaItemId.WHITE_GLAZED_TERRACOTTA)
          .build();
}
