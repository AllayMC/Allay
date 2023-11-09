package org.allaymc.api.item.interfaces.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneButtonStack extends ItemStack {
  ItemType<ItemPolishedBlackstoneButtonStack> POLISHED_BLACKSTONE_BUTTON_TYPE = ItemTypeBuilder
          .builder(ItemPolishedBlackstoneButtonStack.class)
          .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BUTTON)
          .build();
}
