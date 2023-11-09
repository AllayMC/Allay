package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPumpkinPieStack extends ItemStack {
  ItemType<ItemPumpkinPieStack> PUMPKIN_PIE_TYPE = ItemTypeBuilder
          .builder(ItemPumpkinPieStack.class)
          .vanillaItem(VanillaItemId.PUMPKIN_PIE)
          .build();
}
