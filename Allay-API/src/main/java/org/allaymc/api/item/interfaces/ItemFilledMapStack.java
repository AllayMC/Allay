package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFilledMapStack extends ItemStack {
  ItemType<ItemFilledMapStack> FILLED_MAP_TYPE = ItemTypeBuilder
          .builder(ItemFilledMapStack.class)
          .vanillaItem(VanillaItemId.FILLED_MAP)
          .build();
}
