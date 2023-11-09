package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement49Stack extends ItemStack {
  ItemType<ItemElement49Stack> ELEMENT_49_TYPE = ItemTypeBuilder
          .builder(ItemElement49Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_49)
          .build();
}
