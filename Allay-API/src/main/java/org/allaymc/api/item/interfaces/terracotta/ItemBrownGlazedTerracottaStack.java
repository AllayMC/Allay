package org.allaymc.api.item.interfaces.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownGlazedTerracottaStack extends ItemStack {
  ItemType<ItemBrownGlazedTerracottaStack> BROWN_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemBrownGlazedTerracottaStack.class)
          .vanillaItem(VanillaItemId.BROWN_GLAZED_TERRACOTTA)
          .build();
}
