package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemSpruceDoorStack extends ItemStack {
  ItemType<ItemItemSpruceDoorStack> ITEM_SPRUCE_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemItemSpruceDoorStack.class)
          .vanillaItem(VanillaItemId.ITEM_SPRUCE_DOOR)
          .build();
}
