package org.allaymc.api.item.interfaces.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTuffBricksStack extends ItemStack {
  ItemType<ItemTuffBricksStack> TUFF_BRICKS_TYPE = ItemTypeBuilder
          .builder(ItemTuffBricksStack.class)
          .vanillaItem(VanillaItemId.TUFF_BRICKS)
          .build();
}
