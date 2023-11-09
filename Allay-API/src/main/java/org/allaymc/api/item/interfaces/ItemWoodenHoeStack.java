package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenHoeStack extends ItemStack {
  ItemType<ItemWoodenHoeStack> WOODEN_HOE_TYPE = ItemTypeBuilder
          .builder(ItemWoodenHoeStack.class)
          .vanillaItem(VanillaItemId.WOODEN_HOE)
          .build();
}
