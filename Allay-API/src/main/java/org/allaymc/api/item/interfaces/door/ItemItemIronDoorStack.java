package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemIronDoorStack extends ItemStack {
  ItemType<ItemItemIronDoorStack> ITEM_IRON_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemItemIronDoorStack.class)
          .vanillaItem(VanillaItemId.ITEM_IRON_DOOR)
          .build();
}
