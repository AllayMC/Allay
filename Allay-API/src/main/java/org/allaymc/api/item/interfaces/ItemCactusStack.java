package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCactusStack extends ItemStack {
  ItemType<ItemCactusStack> CACTUS_TYPE = ItemTypeBuilder
          .builder(ItemCactusStack.class)
          .vanillaItem(VanillaItemId.CACTUS)
          .build();
}
