package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement84Stack extends ItemStack {
  ItemType<ItemElement84Stack> ELEMENT_84_TYPE = ItemTypeBuilder
          .builder(ItemElement84Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_84)
          .build();
}
