package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement6Stack extends ItemStack {
  ItemType<ItemElement6Stack> ELEMENT_6_TYPE = ItemTypeBuilder
          .builder(ItemElement6Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_6)
          .build();
}
