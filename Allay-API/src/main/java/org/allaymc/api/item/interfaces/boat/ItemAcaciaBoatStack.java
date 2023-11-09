package org.allaymc.api.item.interfaces.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaBoatStack extends ItemStack {
  ItemType<ItemAcaciaBoatStack> ACACIA_BOAT_TYPE = ItemTypeBuilder
          .builder(ItemAcaciaBoatStack.class)
          .vanillaItem(VanillaItemId.ACACIA_BOAT)
          .build();
}
