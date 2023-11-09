package org.allaymc.api.item.interfaces.button;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakButtonStack extends ItemStack {
  ItemType<ItemDarkOakButtonStack> DARK_OAK_BUTTON_TYPE = ItemTypeBuilder
          .builder(ItemDarkOakButtonStack.class)
          .vanillaItem(VanillaItemId.DARK_OAK_BUTTON)
          .build();
}
