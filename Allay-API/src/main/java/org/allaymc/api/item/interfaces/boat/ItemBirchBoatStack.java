package org.allaymc.api.item.interfaces.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchBoatStack extends ItemStack {
  ItemType<ItemBirchBoatStack> BIRCH_BOAT_TYPE = ItemTypeBuilder
          .builder(ItemBirchBoatStack.class)
          .vanillaItem(VanillaItemId.BIRCH_BOAT)
          .build();
}
