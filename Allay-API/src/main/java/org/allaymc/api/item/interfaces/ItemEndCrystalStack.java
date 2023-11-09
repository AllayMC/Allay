package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndCrystalStack extends ItemStack {
  ItemType<ItemEndCrystalStack> END_CRYSTAL_TYPE = ItemTypeBuilder
          .builder(ItemEndCrystalStack.class)
          .vanillaItem(VanillaItemId.END_CRYSTAL)
          .build();
}
