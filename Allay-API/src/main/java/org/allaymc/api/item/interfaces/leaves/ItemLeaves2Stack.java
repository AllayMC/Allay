package org.allaymc.api.item.interfaces.leaves;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeaves2Stack extends ItemStack {
  ItemType<ItemLeaves2Stack> LEAVES2_TYPE = ItemTypeBuilder
          .builder(ItemLeaves2Stack.class)
          .vanillaItem(VanillaItemId.LEAVES2)
          .build();
}
