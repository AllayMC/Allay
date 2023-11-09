package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemClockStack extends ItemStack {
  ItemType<ItemClockStack> CLOCK_TYPE = ItemTypeBuilder
          .builder(ItemClockStack.class)
          .vanillaItem(VanillaItemId.CLOCK)
          .build();
}
