package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenRailStack extends ItemStack {
  ItemType<ItemGoldenRailStack> GOLDEN_RAIL_TYPE = ItemTypeBuilder
          .builder(ItemGoldenRailStack.class)
          .vanillaItem(VanillaItemId.GOLDEN_RAIL)
          .build();
}
