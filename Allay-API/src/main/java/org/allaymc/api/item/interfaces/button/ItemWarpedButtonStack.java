package org.allaymc.api.item.interfaces.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedButtonStack extends ItemStack {
  ItemType<ItemWarpedButtonStack> WARPED_BUTTON_TYPE = ItemTypeBuilder
          .builder(ItemWarpedButtonStack.class)
          .vanillaItem(VanillaItemId.WARPED_BUTTON)
          .build();
}
