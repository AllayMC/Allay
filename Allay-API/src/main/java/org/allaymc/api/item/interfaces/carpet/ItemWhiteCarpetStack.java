package org.allaymc.api.item.interfaces.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteCarpetStack extends ItemStack {
  ItemType<ItemWhiteCarpetStack> WHITE_CARPET_TYPE = ItemTypeBuilder
          .builder(ItemWhiteCarpetStack.class)
          .vanillaItem(VanillaItemId.WHITE_CARPET)
          .build();
}
