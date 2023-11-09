package org.allaymc.api.item.interfaces.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveChestBoatStack extends ItemStack {
  ItemType<ItemMangroveChestBoatStack> MANGROVE_CHEST_BOAT_TYPE = ItemTypeBuilder
          .builder(ItemMangroveChestBoatStack.class)
          .vanillaItem(VanillaItemId.MANGROVE_CHEST_BOAT)
          .build();
}
