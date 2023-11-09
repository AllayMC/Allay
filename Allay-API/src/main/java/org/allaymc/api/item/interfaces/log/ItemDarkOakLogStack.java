package org.allaymc.api.item.interfaces.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakLogStack extends ItemStack {
  ItemType<ItemDarkOakLogStack> DARK_OAK_LOG_TYPE = ItemTypeBuilder
          .builder(ItemDarkOakLogStack.class)
          .vanillaItem(VanillaItemId.DARK_OAK_LOG)
          .build();
}
