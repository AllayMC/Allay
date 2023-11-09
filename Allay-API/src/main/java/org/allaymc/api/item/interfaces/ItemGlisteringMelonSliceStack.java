package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlisteringMelonSliceStack extends ItemStack {
  ItemType<ItemGlisteringMelonSliceStack> GLISTERING_MELON_SLICE_TYPE = ItemTypeBuilder
          .builder(ItemGlisteringMelonSliceStack.class)
          .vanillaItem(VanillaItemId.GLISTERING_MELON_SLICE)
          .build();
}
