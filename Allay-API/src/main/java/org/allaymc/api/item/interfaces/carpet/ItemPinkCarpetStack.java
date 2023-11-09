package org.allaymc.api.item.interfaces.carpet;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkCarpetStack extends ItemStack {
  ItemType<ItemPinkCarpetStack> PINK_CARPET_TYPE = ItemTypeBuilder
          .builder(ItemPinkCarpetStack.class)
          .vanillaItem(VanillaItemId.PINK_CARPET)
          .build();
}
