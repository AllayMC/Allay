package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCalciteStack extends ItemStack {
  ItemType<ItemCalciteStack> CALCITE_TYPE = ItemTypeBuilder
          .builder(ItemCalciteStack.class)
          .vanillaItem(VanillaItemId.CALCITE)
          .build();
}
