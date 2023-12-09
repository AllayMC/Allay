package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCopperDoorStack extends ItemStack {
  ItemType<ItemCopperDoorStack> COPPER_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemCopperDoorStack.class)
          .vanillaItem(VanillaItemId.COPPER_DOOR)
          .build();
}
