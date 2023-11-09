package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement1Stack extends ItemStack {
  ItemType<ItemElement1Stack> ELEMENT_1_TYPE = ItemTypeBuilder
          .builder(ItemElement1Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_1)
          .build();
}
