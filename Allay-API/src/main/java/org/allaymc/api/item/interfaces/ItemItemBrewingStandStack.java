package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemBrewingStandStack extends ItemStack {
  ItemType<ItemItemBrewingStandStack> ITEM_BREWING_STAND_TYPE = ItemTypeBuilder
          .builder(ItemItemBrewingStandStack.class)
          .vanillaItem(VanillaItemId.ITEM_BREWING_STAND)
          .build();
}
