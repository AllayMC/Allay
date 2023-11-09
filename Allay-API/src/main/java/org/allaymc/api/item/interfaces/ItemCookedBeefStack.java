package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookedBeefStack extends ItemStack {
  ItemType<ItemCookedBeefStack> COOKED_BEEF_TYPE = ItemTypeBuilder
          .builder(ItemCookedBeefStack.class)
          .vanillaItem(VanillaItemId.COOKED_BEEF)
          .build();
}
