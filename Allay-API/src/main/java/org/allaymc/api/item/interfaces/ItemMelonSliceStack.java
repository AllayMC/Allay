package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMelonSliceStack extends ItemStack {
  ItemType<ItemMelonSliceStack> MELON_SLICE_TYPE = ItemTypeBuilder
          .builder(ItemMelonSliceStack.class)
          .vanillaItem(VanillaItemId.MELON_SLICE)
          .build();
}
