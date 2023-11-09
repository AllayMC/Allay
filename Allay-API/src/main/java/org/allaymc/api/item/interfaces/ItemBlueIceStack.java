package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueIceStack extends ItemStack {
  ItemType<ItemBlueIceStack> BLUE_ICE_TYPE = ItemTypeBuilder
          .builder(ItemBlueIceStack.class)
          .vanillaItem(VanillaItemId.BLUE_ICE)
          .build();
}
