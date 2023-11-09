package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoalOreStack extends ItemStack {
  ItemType<ItemCoalOreStack> COAL_ORE_TYPE = ItemTypeBuilder
          .builder(ItemCoalOreStack.class)
          .vanillaItem(VanillaItemId.COAL_ORE)
          .build();
}
