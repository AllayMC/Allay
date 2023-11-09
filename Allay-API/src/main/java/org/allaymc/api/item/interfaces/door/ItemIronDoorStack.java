package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronDoorStack extends ItemStack {
  ItemType<ItemIronDoorStack> IRON_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemIronDoorStack.class)
          .vanillaItem(VanillaItemId.IRON_DOOR)
          .build();
}
