package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSkullPotterySherdStack extends ItemStack {
  ItemType<ItemSkullPotterySherdStack> SKULL_POTTERY_SHERD_TYPE = ItemTypeBuilder
          .builder(ItemSkullPotterySherdStack.class)
          .vanillaItem(VanillaItemId.SKULL_POTTERY_SHERD)
          .build();
}
