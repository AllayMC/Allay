package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemWarpedDoorStack extends ItemStack {
  ItemType<ItemItemWarpedDoorStack> ITEM_WARPED_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemItemWarpedDoorStack.class)
          .vanillaItem(VanillaItemId.ITEM_WARPED_DOOR)
          .build();
}
