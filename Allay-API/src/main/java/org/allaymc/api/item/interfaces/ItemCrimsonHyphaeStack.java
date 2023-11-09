package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonHyphaeStack extends ItemStack {
  ItemType<ItemCrimsonHyphaeStack> CRIMSON_HYPHAE_TYPE = ItemTypeBuilder
          .builder(ItemCrimsonHyphaeStack.class)
          .vanillaItem(VanillaItemId.CRIMSON_HYPHAE)
          .build();
}
