package org.allaymc.api.item.interfaces.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedMangroveLogStack extends ItemStack {
  ItemType<ItemStrippedMangroveLogStack> STRIPPED_MANGROVE_LOG_TYPE = ItemTypeBuilder
          .builder(ItemStrippedMangroveLogStack.class)
          .vanillaItem(VanillaItemId.STRIPPED_MANGROVE_LOG)
          .build();
}
