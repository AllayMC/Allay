package org.allaymc.api.item.interfaces.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChiseledNetherBricksStack extends ItemStack {
  ItemType<ItemChiseledNetherBricksStack> CHISELED_NETHER_BRICKS_TYPE = ItemTypeBuilder
          .builder(ItemChiseledNetherBricksStack.class)
          .vanillaItem(VanillaItemId.CHISELED_NETHER_BRICKS)
          .build();
}
