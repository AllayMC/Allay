package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemArmsUpPotterySherdStack extends ItemStack {
  ItemType<ItemArmsUpPotterySherdStack> ARMS_UP_POTTERY_SHERD_TYPE = ItemTypeBuilder
          .builder(ItemArmsUpPotterySherdStack.class)
          .vanillaItem(VanillaItemId.ARMS_UP_POTTERY_SHERD)
          .build();
}
