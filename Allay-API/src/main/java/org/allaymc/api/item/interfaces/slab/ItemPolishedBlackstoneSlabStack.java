package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneSlabStack extends ItemStack {
  ItemType<ItemPolishedBlackstoneSlabStack> POLISHED_BLACKSTONE_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemPolishedBlackstoneSlabStack.class)
          .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_SLAB)
          .build();
}
