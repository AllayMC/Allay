package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCobbledDeepslateSlabStack extends ItemStack {
  ItemType<ItemCobbledDeepslateSlabStack> COBBLED_DEEPSLATE_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemCobbledDeepslateSlabStack.class)
          .vanillaItem(VanillaItemId.COBBLED_DEEPSLATE_SLAB)
          .build();
}
