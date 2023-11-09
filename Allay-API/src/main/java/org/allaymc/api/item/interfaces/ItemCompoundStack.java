package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCompoundStack extends ItemStack {
  ItemType<ItemCompoundStack> COMPOUND_TYPE = ItemTypeBuilder
          .builder(ItemCompoundStack.class)
          .vanillaItem(VanillaItemId.COMPOUND)
          .build();
}
