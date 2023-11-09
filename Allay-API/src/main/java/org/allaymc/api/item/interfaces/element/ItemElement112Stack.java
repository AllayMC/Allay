package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement112Stack extends ItemStack {
  ItemType<ItemElement112Stack> ELEMENT_112_TYPE = ItemTypeBuilder
          .builder(ItemElement112Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_112)
          .build();
}
