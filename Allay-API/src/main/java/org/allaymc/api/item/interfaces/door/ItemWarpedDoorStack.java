package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedDoorStack extends ItemStack {
  ItemType<ItemWarpedDoorStack> WARPED_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemWarpedDoorStack.class)
          .vanillaItem(VanillaItemId.WARPED_DOOR)
          .build();
}
