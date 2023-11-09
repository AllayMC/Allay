package org.allaymc.api.item.interfaces.liquid;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaterStack extends ItemStack {
  ItemType<ItemWaterStack> WATER_TYPE = ItemTypeBuilder
          .builder(ItemWaterStack.class)
          .vanillaItem(VanillaItemId.WATER)
          .build();
}
