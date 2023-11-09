package org.allaymc.api.item.interfaces.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemQuartzBricksStack extends ItemStack {
  ItemType<ItemQuartzBricksStack> QUARTZ_BRICKS_TYPE = ItemTypeBuilder
          .builder(ItemQuartzBricksStack.class)
          .vanillaItem(VanillaItemId.QUARTZ_BRICKS)
          .build();
}
