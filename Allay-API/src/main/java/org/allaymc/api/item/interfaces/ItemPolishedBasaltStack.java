package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBasaltStack extends ItemStack {
  ItemType<ItemPolishedBasaltStack> POLISHED_BASALT_TYPE = ItemTypeBuilder
          .builder(ItemPolishedBasaltStack.class)
          .vanillaItem(VanillaItemId.POLISHED_BASALT)
          .build();
}
