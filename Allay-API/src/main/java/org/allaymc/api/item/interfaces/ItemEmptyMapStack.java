package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEmptyMapStack extends ItemStack {
  ItemType<ItemEmptyMapStack> EMPTY_MAP_TYPE = ItemTypeBuilder
          .builder(ItemEmptyMapStack.class)
          .vanillaItem(VanillaItemId.EMPTY_MAP)
          .build();
}
