package org.allaymc.api.item.interfaces.fence;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOakFenceStack extends ItemStack {
  ItemType<ItemOakFenceStack> OAK_FENCE_TYPE = ItemTypeBuilder
          .builder(ItemOakFenceStack.class)
          .vanillaItem(VanillaItemId.OAK_FENCE)
          .build();
}
