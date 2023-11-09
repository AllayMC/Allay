package org.allaymc.api.item.interfaces.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedDarkOakLogStack extends ItemStack {
  ItemType<ItemStrippedDarkOakLogStack> STRIPPED_DARK_OAK_LOG_TYPE = ItemTypeBuilder
          .builder(ItemStrippedDarkOakLogStack.class)
          .vanillaItem(VanillaItemId.STRIPPED_DARK_OAK_LOG)
          .build();
}
