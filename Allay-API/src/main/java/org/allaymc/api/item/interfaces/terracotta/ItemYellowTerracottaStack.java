package org.allaymc.api.item.interfaces.terracotta;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowTerracottaStack extends ItemStack {
  ItemType<ItemYellowTerracottaStack> YELLOW_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemYellowTerracottaStack.class)
          .vanillaItem(VanillaItemId.YELLOW_TERRACOTTA)
          .build();
}
