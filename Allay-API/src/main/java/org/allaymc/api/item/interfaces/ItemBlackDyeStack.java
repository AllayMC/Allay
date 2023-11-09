package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackDyeStack extends ItemStack {
  ItemType<ItemBlackDyeStack> BLACK_DYE_TYPE = ItemTypeBuilder
          .builder(ItemBlackDyeStack.class)
          .vanillaItem(VanillaItemId.BLACK_DYE)
          .build();
}
