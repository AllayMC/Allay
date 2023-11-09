package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemWheatStack extends ItemStack {
  ItemType<ItemItemWheatStack> ITEM_WHEAT_TYPE = ItemTypeBuilder
          .builder(ItemItemWheatStack.class)
          .vanillaItem(VanillaItemId.ITEM_WHEAT)
          .build();
}
