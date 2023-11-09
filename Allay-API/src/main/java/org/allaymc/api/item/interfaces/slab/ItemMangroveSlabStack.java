package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveSlabStack extends ItemStack {
  ItemType<ItemMangroveSlabStack> MANGROVE_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemMangroveSlabStack.class)
          .vanillaItem(VanillaItemId.MANGROVE_SLAB)
          .build();
}
