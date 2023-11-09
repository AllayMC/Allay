package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGhastTearStack extends ItemStack {
  ItemType<ItemGhastTearStack> GHAST_TEAR_TYPE = ItemTypeBuilder
          .builder(ItemGhastTearStack.class)
          .vanillaItem(VanillaItemId.GHAST_TEAR)
          .build();
}
