package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement8Stack extends ItemStack {
  ItemType<ItemElement8Stack> ELEMENT_8_TYPE = ItemTypeBuilder
          .builder(ItemElement8Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_8)
          .build();
}
