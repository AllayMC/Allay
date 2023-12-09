package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGraniteStack extends ItemStack {
  ItemType<ItemGraniteStack> GRANITE_TYPE = ItemTypeBuilder
          .builder(ItemGraniteStack.class)
          .vanillaItem(VanillaItemId.GRANITE)
          .build();
}
