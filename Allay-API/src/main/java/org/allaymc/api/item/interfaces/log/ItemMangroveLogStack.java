package org.allaymc.api.item.interfaces.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveLogStack extends ItemStack {
  ItemType<ItemMangroveLogStack> MANGROVE_LOG_TYPE = ItemTypeBuilder
          .builder(ItemMangroveLogStack.class)
          .vanillaItem(VanillaItemId.MANGROVE_LOG)
          .build();
}
