package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedMushroomStack extends ItemStack {
  ItemType<ItemRedMushroomStack> RED_MUSHROOM_TYPE = ItemTypeBuilder
          .builder(ItemRedMushroomStack.class)
          .vanillaItem(VanillaItemId.RED_MUSHROOM)
          .build();
}
