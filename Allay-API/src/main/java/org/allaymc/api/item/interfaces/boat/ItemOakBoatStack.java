package org.allaymc.api.item.interfaces.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOakBoatStack extends ItemStack {
  ItemType<ItemOakBoatStack> OAK_BOAT_TYPE = ItemTypeBuilder
          .builder(ItemOakBoatStack.class)
          .vanillaItem(VanillaItemId.OAK_BOAT)
          .build();
}
