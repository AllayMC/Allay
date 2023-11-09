package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMushroomStewStack extends ItemStack {
  ItemType<ItemMushroomStewStack> MUSHROOM_STEW_TYPE = ItemTypeBuilder
          .builder(ItemMushroomStewStack.class)
          .vanillaItem(VanillaItemId.MUSHROOM_STEW)
          .build();
}
