package org.allaymc.api.item.interfaces.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaFenceStack extends ItemStack {
  ItemType<ItemAcaciaFenceStack> ACACIA_FENCE_TYPE = ItemTypeBuilder
          .builder(ItemAcaciaFenceStack.class)
          .vanillaItem(VanillaItemId.ACACIA_FENCE)
          .build();
}
