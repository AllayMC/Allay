package org.allaymc.api.item.interfaces.log;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLog2Stack extends ItemStack {
  ItemType<ItemLog2Stack> LOG2_TYPE = ItemTypeBuilder
          .builder(ItemLog2Stack.class)
          .vanillaItem(VanillaItemId.LOG2)
          .build();
}
