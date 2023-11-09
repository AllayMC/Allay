package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement61Stack extends ItemStack {
  ItemType<ItemElement61Stack> ELEMENT_61_TYPE = ItemTypeBuilder
          .builder(ItemElement61Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_61)
          .build();
}
