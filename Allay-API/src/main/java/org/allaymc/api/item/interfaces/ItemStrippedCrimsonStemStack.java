package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedCrimsonStemStack extends ItemStack {
  ItemType<ItemStrippedCrimsonStemStack> STRIPPED_CRIMSON_STEM_TYPE = ItemTypeBuilder
          .builder(ItemStrippedCrimsonStemStack.class)
          .vanillaItem(VanillaItemId.STRIPPED_CRIMSON_STEM)
          .build();
}
