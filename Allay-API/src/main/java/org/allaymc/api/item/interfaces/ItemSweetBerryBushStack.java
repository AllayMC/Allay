package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSweetBerryBushStack extends ItemStack {
  ItemType<ItemSweetBerryBushStack> SWEET_BERRY_BUSH_TYPE = ItemTypeBuilder
          .builder(ItemSweetBerryBushStack.class)
          .vanillaItem(VanillaItemId.SWEET_BERRY_BUSH)
          .build();
}
