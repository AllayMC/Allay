package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemJungleDoorStack extends ItemStack {
  ItemType<ItemItemJungleDoorStack> ITEM_JUNGLE_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemItemJungleDoorStack.class)
          .vanillaItem(VanillaItemId.ITEM_JUNGLE_DOOR)
          .build();
}
