package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMelonStemStack extends ItemStack {
  ItemType<ItemMelonStemStack> MELON_STEM_TYPE = ItemTypeBuilder
          .builder(ItemMelonStemStack.class)
          .vanillaItem(VanillaItemId.MELON_STEM)
          .build();
}
