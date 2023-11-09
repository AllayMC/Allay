package org.allaymc.api.item.interfaces.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrackedNetherBricksStack extends ItemStack {
  ItemType<ItemCrackedNetherBricksStack> CRACKED_NETHER_BRICKS_TYPE = ItemTypeBuilder
          .builder(ItemCrackedNetherBricksStack.class)
          .vanillaItem(VanillaItemId.CRACKED_NETHER_BRICKS)
          .build();
}
