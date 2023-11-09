package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemActivatorRailStack extends ItemStack {
  ItemType<ItemActivatorRailStack> ACTIVATOR_RAIL_TYPE = ItemTypeBuilder
          .builder(ItemActivatorRailStack.class)
          .vanillaItem(VanillaItemId.ACTIVATOR_RAIL)
          .build();
}
