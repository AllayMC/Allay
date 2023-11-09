package org.allaymc.api.item.interfaces.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedJungleLogStack extends ItemStack {
  ItemType<ItemStrippedJungleLogStack> STRIPPED_JUNGLE_LOG_TYPE = ItemTypeBuilder
          .builder(ItemStrippedJungleLogStack.class)
          .vanillaItem(VanillaItemId.STRIPPED_JUNGLE_LOG)
          .build();
}
