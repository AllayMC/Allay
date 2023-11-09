package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagmaCreamStack extends ItemStack {
  ItemType<ItemMagmaCreamStack> MAGMA_CREAM_TYPE = ItemTypeBuilder
          .builder(ItemMagmaCreamStack.class)
          .vanillaItem(VanillaItemId.MAGMA_CREAM)
          .build();
}
