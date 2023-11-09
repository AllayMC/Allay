package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanDyeStack extends ItemStack {
  ItemType<ItemCyanDyeStack> CYAN_DYE_TYPE = ItemTypeBuilder
          .builder(ItemCyanDyeStack.class)
          .vanillaItem(VanillaItemId.CYAN_DYE)
          .build();
}
