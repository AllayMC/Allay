package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPointedDripstoneStack extends ItemStack {
  ItemType<ItemPointedDripstoneStack> POINTED_DRIPSTONE_TYPE = ItemTypeBuilder
          .builder(ItemPointedDripstoneStack.class)
          .vanillaItem(VanillaItemId.POINTED_DRIPSTONE)
          .build();
}
