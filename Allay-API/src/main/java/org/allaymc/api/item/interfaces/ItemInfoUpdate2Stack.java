package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemInfoUpdate2Stack extends ItemStack {
  ItemType<ItemInfoUpdate2Stack> INFO_UPDATE2_TYPE = ItemTypeBuilder
          .builder(ItemInfoUpdate2Stack.class)
          .vanillaItem(VanillaItemId.INFO_UPDATE2)
          .build();
}
