package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCartographyTableStack extends ItemStack {
  ItemType<ItemCartographyTableStack> CARTOGRAPHY_TABLE_TYPE = ItemTypeBuilder
          .builder(ItemCartographyTableStack.class)
          .vanillaItem(VanillaItemId.CARTOGRAPHY_TABLE)
          .build();
}
