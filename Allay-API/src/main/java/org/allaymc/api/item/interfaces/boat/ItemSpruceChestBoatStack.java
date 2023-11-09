package org.allaymc.api.item.interfaces.boat;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceChestBoatStack extends ItemStack {
  ItemType<ItemSpruceChestBoatStack> SPRUCE_CHEST_BOAT_TYPE = ItemTypeBuilder
          .builder(ItemSpruceChestBoatStack.class)
          .vanillaItem(VanillaItemId.SPRUCE_CHEST_BOAT)
          .build();
}
