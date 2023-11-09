package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonRootsStack extends ItemStack {
  ItemType<ItemCrimsonRootsStack> CRIMSON_ROOTS_TYPE = ItemTypeBuilder
          .builder(ItemCrimsonRootsStack.class)
          .vanillaItem(VanillaItemId.CRIMSON_ROOTS)
          .build();
}
