package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrassPathStack extends ItemStack {
  ItemType<ItemGrassPathStack> GRASS_PATH_TYPE = ItemTypeBuilder
          .builder(ItemGrassPathStack.class)
          .vanillaItem(VanillaItemId.GRASS_PATH)
          .build();
}
