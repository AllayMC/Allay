package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSeaPickleStack extends ItemStack {
  ItemType<ItemSeaPickleStack> SEA_PICKLE_TYPE = ItemTypeBuilder
          .builder(ItemSeaPickleStack.class)
          .vanillaItem(VanillaItemId.SEA_PICKLE)
          .build();
}
