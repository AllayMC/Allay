package org.allaymc.api.item.interfaces.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleLogStack extends ItemStack {
  ItemType<ItemJungleLogStack> JUNGLE_LOG_TYPE = ItemTypeBuilder
          .builder(ItemJungleLogStack.class)
          .vanillaItem(VanillaItemId.JUNGLE_LOG)
          .build();
}
