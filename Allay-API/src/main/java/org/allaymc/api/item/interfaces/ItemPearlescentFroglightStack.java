package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPearlescentFroglightStack extends ItemStack {
  ItemType<ItemPearlescentFroglightStack> PEARLESCENT_FROGLIGHT_TYPE = ItemTypeBuilder
          .builder(ItemPearlescentFroglightStack.class)
          .vanillaItem(VanillaItemId.PEARLESCENT_FROGLIGHT)
          .build();
}
