package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnortPotterySherdStack extends ItemStack {
  ItemType<ItemSnortPotterySherdStack> SNORT_POTTERY_SHERD_TYPE = ItemTypeBuilder
          .builder(ItemSnortPotterySherdStack.class)
          .vanillaItem(VanillaItemId.SNORT_POTTERY_SHERD)
          .build();
}
