package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSweetBerriesStack extends ItemStack {
  ItemType<ItemSweetBerriesStack> SWEET_BERRIES_TYPE = ItemTypeBuilder
          .builder(ItemSweetBerriesStack.class)
          .vanillaItem(VanillaItemId.SWEET_BERRIES)
          .build();
}
