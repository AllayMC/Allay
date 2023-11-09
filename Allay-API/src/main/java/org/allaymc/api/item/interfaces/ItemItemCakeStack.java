package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemCakeStack extends ItemStack {
  ItemType<ItemItemCakeStack> ITEM_CAKE_TYPE = ItemTypeBuilder
          .builder(ItemItemCakeStack.class)
          .vanillaItem(VanillaItemId.ITEM_CAKE)
          .build();
}
