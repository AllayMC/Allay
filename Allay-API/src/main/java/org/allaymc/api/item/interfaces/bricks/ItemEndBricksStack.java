package org.allaymc.api.item.interfaces.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndBricksStack extends ItemStack {
  ItemType<ItemEndBricksStack> END_BRICKS_TYPE = ItemTypeBuilder
          .builder(ItemEndBricksStack.class)
          .vanillaItem(VanillaItemId.END_BRICKS)
          .build();
}
