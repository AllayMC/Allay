package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRailStack extends ItemStack {
  ItemType<ItemRailStack> RAIL_TYPE = ItemTypeBuilder
          .builder(ItemRailStack.class)
          .vanillaItem(VanillaItemId.RAIL)
          .build();
}
