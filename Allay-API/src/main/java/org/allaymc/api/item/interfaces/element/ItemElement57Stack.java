package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement57Stack extends ItemStack {
  ItemType<ItemElement57Stack> ELEMENT_57_TYPE = ItemTypeBuilder
          .builder(ItemElement57Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_57)
          .build();
}
