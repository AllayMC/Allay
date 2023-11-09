package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateBrickDoubleSlabStack extends ItemStack {
  ItemType<ItemDeepslateBrickDoubleSlabStack> DEEPSLATE_BRICK_DOUBLE_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemDeepslateBrickDoubleSlabStack.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_BRICK_DOUBLE_SLAB)
          .build();
}
