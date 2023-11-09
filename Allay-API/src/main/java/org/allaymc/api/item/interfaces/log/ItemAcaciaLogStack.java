package org.allaymc.api.item.interfaces.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaLogStack extends ItemStack {
  ItemType<ItemAcaciaLogStack> ACACIA_LOG_TYPE = ItemTypeBuilder
          .builder(ItemAcaciaLogStack.class)
          .vanillaItem(VanillaItemId.ACACIA_LOG)
          .build();
}
