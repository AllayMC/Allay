package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiscFragment5Stack extends ItemStack {
  ItemType<ItemDiscFragment5Stack> DISC_FRAGMENT_5_TYPE = ItemTypeBuilder
          .builder(ItemDiscFragment5Stack.class)
          .vanillaItem(VanillaItemId.DISC_FRAGMENT_5)
          .build();
}
