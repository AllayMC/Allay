package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCaveVinesStack extends ItemStack {
  ItemType<ItemCaveVinesStack> CAVE_VINES_TYPE = ItemTypeBuilder
          .builder(ItemCaveVinesStack.class)
          .vanillaItem(VanillaItemId.CAVE_VINES)
          .build();
}
