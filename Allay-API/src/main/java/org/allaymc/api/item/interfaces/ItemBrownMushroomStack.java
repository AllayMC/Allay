package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownMushroomStack extends ItemStack {
  ItemType<ItemBrownMushroomStack> BROWN_MUSHROOM_TYPE = ItemTypeBuilder
          .builder(ItemBrownMushroomStack.class)
          .vanillaItem(VanillaItemId.BROWN_MUSHROOM)
          .build();
}
