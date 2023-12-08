package org.allaymc.api.item.interfaces.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTuffBrickStairsStack extends ItemStack {
  ItemType<ItemTuffBrickStairsStack> TUFF_BRICK_STAIRS_TYPE = ItemTypeBuilder
          .builder(ItemTuffBrickStairsStack.class)
          .vanillaItem(VanillaItemId.TUFF_BRICK_STAIRS)
          .build();
}
