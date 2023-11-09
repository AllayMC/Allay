package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmoothStoneStack extends ItemStack {
  ItemType<ItemSmoothStoneStack> SMOOTH_STONE_TYPE = ItemTypeBuilder
          .builder(ItemSmoothStoneStack.class)
          .vanillaItem(VanillaItemId.SMOOTH_STONE)
          .build();
}
