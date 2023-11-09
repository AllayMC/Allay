package org.allaymc.api.item.interfaces.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedTrapdoorStack extends ItemStack {
  ItemType<ItemWarpedTrapdoorStack> WARPED_TRAPDOOR_TYPE = ItemTypeBuilder
          .builder(ItemWarpedTrapdoorStack.class)
          .vanillaItem(VanillaItemId.WARPED_TRAPDOOR)
          .build();
}
