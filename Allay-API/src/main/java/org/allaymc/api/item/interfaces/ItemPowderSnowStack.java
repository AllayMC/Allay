package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPowderSnowStack extends ItemStack {
  ItemType<ItemPowderSnowStack> POWDER_SNOW_TYPE = ItemTypeBuilder
          .builder(ItemPowderSnowStack.class)
          .vanillaItem(VanillaItemId.POWDER_SNOW)
          .build();
}
