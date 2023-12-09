package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedGraniteStack extends ItemStack {
  ItemType<ItemPolishedGraniteStack> POLISHED_GRANITE_TYPE = ItemTypeBuilder
          .builder(ItemPolishedGraniteStack.class)
          .vanillaItem(VanillaItemId.POLISHED_GRANITE)
          .build();
}
