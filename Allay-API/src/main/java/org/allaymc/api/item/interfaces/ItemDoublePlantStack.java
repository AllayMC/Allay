package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoublePlantStack extends ItemStack {
  ItemType<ItemDoublePlantStack> DOUBLE_PLANT_TYPE = ItemTypeBuilder
          .builder(ItemDoublePlantStack.class)
          .vanillaItem(VanillaItemId.DOUBLE_PLANT)
          .build();
}
