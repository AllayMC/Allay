package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulSoilStack extends ItemStack {
  ItemType<ItemSoulSoilStack> SOUL_SOIL_TYPE = ItemTypeBuilder
          .builder(ItemSoulSoilStack.class)
          .vanillaItem(VanillaItemId.SOUL_SOIL)
          .build();
}
