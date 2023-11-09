package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemConcretePowderStack extends ItemStack {
  ItemType<ItemConcretePowderStack> CONCRETE_POWDER_TYPE = ItemTypeBuilder
          .builder(ItemConcretePowderStack.class)
          .vanillaItem(VanillaItemId.CONCRETE_POWDER)
          .build();
}
