package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDirtWithRootsStack extends ItemStack {
  ItemType<ItemDirtWithRootsStack> DIRT_WITH_ROOTS_TYPE = ItemTypeBuilder
          .builder(ItemDirtWithRootsStack.class)
          .vanillaItem(VanillaItemId.DIRT_WITH_ROOTS)
          .build();
}
