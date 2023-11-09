package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherGoldOreStack extends ItemStack {
  ItemType<ItemNetherGoldOreStack> NETHER_GOLD_ORE_TYPE = ItemTypeBuilder
          .builder(ItemNetherGoldOreStack.class)
          .vanillaItem(VanillaItemId.NETHER_GOLD_ORE)
          .build();
}
