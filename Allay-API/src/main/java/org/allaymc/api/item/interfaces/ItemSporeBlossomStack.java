package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSporeBlossomStack extends ItemStack {
  ItemType<ItemSporeBlossomStack> SPORE_BLOSSOM_TYPE = ItemTypeBuilder
          .builder(ItemSporeBlossomStack.class)
          .vanillaItem(VanillaItemId.SPORE_BLOSSOM)
          .build();
}
