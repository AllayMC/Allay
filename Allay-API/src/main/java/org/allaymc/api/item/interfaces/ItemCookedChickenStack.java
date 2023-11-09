package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedChickenStack extends ItemStack {
  ItemType<ItemCookedChickenStack> COOKED_CHICKEN_TYPE = ItemTypeBuilder
          .builder(ItemCookedChickenStack.class)
          .vanillaItem(VanillaItemId.COOKED_CHICKEN)
          .build();
}
