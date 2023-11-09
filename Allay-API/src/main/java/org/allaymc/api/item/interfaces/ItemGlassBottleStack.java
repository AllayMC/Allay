package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlassBottleStack extends ItemStack {
  ItemType<ItemGlassBottleStack> GLASS_BOTTLE_TYPE = ItemTypeBuilder
          .builder(ItemGlassBottleStack.class)
          .vanillaItem(VanillaItemId.GLASS_BOTTLE)
          .build();
}
