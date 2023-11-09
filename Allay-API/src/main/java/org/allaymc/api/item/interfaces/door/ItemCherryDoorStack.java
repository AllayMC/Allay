package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryDoorStack extends ItemStack {
  ItemType<ItemCherryDoorStack> CHERRY_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemCherryDoorStack.class)
          .vanillaItem(VanillaItemId.CHERRY_DOOR)
          .build();
}
