package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateBrickSlabStack extends ItemStack {
  ItemType<ItemDeepslateBrickSlabStack> DEEPSLATE_BRICK_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemDeepslateBrickSlabStack.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_BRICK_SLAB)
          .build();
}
