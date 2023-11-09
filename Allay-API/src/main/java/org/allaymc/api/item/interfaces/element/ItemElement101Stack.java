package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement101Stack extends ItemStack {
  ItemType<ItemElement101Stack> ELEMENT_101_TYPE = ItemTypeBuilder
          .builder(ItemElement101Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_101)
          .build();
}
