package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement23Stack extends ItemStack {
  ItemType<ItemElement23Stack> ELEMENT_23_TYPE = ItemTypeBuilder
          .builder(ItemElement23Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_23)
          .build();
}
