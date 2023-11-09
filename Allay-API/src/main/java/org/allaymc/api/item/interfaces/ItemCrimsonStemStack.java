package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonStemStack extends ItemStack {
  ItemType<ItemCrimsonStemStack> CRIMSON_STEM_TYPE = ItemTypeBuilder
          .builder(ItemCrimsonStemStack.class)
          .vanillaItem(VanillaItemId.CRIMSON_STEM)
          .build();
}
