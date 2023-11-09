package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedCutCopperSlabStack extends ItemStack {
  ItemType<ItemExposedCutCopperSlabStack> EXPOSED_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemExposedCutCopperSlabStack.class)
          .vanillaItem(VanillaItemId.EXPOSED_CUT_COPPER_SLAB)
          .build();
}
