package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTripWireStack extends ItemStack {
  ItemType<ItemTripWireStack> TRIP_WIRE_TYPE = ItemTypeBuilder
          .builder(ItemTripWireStack.class)
          .vanillaItem(VanillaItemId.TRIP_WIRE)
          .build();
}
