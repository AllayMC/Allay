package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherrySlabStack extends ItemStack {
  ItemType<ItemCherrySlabStack> CHERRY_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemCherrySlabStack.class)
          .vanillaItem(VanillaItemId.CHERRY_SLAB)
          .build();
}
