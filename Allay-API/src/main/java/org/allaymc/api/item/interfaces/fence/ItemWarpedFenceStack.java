package org.allaymc.api.item.interfaces.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedFenceStack extends ItemStack {
  ItemType<ItemWarpedFenceStack> WARPED_FENCE_TYPE = ItemTypeBuilder
          .builder(ItemWarpedFenceStack.class)
          .vanillaItem(VanillaItemId.WARPED_FENCE)
          .build();
}
