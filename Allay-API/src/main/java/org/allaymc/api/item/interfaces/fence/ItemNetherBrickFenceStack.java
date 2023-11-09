package org.allaymc.api.item.interfaces.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherBrickFenceStack extends ItemStack {
  ItemType<ItemNetherBrickFenceStack> NETHER_BRICK_FENCE_TYPE = ItemTypeBuilder
          .builder(ItemNetherBrickFenceStack.class)
          .vanillaItem(VanillaItemId.NETHER_BRICK_FENCE)
          .build();
}
