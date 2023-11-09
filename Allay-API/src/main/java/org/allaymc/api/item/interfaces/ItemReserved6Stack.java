package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemReserved6Stack extends ItemStack {
  ItemType<ItemReserved6Stack> RESERVED6_TYPE = ItemTypeBuilder
          .builder(ItemReserved6Stack.class)
          .vanillaItem(VanillaItemId.RESERVED6)
          .build();
}
