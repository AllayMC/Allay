package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronOreStack extends ItemStack {
  ItemType<ItemIronOreStack> IRON_ORE_TYPE = ItemTypeBuilder
          .builder(ItemIronOreStack.class)
          .vanillaItem(VanillaItemId.IRON_ORE)
          .build();
}
