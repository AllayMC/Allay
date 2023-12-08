package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedAndesiteStack extends ItemStack {
  ItemType<ItemPolishedAndesiteStack> POLISHED_ANDESITE_TYPE = ItemTypeBuilder
          .builder(ItemPolishedAndesiteStack.class)
          .vanillaItem(VanillaItemId.POLISHED_ANDESITE)
          .build();
}
