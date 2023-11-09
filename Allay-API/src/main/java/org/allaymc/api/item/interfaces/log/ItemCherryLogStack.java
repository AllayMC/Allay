package org.allaymc.api.item.interfaces.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryLogStack extends ItemStack {
  ItemType<ItemCherryLogStack> CHERRY_LOG_TYPE = ItemTypeBuilder
          .builder(ItemCherryLogStack.class)
          .vanillaItem(VanillaItemId.CHERRY_LOG)
          .build();
}
