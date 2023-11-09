package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmoothBasaltStack extends ItemStack {
  ItemType<ItemSmoothBasaltStack> SMOOTH_BASALT_TYPE = ItemTypeBuilder
          .builder(ItemSmoothBasaltStack.class)
          .vanillaItem(VanillaItemId.SMOOTH_BASALT)
          .build();
}
