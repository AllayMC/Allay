package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveDoorStack extends ItemStack {
  ItemType<ItemMangroveDoorStack> MANGROVE_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemMangroveDoorStack.class)
          .vanillaItem(VanillaItemId.MANGROVE_DOOR)
          .build();
}
