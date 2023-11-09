package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedNyliumStack extends ItemStack {
  ItemType<ItemWarpedNyliumStack> WARPED_NYLIUM_TYPE = ItemTypeBuilder
          .builder(ItemWarpedNyliumStack.class)
          .vanillaItem(VanillaItemId.WARPED_NYLIUM)
          .build();
}
