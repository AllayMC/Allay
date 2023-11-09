package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFlintAndSteelStack extends ItemStack {
  ItemType<ItemFlintAndSteelStack> FLINT_AND_STEEL_TYPE = ItemTypeBuilder
          .builder(ItemFlintAndSteelStack.class)
          .vanillaItem(VanillaItemId.FLINT_AND_STEEL)
          .build();
}
