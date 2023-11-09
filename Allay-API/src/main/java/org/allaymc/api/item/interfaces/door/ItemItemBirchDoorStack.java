package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemBirchDoorStack extends ItemStack {
  ItemType<ItemItemBirchDoorStack> ITEM_BIRCH_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemItemBirchDoorStack.class)
          .vanillaItem(VanillaItemId.ITEM_BIRCH_DOOR)
          .build();
}
