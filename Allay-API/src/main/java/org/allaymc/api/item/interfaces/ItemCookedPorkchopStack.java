package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedPorkchopStack extends ItemStack {
  ItemType<ItemCookedPorkchopStack> COOKED_PORKCHOP_TYPE = ItemTypeBuilder
          .builder(ItemCookedPorkchopStack.class)
          .vanillaItem(VanillaItemId.COOKED_PORKCHOP)
          .build();
}
