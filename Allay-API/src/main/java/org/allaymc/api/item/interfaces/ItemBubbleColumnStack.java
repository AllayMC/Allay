package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBubbleColumnStack extends ItemStack {
  ItemType<ItemBubbleColumnStack> BUBBLE_COLUMN_TYPE = ItemTypeBuilder
          .builder(ItemBubbleColumnStack.class)
          .vanillaItem(VanillaItemId.BUBBLE_COLUMN)
          .build();
}
