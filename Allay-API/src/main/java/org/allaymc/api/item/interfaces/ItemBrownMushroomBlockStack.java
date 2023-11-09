package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownMushroomBlockStack extends ItemStack {
  ItemType<ItemBrownMushroomBlockStack> BROWN_MUSHROOM_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemBrownMushroomBlockStack.class)
          .vanillaItem(VanillaItemId.BROWN_MUSHROOM_BLOCK)
          .build();
}
