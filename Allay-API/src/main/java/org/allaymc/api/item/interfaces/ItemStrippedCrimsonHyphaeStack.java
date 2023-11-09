package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedCrimsonHyphaeStack extends ItemStack {
  ItemType<ItemStrippedCrimsonHyphaeStack> STRIPPED_CRIMSON_HYPHAE_TYPE = ItemTypeBuilder
          .builder(ItemStrippedCrimsonHyphaeStack.class)
          .vanillaItem(VanillaItemId.STRIPPED_CRIMSON_HYPHAE)
          .build();
}
