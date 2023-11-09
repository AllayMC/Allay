package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPlentyPotterySherdStack extends ItemStack {
  ItemType<ItemPlentyPotterySherdStack> PLENTY_POTTERY_SHERD_TYPE = ItemTypeBuilder
          .builder(ItemPlentyPotterySherdStack.class)
          .vanillaItem(VanillaItemId.PLENTY_POTTERY_SHERD)
          .build();
}
