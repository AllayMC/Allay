package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement63Stack extends ItemStack {
  ItemType<ItemElement63Stack> ELEMENT_63_TYPE = ItemTypeBuilder
          .builder(ItemElement63Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_63)
          .build();
}
