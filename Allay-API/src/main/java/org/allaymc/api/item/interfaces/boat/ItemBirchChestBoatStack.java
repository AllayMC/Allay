package org.allaymc.api.item.interfaces.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchChestBoatStack extends ItemStack {
  ItemType<ItemBirchChestBoatStack> BIRCH_CHEST_BOAT_TYPE = ItemTypeBuilder
          .builder(ItemBirchChestBoatStack.class)
          .vanillaItem(VanillaItemId.BIRCH_CHEST_BOAT)
          .build();
}
