package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement77Stack extends ItemStack {
  ItemType<ItemElement77Stack> ELEMENT_77_TYPE = ItemTypeBuilder
          .builder(ItemElement77Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_77)
          .build();
}
