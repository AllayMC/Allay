package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleDoorStack extends ItemStack {
  ItemType<ItemJungleDoorStack> JUNGLE_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemJungleDoorStack.class)
          .vanillaItem(VanillaItemId.JUNGLE_DOOR)
          .build();
}
