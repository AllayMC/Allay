package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedMuttonStack extends ItemStack {
  ItemType<ItemCookedMuttonStack> COOKED_MUTTON_TYPE = ItemTypeBuilder
          .builder(ItemCookedMuttonStack.class)
          .vanillaItem(VanillaItemId.COOKED_MUTTON)
          .build();
}
