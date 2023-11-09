package org.allaymc.api.item.interfaces.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedSpruceLogStack extends ItemStack {
  ItemType<ItemStrippedSpruceLogStack> STRIPPED_SPRUCE_LOG_TYPE = ItemTypeBuilder
          .builder(ItemStrippedSpruceLogStack.class)
          .vanillaItem(VanillaItemId.STRIPPED_SPRUCE_LOG)
          .build();
}
