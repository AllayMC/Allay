package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeatherStack extends ItemStack {
  ItemType<ItemLeatherStack> LEATHER_TYPE = ItemTypeBuilder
          .builder(ItemLeatherStack.class)
          .vanillaItem(VanillaItemId.LEATHER)
          .build();
}
