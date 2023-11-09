package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonSlabStack extends ItemStack {
  ItemType<ItemCrimsonSlabStack> CRIMSON_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemCrimsonSlabStack.class)
          .vanillaItem(VanillaItemId.CRIMSON_SLAB)
          .build();
}
