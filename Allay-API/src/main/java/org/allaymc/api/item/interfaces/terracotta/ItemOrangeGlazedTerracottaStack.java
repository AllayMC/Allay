package org.allaymc.api.item.interfaces.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeGlazedTerracottaStack extends ItemStack {
  ItemType<ItemOrangeGlazedTerracottaStack> ORANGE_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemOrangeGlazedTerracottaStack.class)
          .vanillaItem(VanillaItemId.ORANGE_GLAZED_TERRACOTTA)
          .build();
}
