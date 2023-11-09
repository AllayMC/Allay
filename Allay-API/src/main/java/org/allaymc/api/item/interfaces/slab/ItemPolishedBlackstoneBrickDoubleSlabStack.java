package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneBrickDoubleSlabStack extends ItemStack {
  ItemType<ItemPolishedBlackstoneBrickDoubleSlabStack> POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemPolishedBlackstoneBrickDoubleSlabStack.class)
          .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB)
          .build();
}
