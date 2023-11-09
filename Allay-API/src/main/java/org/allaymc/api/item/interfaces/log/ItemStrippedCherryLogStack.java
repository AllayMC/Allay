package org.allaymc.api.item.interfaces.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedCherryLogStack extends ItemStack {
  ItemType<ItemStrippedCherryLogStack> STRIPPED_CHERRY_LOG_TYPE = ItemTypeBuilder
          .builder(ItemStrippedCherryLogStack.class)
          .vanillaItem(VanillaItemId.STRIPPED_CHERRY_LOG)
          .build();
}
