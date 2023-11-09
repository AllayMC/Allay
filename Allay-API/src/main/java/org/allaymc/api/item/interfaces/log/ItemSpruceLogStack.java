package org.allaymc.api.item.interfaces.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceLogStack extends ItemStack {
  ItemType<ItemSpruceLogStack> SPRUCE_LOG_TYPE = ItemTypeBuilder
          .builder(ItemSpruceLogStack.class)
          .vanillaItem(VanillaItemId.SPRUCE_LOG)
          .build();
}
