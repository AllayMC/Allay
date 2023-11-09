package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemWoodenDoorStack extends ItemStack {
  ItemType<ItemItemWoodenDoorStack> ITEM_WOODEN_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemItemWoodenDoorStack.class)
          .vanillaItem(VanillaItemId.ITEM_WOODEN_DOOR)
          .build();
}
