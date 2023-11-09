package org.allaymc.api.item.interfaces.wool;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlueWoolStack extends ItemStack {
  ItemType<ItemBlueWoolStack> BLUE_WOOL_TYPE = ItemTypeBuilder
          .builder(ItemBlueWoolStack.class)
          .vanillaItem(VanillaItemId.BLUE_WOOL)
          .build();
}
