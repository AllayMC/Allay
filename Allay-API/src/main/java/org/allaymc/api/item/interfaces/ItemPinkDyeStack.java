package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkDyeStack extends ItemStack {
  ItemType<ItemPinkDyeStack> PINK_DYE_TYPE = ItemTypeBuilder
          .builder(ItemPinkDyeStack.class)
          .vanillaItem(VanillaItemId.PINK_DYE)
          .build();
}
