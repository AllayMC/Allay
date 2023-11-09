package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement43Stack extends ItemStack {
  ItemType<ItemElement43Stack> ELEMENT_43_TYPE = ItemTypeBuilder
          .builder(ItemElement43Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_43)
          .build();
}
