package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackstoneSlabStack extends ItemStack {
  ItemType<ItemBlackstoneSlabStack> BLACKSTONE_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemBlackstoneSlabStack.class)
          .vanillaItem(VanillaItemId.BLACKSTONE_SLAB)
          .build();
}
