package org.allaymc.api.item.interfaces.door;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemDarkOakDoorStack extends ItemStack {
  ItemType<ItemItemDarkOakDoorStack> ITEM_DARK_OAK_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemItemDarkOakDoorStack.class)
          .vanillaItem(VanillaItemId.ITEM_DARK_OAK_DOOR)
          .build();
}
