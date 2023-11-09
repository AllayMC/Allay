package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTropicalFishStack extends ItemStack {
  ItemType<ItemTropicalFishStack> TROPICAL_FISH_TYPE = ItemTypeBuilder
          .builder(ItemTropicalFishStack.class)
          .vanillaItem(VanillaItemId.TROPICAL_FISH)
          .build();
}
