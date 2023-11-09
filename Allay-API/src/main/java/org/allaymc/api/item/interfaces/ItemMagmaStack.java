package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagmaStack extends ItemStack {
  ItemType<ItemMagmaStack> MAGMA_TYPE = ItemTypeBuilder
          .builder(ItemMagmaStack.class)
          .vanillaItem(VanillaItemId.MAGMA)
          .build();
}
