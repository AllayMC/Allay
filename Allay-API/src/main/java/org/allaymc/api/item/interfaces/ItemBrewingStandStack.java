package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrewingStandStack extends ItemStack {
  ItemType<ItemBrewingStandStack> BREWING_STAND_TYPE = ItemTypeBuilder
          .builder(ItemBrewingStandStack.class)
          .vanillaItem(VanillaItemId.BREWING_STAND)
          .build();
}
